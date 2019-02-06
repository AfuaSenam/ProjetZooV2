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
    public AnimalImpl animal1;
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

        //Ajout animal
        animal1=new AnimalImpl("Chien",50,"asset/chien.png","asset/mine.png",false);

        //Fonction obstacle
        String image = new String("asset/sapin.png");
        Image im = new Image(image);

        ArrayList<ObstacleImpl> listObst=new ArrayList<ObstacleImpl>();
        for (int i = 0; i < 15; i++) {
            ObstacleImpl obs = new ObstacleImpl(im);
            obs.setImageObstacle("asset/sapin.png");
            obs.setPosition();
            listObst.add(obs);
        }
        zoo.setListObstacle(listObst);


        //------------------------------Animation timer-------------------//
        final long startNanoTime = System.nanoTime();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - startNanoTime) / 10000000000000.0;
                // startNanoTime = currentNanoTime;

                try {
                    boolean rs=(animal1.deplacement(zoo.getListObstacle(), zoo.getListAnimaux()));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                try {
                    animal1.update(elapsedTime);//pour le temps
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                gc.clearRect(0, 0, 512,512);
                try {
                    animal1.render( gc );
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                try {
                    for (ObstacleImpl obs : zoo.getListObstacle())
                        obs.render( gc );
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        primaryStage.show();
    }
}
