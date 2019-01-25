package sample;


import javafx.scene.image.Image;

import java.io.Serializable;

public class EspeceImpl {
    private Image imageEspece;
    private double width;
    private double height;
    private String nomEspece = new String();
    private double vitesse=0;

    public EspeceImpl(){ }

    public EspeceImpl(String nom, double vit){
        nomEspece = nom;
        vitesse = vit;
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
}
