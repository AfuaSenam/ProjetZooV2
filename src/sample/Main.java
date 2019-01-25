package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;



import java.util.ArrayList;
import java.util.Iterator;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Zoo");
        //primaryStage.setScene(new Scene(root, 300, 275));


        Group group = new Group();
        Scene theScene = new Scene( group );
        primaryStage.setScene( theScene );

        final double WIDTH = 30; // image width
        final double HEIGHT = 30; // image height

        final double CANVAS_WIDTH = 512;
        final double CANVAS_HEIGHT = 512;

        Canvas canvas = new Canvas( CANVAS_WIDTH, CANVAS_HEIGHT ); // Create new canvas
        group.getChildren().add( canvas );

        final GraphicsContext gc = canvas.getGraphicsContext2D();


        /*Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont( theFont );
        gc.setFill( Color.GREEN );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);*/

        Objet chien = new Objet();
        chien.setImage("asset/chien.png");
        chien.setPosition(200, 0);
        chien.setDestination(150,150);//x colonne, y ligne
        chien.setVitesse(50);

        ArrayList<Objet> obstacle = new ArrayList<Objet>();

        obstacle=chien.ajoutObstacle();

        /*LongValue lastNanoTime = new LongValue( System.nanoTime() );

        IntValue score = new IntValue(0);*/
        ArrayList<String> input = new ArrayList<String>();

       final long startNanoTime = System.nanoTime();
        ArrayList<Objet> finalObstacle = obstacle;
       // ArrayList<Objet> finalObstacle1 = obstacle;
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - startNanoTime) / 10000000000.0;
               // startNanoTime = currentNanoTime;

                // game logic

                boolean rs=(chien.deplacement(finalObstacle));



               /* if (input.contains("LEFT"))
                    chien.addVelocity(-chien.getVitesse(),0);
                if (input.contains("RIGHT"))
                    chien.addVelocity(chien.getVitesse(),0);
                if (input.contains("UP"))
                    chien.addVelocity(0,-chien.getVitesse());
                if (input.contains("DOWN"))
                    chien.addVelocity(0,chien.getVitesse());*/

                chien.update(elapsedTime);//pour le temps

                // collision detection



                // render

                gc.clearRect(0, 0, 512,512);
                chien.render( gc );

                chien.renderObs(gc,finalObstacle);

                /*String pointsText = "Cash: $" + (100 * score.value);
                gc.fillText( pointsText, 360, 36 );
                gc.strokeText( pointsText, 360, 36 );*/
            }
        }.start();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
