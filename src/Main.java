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

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage)
    {
        theStage.setMaximized(true);
        theStage.setTitle( "Timeline Example" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
        double stageW = theStage.getWidth();
        double stageH = theStage.getHeight();



        Canvas canvas = new Canvas( 800, 600 );
        root.getChildren().add( canvas );

        theStage.show();
        canvas.setWidth(stageW);
        canvas.setHeight(stageH);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Sprite ball = new Sprite();
        ball.setImage( "pelota.png" );

        ball.setPosition(Math.random()*400+1, Math.random()*300+1);

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );

        final long timeStart = System.currentTimeMillis();

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                new EventHandler<ActionEvent>()
                {
                    double x = 5;

                    double y = 5;

                    public void handle(ActionEvent ae)
                    {


                        ball.setVelocity(x, y);

                        x = ball.impactoX();

                        y = ball.impactoY();

                        ball.update();

                        // Clear the canvas
                        gc.clearRect(0, 0, 800,600);

                        // background image clears canvas
                       // gc.drawImage( background, 0, 0);

                        ball.render(gc);

                    }
                });

        gameLoop.getKeyFrames().add( kf );
        gameLoop.play();


    }
}