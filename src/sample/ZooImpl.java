package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ZooImpl extends Application implements Zoo {

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

    @Override
    public void start(Stage primaryStage) throws Exception, RemoteException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Zoo");
        //primaryStage.setScene(new Scene(root, 300, 275));

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

        setListObstacle(Obstacle.ajoutObstacle());

        //------------------------------Animation timer-------------------//
        final long startNanoTime = System.nanoTime();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - startNanoTime) / 10000000000000.0;
                // startNanoTime = currentNanoTime;

                /*try {
                   // boolean rs=(animal1.deplacement(getListObstacle(), getListAnimaux()));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }*/

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
                /*try {
                    //Obstacle.renderObs(gc,getListObstacle());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }*/
            }
        }.start();

        primaryStage.show();
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
    public void beginZoo() throws RemoteException, Exception {
        launch();
    }

    @Override
    public void setListObstacle(ArrayList<ObstacleImpl> listObstacle) throws RemoteException {
        this.listObstacle = listObstacle;
    }

    @Override
    public void ajouterAninmal(AnimalImpl ani) throws RemoteException {
        this.listAnimaux.add(ani);
    }

    @Override
    public void ajouterAninmal(String nomEspece, double vitesse, String imageEspece, String imageDestination, boolean genre) throws RemoteException {
        AnimalImpl ani = new AnimalImpl(nomEspece, vitesse, imageEspece, imageDestination, genre);
        this.listAnimaux.add(ani);
    }
    @Override
    public void renderObs(GraphicsContext gc) throws RemoteException {
        for (ObstacleImpl obs : listObstacle)
            obs.render( gc );
    }
    @Override
    public void ajoutObstacle() throws RemoteException {
        ArrayList<ObstacleImpl> obst=new ArrayList<ObstacleImpl>();
        for (int i = 0; i < 15; i++) {
            ObstacleImpl obs = new ObstacleImpl();
            obs.setImageObstacle("asset/sapin.png");
            obs.setPosition();
            obst.add(obs);
        }
        setListObstacle(obst);
    }
}
