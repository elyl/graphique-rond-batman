import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

public class Dessin {
	int largeur;
	int hauteur;
	List<Motif> motifs;
	
	public Dessin() {
		this.largeur = 800;
		this.hauteur = 600;
	}
	
	public void ajouterMotif() {

	}
	
	public void ajouterMotif(Motif motifSelectionne) {
		
	}
	
	public boolean createObject(String name) {
		return true;
	}
	
	public void dessiner(Graphics2D g) {
		
	}
	
	public Motif effacerMotif(Point point) {
		return null;
	}
	
	public boolean find(Point point) {
		return true;
	}
	
	public int getLargeur() {
		return largeur;
	}
	
	public int getHauteur() {
		return hauteur;
	}
	
	public List<Motif> getMotifs() {
		return motifs;
	}
	
	public void setLargeur(int l) {
		this.largeur = l;
	}
	
	public void setHauteur(int h) {
		this.hauteur = h;
	}
	
	public void setMotifs(List<Motif> motifs) {
		this.motifs = motifs;
	}
	
	public void supprimerMotif(Motif motif) {
		
	}
	
	public void supprimerMotif(int indice) {
		
	}
	
	public String toString() {
		return "";
	}
	
	public void toutDessiner(Graphics2D g) {
		
	}
}
