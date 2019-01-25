package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class ObstacleImpl implements Obstacle {
    private Image imageObstacle;
    private double positionX;
    private double positionY;
    private double width;
    private double height;

    public ObstacleImpl()
    {

    }

    public Image getImageObstacle() {
        return imageObstacle;
    }

    public void setImageObstacle(Image i)
    {
        imageObstacle = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImageObstacle(String filename)
    {
        Image i = new Image(filename);
        setImageObstacle(i);
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void setPosition() {
        positionX = 350 * Math.random() + 50;
        positionY = 350 * Math.random() + 50;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( imageObstacle, positionX, positionY );
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX, positionY, width, height);
    }//fonction permettant de savoir si il y a un obstacle à cette position

    public boolean intersects(ObstacleImpl s)
    {
        return s.getBoundary().intersects( getBoundary());
    }

    public void renderObs(GraphicsContext gc,ArrayList<ObstacleImpl> obstacle){
        for (ObstacleImpl obs : obstacle)
            obs.render( gc );
    }

}
