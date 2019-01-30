package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ZooImpl extends UnicastRemoteObject implements Zoo {

    public String nomZoo = new String();
    public ArrayList<AnimalImpl> listAnimaux = new ArrayList<AnimalImpl>();
    public ArrayList<ObstacleImpl> listObstacle = new ArrayList<ObstacleImpl>();
    public AnimalImpl animal1;

    public AnimalImpl getAnimal1() {
        return animal1;
    }

    public void setAnimal1(AnimalImpl animal1) {
        this.animal1 = animal1;
    }

    public ZooImpl() throws RemoteException {

    }

    public ZooImpl(Stage apl) throws RemoteException,Exception {
        this.beginZoo(apl);
    }

    @Override
    public String getNomZoo() throws RemoteException {
        return nomZoo;
    }

    @Override
    public void setNomZoo(String nomZoo) throws RemoteException {
        this.nomZoo = nomZoo;
    }

    @Override
    public ArrayList<AnimalImpl> getListAnimaux() throws RemoteException {
        return listAnimaux;
    }

    @Override
    public void setListAnimaux(ArrayList<AnimalImpl> listAnimaux) throws RemoteException {
        this.listAnimaux = listAnimaux;
    }

    @Override
    public ArrayList<ObstacleImpl> getListObstacle() throws RemoteException {
        return listObstacle;
    }

    @Override
    public void beginZoo(Stage primaryStage) throws RemoteException, Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Zoo");
        //primaryStage.setScene(new Scene(root, 300, 275));

        Group group = new Group();
        Scene theScene = new Scene( group );
        primaryStage.setScene( theScene );

        final double WIDTH = 30; // image width
        final double HEIGHT = 30; // image height

        final double CANVAS_WIDTH = 512;
        final double CANVAS_HEIGHT = 512;

        Canvas canvas = new Canvas( CANVAS_WIDTH, CANVAS_HEIGHT ); // Create new canvas
        group.getChildren().add( canvas );

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        //Ajout animal
        animal1=new AnimalImpl("Chien",50,"asset/chien.jpg","asset/mine.png",false);

        //Fonction obstacle

        setListObstacle(Obstacle.ajoutObstacle());

        //------------------------------Animation timer-------------------//
        final long startNanoTime = System.nanoTime();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - startNanoTime) / 10000000000.0;
                // startNanoTime = currentNanoTime;

                try {
                    boolean rs=(animal1.deplacement(getListObstacle()));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                animal1.update(elapsedTime);//pour le temps
                gc.clearRect(0, 0, 512,512);
                animal1.render( gc );
                try {
                    Obstacle.renderObs(gc,getListObstacle());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        primaryStage.show();

    }

    @Override
    public void setListObstacle(ArrayList<ObstacleImpl> listObstacle) throws RemoteException {
        this.listObstacle = listObstacle;
    }

    @Override
    public void ajouterAninmal(AnimalImpl ani) throws RemoteException {
        this.listAnimaux.add(ani);
    }
}
