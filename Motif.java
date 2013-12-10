import java.awt.Color;
import java.awt.Shape;
import java.util.Observable;

public class Motif extends Observable {
    private Color	color, borderColor;
    private Shape	s;
    private boolean	selected;

    public Motif(Color color, Shape s)
    {
	this.s = s;
	this.color = color;
	this.borderColor = color;
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
}
