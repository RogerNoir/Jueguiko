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

    int vidas = 5;
    int puntos = 0;
    int nivel = 0;
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

        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont( theFont );
        gc.setFill( Color.WHITE );
        gc.setStroke( Color.WHITE );
        gc.setLineWidth(1);

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


        theScene.setOnMouseClicked(e ->
                    {
                        for (Sprite1 naves1 : listaNaves ) {

                            if ( naves1.containsPoint( e.getX(), e.getY() ) )
                            {
                                puntos++;
                           }

                        }
                });


        new AnimationTimer()
        {
            double x = 500;

            double y = 0;
            public void handle(long currentNanoTime)
            {
                for (Sprite1 naves1 : listaNaves ) {

                    naves1.update();
                    naves1.render(gc);
                    naves1.setVelocity(x,y);

                    x =naves1.impactoX();
                    y = naves1.impactoY();

                    if (naves1.intersects(tierra1)){
                            naves1.setPosition(0,700 * Math.random() + 1);
                            vidas = vidas -1;
                    }
                }
                gc.clearRect(x, y, 1000,700);

                gc.drawImage(space, 0, 0);
                tierra1.render(gc);


                for (Sprite1 naves1 : listaNaves ) {
                    naves1.render(gc);
                }

                //mostrar puntos, vidas y nivel
                String pointsText = "Puntos: " + puntos;
                gc.fillText( pointsText, 750, 36 );
                gc.strokeText( pointsText, 750, 36 );

                String lifesText = "Vidas: " + vidas;
                gc.fillText( lifesText, 880, 36 );
                gc.strokeText( lifesText, 880, 36 );
                nivel = 1;
                String levelText = "Nivel : " + nivel ;
                gc.fillText( levelText, 470, 36 );
                gc.strokeText( levelText, 470, 36 );



                //level 2
                if (puntos >= 6) {
                    nivel = 2;
                    levelText = "Nivel :" + nivel;
                    gc.fillText( levelText, 470, 36 );
                    gc.strokeText( levelText, 470, 36 );
                    tierra1.setImage("earth2.png");
                    tierra1.setPosition(350,250);
                    tierra1.render(gc);

                    //colision naves con tierra 2
                    for (Sprite1 naves1 : listaNaves ) {
                        if (naves1.intersects(tierra1)){
                            for (Sprite1 naves2 : listaNaves ){
                                naves2.setPosition(0,700 * Math.random() + 1);
                                vidas = vidas-1;
                            }
                        }
                    }

                }


                //level 3
                if (puntos >= 12) {
                    nivel = 3;
                    levelText = "Nivel :" + nivel;
                    gc.fillText( levelText, 470, 36 );
                    gc.strokeText( levelText, 470, 36 );
                    tierra1.setImage("earth3.png");
                    tierra1.setPosition(300,230);
                    tierra1.render(gc);


                    //colision naves con tierra 3
                    for (Sprite1 naves1 : listaNaves ) {
                        if (naves1.intersects(tierra1)){
                            for (Sprite1 naves2 : listaNaves ){
                                naves2.setPosition(0,700 * Math.random() + 1);
                                vidas=vidas-1;
                            }
                        }
                    }


                }

                //si hay 0 vidas que salga GAME OVER
                if (vidas <= 0) {
                    gc.clearRect(x, y, 1000,700);
                    gc.drawImage(space, 0, 0);
                    Font theFont1 = Font.font( "Helvetica", FontWeight.BOLD, 100 );
                    gc.setFont( theFont1 );
                    gc.setFill( Color.RED);
                    gc.setStroke( Color.RED);
                    gc.setLineWidth(1);

                    String gameOver = "GAME OVER ";
                    gc.fillText(gameOver, 200, 400);
                    gc.strokeText(gameOver, 200, 400);

                }
                //si llega a la puntuacion mas alta que salga YOU WIN
                if(puntos == 15){
                    gc.clearRect(x, y, 1000,700);
                    gc.drawImage(space, 0, 0);
                    Font theFont1 = Font.font( "Helvetica", FontWeight.BOLD, 100 );
                    gc.setFont( theFont1 );
                    gc.setFill( Color.GREEN);
                    gc.setStroke( Color.GREEN );
                    gc.setLineWidth(1);

                    String gameOver = "YOU WIN ";
                    gc.fillText(gameOver, 300, 400);
                    gc.strokeText(gameOver, 300, 400);

                }


            }

        }.start();

        theStage.show();

        }
    }
