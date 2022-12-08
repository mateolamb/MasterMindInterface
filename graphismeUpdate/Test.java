import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Test
{
    private JLabel label;


    private static List<Label> InstanceList = new ArrayList<>(); // liste des instances de la classe, pour les afficher simplement.

    public Test(JLabel label){
        this.label=label;
        draw();
    }

    public void setText(String text) {
        Fenetre.actu();
        this.label.setText(text);
    }

    public String getText() { // recupere la couleur (pratique pour l'affichage)
        return this.label.getText();
    }


    public static Label getInstance(int id) { // methode de classe permettant de recuperer l'instance numero 'id' (sans modifier la liste)
        return InstanceList.get(id);
    }

    public static int numberInstances() { // utile pour afficher tous les points crees : boucle de id=0 a id=Point.numerInstances
        return InstanceList.size();
    }

    private void draw() { // permet d'actualiser l'affichage lors d'un changement de couleur
        Fenetre.getInstance().actualiserAffichage();
    }

    public JLabel getLabel() {
        return label;
    }


}

