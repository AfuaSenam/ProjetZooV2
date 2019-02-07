package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

//import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Client extends Application{
    public static void main(String argv[]) throws Exception {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Registry a= LocateRegistry.getRegistry("localhost",1099);
        Zoo zoo = (Zoo) a.lookup("zoo");
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
            for (int i = 0; i < 7; i++) {
                ObstacleImpl obs = new ObstacleImpl("asset/sapin.png");
                listObst.add(obs);
            }
            zoo.setListObstacle(listObst);
            System.out.println(zoo.getListObstacle());
        }

        //Ajout animal sur position !obstacle
        for (int i = 0; i < 4; i++) {
            AnimalImpl ani = new AnimalImpl("Chien",50,"asset/chien.png","asset/mine.png",false);

            if(ani.verifIntersectObst(zoo.getListObstacle()) && ani.verifIntersectAni(zoo.getListAnimaux())){
                zoo.ajouterAninmal(ani);
                System.out.println(i +"X = " + ani.getPositionX());
                System.out.println(i +"Y = " + ani.getPositionY());

            }


        }
        System.out.println(zoo.getListAnimaux());


        //------------------------------Animation timer-------------------//
        final long startNanoTime = System.nanoTime();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - startNanoTime) / 10000000000000.0;
                // startNanoTime = currentNanoTime;

                // Deplacement animal
                try {
                    for (AnimalImpl ani : zoo.getListAnimaux()){
                        boolean rs=(ani.deplacement(zoo.getListObstacle(), zoo.getListAnimaux()));

                       // System.out.println(ani+ " " + rs);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                // Recalcul position Animal
                try {
                    int i=0;
                    for (AnimalImpl ani : zoo.getListAnimaux()){
                        ani.update(elapsedTime);//pour le temps
                        /*if(i==1){
                            System.out.println("1: X = " + ani.getPositionX());
                            System.out.println("1: Y = " + ani.getPositionY());
                            System.out.println("1: Velo X = " + ani.getVelocityX());
                            System.out.println("1: Velo Y = " + ani.getVelocityY());
                        }
                        if(i==2){
                            System.out.println("2: X = " + ani.getPositionX());
                            System.out.println("2: Y = " + ani.getPositionY());
                            System.out.println("2: Velo X = " + ani.getVelocityX());
                            System.out.println("2: Velo Y = " + ani.getVelocityY());
                        }
                        i++;*/
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                gc.clearRect(0, 0, 512,512);
                gc.drawImage(image,0,0,512,512);

                // Display Animal
                try {
                    for (AnimalImpl ani : zoo.getListAnimaux()){
                        ani.render( gc );
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                // Display obstacle
                try {
                    for (ObstacleImpl obs : zoo.getListObstacle()){
                        gc.drawImage(obs.getImageObstacle(), obs.getPositionY(), obs.getPositionY());
                        //gc.drawImage(obs.getImageObstacle(), obs.getPositionX(), obs.getPositionY());
                    }

                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        }.start();

        primaryStage.show();
    }
}
