package sample;

import javafx.stage.Stage;

import java.io.Serializable;
import java.util.ArrayList;
import java.rmi.*;

public interface Zoo extends Remote {
	public String getNomZoo() throws RemoteException;
	public void setNomZoo(String nomZoo) throws RemoteException;
	public ArrayList<Objet> getListAnimaux() throws RemoteException;
	public void setListAnimaux(ArrayList<Objet> listAnimaux) throws RemoteException;
	public ArrayList<Objet> getListObstacle() throws RemoteException;
	public void beginZoo(Stage primaryStage) throws RemoteException,Exception;
	public void setListObstacle(ArrayList<Objet> listObstacle) throws RemoteException;
	public void ajouterAninmal(Objet ani) throws RemoteException;

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
