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
import javafx.scene.image.Image;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ZooImpl implements Zoo, Serializable {

    public String nomZoo = new String();
    public ArrayList<AnimalImpl> listAnimaux = new ArrayList<AnimalImpl>();
    public ArrayList<ObstacleImpl> listObstacle = new ArrayList<ObstacleImpl>();

    public ZooImpl() throws RemoteException {    }

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
    public void ajoutObstacle(String im) throws RemoteException {
        ObstacleImpl obs = new ObstacleImpl(im);
        this.listObstacle.add(obs);
    }
/*
    @Override
    public void ajoutObstacle(Image im) throws RemoteException {
        ArrayList<ObstacleImpl> obst=new ArrayList<ObstacleImpl>();
        for (int i = 0; i < 15; i++) {
            ObstacleImpl obs = new ObstacleImpl();
            obs.setUrlObstacle(im);
            obs.setPosition();
            obst.add(obs);
        }
        setListObstacle(obst);
    }
*/
}
