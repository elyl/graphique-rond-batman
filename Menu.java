import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Menu extends JPanel
{
    public Menu(ActionListener e)
    {
	JButton	tmp;

	setPreferredSize(new Dimension(200, 50));
	setLayout(new GridLayout(3, 1));
	tmp = new JButton("Rectangle");
	tmp.addActionListener(e);
	add(tmp);
	tmp = new JButton("Droite");
	tmp.addActionListener(e);
	add(tmp);
	tmp = new JButton("Ellipse");
	tmp.addActionListener(e);
	add(tmp);
    }
}