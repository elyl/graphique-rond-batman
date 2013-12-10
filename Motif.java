import java.awt.Color;
import java.awt.Shape;
import java.awt.Rectangle;
import java.util.Observable;

public class Motif extends Observable {
    private Color	color, borderColor;
    private Shape	s;
    private boolean	selected;
    private int		x;
    private int		y;

    public Motif(int x, int y, Color color)
    {
	this.x = x;
	this.y = y;
	this.color = color;
	this.borderColor = color;
	this.s = new Rectangle(x, y, 100, 200);
    }

    public void setSelected(boolean selected)
    {
	if (selected == true)
	    this.borderColor = Color.BLACK;
	else
	    this.borderColor = this.color;
	if (selected != this.selected)
	    {
		setChanged();
		notifyObservers();
	    }
	this.selected = selected;
    }

    public void setX(int x)
    {
	this.x = x;
	updateShape();
    }

    public void setY(int y)
    {
	this.y = y;
	updateShape();
    }

    public int getX()
    {
	return (this.x);
    }

    public int getY()
    {
	return (this.y);
    }

    public Shape getShape()
    {
	return this.s;
    }

    public Color getColor()
    {
	return this.color;
    }

    public Color getBorderColor()
    {
	return this.borderColor;
    }

    public void setColor(Color color)
    {
	this.color = color;
	setChanged();
	notifyObservers();
    }

    public void setBorderColor(Color borderColor)
    {
	this.borderColor = borderColor;
	setChanged();
	notifyObservers();
    }

    public void updateShape()
    {
	this.s = new Rectangle(x, y, 100, 200);
	setChanged();
	notifyObservers();
    }
}