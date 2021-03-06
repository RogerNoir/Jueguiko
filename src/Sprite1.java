import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

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
    int vel = 1;

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

    public void update() {

        if (velocityY<0) {
            positionX += velocityX+vel;
            positionY += -(500/350)*3-vel;
        }else {
            positionX += velocityX+vel;
            positionY += velocityY+vel;
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


    public double impactoX() {
        if (widthWin/2>positionX){
            return velocityX=(500/350)*2;
        }else {
            return velocityX-=2;
        }
    }

    public double impactoY()  {
        if (heightWin/2>positionY){
            return velocityY=(500/positionY)/2;
        }else {
            return velocityY-=1;

        }
    }

    public boolean containsPoint(double x,double y) {
        if (x >= this.positionX && y >= this.positionY && x <= (this.positionX+this.width) && y <= (this.positionY+this.height)){
            setPosition(0,700 * Math.random() + 1);
            return true;
        } else return false;
    }
}
