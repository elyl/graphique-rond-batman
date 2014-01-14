import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;

public class Controleur {
	private Dessin draw;
	private Proprietees properties;
	private JFrame frame;
	private int curentShapeType;
	private Color currentColor;
	private Motif motive;
	private Motif motiveCopie;
	private JColorChooser colorChooser;
	private ListMotifs jlist;

	/**
	 * Création d'une nouvelle instance de la classe Controleur.
	 */
	public Controleur() {
		frame = new JFrame("Graphicron 3000");
		frame.setPreferredSize(new Dimension(1200, 800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());

		draw = new Dessin(new ControleurDessin(), 1200, 500);
		
		// Définition de la palette de couleurs
		colorChooser = new JColorChooser(Color.RED);
		colorChooser.setPreviewPanel(new JPanel());
		colorChooser.getSelectionModel().addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e)
			{
			    currentColor = colorChooser.getColor();
			}
		    });
		
		// Définition de la liste des figures dans leur ordre de superposition
		jlist = new ListMotifs(new ControleurList(), draw.getMotifs());
		
		// Défition des propriétés de la figure courante
		properties = new Proprietees(new ControleurProprietees());
		
		// Définition de la palette de couleurs
		colorChooser = new JColorChooser(Color.RED);
		colorChooser.setPreviewPanel(new JPanel());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;

		int i = 0;

		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.gridwidth = 3;
		frame.add(new Menu(new ControleurMenu()), gbc);
		i++;

		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		frame.add(draw, gbc);
		i++;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0;
		gbc.weighty = 0;
		frame.add(jlist, gbc);

		gbc.gridx = 1;
		gbc.gridy = i;
		gbc.ipadx = 20;
		gbc.gridwidth = 1;
		frame.add(properties, gbc);

		gbc.gridx = 2;
		gbc.gridy = i;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		frame.add(colorChooser, gbc);

		frame.pack();
		frame.setVisible(true);
	}

	public void selectItem(Point p) {
		Motif tmp;

		tmp = draw.getShape(p);
		if (tmp != null)
			selectItem(tmp);
	}

	public void selectItem(Motif m) {
		if (motive != null)
			motive.setSelected(false);
		m.setSelected(true);
		properties.updateData(m);
		motive = m;
	}
	
	private class ControleurDessin extends KeyMouseListener {
		private int x;
		private int y;

		public void mouseClicked(MouseEvent e) {
			draw.requestFocus();
			selectItem(new Point(e.getX(), e.getY()));
		}
		
		public void mouseDragged(MouseEvent e) {
			if (motive != null && curentShapeType == Motif.NULL) {
				motive.setX(motive.getX() + e.getX() - x);
				motive.setY(motive.getY() + e.getY() - y);
				x = e.getX();
				y = e.getY();
			} else if (motive != null) {
				motive.resize(e.getX() - x, e.getY() - y);
			}
		}

		public void mousePressed(MouseEvent e) {
			x = e.getX();
			y = e.getY();
			if (curentShapeType != 0) {
				if (motive != null)
					motive.setSelected(false);
				motive = new Motif(x, y, 0, 0, currentColor, curentShapeType);
				motive.setSelected(true);
				draw.ajouterMotif(motive);
				properties.updateData(motive);
			} else
				selectItem(new Point(x, y));
			System.out.println(motive);
		}

		public void mouseReleased(MouseEvent e) {
			if (motive != null && curentShapeType == 0) {
				motive.setX(motive.getX() + e.getX() - x);
				motive.setY(motive.getY() + e.getY() - y);
			}
			curentShapeType = Motif.NULL;
		}

		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				motive.moveLeft();
				break;
			case KeyEvent.VK_RIGHT:
				motive.moveRight();
				break;
			case KeyEvent.VK_UP:
				motive.moveUp();
				break;
			case KeyEvent.VK_DOWN:
				motive.moveDown();
				break;
			case KeyEvent.VK_DELETE:
				draw.supprimerMotif(motive);
				break;
			case KeyEvent.VK_C:
				if (e.isControlDown())
					motiveCopie = motive.clone();
				break;
			case KeyEvent.VK_V:
				if (e.isControlDown()) {
					motiveCopie.setX((int) MouseInfo.getPointerInfo()
							.getLocation().getX());
					motiveCopie.setY((int) MouseInfo.getPointerInfo()
							.getLocation().getY() - 75);
					draw.ajouterMotif(motiveCopie);
					motiveCopie = motiveCopie.clone();
				}
				break;
			}
		}

		public void mouseWheelMoved(MouseWheelEvent e) {
			int n = e.getWheelRotation();

			if (n > 0)
				motive.scaleDown();
			else
				motive.scaleUp();
		}

		public void keyTyped(KeyEvent e) { }

	}

	private class ControleurMenu implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		    //currentColor = colorChooser.getColor();
			if (e.getActionCommand().equals("Rectangle"))
				curentShapeType = Motif.RECTANGLE;
			else if (e.getActionCommand().equals("Carré"))
				curentShapeType = Motif.SQUARE;
			else if (e.getActionCommand().equals("Ellipse"))
				curentShapeType = Motif.ELLIPSE;
			else if (e.getActionCommand().equals("Cercle"))
				curentShapeType = Motif.CIRCLE;
			else if (e.getActionCommand().equals("Ligne"))
				curentShapeType = Motif.LINE;
			else if (e.getActionCommand().equals("Exporter"))
				Sauvegarde.export("save", draw.getMotifs());
			else if (e.getActionCommand().equals("Importer"))
				Sauvegarde.importMotif("save", draw);
			draw.requestFocus();
		}
	}

	private class ControleurProprietees extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			Color c;

			if (e.getKeyChar() == 10) {
				c = new Color(Integer.parseInt(properties.getfColor().replace("#", ""), 16));
				motive.setName(properties.getName());
				motive.resizeAndMove(Integer.parseInt(properties.getfX()),
						Integer.parseInt(properties.getfY()),
						Integer.parseInt(properties.getfWidth()),
						Integer.parseInt(properties.getfHeight()));
				motive.setColor(c);
			}
		}
	}

	private class ControleurList extends ListActionListener {
		public void valueChanged(ListSelectionEvent e) {
			Motif tmp;

			if (!e.getValueIsAdjusting()) {
				tmp = (Motif) draw.getMotifs().get(jlist.getSelectedIndex());
				selectItem(tmp);
			}
		}

		public void actionPerformed(ActionEvent e) {
			int index;
			Motif tmp;

			index = jlist.getSelectedIndex();
			if (e.getActionCommand().equals("+") && index > 0) {
				tmp = (Motif) draw.getMotifs().set(index - 1, motive);
				draw.getMotifs().set(index, tmp);
				jlist.setSelectedIndex(index - 1);
			} else if (e.getActionCommand().equals("-")
					&& index < jlist.getListSize() - 1) {
				tmp = (Motif) draw.getMotifs().set(index + 1, motive);
				draw.getMotifs().set(index, tmp);
				jlist.setSelectedIndex(index + 1);
			}
			draw.repaint();
		}
	}

	public static void main(String args[]) {
		new Controleur();
	}
}
