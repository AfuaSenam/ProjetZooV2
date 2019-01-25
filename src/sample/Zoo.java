package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Zoo implements Serializable {
	private String nomZoo = new String();
	private ArrayList<Objet> listAnimaux = new ArrayList<Objet>();
	private ArrayList<Objet> listObstacle = new ArrayList<Objet>();

	public Zoo() {
		
	}
	
	/// Getters & Setters
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

}
