package sample;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class ObstacleImpl implements Obstacle {

    public ArrayList<Objet.Objet> ajoutObstacle(){
        ArrayList<Objet.Objet> obstacle = new ArrayList<Objet.Objet>();

        for (int i = 0; i < 15; i++)
        {
            Objet.Objet moneybag = new Objet.Objet();
            moneybag.setImage("asset/sapin.png");
            double px = 350 * Math.random() + 50;
            double py = 350 * Math.random() + 50;
            moneybag.setPosition(px,py);
            obstacle.add( moneybag );
        }
        return obstacle;
    }

    public void renderObs(GraphicsContext gc, ArrayList<Objet.Objet> obstacle){
        for (Objet.Objet moneybag : obstacle)
            moneybag.render( gc );
    }
}
