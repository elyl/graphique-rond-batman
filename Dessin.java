import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;

import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

public class Dessin extends JPanel implements Observer {
	private int width;
	private int height;
	private DefaultListModel motifs;
	private RenderingHints hints;

	/**
	 * Création d'une nouvelle instance de la classe Dessin.
	 * 
	 * @param c Groupe d'écoutes
	 * @param width Largeur
	 * @param height Hauteur
	 */
	public Dessin(KeyMouseListener c, int width, int height) {
	    super();
	    this.setBackground(Color.WHITE);
	    this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.width = width;
		this.height = height;
		this.motifs = new DefaultListModel();
		this.addMouseListener(c);
		this.addKeyListener(c);
		this.addMouseMotionListener(c);
		this.addMouseWheelListener(c);
		this.setFocusable(true);
		this.requestFocus();
		this.hints = new RenderingHints(null);
		this.hints.put(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		repaint();
	}

	/**
	 * Permet de dessiner de nouveaux composants graphiques.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;

		g2D.addRenderingHints(hints);
		toutDessiner(g2D);
	}

	/**
	 * Permet de mettre à jour la vue.
	 */
	public void update(Observable o, Object arg) {
		repaint();
	}

	/**
	 * Permet d'ajouter le motif sélectionné à la liste de motifs.
	 * 
	 * @param motifSelectionne Motif sélectionné
	 */
	public void ajouterMotif(Motif motifSelectionne) {
		motifs.addElement(motifSelectionne);
		motifSelectionne.addObserver(this);
		repaint();
	}

	/**
	 * Permet de dessiner un nouveau motif.
	 * 
	 * @param m Motif à dessiner
	 */
	public void dessiner(Graphics2D g, Motif m) {
		g.setColor(m.getColor());
		g.fill(m.getShape());
		g.setColor(m.getBorderColor());
		g.draw(m.getShape());
	}

	/**
	 * Retourne la largeur du motif courant.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Retourne la hauteur du motif courant.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Retourne la liste des motifs composants le dessin.
	 * @return Liste des motifs
	 */
	public DefaultListModel getMotifs() {
		return motifs;
	}

	/**
	 * Remplace la largeur du motif courant par la valeur passée en paramètre.
	 * @param width Nouvelle largeur
	 */
	public void setWidth(int width) {
		this.width = width;
	}
 
	/**
	 * Remplace la hauteur du motif courant par la valeur passée en paramètre.
	 * @param height Nouvelle hauteur
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Supprime le motif passé en paramètre.
	 * @param motif Motif à supprimer
	 */
	public void supprimerMotif(Motif motif) {
		motifs.removeElement(motif);
		repaint();
	}

	/**
	 * Supprime le motif situé à l'index passé en paramètre.
	 * @param indice Indice du motif à supprimer
	 */
	public void supprimerMotif(int indice) {
		motifs.remove(indice);
		repaint();
	}

	/**
	 * Retourne le motif contenant le point passé en paramètre.
	 * @param p Point
	 * @return Motif contenant p
	 */
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

	/**
	 * Dessine l'intégralité des motifs.
	 */
	public void toutDessiner(Graphics2D g) {
		Enumeration itr;

		itr = motifs.elements();
		while (itr.hasMoreElements())
		    dessiner(g, (Motif)itr.nextElement());
	}
}
