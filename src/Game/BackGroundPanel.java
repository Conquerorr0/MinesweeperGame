package Game;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * This class is written to set the background image for the splash screen.
 * @author Conquerorr0
 */
class BackGroundPanel extends JPanel {

    public BufferedImage backgroundImage;

    public BackGroundPanel(BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
