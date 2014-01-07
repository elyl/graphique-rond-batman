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

    public Menu(ActionListener e)
    {
	JButton		tmp;

	setPreferredSize(new Dimension(150, 50));
	setLayout(new GridLayout(1, 4));
	tmp = new JButton("Rectangle");
	tmp.addActionListener(e);
	add(tmp);
	tmp = new JButton("Droite");
	tmp.addActionListener(e);
	add(tmp);
	tmp = new JButton("Ellipse");
	tmp.addActionListener(e);
	add(tmp);
	tmp = new JButton("Polygone");
	tmp.addActionListener(e);
	add(tmp);

	}
}
