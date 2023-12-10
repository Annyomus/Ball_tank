
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel {
    Ball ball;
    Rectangle wall;
    Voice_Detection v;
    Timer timer = new Timer(2, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            collision();
            increase_gravity_Balls();
            repaint();

        }
    });

    Panel() {
        v = new Voice_Detection();

        ball = new Ball(0, 0, 30, 30, 80);
        setBackground(Color.BLACK);
        timer.start();
        wall = new Rectangle(0, 950, 1920, 180);
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        ball.draw(g2d);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(wall.x, wall.y, wall.width, wall.height);
        collision();

    }

    public void collision() {

        for (int i = 0; i < ball.getBalls().length; i++) {
            if (ball.getBalls()[i].intersects(wall)) {
                ball.getBalls()[i].falling = false;
            }
        }
    }

    public void increase_gravity_Balls() {
        if (v.measure() > 0) {
            for (Ball ball : ball.balls) {
                ball.gravity += 0.1f;
            }

        }
            if (v.measure() < 0 && ball.balls[0].gravity > 1.5f) {
                for (Ball ball : ball.balls) {
                    ball.gravity -= 0.1f;
                }
            }


        }

}
