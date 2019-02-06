package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

import java.rmi.RemoteException;
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

    public void setPosition(double x, double y) throws RemoteException {
        positionX = x;
        positionY = y;
    }

    public void setPosition() throws RemoteException {
        positionX = 350 * Math.random() + 50;
        positionY = 350 * Math.random() + 50;
        // + verifier qu'il n'y a rien à cet endroit
    }


    public void setVelocity(double x, double y) throws RemoteException {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y) throws RemoteException {
        velocityX += x;
        velocityY += y;
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

    public EspeceImpl getEspece() throws RemoteException {
        return espece;
    }

    public void setEspece(EspeceImpl espece) throws RemoteException {
        this.espece = espece;
    }

    public void update(double time) throws RemoteException {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public void render(GraphicsContext gc) throws RemoteException {
        gc.drawImage(espece.getImageEspece(), positionX, positionY);
    }

    public Rectangle2D getBoundary() throws RemoteException {
        return new Rectangle2D(positionX, positionY, espece.getWidth(), espece.getHeight());
    }//fonction permettant de savoir si il y a un obstacle à cette position

    public boolean intersects(ObstacleImpl s) throws RemoteException {
        return s.getBoundary().intersects(this.getBoundary());
    }

    public boolean intersects(AnimalImpl ani) throws RemoteException {
        return ani.getBoundary().intersects(this.getBoundary());
    }

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
                        //   ZooImpl.ajouterAnimal(this.espece.getNomEspece(), this.espece.getVitesse(), this.espece.getImageEspece(), "destination.png", true);
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

    public boolean deplacement(ArrayList<ObstacleImpl> listObstacle, ArrayList<AnimalImpl> listAnimaux) throws RemoteException {
        //gestion d'obstacle
        boolean rs = false;
        double resultX;
        double resultY;
        setVelocity(0, 0);

        //for (ObstacleImpl)

        Iterator<ObstacleImpl> obstacleIter = listObstacle.iterator();
        while (obstacleIter.hasNext()) {
            rencontre(listAnimaux);
            resultX = getPositionX() - destination.getDestinationX();
            resultY = getPositionY() - destination.getDestinationY();
            ObstacleImpl obs = obstacleIter.next();
            if ((this.getPositionX() == this.getDestination().getDestinationX()) && (this.getPositionY() == this.getDestination().getDestinationY())) {
                this.destination.setDestination();
            } else {
                if (this.intersects(obs)) {
                    //obstacleIter.remove();
                    //score.value++;

                    this.destination.setDestination();
                } else {
                    if (resultX < 0 && resultY < 0) {//colonne inferieur et ligne inferieure
                        addVelocity(0, espece.getVitesse());//down
                    } else if (resultX > 0 && resultY > 0) {
                        addVelocity(0, -espece.getVitesse());//up
                    } else if (resultX > 0 && resultY < 0) {//left
                        addVelocity(-espece.getVitesse(), 0);
                    } else if (resultX < 0 && resultY > 0) {//right
                        addVelocity(espece.getVitesse(), 0);
                    } else if (resultX == 0 && resultY == 0) {//right
                        rs = true;
                    }
                }
            }

        }
        return rs;
    }

    public void renderObs(GraphicsContext gc, ArrayList<Obstacle> obstacle) throws RemoteException {
        for (Obstacle obs : obstacle)
            obs.render(gc);
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
