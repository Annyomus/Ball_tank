import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Ball extends Rectangle {

     float acceleration = 1f;
    float gravity = 1f;

    Ball[] balls;
    Random rnd;
    boolean falling = true;
    Ball(int x, int y, int width, int height, int ballsCount){
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        balls = new Ball[ballsCount];
        rnd = new Random();
        Spawn_Balls();
    }
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);

        for (Ball ball : balls) {
            g2d.fillOval(ball.x, ball.y, width, height);
            ball.falling(ball);
        }
    }
    public void Spawn_Balls(){
        for (int i = 0; i < balls.length; i++) {
           x = rnd.nextInt(1800);
           y = rnd.nextInt(800);
           balls[i] = new Ball(x, y, width, height, 0);
        }
    }
    public void falling(Ball ball){
        if (falling){
                ball.y += (int) acceleration;
                acceleration +=  1f;

        }
        else{
            acceleration =  acceleration / 2 ;
            ball.y -= (int) (gravity * acceleration);
            if (acceleration < 1f){
                acceleration = 0.1f;
                ball.falling = true;
            }
        }
    }



    public Ball[] getBalls() {
        return balls;
    }
}
