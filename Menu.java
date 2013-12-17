import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Arrays;

public class Menu extends JPanel
{
    public static final Color COLORLIST[] = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};

    public Menu(ListActionListener e)
    {
	JButton	tmp;
	JList<String>	l;

	setPreferredSize(new Dimension(300, 300));
	setLayout(new GridLayout(4, 1));
	tmp = new JButton("Rectangle");
	tmp.addActionListener(e);
	add(tmp);
	tmp = new JButton("Droite");
	tmp.addActionListener(e);
	add(tmp);
	tmp = new JButton("Ellipse");
	tmp.addActionListener(e);
	add(tmp);
	l = new JList<String>(generateStringColorList());
	l.addListSelectionListener(e);
	add(l);
    }

    public Vector<String> generateStringColorList()
    {
	Vector<String>	list;
	Iterator<Color>	itr;

	list = new Vector<String>();
	itr = Arrays.asList(COLORLIST).iterator();
	while (itr.hasNext())
	    list.add(itr.next().toString());
	return (list);
    }
}