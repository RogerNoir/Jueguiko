import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;

public class Sprite1 {
    private Image image;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    private double widthWin = 1000;
    private double heightWin = 700;

    //x=500 y=350

    public Sprite1() {
        positionX = 0;
        positionY = 0;
        velocityX = 0;
        velocityY = 0;
    }

    public void setImage(Image i) {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage(String filename) {
        Image i = new Image(filename);
        setImage(i);

    }

    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }

    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
    }

    /*
    public void addVelocity(double x, double y) {
        velocityX += x;
        velocityY += y;
    }
     */
    public void update() {
        if (velocityY<0) {
            positionX += velocityX;
            positionY += -(500/350)*3;
        }else {
            positionX += velocityX;
            positionY += velocityY;
        }
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Sprite1 s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }

    public boolean OnCick(double x, double y) {

        Boolean valor = false;

        if(x > positionX && x < positionX + width && y > positionY && y < positionY + height) {
            valor = true;
        } else
            valor = false;
        return valor;
    }

    public double impactoX()
    {
        if (widthWin/2>positionX){
            return velocityX=(500/350)*3;
        }else {
            return velocityX-=3;
        }
    }

    public double impactoY()  {
        if (heightWin/2>positionY){
            return velocityY=(500/positionY)/3;
        }else {
            return velocityY-=1;
        }
    }


}
