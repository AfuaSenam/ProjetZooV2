package sample;


import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;

public class EspeceImpl implements Serializable {
    private String urlEspece;
    private double width;
    private double height;
    private String nomEspece = new String();
    private double vitesse=0;
    private ArrayList<AnimalImpl> listAnimal = new ArrayList<AnimalImpl>();

    public EspeceImpl(String nom, double vit, String image){
        nomEspece = nom;
        vitesse = vit;
        setUrlEspece(image);
    }

    public EspeceImpl() {    }

    public String getNomEspece() { return nomEspece; }

    public void setNomEspece(String nomEspece) { this.nomEspece = nomEspece; }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void setUrlEspece(String i)
    {
        this.urlEspece = i;
    }

    public Image getImageEspece()
    {
        Image i = new Image(urlEspece);
        width = i.getWidth();
        height = i.getHeight();
        return i;
    }

    public String getUrlEspece(){
        return urlEspece;
    }

    public void ajouterAnimal(AnimalImpl animal){
        listAnimal.add(animal);
    }

    public ArrayList<AnimalImpl> getListAnimal() {
        return listAnimal;
    }

    public void setListAnimal(ArrayList<AnimalImpl> listAnimal) {
        this.listAnimal = listAnimal;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
