package sample;

import javafx.scene.image.Image;

import java.rmi.Remote;

public interface Espece extends Remote {

    public String getNomEspece();
    public void setNomEspece(String nomEspece);
    public void setVitesse(double vitesse);
    public double getVitesse();

    public void setImageEspece(Image i);
    public void setImageEspece(String filename);
}
