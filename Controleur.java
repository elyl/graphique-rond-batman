import javax.swing.*;
import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.logging.*;

public class Controleur
{
    private Dessin		d;
    private JFrame		f;
    private int			s;
    private Color		currentColor;

    /* Variables temporaires le temps du dev*/
    private JFrame		f2;
    private Logger		logger;

    public Controleur()
    {
	Handler	fh;
	/* LOG */
	try
	    {
		logger = Logger.getLogger("MyLog");
		fh = new FileHandler("log.txt", true);
		logger.addHandler(fh);
		fh.setFormatter(new SimpleFormatter());
	    }
	catch (Exception e)
	    { e.printStackTrace(); }

	f = new JFrame("Dessin vectoriel");
	d = new Dessin(new ControleurDessin());
	f.setPreferredSize(new Dimension(800, 600));
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.add(d);
	d.ajouterMotif(new Motif(50, 50, Color.GREEN));
	d.ajouterMotif(new Motif(200, 200, Color.PINK));
	f.pack();
	f.setVisible(true);

	/* DEV */
	f2 = new JFrame("Menu");
	f2.add(new Menu(new ControleurMenu()));
	f2.pack();
	f2.setVisible(true);
	currentColor = Color.PINK;
	
    }

    private class ControleurDessin extends KeyMouseListener
    {
	private Motif	m;
	private int	x;
	private int	y;

	public void mouseClicked(MouseEvent e)
	{
	    selectItem(new Point(e.getX(), e.getY()));
	    logger.info("Clic: x=" + e.getX() + ", y=" + e.getY()); /* LOG */
	}

	public void selectItem(Point p)
	{
	   Motif	tmp;

	   tmp = d.getShape(p);
	    if (tmp != null)
		{
		    tmp.setSelected(true);
		    logger.info(tmp.toString() + " selectione"); /* LOG */
		}
	    else
		logger.info("Aucun objet selectione"); /* LOG */
	    if (m != null && m != tmp)
		{
		    m.setSelected(false);
		    logger.info(m.toString() + " deselectione"); /* LOG */
		}
	    m = tmp;
	}

	public void mousePressed(MouseEvent e)
	{
	    x = e.getX();
	    y = e.getY();
	    if (s != 0)
		{
		    m = new Motif(x, y, 0, 0, currentColor, s);
		    d.ajouterMotif(m);
		}
	    else
		selectItem(new Point(e.getX(), e.getY()));
	}

	public void mouseReleased(MouseEvent e)
	{
	    if (m != null && s == 0)
		{
		    m.setX(m.getX() + e.getX() - x);
		    m.setY(m.getY() + e.getY() - y);
		}
	    s = Motif.NULL;
	}

	public void mouseDragged(MouseEvent e)
	{
	    if (m != null && s == Motif.NULL)
		{
		    m.setX(m.getX() + e.getX() - x);
		    m.setY(m.getY() + e.getY() - y);
		    x = e.getX();
		    y = e.getY();
		}
	    else if (m != null)
		{
		    m.resize(e.getX() - x, e.getY() - y);
		}
	}

	public void keyTyped(KeyEvent e)
	{
	    System.out.println(e.getKeyCode());
	    if (e.getKeyChar() == 127)
		d.supprimerMotif(m);
	    logger.info("Clavier: " + e.getKeyChar());
	}
    }

    private class ControleurMenu implements ActionListener
    {
	public void actionPerformed(ActionEvent e)
	{
	    if (e.getActionCommand().equals("Rectangle"))
		s = Motif.RECTANGLE;
	    else if (e.getActionCommand().equals("Ellipse"))
		s = Motif.ELLIPSE;
	}
    }
    
    private class ControleurProprietees extends KeyAdapter
    {}
	
    public static void main(String args[])
    {
	new Controleur();
    }
}