import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.RenderingHints;
import javax.swing.DefaultListModel;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

@SuppressWarnings("serial")
public class Dessin extends JPanel implements Observer {
	private int width;
	private int height;
	private DefaultListModel motifs;
	private RenderingHints hints;

	public Dessin(KeyMouseListener c, int width, int height) {
	    super();
		this.width = width;
		this.height = height;
		this.motifs = new DefaultListModel();
		this.addMouseListener(c);
		this.addKeyListener(c);
		this.addMouseMotionListener(c);
		this.setFocusable(true);
		this.requestFocus();
		this.hints = new RenderingHints(null);
		this.hints.put(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;

		g2D.addRenderingHints(hints);
		toutDessiner(g2D);
	}

	public void update(Observable o, Object arg) {
		repaint();
	}

	public void ajouterMotif(Motif motifSelectionne) {
		motifs.addElement(motifSelectionne);
		motifSelectionne.addObserver(this);
		repaint();
	}

	public void dessiner(Graphics2D g, Motif m) {
		g.setColor(m.getColor());
		g.fill(m.getShape());
		g.setColor(m.getBorderColor());
		g.draw(m.getShape());
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public DefaultListModel getMotifs() {
		return motifs;
	}

	public void setWidth(int l) {
		this.width = l;
	}

	public void setHeight(int h) {
		this.height = h;
	}

	public void supprimerMotif(Motif motif) {
		motifs.removeElement(motif);
		repaint();
	}

	public void supprimerMotif(int indice) {
		motifs.remove(indice);
		repaint();
	}

	public Motif getShape(Point p) {
		Enumeration itr;
		Motif tmp;

		itr = motifs.elements();
		while (itr.hasMoreElements()) {
		    tmp = (Motif)itr.nextElement();
			if (tmp.getShape().contains(p))
				return (tmp);
		}
		return (null);
	}

	public String toString() {
		return "";
	}

	public void toutDessiner(Graphics2D g) {
		Enumeration itr;

		itr = motifs.elements();
		while (itr.hasMoreElements())
		    dessiner(g, (Motif)itr.nextElement());
	}
}
