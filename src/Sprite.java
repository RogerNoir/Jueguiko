import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class Sprite
{
    private Image image;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;

    public Sprite()
    {
        positionX = 0;
        positionY = 0;
        velocityX = 0;
        velocityY = 0;
    }

    public void setImage(Image i)
    {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage(String filename)
    {
        Image i = new Image(filename);
        setImage(i);
    }

    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
    }

    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }


   public void update()
    {
        positionX += velocityX;
        positionY += velocityY;
   }

    public double impactoX()
    {
        if(positionX < 0)
        {
            if(velocityX < 0)
            {
                velocityX = (velocityX/velocityX)+2;
                return velocityX;

            } else return velocityX;
        }

        if (positionX > 800 - width)
        {
            if(velocityX > 0)
            {
                velocityX = -velocityX;
                return velocityX;

            } else return velocityX;
        }

        return velocityX;
    }


    public double impactoY()
    {
        if(positionY < 0)
        {
            if(velocityY < 0)
            {
                velocityY = (velocityY/velocityY)+2;
                return velocityY;

            } else return velocityY;
        }

        if (positionY > 600 - width)
        {
            if(velocityY > 0)
            {
                velocityY = -velocityY;
                return velocityY;

            } else return velocityY;
        }

        return velocityY;
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );

    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }

    public String toString()
    {
        return " Position: [" + positionX + "," + positionY + "]"
                + " Velocity: [" + velocityX + "," + velocityY + "]";
    }
}