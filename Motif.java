import java.awt.Color;
import java.awt.Shape;
import java.util.Observable;

public class Motif extends Observable {
	private Color color, borderColor;
	private Shape s;

	public Motif (Shape s, Color color, Color borderColor) {
		this.s = s;
		this.color = color;
		this.borderColor = borderColor;
	}

	public Shape getShape() {
		return this.s;
	}

	public Color getColor() {
		return this.color;
	}

	public Color getBorderColor() {
		return this.borderColor;
	}

	public void setColor(Color color) {
		this.color = color;
		setChanged();
		notifyObservers();
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
		setChanged();
		notifyObservers();

	}
}
