import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;

public class ListMotifs extends JPanel {
	private JList jlist;

	/**
	 * Créé une nouvelle instance de la classe ListMotifs.
	 * @param c Groupe d'écoutes
	 * @param motifs Liste des motifs composants le dessin
	 */
	public ListMotifs(ListActionListener c, DefaultListModel motifs) {
		JButton tmp;
		JScrollPane listScrollPane;
		JPanel buttonPane;
		
		jlist = new JList(motifs);
		jlist.addListSelectionListener(c);
		jlist.setVisibleRowCount(5);
		listScrollPane = new JScrollPane(jlist);
		
		buttonPane = new JPanel();
		buttonPane.setLayout(new GridLayout(2, 1));
		tmp = new JButton("+");
		tmp.addActionListener(c);
		buttonPane.add(tmp);
		tmp = new JButton("-");
		tmp.addActionListener(c);
		buttonPane.add(tmp);

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		add(new Label("Ordre de superposition"), gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH; 
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		add(listScrollPane, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE; 
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		add(buttonPane, gbc);
	}

	/**
	 * Retourne  l'index sélectionné de la liste.
	 * @return in Index
	 */
	public int getSelectedIndex() {
		return jlist.getSelectedIndex();
	}

	/**
	 * Retourne la taille de la liste courante.
	 * @return int Taille
	 */
	public int getListSize() {
		return jlist.getModel().getSize();
	}

	/**
	 * Sélectionne l'index de la liste passé en paramètre.
	 * @param index Index
	 */
	public void setSelectedIndex(int index) {
		jlist.setSelectedIndex(index);
	}
}
