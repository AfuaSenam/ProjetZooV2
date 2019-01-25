package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ZooImpl implements Zoo {

    public String nomZoo = new String();
    public ArrayList<Objet> listAnimaux = new ArrayList<Objet>();
    public ArrayList<Objet> listObstacle = new ArrayList<Objet>();
    public Animal animal;
    @Override
    public String getNomZoo() throws RemoteException {
        return nomZoo;
    }

    @Override
    public void setNomZoo(String nomZoo) throws RemoteException {
        this.nomZoo = nomZoo;
    }

    @Override
    public ArrayList<Objet> getListAnimaux() throws RemoteException {
        return listAnimaux;
    }

    @Override
    public void setListAnimaux(ArrayList<Objet> listAnimaux) throws RemoteException {
        this.listAnimaux = listAnimaux;
    }

    @Override
    public ArrayList<Objet> getListObstacle() throws RemoteException {
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
        animal=new Animal();

        //Fonction obstacle

        //setListObstacle(Obstacle.genererObstacles());

        //------------------------------Animation timer-------------------//
        final long startNanoTime = System.nanoTime();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - startNanoTime) / 10000000000.0;
                // startNanoTime = currentNanoTime;

                boolean rs=(chien.deplacement(finalObstacle));

                animal.update(elapsedTime);//pour le temps
                gc.clearRect(0, 0, 512,512);
                animal.render( gc );
                animal.renderObs(gc,getListObstacle());
            }
        }.start();

        primaryStage.show();

    }

    @Override
    public void setListObstacle(ArrayList<Objet> listObstacle) throws RemoteException {
        this.listObstacle = listObstacle;
    }

    @Override
    public void ajouterAninmal(Objet ani) throws RemoteException {
        this.listAnimaux.add(ani);
    }
}
