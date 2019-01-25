package sample;

import javafx.scene.image.Image;

import java.rmi.Remote;

public interface Destination extends Remote {
    private Image destinationImage;
    private double destinationX;
    private double destinationY;
}
