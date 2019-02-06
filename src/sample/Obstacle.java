package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Obstacle extends Remote {

    public Image getImageObstacle() throws RemoteException;

    public void setImageObstacle(Image i) throws RemoteException;

    public void setImageObstacle(String filename) throws RemoteException;

    public double getPositionX() throws RemoteException;

    public void setPositionX(double positionX) throws RemoteException;

    public void setPosition(double positionX, double positionY) throws RemoteException;

    public void setPosition() throws RemoteException;

    public double getPositionY() throws RemoteException;

    public void setPositionY(double positionY) throws RemoteException;

    public double getWidth() throws RemoteException;

    public void setWidth(double width) throws RemoteException;

    public double getHeight() throws RemoteException;

    public void setHeight(double height) throws RemoteException;

    public void render(GraphicsContext gc) throws RemoteException;

    public Rectangle2D getBoundary() throws RemoteException;

    public void renderObs(GraphicsContext gc, ArrayList<ObstacleImpl> obstacle) throws RemoteException;

    public boolean intersects(ObstacleImpl s) throws RemoteException;

}



