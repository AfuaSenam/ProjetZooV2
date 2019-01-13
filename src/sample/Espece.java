package sample;

public class Espece {
    private String nomEspece = new String();
    private double vitesse=0;

    public Espece(){ }

    public Espece(String nom, double vit){
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
}
