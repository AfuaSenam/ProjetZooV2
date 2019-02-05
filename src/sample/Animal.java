package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.rmi.Remote;
import java.util.ArrayList;

public interface Animal extends Remote {

    public void setPosition();
    public void setPosition(double x, double y);
    public void setVelocity(double x, double y);
    public void addVelocity(double x, double y);
    public double getPositionX();
    public void setPositionX(double positionX);
    public double getPositionY();
    public void setPositionY(double positionY);
    public Destination getDestination();
    public void setDestination(Destination destination);
    public boolean isMale() ;
    public void setMale(boolean male);
    public EspeceImpl getEspece();
    public void setEspece(EspeceImpl espece);

    public void update(double time);
    public Rectangle2D getBoundary();
    public boolean intersects(AnimalImpl ani);
    public boolean rencontre(ArrayList<AnimalImpl> listAnimaux);
    public boolean deplacement(ArrayList<ObstacleImpl> listObstacle, ArrayList<AnimalImpl> listAnimaux);

    public String toString();

}
