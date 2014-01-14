import java.awt.Color;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Observable;

public class Motif extends Observable implements Cloneable {
	public static final int NULL = 0;
	public static final int RECTANGLE = 1;
	public static final int ELLIPSE = 2;
	public static final int LINE = 3;
	public static final int POLYGON = 4;
	public static final int SQUARE = 5;
	public static final int CIRCLE = 6;

	public static final int MOVE_DISTANCE = 10;

	protected static int numero = 0;

	protected Color color, borderColor;
	protected Shape s;
	protected boolean selected;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int shapeType;
	protected String name;

	/**
	 * Créé une nouvelle instance de la classe Motif avec la couleur passée en paramètre.
	 * @param c Couleur
	 */
	public Motif(Color c) {
		this(0, 0, 0, 0, c, Motif.POLYGON);
	}

	/**
	 * Créé une nouvelle instance de la classe Motif avec la couleur, l'abscisse et l'ordonnée passées en paramètre.
	 * @param x Abscisse
	 * @param y Ordonnée
	 * @param color Couleur
	 */
	public Motif(int x, int y, Color color) {
		this(x, y, 50, 50, color);
	}
	
	/**
	 * Créé une nouvelle instance de la classe Motif avec la couleur, l'abscisse, l'ordonnée, la hauteur et la largeur passées en paramètre.
	 * @param x Abscisse
	 * @param y Ordonnée
	 * @param width Largeur
	 * @param height Hauteur
	 * @param color Couleur
	 */
	public Motif(int x, int y, int width, int height, Color color) {
		this(x, y, width, height, color, Motif.RECTANGLE);
	}

