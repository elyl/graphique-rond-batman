import java.awt.Color;
import java.awt.Polygon;

public class Polygone extends Motif {
	public static final int MAX_POINTS = 255;

	private int x[];
	private int y[];
	private int totalPoint;

	public Polygone(Color c) {
		super(c);
		this.x = new int[Polygone.MAX_POINTS];
		this.y = new int[Polygone.MAX_POINTS];
		this.totalPoint = 0;
	}

	public void addPoint(int x, int y) {
		this.x[this.totalPoint] = x;
		this.y[this.totalPoint] = y;
		totalPoint++;
		super.s = new Polygon(this.x, this.y, totalPoint);
		setChanged();
		notifyObservers();
	}

	public void setX() {
	}

	public void setY() {
	}
}