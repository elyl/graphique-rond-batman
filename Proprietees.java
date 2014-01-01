import java.util.Observer;
import java.util.Observable;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Proprietees extends JPanel implements Observer
{
    private JTextField	f_Color;
    private JTextField	f_X;
    private JTextField	f_Y;
    private JTextField	f_Width;
    private JTextField	f_Height;
    private JLabel	lbl;
    private Motif	motif;

    public Proprietees(KeyListener c)
    {
	setLayout(new GridLayout(7, 2));
	add(new JLabel("Type"));
	lbl = new JLabel("Rectangle");
	add(lbl);
	add(new JLabel("Color"));
	f_Color = new JTextField();
	f_Color.addKeyListener(c);
	add(f_Color);
	add(new JLabel("X"));
	f_X = new JTextField();
	f_X.addKeyListener(c);
	add(f_X);
	add(new JLabel("Y"));
	f_Y = new JTextField();
	f_Y.addKeyListener(c);
	add(f_Y);
	add(new JLabel("Width"));
	f_Width = new JTextField();
	f_Width.addKeyListener(c);
	add(f_Width);
	add(new JLabel("Height"));
	f_Height = new JTextField();
	f_Height.addKeyListener(c);
	add(f_Height);
    }

    public void updateData(Motif m)
    {
	updateData(m.getX(), m.getY(), m.getWidth(), m.getHeight(), m.getColor());
	if (motif != null)
	    motif.deleteObserver(this);
	motif = m;
	m.addObserver(this);
    }

    public void updateData(int x, int y, int width, int height, Color color)
    {
	f_X.setText(new Integer(x).toString());
	f_Y.setText(new Integer(y).toString());
	f_Width.setText(new Integer(width).toString());
	f_Height.setText(new Integer(height).toString());
	f_Color.setText("#" + (Integer.toHexString(color.getRed()) + Integer.toHexString(color.getGreen()) + Integer.toHexString(color.getBlue())).toUpperCase());
	repaint();
    }

    public void update(Observable obs, Object o)
    {
	updateData((Motif)obs);
    }

    public String getfX()
    {
	return (f_X.getText());
    }

    public String getfY()
    {
	return (f_Y.getText());
    }

    public String getfWidth()
    {
	return (f_Width.getText());
    }

    public String getfHeight()
    {
	return (f_Height.getText());
    }

    public String getfColor()
    {
	return (f_Color.getText());
    }
}
