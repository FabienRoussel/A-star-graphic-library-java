package Vue;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Fabien on 03/05/2017
 */
public class Panneau extends JPanel {
    private int posX = 0;
    private int posY = 0;

    public void paintComponent(Graphics g){
        g.setColor(new Color(0,0,100));
        g.setFont(new Font("Cooper Black", Font.PLAIN, 36));
        g.drawString("Labyrinthe", (int)(getToolkit().getScreenSize().getWidth()/4)-g.getFontMetrics().stringWidth("Labyrinthe")/2, 45);
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
