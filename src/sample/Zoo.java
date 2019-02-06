package sample;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.rmi.*;
import javafx.scene.image.Image;

public interface Zoo extends Remote {
	public String getNomZoo() throws RemoteException;
	public void setNomZoo(String nomZoo) throws RemoteException;
	public ArrayList<AnimalImpl> getListAnimaux() throws RemoteException;
	public void setListAnimaux(ArrayList<AnimalImpl> listAnimaux) throws RemoteException;
	public ArrayList<ObstacleImpl> getListObstacle() throws RemoteException;
	public void setListObstacle(ArrayList<ObstacleImpl> listObstacle) throws RemoteException;
	public void ajouterAninmal(AnimalImpl ani) throws RemoteException;
	public void renderObs(GraphicsContext gc) throws RemoteException;
	public void ajoutObstacle(Image im) throws RemoteException;
	public void ajouterAninmal(String nomEspece, double vitesse, String imageEspece, String imageDestination, boolean genre) throws RemoteException;
}
