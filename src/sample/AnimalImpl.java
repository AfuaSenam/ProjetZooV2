package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Iterator;

public class AnimalImpl implements Animal {
    //x colonne, y ligne
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private Destination destination;
    private boolean male = true;
    private EspeceImpl espece;

    // constructeur + Creation espece
    public AnimalImpl(String nomEspece, double vitesse, String imageEspece, String imageDestination, boolean genre){
        espece = new EspeceImpl(nomEspece,  vitesse, imageEspece);
        // + ajouter cet animal à la liste de l'espece
        espece.ajouterAnimal(this);
        destination = new DestinationImpl(imageDestination);
        male = genre;
        setPosition();

    }

    // Constructeur
    public AnimalImpl(EspeceImpl especeImpl, String imageDestination, boolean genre){
        espece = especeImpl;
        espece.ajouterAnimal(this);
        destination = new DestinationImpl(imageDestination);
        male = genre;
        setPosition();
    }

    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
    }

    public void setPosition()
    {
        positionX = 350 * Math.random() + 50;
        positionY = 350 * Math.random() + 50;
        // + verifier qu'il n'y a rien à cet endroit
    }


    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public EspeceImpl getEspece() {
        return espece;
    }

    public void setEspece(EspeceImpl espece) {
        this.espece = espece;
    }

    public void update(double time)
    {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( espece.getImageEspece(), positionX, positionY );
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX, positionY, espece.getWidth(), espece.getHeight());
    }//fonction permettant de savoir si il y a un obstacle à cette position

    public boolean intersects(ObstacleImpl s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }

    public boolean intersects(AnimalImpl ani)
    {
        return ani.getBoundary().intersects( this.getBoundary() );
    }

    public boolean deplacement(ArrayList<ObstacleImpl> listObstacle){
        //gestion d'obstacle
        boolean rs=false;
        double resultX;
        double resultY;
        setVelocity(0,0);
        resultX=getPositionX()-destination.getDestinationX();
        resultY=getPositionY()-destination.getDestinationY();

        Iterator<ObstacleImpl> obstacleIter = listObstacle.iterator();
        while ( obstacleIter.hasNext() )
        {
            ObstacleImpl obs = obstacleIter.next();
            if ( this.intersects(obs) )
            {
                //obstacleIter.remove();
                //score.value++;
            }else{
                if(resultX<0 && resultY<0){//colonne inferieur et ligne inferieure
                    addVelocity(0,espece.getVitesse());//down
                }else if(resultX>0 && resultY>0){
                    addVelocity(0,-espece.getVitesse());//up
                }else if(resultX>0 && resultY<0){//left
                    addVelocity(-espece.getVitesse(),0);
                }else if(resultX<0 && resultY>0){//right
                    addVelocity(espece.getVitesse(),0);
                }else if(resultX==0 && resultY==0) {//right
                    rs = true;
                }
            }
        }
        return rs;
    }

    @Override
    public String toString() {
        return "AnimalImpl{" +
                "positionX=" + positionX +
                ", positionY=" + positionY +
                ", velocityX=" + velocityX +
                ", velocityY=" + velocityY +
                ", destination=" + destination.toString() +
                ", male=" + male +
                ", espece=" + espece.toString() +
                '}';
    }
}
