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
	d.ajouterMotif(new Motif(Color.GREEN, Color.BLUE, new Rectangle(50, 50, 100, 200)));
	f.pack();
	f.setVisible(true);
    }

    private class ControleurDessin implements MouseListener
    {
	public void mouseClicked(MouseEvent e)
	{
	    if (d.getShape(new Point(e.getX(), e.getY())) != null)
		System.out.println("cliclic");
	}

	public void mouseEntered(MouseEvent e)
	{}
	
	public void mouseExited(MouseEvent e)
	{}
	
	public void mousePressed(MouseEvent e)
	{}

	public void mouseReleased(MouseEvent e)
	{}
    }

    private class ControleurMenu implements MouseListener
    {
	public void mouseClicked(MouseEvent e)
	{}

	public void mouseEntered(MouseEvent e)
	{}
	
	public void mouseExited(MouseEvent e)
	{}
	
	public void mousePressed(MouseEvent e)
	{}

	public void mouseReleased(MouseEvent e)
	{}
    }
    
    private class ControleurProprietees implements KeyListener
    {
	public void keyPressed(KeyEvent e)
	{}

	public void keyReleased(KeyEvent e)
	{}
	
	public void keyTyped(KeyEvent e)
	{}
    }
	
    public static void main(String args[])
    {
	new Controleur();
    }
}