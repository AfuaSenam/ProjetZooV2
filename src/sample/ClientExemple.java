package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

//import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class ClientExemple extends Application{
    public static void main(String argv[]) throws Exception {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Registry reg= LocateRegistry.getRegistry("localhost",1099);
        Zoo zoo = (Zoo) reg.lookup("zoo");
        zoo.setNomZoo("okk");
        System.out.println(zoo.getNomZoo());

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Zoo");

        Group group = new Group();
        Scene theScene = new Scene( group );
        primaryStage.setScene( theScene );

        final double WIDTH = 15; // image width
        final double HEIGHT = 15; // image height

        final double CANVAS_WIDTH = 512;
        final double CANVAS_HEIGHT = 512;

        Canvas canvas = new Canvas( CANVAS_WIDTH, CANVAS_HEIGHT ); // Create new canvas
        group.getChildren().add( canvas );

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        //image de fond//
        Image image=new Image("asset/fond.png");

        //Ajout obstacle ssi premier client
        if(zoo.getListObstacle().isEmpty()){
            ArrayList<ObstacleImpl> listObst=new ArrayList<ObstacleImpl>();
            for (int i = 0; i < 15; i++) {
                ObstacleImpl obs = new ObstacleImpl("asset/sapin.png");
                listObst.add(obs);
            }
            zoo.setListObstacle(listObst);
            System.out.println(zoo.getListObstacle());
        }

        //Ajout animal

        for (int i = 0; i < 3; i++) {
            AnimalImpl ani = new AnimalImpl("Chien",50,"asset/chat.png","asset/mine.png",false);

                if(ani.verifIntersectAni(zoo.getListAnimaux()) && ani.verifIntersectObst(zoo.getListObstacle())){

                    zoo.ajouterAninmal(ani);
            }
        }
        System.out.println(zoo.getListAnimaux());


        //Try with Timeline//

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );

        final long timeStart = System.currentTimeMillis();

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),                // 0,017(60 FPS)
                new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {
                        double t = (System.currentTimeMillis() - timeStart) / 1000.0;

                        //double x = 232 + 128 * Math.cos(t);
                        //double y = 232 + 128 * Math.sin(t);
                        try {

                            for (AnimalImpl ani : zoo.getListAnimaux()){
                                ani.update(t);//pour le temps
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        //deplacement

                        /*try {
                            for (AnimalImpl ani : zoo.getListAnimaux()){
                                boolean rs=(ani.deplacement(zoo.getListObstacle(), zoo.getListAnimaux()));
                                System.out.println(ani.getPositionX()+" Y : "+ani.getPositionY()+" Destination : "+ani.getDestination().getDestinationX()+" Y: "+ani.getDestination().getDestinationY());

                                // System.out.println(ani+ " " + rs);
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }*/
                        try {
                            for (AnimalImpl ani : zoo.getListAnimaux()){
                                ani.setPositionX(232 + 128 * Math.cos(t));
                                ani.setPositionY(232 + 128 * Math.sin(t));
                                
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        System.out.println();

                        // Clear the canvas
                        gc.clearRect(0, 0, 512,512);

                        //background image canvas
                        gc.drawImage(image,0,0,512,512);

                        // background image clears canvas
                        try {
                            for (AnimalImpl ani : zoo.getListAnimaux()){
                                gc.drawImage(ani.getEspece().getImageEspece(), ani.getPositionY(), ani.getPositionY());
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }

                        try {
                            for (ObstacleImpl obs : zoo.getListObstacle()){
                                gc.drawImage(obs.getImageObstacle(), obs.getPositionY(), obs.getPositionY());
                                //gc.drawImage(obs.getImageObstacle(), obs.getPositionX(), obs.getPositionY());
                            }

                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });

        gameLoop.getKeyFrames().add( kf );
        gameLoop.play();
        primaryStage.show();
    }
}
