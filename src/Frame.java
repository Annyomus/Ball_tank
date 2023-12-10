import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    Dimension dimension;
    Panel panel;
    Frame(String title){
        super(title);

        // Default
        dimension = new Dimension(1920, 1980);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1920, 1980);
        // Adding components
        panel = new Panel();
        add(panel);
        repaint();
        revalidate();

    }
}
