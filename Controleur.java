import javax.swing.*;
import java.awt.Color;
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
	d.ajouterMotif(new Motif(50, 50, Color.GREEN));
	d.ajouterMotif(new Motif(200, 200, Color.PINK));
	f.pack();
	f.setVisible(true);
    }

    private class ControleurDessin extends KeyMouseListener
    {
	private Motif	s;
	private int	x;
	private int	y;

	public void mouseClicked(MouseEvent e)
	{
	    selectItem(new Point(e.getX(), e.getY()));
	}
	public void selectItem(Point p)
	{
	   Motif	tmp;

	   tmp = d.getShape(p);
	    if (tmp != null)
		tmp.setSelected(true);
	    if (s != null && s != tmp)
		s.setSelected(false);
	    s = tmp;
	}

	public void mousePressed(MouseEvent e)
	{
	    selectItem(new Point(e.getX(), e.getY()));
	    x = e.getX();
	    y = e.getY();
	}

	public void mouseReleased(MouseEvent e)
	{
	    if (s != null)
		{
		    s.setX(s.getX() + e.getX() - x);
		    s.setY(s.getY() + e.getY() - y);
		}
	}

	public void mouseDragged(MouseEvent e)
	{
	    if (s != null)
		{
		    s.setX(s.getX() + e.getX() - x);
		    s.setY(s.getY() + e.getY() - y);
		    x = e.getX();
		    y = e.getY();
		}
	}

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