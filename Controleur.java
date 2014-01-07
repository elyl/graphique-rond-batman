import javax.swing.*;
import javax.swing.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.logging.*;

public class Controleur
{
    private Dessin		d;
    private Proprietees		prop;
    private JFrame		f;
    private int			s;
    private Color		currentColor;
    private Motif		m;
    private JColorChooser colorChooser; //Var pour la palette
    /* Variables temporaires le temps du dev*/
    private JFrame		f2;
    private Logger		logger;

    public Controleur()
    {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
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
	d = new Dessin(new ControleurDessin(), (int)screenSize.getWidth(), (int)screenSize.getHeight());
	f.setPreferredSize(new Dimension((int)screenSize.getWidth(), (int)screenSize.getHeight()));
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.add(new Menu(new ControleurMenu()), BorderLayout.NORTH);
	//colorChooser
	colorChooser = new JColorChooser();
	colorChooser.setPreviewPanel(new JPanel());
	colorChooser.setPreferredSize(new Dimension(500, 150));
	//
	f.add(colorChooser, BorderLayout.SOUTH);
	d.ajouterMotif(new Motif(50, 50, -50, -50, Color.GREEN));
	d.ajouterMotif(new Motif(200, 200, Color.PINK));
	f.add(d);
	f.pack();
	f.setVisible(true);

	/* DEV */
	prop = new Proprietees(new ControleurProprietees());
	f2 = new JFrame("Menu");
	f2.setPreferredSize(new Dimension(400, 200));
	f2.add(prop);
	f2.pack();
	f2.setVisible(true);
	currentColor = Color.WHITE;
	
    }

    private class ControleurDessin extends KeyMouseListener
    {
	private int	x;
	private int	y;

	public void mouseClicked(MouseEvent e)
	{
	    d.requestFocus();
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
		    prop.updateData(tmp);
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
		    if (m != null)
			m.setSelected(false);
		    m = new Motif(x, y, 0, 0, currentColor, s);
		    m.setSelected(true);
		    d.ajouterMotif(m);
		    //selectItem(new Point(x, y));
		    prop.updateData(m);
		    logger.info("Creation et ajout de " + m); /* LOG */
		}
	    else
		selectItem(new Point(x, y));
		System.out.println(m);
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
	    logger.info("Clavier: " + e.getKeyChar());
	}
    }

    private class ControleurMenu implements ActionListener
    {
	public void actionPerformed(ActionEvent e)
	{
	    JComboBox src;
	    currentColor=colorChooser.getColor();
	    if (e.getActionCommand().equals("Rectangle"))
		s = Motif.RECTANGLE;
	    else if (e.getActionCommand().equals("Ellipse"))
		s = Motif.ELLIPSE;
	    else if (e.getActionCommand().equals("Droite"))
		s = Motif.LINE;
	    /*else if (e.getActionCommand().equals("comboBoxChanged")){
	    	src = (JComboBox) e.getSource();
	    	currentColor = Menu.COLORLIST[src.getSelectedIndex()];
	    	
	    }*/
	    d.requestFocus();
	}
    }
    
    private class ControleurProprietees extends KeyAdapter
    {
	public void keyTyped(KeyEvent e)
	{
	    Color c;

	    logger.info(e.toString());
	    if (e.getKeyChar() == 10)
		{
		    c = new Color(Integer.parseInt(prop.getfColor().replace("#", ""), 16));
		    m.resizeAndMove(Integer.parseInt(prop.getfX()), Integer.parseInt(prop.getfY()), Integer.parseInt(prop.getfWidth()), Integer.parseInt(prop.getfHeight()));
		    m.setColor(c);
		}
	}
    }
	
    public static void main(String args[])
    {
	new Controleur();
    }
}
