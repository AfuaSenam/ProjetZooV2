package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.rmi.Remote;
import java.util.ArrayList;

public interface Obstacle extends Remote {

    public ArrayList<Objet.Objet> ajoutObstacle();
    public void renderObs(GraphicsContext gc, ArrayList<Objet.Objet> obstacle);
}

