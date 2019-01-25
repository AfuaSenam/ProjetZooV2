package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.rmi.Remote;
import java.util.ArrayList;

public interface Animal extends Remote {
    private Image image;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    private Destination destination;
    private boolean male = true;
    private Espece espece = new Espece();
    private double vitesse;

    public void update(double time);
    public Rectangle2D getBoundary();
    public boolean intersects(Objet.Objet s);
    public boolean deplacement(ArrayList<Objet.Objet> obstacle);

}
