import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

public class Menu extends JPanel
{
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
	tmp = new JButton("Sauvegarder");
	tmp.addActionListener(e);
	add(tmp);	
	tmp = new JButton("Importer");
	tmp.addActionListener(e);
	add(tmp);

	}
}
