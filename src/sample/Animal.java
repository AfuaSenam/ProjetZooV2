package sample;

import javafx.geometry.Rectangle2D;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Animal extends Remote {

    public void setPosition() throws RemoteException;
    public void setPosition(double x, double y) throws RemoteException;
    public void setVelocity(double x, double y) throws RemoteException;
    public void addVelocity(double x, double y) throws RemoteException;
    public double getPositionX() throws RemoteException;
    public void setPositionX(double positionX) throws RemoteException;
    public double getPositionY() throws RemoteException;
    public void setPositionY(double positionY) throws RemoteException;
    public Destination getDestination() throws RemoteException;
    public void setDestination(Destination destination) throws RemoteException;
    public boolean isMale() throws RemoteException;
    public void setMale(boolean male) throws RemoteException;
    public EspeceImpl getEspece() throws RemoteException;
    public void setEspece(EspeceImpl espece) throws RemoteException;

    public void update(double time) throws RemoteException;
    public Rectangle2D getBoundary() throws RemoteException;
    public boolean intersects(AnimalImpl ani) throws RemoteException;
    public boolean rencontre(ArrayList<AnimalImpl> listAnimaux) throws RemoteException;
    public boolean deplacement(ArrayList<ObstacleImpl> listObstacle, ArrayList<AnimalImpl> listAnimaux) throws RemoteException;

    public String toString();

}
