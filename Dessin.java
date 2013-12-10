import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import java.awt.event.*;

public class Dessin extends JPanel implements Observer {
	private int width;
	private int height;
	private List<Motif> motives;

	public Dessin(MouseListener c) {
		this.width = 800;
		this.height = 600;
		this.motives = new ArrayList<Motif>();
		this.addMouseListener(c);
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;

		toutDessiner(g2D);
	}

	public void update(Observable o, Object arg) {
		repaint();
	}

	public void ajouterMotif(Motif motifSelectionne) {
		motives.add(motifSelectionne);
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

	public List<Motif> getMotifs() {
		return motives;
	}

	public void setWidth(int l) {
		this.width = l;
	}

	public void setHeight(int h) {
		this.height = h;
	}

	public void setMotifs(List<Motif> motifs) {
		this.motives = motifs;
	}

	public void supprimerMotif(Motif motif) {
		motives.remove(motif);
	}

	public void supprimerMotif(int indice) {
		motives.remove(indice);
	}

	public Motif getShape(Point p) {
		Iterator<Motif> itr;
		Motif tmp;

		itr = motives.iterator();
		while (itr.hasNext()) {
			tmp = itr.next();
			if (tmp.getShape().contains(p))
				return (tmp);
		}
		return (null);
	}

	public String toString() {
		return "";
	}

	public void toutDessiner(Graphics2D g) {
		Iterator<Motif> itr;

		itr = motives.iterator();
		while (itr.hasNext())
			dessiner(g, itr.next());
	}
}
