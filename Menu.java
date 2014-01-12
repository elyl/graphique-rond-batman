import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Menu extends JPanel {
	public Menu(ActionListener e) {
		JButton tmp;
		String tab[] = {"Ligne", "Rectangle", "Ellipse", "Exporter", "Importer"};

		setPreferredSize(new Dimension(150, 50));
		setLayout(new GridLayout(1, 4));
		
		for(int i = 0; i < tab.length; i++) {
			tmp = new JButton(tab[i]);
			tmp.addActionListener(e);
			add(tmp);
		}
	}
}
