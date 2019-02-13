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
       /* Registry reg= LocateRegistry.getRegistry("localhost",1099);
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

            zoo.ajoutListeObs("asset/sapin.png", 2);
            zoo.ajoutListeObs("asset/palmier.png", 2);
            zoo.ajoutListeObs("asset/herbe.png", 7);

            System.out.println(zoo.getListObstacle());
        }

        //Ajout animal sur position !obstacle
        for (int i = 0; i < 4; i++) {
            // AnimalImpl ani = new AnimalImpl("Chien",50,"asset/chien.png","asset/mine.png",false);
            zoo.ajouterAnimal("Chien", 50, "asset/chien.png", "asset/mine.png", true);
        }
        System.out.println(zoo.getListAnimaux());


        //Try with Timeline//

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );

        final long timeStart = System.currentTimeMillis();

        KeyFrame kf = new KeyFrame(
                Duration.seconds(1),                // 0,017(60 FPS)
                new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae) {
                        double t = (System.currentTimeMillis() - timeStart) / 10000.0;

                        //double x = 232 + 128 * Math.cos(t);
                        //double y = 232 + 128 * Math.sin(t);

                        try {
                            gc.clearRect(0, 0, 512,512);
                            gc.drawImage(image,0,0,512,512);


                            System.out.println("-----------------------------------");
                            int i=0;
                            zoo.deplacementListAnimaux(t);
                            for (AnimalImpl ani : zoo.getListAnimaux()) {

                            //*System.out.println("debut X = " + ani.getPositionX());
                                System.out.println("debut Y = " + ani.getPositionY());

                                zoo.setDeplacementAnimal(ani);

                                System.out.println("D X = " + ani.getPositionX());
                                System.out.println("D Y = " + ani.getPositionY());

                                zoo.setUpdateAnimal(ani, t);

                                System.out.println("U X = " + ani.getPositionX());
                                System.out.println("U Y = " + ani.getPositionY());

                                Image im = new Image(zoo.getUrlAnimal(ani));
                                gc.drawImage(im, zoo.getPositionXAnimal(ani), zoo.getPositionYAnimal(ani));

                                if(i==1){
                                    System.out.println("1 X = " + ani.getPositionX());
                                    System.out.println("1 Y = " + ani.getPositionY());
                                }i++;


                            }

                            // Display obstacle
                            for (ObstacleImpl obs : zoo.getListObstacle()){
                                gc.drawImage(obs.getImageObstacle(), obs.getPositionX(), obs.getPositionY());
                            }

                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }

                    }
                });

        gameLoop.getKeyFrames().add( kf );
        gameLoop.play();
        primaryStage.show();
    }*/
}}
