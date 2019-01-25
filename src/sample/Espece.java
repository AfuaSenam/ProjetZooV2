package sample;

import javafx.scene.image.Image;

import java.rmi.Remote;
import java.util.ArrayList;

public interface Espece extends Remote {

    public String getNomEspece();
    public void setNomEspece(String nomEspece);
    public void setVitesse(double vitesse);
    public double getVitesse();
    public void ajouterAnimal(AnimalImpl animal);
    public ArrayList<AnimalImpl> getListAnimal();
    public void setListAnimal(ArrayList<AnimalImpl> listAnimal);

    public void setImageEspece(Image i);
    public void setImageEspece(String filename);
}
