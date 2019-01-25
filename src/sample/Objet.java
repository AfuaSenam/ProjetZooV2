package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;


public class Objet implements Serializable {

    private Image image;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    private double destinationX;
    private double destinationY;
    private boolean male = true;
    private EspeceImpl especeImpl = new EspeceImpl();
    private Image destinationImage;
    private double vitesse;
    //private ArrayList<Objet> obst=new ArrayList<Objet>();


    public Objet()
    {
        positionX = 0;
        positionY = 0;
        velocityX = 0;
        velocityY = 0;
    }

    public EspeceImpl getEspeceImpl() {
        return especeImpl;
    }

    public void setEspeceImpl(EspeceImpl especeImpl) {
        this.especeImpl = especeImpl;
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

    public void setImage(Image i)
    {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage(String filename)
    {
        Image i = new Image(filename);
        setImage(i);
    }

    //imageDestination

    public double getVitesse() {
        return vitesse;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }
    
    public void setDestinationImage(Image i)
    {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setDestinationImage(String filename)
    {
        Image i = new Image(filename);
        setDestinationImage(i);
    }

    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
    }

    public void setDestination(double x, double y)
    {
        destinationX = 350 * x + 50;
        destinationY = 350 * y + 50;
    }

    public void setDestination()
    {
        destinationX = 350 * Math.random() + 50;
        destinationY = 350 * Math.random() + 50;
    }

    public double getDestinationX() {
        return destinationX;
    }

    public double getDestinationY() {
        return destinationY;
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


    public void update(double time)
    {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }//fonction permettant de savoir si il y a un objet deja à cette position

    public boolean intersects(Objet s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }

    public boolean deplacement(ArrayList<Objet> obstacle){
        //gestion d'obstacle
        boolean rs=false;
        double resultX;
        double resultY;
        setVelocity(0,0);
        resultX=getPositionX()-getDestinationX();
        resultY=getPositionY()-getDestinationY();

        Iterator<Objet> obstacleIter = obstacle.iterator();
        while ( obstacleIter.hasNext() )
        {
            Objet obs = obstacleIter.next();
            if ( this.intersects(obs) )
            {

                //obstacleIter.remove();
                //score.value++;
            }else{
                if(resultX<0 && resultY<0){//colonne inferieur et ligne inferieure
                    addVelocity(0,getVitesse());//down
                }else if(resultX>0 && resultY>0){
                    addVelocity(0,-getVitesse());//up
                }else if(resultX>0 && resultY<0){//left
                    addVelocity(-getVitesse(),0);
                }else if(resultX<0 && resultY>0){//right
                    addVelocity(getVitesse(),0);
                }else if(resultX==0 && resultY==0) {//right
                    rs = true;
                }
            }
        }


         return rs;
    }

    public ArrayList<Objet> ajoutObstacle(){
        ArrayList<Objet> obstacle = new ArrayList<Objet>();

        for (int i = 0; i < 15; i++)
        {
            Objet moneybag = new Objet();
            moneybag.setImage("asset/sapin.png");
            double px = 350 * Math.random() + 50;
            double py = 350 * Math.random() + 50;
            moneybag.setPosition(px,py);
            obstacle.add( moneybag );
        }
        return obstacle;
    }

    public void renderObs(GraphicsContext gc,ArrayList<Objet> obstacle){
        for (Objet moneybag : obstacle)
            moneybag.render( gc );
    }

}
