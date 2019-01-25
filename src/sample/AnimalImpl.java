package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Iterator;

public class AnimalImpl implements Animal {

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
    }//fonction permettant de savoir si il y a un obstacle à cette position

    public boolean intersects(Objet.Objet s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }

    public boolean deplacement(ArrayList<Objet.Objet> obstacle){
        //gestion d'obstacle
        boolean rs=false;

        return rs;
    }
}
