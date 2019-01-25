package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.rmi.Remote;
import java.util.ArrayList;

public interface Animal extends Remote {

    public void ajoutAnimal(String nomImage);
    public void update(double time);
    public Rectangle2D getBoundary();
    public boolean intersects(Objet.Objet s);
    public boolean deplacement(ArrayList<Objet.Objet> obstacle);

}
