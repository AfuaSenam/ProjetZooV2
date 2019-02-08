package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

public class ZooImpl implements Zoo, Serializable {

    public String nomZoo = new String();
    public ArrayList<AnimalImpl> listAnimaux = new ArrayList<AnimalImpl>();
    public ArrayList<ObstacleImpl> listObstacle = new ArrayList<ObstacleImpl>();

    public ZooImpl() throws RemoteException {    }

    @Override
    public String getNomZoo() throws RemoteException {
        return nomZoo;
    }

    @Override
    public void setNomZoo(String nomZoo) throws RemoteException {
        this.nomZoo = nomZoo;
    }

    @Override
    public ArrayList<AnimalImpl> getListAnimaux() throws RemoteException {
        /*for (AnimalImpl ani : listAnimaux) {
            ani.setPositionY(ani.getPositionY()+10);
        }*/
        return listAnimaux;
    }

    @Override
    public void setListAnimaux(ArrayList<AnimalImpl> listAnimaux) throws RemoteException {
        this.listAnimaux = listAnimaux;
    }

    @Override
    public ArrayList<ObstacleImpl> getListObstacle() throws RemoteException {
        return listObstacle;
    }

    @Override
    public void setListObstacle(ArrayList<ObstacleImpl> listObstacle) throws RemoteException {
        this.listObstacle = listObstacle;
    }

    @Override
    public void ajouterAnimal(AnimalImpl ani) throws RemoteException {
        this.listAnimaux.add(ani);
    }

    @Override
    public void ajouterAnimal(String nomEspece, double vitesse, String imageEspece, String imageDestination, boolean genre) throws RemoteException {
        AnimalImpl ani = new AnimalImpl(nomEspece, vitesse, imageEspece, imageDestination, genre);
        if(ani.verifIntersectObst(getListObstacle()) && ani.verifIntersectAni(getListAnimaux())){
            this.listAnimaux.add(ani);
        }
    }

    @Override
    public void ajoutObstacle(String im) throws RemoteException {
        ObstacleImpl obs = new ObstacleImpl(im);
        this.listObstacle.add(obs);
    }

    @Override
    public void ajoutListeObs(String url, int n) throws RemoteException {
        for (int i = 0; i < n; i++) {
            ObstacleImpl obs = new ObstacleImpl(url);
            listObstacle.add(obs);
        }
    }

    @Override
    public void setPositionAnimal(AnimalImpl ani, double x, double y) throws RemoteException{
        ani.setPositionX(x);
        ani.setPositionY(y);
    }

    @Override
    public void setDeplacementAnimal(AnimalImpl ani) throws RemoteException{
        //ani.deplacement(listObstacle, listAnimaux);

        //gestion d'obstacle
        boolean rs = false;
        double resultX;
        double resultY;
        //for (ObstacleImpl)
        Iterator<ObstacleImpl> obstacleIter = listObstacle.iterator();
        System.out.println("-----------------------------------");
       // while (obstacleIter.hasNext()) {
            if(ani.rencontre(listAnimaux))
            {
                ajouterAnimal(ani.getEspece().getNomEspece(), ani.getEspece().getVitesse(), ani.getEspece().getUrlEspece(), "asset/mine.png", true);
            }
            ani.setVelocity(0, 0);
            resultX = ani.getPositionX() - ani.getDestination().getDestinationX();
            resultY = ani.getPositionY() - ani.getDestination().getDestinationY();

            ObstacleImpl obs = obstacleIter.next();
            if ((ani.getPositionX() == ani.getDestination().getDestinationX()) && (ani.getPositionY() == ani.getDestination().getDestinationY())) {
                ani.getDestination().setDestination();
            } else {
                if (ani.intersects(obs)) {
                    //obstacleIter.remove();
                    //score.value++;
                    ani.getDestination().setDestination(); // si intersection => new destination random

                } else {
                    // inferieur X / ligne Y

                    System.out.println("vitesse = " + ani.getEspece().getVitesse());
                    if (resultX < 0 && resultY < 0) {
                        ani.addVelocity(0, ani.getEspece().getVitesse());//down
                        System.out.println("go down");

                    } else if (resultX > 0 && resultY > 0) {
                        ani.addVelocity(0, -ani.getEspece().getVitesse());//up
                        System.out.println("go up");

                    } else if (resultX > 0 && resultY < 0) {//left
                        ani.addVelocity(-ani.getEspece().getVitesse(), 0);
                        System.out.println("go left");

                    } else if (resultX < 0 && resultY > 0) {//right
                        ani.addVelocity(ani.getEspece().getVitesse(), 0);
                        System.out.println("go right");

                    } else if (resultX == 0 && resultY == 0) {//destination atteinte
                        rs = true;
                        System.out.println("stop");
                    }
                }
            }

       // }
        //return rs;
    }

    @Override
    public void setUpdateAnimal(AnimalImpl ani, double time) throws RemoteException{
        //ani.update(time);
        System.out.println("in U X = " + ani.getPositionX());
        System.out.println("in U Y = " + ani.getPositionY());

        time = time*1000000;
        ani.setPositionX((ani.getPositionX()+ani.getVelocityX()*time));
        ani.setPositionY((ani.getPositionY()+ani.getVelocityY()*time));

        System.out.println("out U X = " + ani.getPositionX());
        System.out.println("out U Y = " + ani.getPositionY());
    }

    @Override
    public double getPositionXAnimal(AnimalImpl ani) throws RemoteException{
        return ani.getPositionX();
    }

    @Override
    public double getPositionYAnimal(AnimalImpl ani) throws RemoteException{
        return ani.getPositionY();
    }

    @Override
    public String getUrlAnimal(AnimalImpl ani) throws RemoteException{
        return ani.getUrlEspece();
    }

    @Override
    public void deplacementListAnimaux(double time) throws RemoteException{
        for (AnimalImpl ani : listAnimaux) {

            System.out.println("debut X = " + ani.getPositionX());
            System.out.println("debut Y = " + ani.getPositionY());

            setDeplacementAnimal(ani);

            System.out.println("D X = " + ani.getPositionX());
            System.out.println("D Y = " + ani.getPositionY());

            setUpdateAnimal(ani, time);

            System.out.println("U X = " + ani.getPositionX());
            System.out.println("U Y = " + ani.getPositionY());
        }
    }
}
