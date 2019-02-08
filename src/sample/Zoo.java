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
	public void ajouterAnimal(AnimalImpl ani) throws RemoteException;
	public void ajouterAnimal(String nomEspece, double vitesse, String imageEspece, String imageDestination, boolean genre) throws RemoteException;
	public void ajoutObstacle(String im) throws RemoteException;
	public void ajoutListeObs(String url,int n) throws RemoteException;

	public void setPositionAnimal(AnimalImpl ani, double x, double y) throws RemoteException;
	public void setDeplacementAnimal(AnimalImpl ani) throws RemoteException;
	public void deplacementListAnimaux(double time) throws RemoteException;
		public void setUpdateAnimal(AnimalImpl ani, double time) throws RemoteException;
	public String getUrlAnimal(AnimalImpl ani) throws RemoteException;
	public double getPositionXAnimal(AnimalImpl ani) throws RemoteException;
	public double getPositionYAnimal(AnimalImpl ani) throws RemoteException;
	}