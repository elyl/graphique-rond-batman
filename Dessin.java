import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class Dessin extends JPanel implements Observer {
    private int	width;
    private int height;
    private List<Motif> motifs;
    private RenderingHints hints;
    
    public Dessin(KeyMouseListener c, int width, int height) {
	this.width = width;
	this.height = height;
	this.motifs = new ArrayList<Motif>();
	this.addMouseListener(c);
	this.addKeyListener(c);
	this.addMouseMotionListener(c);
	this.setFocusable(true);
	this.requestFocus();
	this.hints = new RenderingHints(null);
	this.hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	repaint();
    }

    public void paintComponent(Graphics g)
    {
	super.paintComponent(g);
	Graphics2D g2D = (Graphics2D)g;
	
	g2D.addRenderingHints(hints);
	toutDessiner(g2D);
    }
    
    public void update(Observable o, Object arg)
    {
	repaint();
    }
	
    public void ajouterMotif(Motif motifSelectionne) {
	motifs.add(motifSelectionne);
	motifSelectionne.addObserver(this);
	repaint();
    }
    
    /*public boolean createObject(String name) {
	return true;
	}*/
    
    public void dessiner(Graphics2D g, Motif m) {
	g.setColor(m.getColor());
	g.fill(m.getShape());
	g.setColor(m.getBorderColor());
	g.draw(m.getShape());
    }
    
    /*	public Motif effacerMotif(Point point) {
	return this.motifs.remove(point)
	}*/
    
    /*public boolean find(Point point) {
	return true;
    }*/
    
    public int getWidth() {
	return width;
    }
    
    public int getHeight() {
	return height;
    }
    
    public List<Motif> getMotifs() {
	return motifs;
    }
    
    public void setWidth(int l) {
	this.width = l;
    }
    
    public void setHeight(int h) {
	this.height = h;
    }

    public void setMotifs(List<Motif> motifs) {
	this.motifs = motifs;
    }
    
    public void supprimerMotif(Motif motif) {
	motifs.remove(motif);
	repaint();
    }
    
    public void supprimerMotif(int indice) {
	motifs.remove(indice);
	repaint();
    }

    public Motif getShape(Point p)
    {
	Iterator<Motif>	itr;
	Motif		tmp;

	itr = motifs.iterator();
	while (itr.hasNext())
	    {
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
	Iterator<Motif>	itr;

	itr = motifs.iterator();
	while (itr.hasNext())
	    dessiner(g, itr.next());
    }
}
