import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import java.security.SecureRandom;
import java.util.ArrayList;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage)
    {
        theStage.setTitle("Defiende tu planeta!");
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );


        Canvas canvas = new Canvas( 1000, 700 );
        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image space = new Image("space.png");
        gc.drawImage(space,0,0);

        Sprite1 sp1 = new Sprite1();
        sp1.setImage("nave.png");
        sp1.render(gc);

        Sprite2 sp2 = new Sprite2();
        sp2.setImage("earth1.png");
        sp2.setPosition(400,250);
        sp2.render(gc);



        theStage.show();

    }
}