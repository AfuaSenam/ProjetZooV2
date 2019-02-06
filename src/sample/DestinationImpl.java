package sample;

import javafx.scene.image.Image;

import java.io.Serializable;

public class DestinationImpl implements Destination, Serializable {
    private Image imageDestination;
    private double width;
    private double height;
    private double destinationX;
    private double destinationY;

    // Constructeur avec Random
    public DestinationImpl(){}

    public DestinationImpl(String imageDestination) {
        setimageDestination(imageDestination);
        setDestination();
    }

    //getters & setters
    public void setDestination(double x, double y)
    {
        destinationX = 350 * x + 50;
        destinationY = 350 * y + 50;
    }

    public void setDestination()
    {
        destinationX = 350 * Math.random() + 50;
        destinationY = 350 * Math.random() + 50;
    }

    public double getDestinationX() {
        return destinationX;
    }

    public double getDestinationY() {
        return destinationY;
    }

    public void setimageDestination(Image i)
    {
        imageDestination = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setimageDestination(String filename)
    {
        Image i = new Image(filename);
        setimageDestination(i);
    }
}
