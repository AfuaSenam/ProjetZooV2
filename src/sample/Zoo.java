package sample;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.rmi.*;

public interface Zoo extends Remote {
	public String getNomZoo() throws RemoteException;
	public void setNomZoo(String nomZoo) throws RemoteException;
	public ArrayList<AnimalImpl> getListAnimaux() throws RemoteException;
	public void setListAnimaux(ArrayList<AnimalImpl> listAnimaux) throws RemoteException;
	public ArrayList<ObstacleImpl> getListObstacle() throws RemoteException;
	public void beginZoo() throws RemoteException,Exception;
	public void setListObstacle(ArrayList<ObstacleImpl> listObstacle) throws RemoteException;
	public void ajouterAninmal(AnimalImpl ani) throws RemoteException;

	/*/// Getters & Setters
	public String getNomZoo() {
		return nomZoo;
	}

	public void setNomZoo(String nomZoo) {
		this.nomZoo = nomZoo;
	}

	public ArrayList<Objet> getListAnimaux() {
		return listAnimaux;
	}

	public void setListAnimaux(ArrayList<Objet> listAnimaux) {
		this.listAnimaux = listAnimaux;
	}

	public ArrayList<Objet> getListObstacle() {
		return listObstacle;
	}

	public void setListObstacle(ArrayList<Objet> listObstacle) {
		this.listObstacle = listObstacle;
	}
	
	public void ajouterAninmal(Objet ani) {
		this.listAnimaux.add(ani);
	}
*/
}
