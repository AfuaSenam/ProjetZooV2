package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.rmi.Remote;
import java.util.ArrayList;

public interface Obstacle extends Remote {
    
    public Image getImageObstacle();
    public void setImageObstacle(Image i);
    public void setImageObstacle(String filename);
    public double getPositionX();
    public void setPositionX(double positionX);
    public void setPosition(double positionX, double positionY);
    public void setPosition();
    public double getPositionY();
    public void setPositionY(double positionY);
    public double getWidth();
    public void setWidth(double width);
    public double getHeight();
    public void setHeight(double height);
    public void render(GraphicsContext gc);
    public Rectangle2D getBoundary();
    public boolean intersects(ObstacleImpl s);
    public static ArrayList<ObstacleImpl> ajoutObstacle(){
        ArrayList<ObstacleImpl> obstacle = new ArrayList<ObstacleImpl>();

        for (int i = 0; i < 15; i++)
        {
            ObstacleImpl obs = new ObstacleImpl();
            obs.setImageObstacle("asset/sapin.png");
            obs.setPosition();
            obstacle.add( obs );
        }
        return obstacle;
    }
    public static void renderObs(GraphicsContext gc,ArrayList<ObstacleImpl> obstacle){
        for (ObstacleImpl obs : obstacle)
            obs.render( gc );
    };

}

