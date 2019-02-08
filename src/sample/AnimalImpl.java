package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

public class AnimalImpl implements Animal, Serializable {
    //x colonne, y ligne
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private Destination destination;
    private boolean male = true;
    private EspeceImpl espece;


    // constructeur + Creation espece
    public AnimalImpl(String nomEspece, double vitesse, String imageEspece, String imageDestination, boolean genre) throws RemoteException {
        espece = new EspeceImpl(nomEspece, vitesse, imageEspece);
        // + ajouter cet animal à la liste de l'espece
        espece.ajouterAnimal(this);
        destination = new DestinationImpl(imageDestination);
        male = genre;
        setPosition();
    }

    // Constructeur
    public AnimalImpl(EspeceImpl especeImpl, String imageDestination, boolean genre) throws RemoteException {
        espece = especeImpl;
        espece.ajouterAnimal(this);
        destination = new DestinationImpl(imageDestination);
        male = genre;
        setPosition();

    }

    public AnimalImpl(){}

    //getters & setters
    @Override
    public void setPosition(double x, double y) throws RemoteException {
        positionX = x;
        positionY = y;
    }


    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setPosition() throws RemoteException {
        positionX =  Math.random()*(512-50);
        positionY =  Math.random()*(512-50);
    }

    @Override
    public void setVelocity(double x, double y) throws RemoteException {
        velocityX = x;
        velocityY = y;
    }

    @Override
    public void addVelocity(double x, double y) throws RemoteException {
        this.velocityX += x;
        this.velocityY += y;
    }

    public double getPositionX() throws RemoteException {
        return positionX;
    }

    public void setPositionX(double positionX) throws RemoteException {
        this.positionX = positionX;
    }

    public double getPositionY() throws RemoteException {
        return positionY;
    }

    public void setPositionY(double positionY) throws RemoteException {
        this.positionY = positionY;
    }

    public Destination getDestination() throws RemoteException {
        return destination;
    }

    public void setDestination(Destination destination) throws RemoteException {
        this.destination = destination;
    }

    public boolean isMale() throws RemoteException {
        return male;
    }

    public void setMale(boolean male) throws RemoteException {
        this.male = male;
    }

    public void setMale() throws RemoteException {
        //this.male = male;
        // set random
    }

    public EspeceImpl getEspece() throws RemoteException {
        return espece;
    }

    public void setEspece(EspeceImpl espece) throws RemoteException {
        this.espece = espece;
    }

    public String getUrlEspece(){
        return espece.getUrlEspece();
    }

    @Override
    public void update(double time) throws RemoteException {

        System.out.println("in U Velo X "+this.velocityX);
        time = time*100;
        System.out.println("time "+ time);
        System.out.println("in U X "+this.getPositionX());

      //  System.out.println("velo * temps "+(this.velocityX ));
        setPositionX((getPositionX()+this.velocityX*time));
        System.out.println("out U X "+this.getPositionX());


        System.out.println("in U Velo Y "+this.velocityY);
        System.out.println("in U Y "+this.getPositionY());

        setPositionY((getPositionY()+this.velocityY *time));
        System.out.println("out U Y "+this.getPositionY());

    }

    @Override
    public void render(GraphicsContext gc) throws RemoteException {
        gc.drawImage(espece.getImageEspece(), positionX, positionY);
    }

    @Override
    public Rectangle2D getBoundary() throws RemoteException {
        return new Rectangle2D(positionX, positionY, espece.getWidth(), espece.getHeight());
    }//fonction permettant de savoir si il y a un obstacle à cette position

    @Override
    public boolean verifIntersectObst(ArrayList<ObstacleImpl> listeObst) throws RemoteException{
        boolean t = true;
        for (ObstacleImpl obs:listeObst){
            if(this.intersects(obs))
                t = false;
        }
        return t;
    }

    @Override
    public boolean verifIntersectAni(ArrayList<AnimalImpl> listeAni) throws RemoteException{
        boolean t=true;
        for (AnimalImpl obs:listeAni){
            if(this.intersects(obs))
                t=false;
        }
        return t;
    }

    @Override
    public boolean intersects(ObstacleImpl s) throws RemoteException {
        return s.getBoundary().intersects(this.getBoundary());
    }

    @Override
    public boolean intersects(AnimalImpl ani) throws RemoteException {
        return ani.getBoundary().intersects(this.getBoundary());
    }

    @Override
    public boolean rencontre(ArrayList<AnimalImpl> listAnimaux) throws RemoteException {
        Iterator<AnimalImpl> animalIter = listAnimaux.iterator();
        boolean bebe = false;
        while (animalIter.hasNext()) {
            AnimalImpl ani = animalIter.next();
            if (this.intersects(ani)) {
                if (this.espece == ani.espece) {
                    if (this.isMale() == ani.isMale()) {
                        // meme genre
                        //pas de bébé
                    } else {
                        // new bébé()
                        bebe = true;
                    }
                } else {
                    // especes différentes
                    //pas de bébé ... un mort ?
                }
            }
        }
        return bebe;

    }

    @Override
    public boolean deplacement(ArrayList<ObstacleImpl> listObstacle, ArrayList<AnimalImpl> listAnimaux) throws RemoteException {
        //gestion d'obstacle
        boolean rs = false;
        double resultX;
        double resultY;

        //for (ObstacleImpl)

        Iterator<ObstacleImpl> obstacleIter = listObstacle.iterator();
        while (obstacleIter.hasNext()) {
            //rencontre(listAnimaux);
            this.setVelocity(0, 0);
            resultX = getPositionX() - destination.getDestinationX();
            resultY = getPositionY() - destination.getDestinationY();

            ObstacleImpl obs = obstacleIter.next();
            if ((this.getPositionX() == this.getDestination().getDestinationX()) && (this.getPositionY() == this.getDestination().getDestinationY())) {
                this.destination.setDestination();
            } else {
                if (this.intersects(obs)) {
                    //obstacleIter.remove();
                    //score.value++;
                    this.destination.setDestination(); // si intersection => new destination random

                } else {
                    // inferieur X / ligne Y

                    System.out.println("vitesse = " + espece.getVitesse());
                    if (resultX < 0 && resultY < 0) {
                        this.addVelocity(0, this.espece.getVitesse());//down
                        //System.out.println("go down");
                        System.out.println("set velocity: "+this.getVelocityX());

                    } else if (resultX > 0 && resultY > 0) {
                        this.addVelocity(0, -this.espece.getVitesse());//up
                        //System.out.println("go up");
                        System.out.println("set velocity: "+this.getVelocityX());

                    } else if (resultX > 0 && resultY < 0) {//left
                        this.addVelocity(-this.espece.getVitesse(), 0);
                        //System.out.println("go left");
                        System.out.println("set velocity: "+this.getVelocityX());

                    } else if (resultX < 0 && resultY > 0) {//right
                        this.addVelocity(this.espece.getVitesse(), 0);
                        //System.out.println("go right");
                        System.out.println("set velocity: "+this.getVelocityX());

                    } else if (resultX == 0 && resultY == 0) {//destination atteinte
                        rs = true;
                        System.out.println("stop");
                    }
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
