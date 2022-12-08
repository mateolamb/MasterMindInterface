
import javax.swing.*;
import java.awt.*;

/* Auteur principal : Loic Wisniewski ; date de creation : decembre 2014 ; mise a jour : 1 decembre 2014  */

public class Panneau extends JPanel {

    public void paintComponent(Graphics g) {

        for (int i = 0; i < Trait.numberInstances(); i++) {
            Trait t = Trait.getInstance(i);
            int[] positions = t.get2Position();
            g.drawLine(positions[0], positions[2], positions[1], positions[3]);
        }

        for (int i = 0; i < Point.numberInstances(); i++) {
            Point p = Point.getInstance(i);
            g.setColor(p.getColor());
            g.fillOval(p.getPosx(), p.getPosy(), 50, 50);
        }




    }
}
