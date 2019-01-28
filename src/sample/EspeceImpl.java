package sample;


import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;

public class EspeceImpl {
    private Image imageEspece;
    private double width;
    private double height;
    private String nomEspece = new String();
    private double vitesse=0;
    private ArrayList<AnimalImpl> listAnimal = new ArrayList<AnimalImpl>();

    public EspeceImpl(String nom, double vit, String image){
        nomEspece = nom;
        vitesse = vit;
        setImageEspece(image);
    }

    public EspeceImpl() {

    }

    public String getNomEspece() { return nomEspece; }

    public void setNomEspece(String nomEspece) { this.nomEspece = nomEspece; }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void setImageEspece(Image i)
    {
        imageEspece = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImageEspece(String filename)
    {
        Image i = new Image(filename);
        setImageEspece(i);
    }

    public Image getImageEspece(){
        return imageEspece;
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
