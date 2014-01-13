import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.PointerInfo;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;

public class Controleur {
	private Dessin draw;
	private Proprietees prop;
    private JFrame frame;
    private int curentShapeType;
    private Color currentColor;
    private Motif motive;
    private Motif motiveCopie;
    private JPanel paneSud;
    private JColorChooser colorChooser;
    /* Variables temporaires le temps du dev */
    private ListMotifs	jlist;
    private Logger logger;

    /**
     * Création d'une nouvelle instance de la classe Contrôleur.
     */
	public Controleur() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Handler fh;
		/* LOG */
		try {
			logger = Logger.getLogger("MyLog");
			fh = new FileHandler("log.txt", true);
			logger.addHandler(fh);
			fh.setFormatter(new SimpleFormatter());
		} catch (Exception e) {
			e.printStackTrace();
		}

		frame = new JFrame("Dessin vectoriel");
		draw = new Dessin(new ControleurDessin(), (int) screenSize.getWidth(),
				(int) screenSize.getHeight());
		frame.setPreferredSize(new Dimension((int) screenSize.getWidth(),
				(int) screenSize.getHeight()));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Menu(new ControleurMenu()), BorderLayout.NORTH);
		
		colorChooser = new JColorChooser(Color.CYAN);
		colorChooser.setPreviewPanel(new JPanel());
		colorChooser.setPreferredSize(new Dimension(600, 200));
		
		jlist = new ListMotifs(new ControleurList(), draw.getMotifs());
		prop = new Proprietees(new ControleurProprietees());
		frame.add(prop, BorderLayout.EAST);
		paneSud = new JPanel();
		paneSud.add(colorChooser, BorderLayout.WEST);
		paneSud.add(jlist, BorderLayout.EAST);
		paneSud.setPreferredSize(new Dimension((int)screenSize.getWidth(), 200));
		frame.add(paneSud, BorderLayout.SOUTH);
		frame.add(draw);
		frame.pack();
		frame.setVisible(true);
	}

    public void selectItem(Point p) {
	Motif tmp;
	
	tmp = draw.getShape(p);
	if (tmp != null) {
	    selectItem(tmp);
	    logger.info(tmp.toString() + " selectione"); /* LOG */
	} else
	    logger.info("Aucun objet selectione"); /* LOG */
    }

    public void selectItem(Motif m)
    {
	if (motive != null)
	    {
		motive.setSelected(false);
		logger.info(motive.toString() + " deselectione"); /* LOG */
	    }
	m.setSelected(true);
	prop.updateData(m);
	motive = m;
    }
    
	private class ControleurDessin extends KeyMouseListener {
		private int x;
		private int y;

		/**
		 * Action réalisée lors de l'évènement "clic de la souris".
		 */
		public void mouseClicked(MouseEvent e) {
			draw.requestFocus();
			selectItem(new Point(e.getX(), e.getY()));
			logger.info("Clic: x=" + e.getX() + ", y=" + e.getY()); /* LOG */
		}

		public void mousePressed(MouseEvent e) {
			x = e.getX();
			y = e.getY();
			if (curentShapeType != 0) {
				if (motive != null)
					motive.setSelected(false);
				motive = new Motif(x, y, 0, 0, currentColor, curentShapeType);
				motive.setSelected(true);
				draw.ajouterMotif(motive);
				// selectItem(new Point(x, y));
				prop.updateData(motive);
				logger.info("Creation et ajout de " + motive); /* LOG */
			} else
				selectItem(new Point(x, y));
			System.out.println(motive);
		}

		public void mouseReleased(MouseEvent e) {
			if (motive != null && curentShapeType == 0) {
				motive.setX(motive.getX() + e.getX() - x);
				motive.setY(motive.getY() + e.getY() - y);
			}
			curentShapeType = Motif.NULL;
		}

		public void mouseDragged(MouseEvent e) {
			if (motive != null && curentShapeType == Motif.NULL) {
				motive.setX(motive.getX() + e.getX() - x);
				motive.setY(motive.getY() + e.getY() - y);
				x = e.getX();
				y = e.getY();
				logger.info("Deplacement de " + motive + " aux coordonees " + x
						+ " + y"); /* LOG */
			} else if (motive != null) {
				motive.resize(e.getX() - x, e.getY() - y);
				logger.info("Redimensionement: " + motive);
			}
			logger.info("Deplacement souris: " + e.getX() + " " + e.getY()); /* LOG */
		}
		
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT: motive.moveLeft(); break;
				case KeyEvent.VK_RIGHT: motive.moveRight(); break;
				case KeyEvent.VK_UP: motive.moveUp(); break;
				case KeyEvent.VK_DOWN: motive.moveDown(); break;
				case KeyEvent.VK_DELETE: draw.supprimerMotif(motive); break;
				case KeyEvent.VK_C :
					if(e.isControlDown())
					    motiveCopie = motive.clone();
					break;
				case KeyEvent.VK_V :
					if(e.isControlDown()){
						motiveCopie.setX((int)MouseInfo.getPointerInfo().getLocation().getX());
						motiveCopie.setY((int)MouseInfo.getPointerInfo().getLocation().getY() - 75);
						draw.ajouterMotif(motiveCopie);
						motiveCopie = motiveCopie.clone();
					} 
					break;
			}
			logger.info("Clavier: " + e.getKeyCode());
		}

		public void keyTyped(KeyEvent e) {
		}
		
		public void mouseWheelMoved(MouseWheelEvent e) {
			int n = e.getWheelRotation();
			
			if(n > 0)
				motive.scaleDown();
			else
				motive.scaleUp();
		}
		
	}

	private class ControleurMenu implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentColor = colorChooser.getColor();
			if (e.getActionCommand().equals("Rectangle"))
				curentShapeType = Motif.RECTANGLE;
			else if (e.getActionCommand().equals("Carre"))
			    curentShapeType = Motif.SQUARE;
			else if (e.getActionCommand().equals("Ellipse"))
				curentShapeType = Motif.ELLIPSE;
			else if (e.getActionCommand().equals("Rond"))
			    curentShapeType = Motif.CIRCLE;
			else if (e.getActionCommand().equals("Ligne"))
				curentShapeType = Motif.LINE;
			else if (e.getActionCommand().equals("Exporter"))
				Sauvegarde.export("save", draw.getMotifs());
			else if (e.getActionCommand().equals("Importer"))
			Sauvegarde.importMotif("save", draw);
			draw.requestFocus();
		}
	}

	private class ControleurProprietees extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			Color c;

			logger.info(e.toString());
			if (e.getKeyChar() == 10) {
				c = new Color(Integer.parseInt(prop.getfColor()
						.replace("#", ""), 16));
				motive.resizeAndMove(Integer.parseInt(prop.getfX()),
						Integer.parseInt(prop.getfY()),
						Integer.parseInt(prop.getfWidth()),
						Integer.parseInt(prop.getfHeight()));
				motive.setColor(c);
			}
		}
	}
    
    private class ControleurList extends ListActionListener
    {
	public void valueChanged(ListSelectionEvent e)
	{
	    Motif	tmp;

	    if (!e.getValueIsAdjusting())
		{
		    tmp = (Motif)draw.getMotifs().get(jlist.getSelectedIndex());
		    selectItem(tmp);
		}
	}

	public void actionPerformed(ActionEvent e)
	{
	    int		index;
	    Motif	tmp;

	    index = jlist.getSelectedIndex();
	    if (e.getActionCommand().equals("+") && index > 0)
		{
		    tmp = (Motif)draw.getMotifs().set(index - 1, motive);
		    draw.getMotifs().set(index, tmp);
		    jlist.setSelectedIndex(index - 1);
		}
	    else if (e.getActionCommand().equals("-") && index < jlist.getListSize() - 1)
		{
		    tmp = (Motif)draw.getMotifs().set(index + 1, motive);
		    draw.getMotifs().set(index, tmp);
		    jlist.setSelectedIndex(index + 1);
		}
	    draw.repaint();
	}
    }

	public static void main(String args[]) {
		new Controleur();
	}
}
