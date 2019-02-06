package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ObstacleImpl  implements Serializable, Obstacle {//extends UnicastRemoteObject implements Obstacle
    private transient Image imageObstacle;
    private double positionX;
    private double positionY;
    private double width;
    private double height;

    public ObstacleImpl()  {
    }

    public ObstacleImpl(Image im) throws RemoteException {
        this.setImageObstacle(im);
        this.setPosition();
    }

    public Image getImageObstacle() throws RemoteException {
        return imageObstacle;
    }

    public void setImageObstacle(Image i) throws RemoteException
    {
        imageObstacle = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImageObstacle(String filename) throws RemoteException
    {
        Image i = new Image(filename);
        setImageObstacle(i);
    }

    public double getPositionX() throws RemoteException {
        return positionX;
    }

    public void setPositionX(double positionX) throws RemoteException {
        this.positionX = positionX;
    }

    public void setPosition(double positionX, double positionY) throws RemoteException {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void setPosition() throws RemoteException {
        positionX = 350 * Math.random() + 50;
        positionY = 350 * Math.random() + 50;
    }

    public double getPositionY() throws RemoteException {
        return positionY;
    }

    public void setPositionY(double positionY) throws RemoteException {
        this.positionY = positionY;
    }

    public double getWidth() throws RemoteException {
        return width;
    }

    public void setWidth(double width) throws RemoteException {
        this.width = width;
    }

    public double getHeight() throws RemoteException {
        return height;
    }

    public void setHeight(double height) throws RemoteException {
        this.height = height;
    }

    public void render(GraphicsContext gc) throws RemoteException
    {
        gc.drawImage( imageObstacle, positionX, positionY );
    }

    public Rectangle2D getBoundary() throws RemoteException
    {
        return new Rectangle2D(positionX, positionY, width, height);
    }//fonction permettant de savoir si il y a un obstacle à cette position

    public void renderObs(GraphicsContext gc, ArrayList<ObstacleImpl> obstacle) throws RemoteException {
        for (ObstacleImpl obs : obstacle)
            obs.render( gc );

    }

    public boolean intersects(ObstacleImpl s) throws RemoteException
    {
        return s.getBoundary().intersects( getBoundary());
    }



}
