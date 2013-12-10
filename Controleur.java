import javax.swing.*;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.*;

public class Controleur
{
    private Dessin		d;
    private JFrame		f;

    public Controleur()
    {
	f = new JFrame("tutu");
	d = new Dessin(new ControleurDessin());
	f.setPreferredSize(new Dimension(800, 600));
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.add(d);
	d.ajouterMotif(new Motif(Color.GREEN, new Rectangle(50, 50, 100, 200)));
	d.ajouterMotif(new Motif(Color.PINK, new Rectangle(200, 200, 150, 300)));
	f.pack();
	f.setVisible(true);
    }

    private class ControleurDessin extends KeyMouseListener
    {
	private Motif	s;

	public void mouseClicked(MouseEvent e)
	{
	   Motif	tmp;

	   tmp = d.getShape(new Point(e.getX(), e.getY()));
	    if (tmp != null)
		tmp.setSelected(true);
	    if (s != null && s != tmp)
		s.setSelected(false);
	    s = tmp;
	}

	public void mousePressed(MouseEvent e)
	{}

	public void mouseReleased(MouseEvent e)
	{}

	public void keyTyped(KeyEvent e)
	{
	    if (e.getKeyChar() == 127)
		d.supprimerMotif(s);
	}
    }

    private class ControleurMenu extends MouseAdapter
    {
    }
    
    private class ControleurProprietees extends KeyAdapter
    {}
	
    public static void main(String args[])
    {
	new Controleur();
    }
}