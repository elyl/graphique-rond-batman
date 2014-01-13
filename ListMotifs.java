import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.GridLayout;

public class ListMotifs extends JPanel
{
    private JList	jlist;

    public ListMotifs(ListActionListener c, DefaultListModel motifs)
    {
	JButton	tmp;
	JPanel	p;

	setLayout(new GridLayout(1, 2));
	jlist = new JList(motifs);
	jlist.addListSelectionListener(c);
	jlist.setVisibleRowCount(5);
	add(jlist);
	p = new JPanel();
	p.setLayout(new GridLayout(2, 1));
	tmp = new JButton("+");
	tmp.addActionListener(c);
	p.add(tmp);
	tmp = new JButton("-");
	tmp.addActionListener(c);
	p.add(tmp);
	add(p);
    }

    public int getSelectedIndex()
    {
	return (jlist.getSelectedIndex());
    }
    
    public int getListSize()
    {
	return (jlist.getModel().getSize());
    }

    public void setSelectedIndex(int index)
    {
	jlist.setSelectedIndex(index);
    }
}
