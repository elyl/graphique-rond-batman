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
    private boolean		s;
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
	    if (s)
		{
		    m = new Motif(x, y, 0, 0, currentColor);
		    d.ajouterMotif(m);
		    logger.info("Creation et ajout de " + m); /* LOG */
		}
	    else
		selectItem(new Point(e.getX(), e.getY()));
	}

	public void mouseReleased(MouseEvent e)
	{
	    if (m != null && !s)
		{
		    m.setX(m.getX() + e.getX() - x);
		    m.setY(m.getY() + e.getY() - y);
		}
	    s = false;
	}

	public void mouseDragged(MouseEvent e)
	{
	    if (m != null && !s)
		{
		    m.setX(m.getX() + e.getX() - x);
		    m.setY(m.getY() + e.getY() - y);
		    x = e.getX();
		    y = e.getY();
		    logger.info("Deplacement de " + m + " aux coordonees " + x + " + y"); /* LOG */
		}
	    else if (m != null)
		{
		    m.resize(e.getX() - x, e.getY() - y);
		    logger.info("Redimensionement: " + m);
		}
	    logger.info("Deplacement souris: " + e.getX() + " " + e.getY()); /* LOG */
	}

	public void keyTyped(KeyEvent e)
	{
	    if (e.getKeyChar() == 127)
		d.supprimerMotif(m);
	}
    }

    private class ControleurMenu implements ActionListener
    {
	public void actionPerformed(ActionEvent e)
	{
	    s = true;
	}
    }
    
    private class ControleurProprietees extends KeyAdapter
    {}
	
    public static void main(String args[])
    {
	new Controleur();
    }
}