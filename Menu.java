import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Menu extends JPanel {
	public Menu(ActionListener e) {
		JButton tmp;
		String tab[] = {"Importer", "Exporter", "<-", "->", "Ligne", "Carré", "Rectangle", "Cercle", "Ellipse"};

		this.setLayout(new FlowLayout());
		this.setSize(800, 100);
		
		for(int i = 0; i < tab.length; i++) {
			tmp = new JButton(tab[i]);
			tmp.addActionListener(e);
			add(tmp);
		}
	}
}
