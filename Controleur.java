import javax.swing.*;
import java.awt.Color;
import java.awt.Rectangle;

public class Controleur
{
    private Dessin	d;
    private JFrame	f;

    public Controleur()
    {
	f = new JFrame("tutu");
	d = new Dessin();
	f.add(d);
	d.ajouterMotif(new Motif(Color.GREEN, Color.BLUE, new Rectangle(50, 50, 100, 200)));
	f.pack();
	f.setVisible(true);
    }

    public static void main(String args[])
    {
	new Controleur();
    }
}