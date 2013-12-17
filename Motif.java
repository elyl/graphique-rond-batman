import java.awt.Color;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.geom.*;
import java.util.Observable;

public class Motif extends Observable {
    public static final int	NULL = 0;
    public static final int	RECTANGLE = 1;
    public static final int	ELLIPSE = 2;
    public static final int	LINE = 3;

    private Color	color, borderColor;
    private Shape	s;
    private boolean	selected;
    private int		x;
    private int		y;
    private int		width;
    private int		height;
    private int		shapeType;

    public Motif(int x, int y, Color color)
    {
	this(x, y, 50, 50, color);
    }

    public Motif(int x, int y, int width, int height, Color color)
    {
	this(x, y, width, height, color, Motif.RECTANGLE);
    }

    public Motif(int x, int y, int width, int height, Color color, int shape)
    {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.color = color;
	this.borderColor = color;
	this.shapeType = shape;
	initShape();
    }

    private void initShape()
    {
	switch (shapeType)
	    {
	    case Motif.RECTANGLE:
		this.s = new Rectangle(this.x, this.y, this.width, this.height);
		break;
	    case Motif.ELLIPSE:
		this.s = new Ellipse2D.Double(this.x, this.y, this.width, this.height);
		break;
	    }
    }

    public Motif()
    {
	this(0, 0, 50, 50, Color.BLUE);
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

    public void setWidth(int width)
    {
	this.width = width;
	updateShape();
    }
    
    public void setHeight(int height)
    {
	this.height = height;
	updateShape();
    }

    public void resize(int width, int height)
    {
	this.height = height;
	this.width = width;
	updateShape();
    }

    public void resizeAndMove(int x, int y, int width, int height)
    {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
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

    public int getWidth()
    {
	return (this.width);
    }

    public int getHeight()
    {
	return (this.height);
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
	initShape();
	setChanged();
	notifyObservers();
    }

    public String toString()
    {
	return (color.toString() + " " + s.toString());
    }
}