import java.util.Observer;
import java.util.Observable;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Proprietees extends JPanel implements Observer {
	private JTextField f_Color;
	private JTextField f_X;
	private JTextField f_Y;
	private JTextField f_Width;
	private JTextField f_Height;
	private Motif motif;

	public Proprietees(KeyListener c) {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(2,2,2,2);
		gbc.anchor = GridBagConstraints.NORTHEAST;
		
		f_Color = new JTextField(8);
		f_Color.addKeyListener(c);
		f_X = new JTextField();
		f_X.addKeyListener(c);
		f_Y = new JTextField();
		f_Y.addKeyListener(c);
		f_Width = new JTextField();
		f_Width.addKeyListener(c);
		f_Height = new JTextField();
		f_Height.addKeyListener(c);
		
		int i=0;
		 
        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Couleur : "),  gbc);
 
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
        add(new JLabel("Coordonnée X : "),  gbc);
 
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
        add(new JLabel("Coordonnée Y : "),  gbc);
 
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
        add(new JLabel("Hauteur : "),  gbc);
 
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
        add(new JLabel("Largeur : "),  gbc);
 
        gbc.gridx = 1;
        gbc.gridy = i;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(f_Width,  gbc);        
	}

	public void updateData(Motif m) {
		updateData(m.getX(), m.getY(), m.getWidth(), m.getHeight(),
				m.getColor());
		if (motif != null)
			motif.deleteObserver(this);
		motif = m;
		m.addObserver(this);
	}

	public void updateData(int x, int y, int width, int height, Color color) {
		f_X.setText(new Integer(x).toString());
		f_Y.setText(new Integer(y).toString());
		f_Width.setText(new Integer(width).toString());
		f_Height.setText(new Integer(height).toString());
		f_Color.setText("#"
				+ (Integer.toHexString(color.getRed())
						+ Integer.toHexString(color.getGreen()) + Integer
							.toHexString(color.getBlue())).toUpperCase());
		repaint();
	}

	public void update(Observable obs, Object o) {
		updateData((Motif) obs);
	}

	public String getfX() {
		return (f_X.getText());
	}

	public String getfY() {
		return (f_Y.getText());
	}

	public String getfWidth() {
		return (f_Width.getText());
	}

	public String getfHeight() {
		return (f_Height.getText());
	}

	public String getfColor() {
		return (f_Color.getText());
	}
}