	/**
	 * Créé une nouvelle instance de la classe Motif avec la couleur, l'abscisse, l'ordonnée, la hauteur, largeur et la forme passées en paramètre.
	 * @param x Abscisse
	 * @param y Ordonnée
	 * @param width Largeur
	 * @param height Hauteur
	 * @param color Couleur
	 * @param shape Forme
	 */
	public Motif(int x, int y, int width, int height, Color color, int shape) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.borderColor = color;
		this.shapeType = shape;
		this.name = "Figure #" + numero++;
		initShape();
	}

	/**
	 * Initialise le motif courant.
	 */
	protected void initShape() {
		int tmpX;
		int tmpY;
		int tmpW;
		int tmpH;

		this.height = (this.shapeType == Motif.SQUARE || this.shapeType == Motif.CIRCLE) ? this.width
				: this.height;
		tmpX = (this.width < 0) ? this.width + this.x : this.x;
		tmpW = (this.width < 0) ? this.width * -1 : this.width;
		tmpY = (this.height < 0) ? this.y + height : this.y;
		tmpH = (this.height < 0) ? this.height * -1 : this.height;
		switch (shapeType) {
		case Motif.CIRCLE:
		case Motif.ELLIPSE:
			this.s = new Ellipse2D.Double(tmpX, tmpY, tmpW, tmpH);
			break;
		case Motif.LINE:
			this.s = new Line2D.Double(this.x, this.y, this.x + this.width,
					this.y + this.height);
			break;
		case Motif.POLYGON:
			this.s = new Polygon();
			break;
		case Motif.SQUARE:
		case Motif.RECTANGLE:
		default:
			this.s = new Rectangle(tmpX, tmpY, tmpW, tmpH);
			break;
		}
	}

	/**
	 * Sélectionne le motif courant.
	 * @param selected Indicateur de sélection
	 */
	public void setSelected(boolean selected) {
		if (selected == true)
			this.borderColor = Color.BLACK;
		else
			this.borderColor = this.color;
		if (selected != this.selected) {
			setChanged();
			notifyObservers();
		}
		this.selected = selected;
	}

	/**
	 * Remplace l'abscisse du motif courant par l'abscisse passée en paramètre.
	 * @param x Abscisse
	 */
	public void setX(int x) {
		this.x = x;
		updateShape();
	}

	/**
	 * Remplace l'ordonnée du motif courant par l'ordonnée passée en paramètre.
	 * @param y Ordonnée
	 */
	public void setY(int y) {
		this.y = y;
		updateShape();
	}

	/**
	 * Remplace la largeur du motif courant par la largeur passée en paramètre.
	 * @param width Largeur
	 */
	public void setWidth(int width) {
		this.width = width;
		updateShape();
	}

	/**
	 * Remplace la hauteur du motif courant par la hauteur passée en paramètre.
	 * @param height Hauteur
	 */
	public void setHeight(int height) {
		this.height = height;
		updateShape();
	}

	/**
	 * Remplace la largeur et la hauteur du motif courant par les largeur et hauteur passées en paramètre.
	 * @param width Largeur
	 * @param height Hauteur
	 */
	public void resize(int width, int height) {
		this.height = height;
		this.width = width;
		updateShape();
	}

	/**
	 * Remplace la position et la taille du motif sélectionné par ceux passées en paramètres.
	 * @param x Abscisse
	 * @param y Ordonnée
	 * @param width Largeur
	 * @param height Hauteur
	 */
	public void resizeAndMove(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		updateShape();
	}

	/**
	 * Retourne l'abscisse du motif courant.
	 * @return Abscisse
	 */
	public int getX() {
		return (this.x);
	}

	/**
	 * Retourne l'ordonnée du motif courant.
	 * @return Ordonnée
	 */
	public int getY() {
		return (this.y);
	}

	/**
	 * Retourne la largeur du motif courant.
	 * @return Largeur
	 */
	public int getWidth() {
		return (this.width);
	}

	/**
	 * Retourne la hauteur du motif courant.
	 * @return Hauteur
	 */
	public int getHeight() {
		return (this.height);
	}

	/**
	 * Retourne la forme du motif courant.
	 * @return Forme
	 */
	public Shape getShape() {
		return this.s;
	}

	/**
	 * Retourne la couleur du motif courant.
	 * @return Couleur
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * Retourne la couleur de la bordure du motif courant.
	 * @return Couleur de bordure
	 */
	public Color getBorderColor() {
		return this.borderColor;
	}

	/**
	 * Retourne le type de forme du motif courant.
	 * @return Type de forme
	 */
	public int getShapeType() {
		return this.shapeType;
	}

	/**
	 * Remplace la couleur du motif courant par la couleur passée en paramètre.
	 * @param color Nouvelle couleur
	 */
	public void setColor(Color color) {
		this.color = color;
		setChanged();
		notifyObservers();
	}

	/**
	 * Remplace la couleur de la bordure du motif courant par la couleur passée en paramètre.
	 * @param borderColor Nouvelle couleur de bordure
	 */
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
		setChanged();
		notifyObservers();
	}

	/**
	 * Remplace le nom du motif courant par le nom passé en paramètre.
	 * @param name Nouveau nom
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retourne le nom du motif courant.
	 * @return Nom
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Met à jour le motif courant.
	 */
	public void updateShape() {
		initShape();
		setChanged();
		notifyObservers();
	}

	/**
	 * Déplace le motif courant vers la gauche.
	 */
	public void moveLeft() {
		this.setX(this.x - Motif.MOVE_DISTANCE);
	}

	/**
	 * Déplace le motif courant vers la droite.
	 */
	public void moveRight() {
		this.setX(this.x + Motif.MOVE_DISTANCE);
	}

	/**
	 * Déplace le motif courant vers le haut.
	 */
	public void moveUp() {
		this.setY(this.y - Motif.MOVE_DISTANCE);
	}

	/**
	 * Déplace le motif courant vers le bas.
	 */
	public void moveDown() {
		this.setY(this.y + Motif.MOVE_DISTANCE);
	}

	/**
	 * Retourne le motif au format texte.
	 */
	public String toString() {
		return name;
	}

	/**
	 * Rétrécit le motif courant.
	 */
	public void scaleDown() {
		this.width--;
		this.height--;
	}

	/**
	 * Agrandit le motif courant.
	 */
	public void scaleUp() {
		this.width++;
		this.height++;

	}

	/**
	 * Clone le motif courant.
	 */
	public Motif clone() {
		return (new Motif(this.x, this.y, this.width, this.height, this.color,
				this.shapeType));
	}
}
