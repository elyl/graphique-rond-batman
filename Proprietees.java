import java.util.Observer;
import java.util.Observable;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Proprietees extends JPanel implements Observer {
	private JTextField f_Name;
	private JTextField f_Color;
	private JTextField f_X;
	private JTextField f_Y;
	private JTextField f_Width;
	private JTextField f_Height;
	private Motif motif;

	/**
	 * Créé une instance de la classe Proprietees.
	 * @param c Groupe d'écoutes
	 */
	public Proprietees(KeyListener c) {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(2,2,2,2);
		
		f_Name = new JTextField(8);
		f_Name.addKeyListener(c);
		f_Color = new JTextField(8);
		f_Color.addKeyListener(c);
		f_X = new JTextField(8);
		f_X.addKeyListener(c);
		f_Y = new JTextField(8);
		f_Y.addKeyListener(c);
		f_Width = new JTextField(8);
		f_Width.addKeyListener(c);
		f_Height = new JTextField(8);
		f_Height.addKeyListener(c);	
		
		int i=0;
		
		gbc.gridx = 0;
        gbc.gridy = i;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(new JLabel("Propriétés de la figure"),  gbc);
        i++;
        
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.anchor = GridBagConstraints.NORTHEAST;
        
        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Nom  "),  gbc);
 
        gbc.gridx = 1;
        gbc.gridy = i;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(f_Name,  gbc);        
 
        i++;
        
        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Couleur  "),  gbc);
 
        gbc.gridx = 1;
        gbc.gridy = i;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(f_Color,  gbc);        
 
        i++;
        
        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Coordonnée X  "),  gbc);
 
        gbc.gridx = 1;
        gbc.gridy = i;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(f_X,  gbc);        
 
        i++;
        
        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Coordonnée Y  "),  gbc);
 
        gbc.gridx = 1;
        gbc.gridy = i;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(f_Y,  gbc);        
 
        i++;
        
        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Hauteur  "),  gbc);
 
        gbc.gridx = 1;
        gbc.gridy = i;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(f_Height,  gbc);        
 
        i++;
        
        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Largeur  "),  gbc);
 
        gbc.gridx = 1;
        gbc.gridy = i;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(f_Width,  gbc);        
	}

	/**
	 * Met à jour les paramètres du motif passé en paramètres à partir des informations contenues dans les champs des propriétées.
	 * @param m Motif
	 */
	public void updateData(Motif m) {
		updateData(m.getName(), m.getX(), m.getY(), m.getWidth(), m.getHeight(),
				m.getColor());
		if (motif != null)
			motif.deleteObserver(this);
		motif = m;
		m.addObserver(this);
	}

	/**
	 * Met à jour les informations contenues dans les champs des propriétées à partir des informations passées en paramètres.
	 * @param name Nom
	 * @param x Abscisse
	 * @param y Ordonnée
	 * @param width Largeur
	 * @param height Hauteur
	 * @param color Couleur
	 */
	public void updateData(String name, int x, int y, int width, int height, Color color) {
		f_Name.setText(name);
		f_X.setText(new Integer(x).toString());
		f_Y.setText(new Integer(y).toString());
		f_Width.setText(new Integer(width).toString());
		f_Height.setText(new Integer(height).toString());
		f_Color.setText("#" + Integer.toHexString(color.getRGB()).substring(2));
		repaint();
	}

	/**
	 * Met à jour le modèle.
	 */
	public void update(Observable obs, Object o) {
		updateData((Motif) obs);
	}
	
	/**
	 * Retourne le texte contenu dans le champ "Nom".
	 */
	public String getName() {
		return f_Name.getText();
	}

	/**
	 * Retourne le texte contenu dans le champ "Coordonnée X".
	 */
	public String getfX() {
		return f_X.getText();
	}

	/**
	 * Retourne le texte contenu dans le champ "Coordonnée Y".
	 */
	public String getfY() {
		return f_Y.getText();
	}

	/**
	 * Retourne le texte contenu dans le champ "Largeur".
	 */
	public String getfWidth() {
		return f_Width.getText();
	}

	/**
	 * Retourne le texte contenu dans le champ "Hauteur".
	 */
	public String getfHeight() {
		return f_Height.getText();
	}

	/**
	 * Retourne le texte contenu dans le champ "Couleur".
	 */
	public String getfColor() {
		return f_Color.getText();
	}
}
