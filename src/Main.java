import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ObservableLongValue;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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


import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage theStage) {
        theStage.setTitle("Defiende tu planeta!");
        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(1000, 700);
        root.getChildren().add(canvas);



        GraphicsContext gc = canvas.getGraphicsContext2D();


        //Ponemos un fondo al juego
        Image space = new Image("space.png");
        gc.drawImage(space, 0, 0);

        //Ponemos la imagen de la tierra en el centro de la ventana
        Sprite1 tierra1 = new Sprite1();
        tierra1.setImage("earth1.png");
        tierra1.setPosition(400, 250);
        tierra1.render(gc);




        ArrayList<Sprite1> listaNaves = new ArrayList<Sprite1>();

       for (int i = 0; i < 3; i++) {

            Sprite1 naves1 = new Sprite1();
            naves1.setImage("nave.png");
            double px = 0;
            double py = 700 * Math.random() + 1;
            naves1.setPosition(px, py);
            listaNaves.add(naves1);
   }

        theScene.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                        for (Sprite1 naves1 : listaNaves ) {

                            if ( naves1.containsPoint( e.getX(), e.getY() ) )
                            {
                                System.out.println("tocado");
                            }
                            else;

                        }

                        }
                        //recorrer array

                });



        new AnimationTimer()
        {
            double x = 500;

            double y = 0;
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.

                for (Sprite1 naves1 : listaNaves ) {

                   naves1.update();
                   naves1.render(gc);
                   naves1.setVelocity(x,y);

                   x =naves1.impactoX();
                    y = naves1.impactoY();

                    if (naves1.intersects(tierra1)){
                        for (Sprite1 naves2 : listaNaves ){
                            naves2.setPosition(0,700 * Math.random() + 1);
                        }
                    };
                }
                gc.clearRect(x, y, 1000,700);

                gc.drawImage(space, 0, 0);
                tierra1.render(gc);


                for (Sprite1 naves1 : listaNaves ) {
                    naves1.render(gc);
                }

            }
        }.start();

        theStage.show();

        }
    }
