package sample;

import javafx.scene.image.Image;

import java.rmi.Remote;

public interface Destination extends Remote {

    public void setDestination(double x, double y);
    public void setDestination();
    public double getDestinationX();
    public double getDestinationY();
    public void setimageDestination(Image i);
    public void setimageDestination(String filename);

}
