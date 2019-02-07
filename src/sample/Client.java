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

public class Client extends Application {
    public static void main(String argv[]) throws Exception {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception, RemoteException{
        Registry a= LocateRegistry.getRegistry("localhost",1099);
        Zoo zoo = (Zoo) a.lookup("zoo");
        zoo.setNomZoo("okk");
        System.out.println(zoo.getNomZoo());

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Zoo");

        Group group = new Group();
        Scene theScene = new Scene( group );
        primaryStage.setScene( theScene );

        final double WIDTH = 5; // image width
        final double HEIGHT = 5; // image height

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
                @Override
                public void handle(long currentNanoTime) {
                    // calculate time since last update.
                    double elapsedTime = (currentNanoTime - startNanoTime) / 10000000000000.0;
                    // startNanoTime = currentNanoTime;

                    try {

                        // Deplacement animal
                        int i=0;
                        for (AnimalImpl ani : zoo.getListAnimaux()){
                            if(i==1){
                                System.out.println("Velo X = " + ani.getVelocityX());
                                System.out.println("Velo Y = " + ani.getVelocityY());

                            }
                            boolean rs=(ani.deplacement(zoo.getListObstacle(), zoo.getListAnimaux()));
                           // System.out.println(ani+ " " + rs);
                            if(i==1){
                                System.out.println("new Velo X = " +  ani.getVelocityX());
                                System.out.println("new Velo Y = " +  ani.getVelocityY());
                            }
                            i++;
                        }

                    // Recalcul position Animal
                        i=0;
                        for (AnimalImpl ani : zoo.getListAnimaux()){
                            if(i==1){
                                System.out.println("U Velo X = " + ani.getVelocityX());
                                System.out.println("U Velo Y = " + ani.getVelocityY());

                            }
                            ani.update(elapsedTime);//pour le temps
                            // System.out.println(ani+ " " + rs);
                            if(i==1){
                                System.out.println("U new Velo X = " +  ani.getVelocityX());
                                System.out.println("U new Velo Y = " +  ani.getVelocityY());
                            }
                            i++;
                        }

                        gc.clearRect(0, 0, 512,512);
                        gc.drawImage(image,0,0,512,512);

                        // Display Animal
                            for (AnimalImpl ani : zoo.getListAnimaux()){

                                ani.render( gc );
                                if(i==1){
                                    System.out.println("1: X = " + ani.getPositionX());
                                    System.out.println("1: Y = " + ani.getPositionY());
                                    System.out.println("1: Velo X = " + ani.getVelocityX());
                                    System.out.println("1: Velo Y = " + ani.getVelocityY());
                                }


                                i++;
                            }

                        // Display obstacle
                            for (ObstacleImpl obs : zoo.getListObstacle()){
                                gc.drawImage(obs.getImageObstacle(), obs.getPositionX(), obs.getPositionY());
                                //gc.drawImage(obs.getImageObstacle(), obs.getPositionX(), obs.getPositionY());
                            }

                    // Display obstacle

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
