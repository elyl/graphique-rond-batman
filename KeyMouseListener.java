import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

public abstract class KeyMouseListener implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	public void keyPressed(KeyEvent e) { }

	public void keyReleased(KeyEvent e) { }
	
	public void keyTyped(KeyEvent e) { }

	public void mouseEntered(MouseEvent e) { }

	public void mouseExited(MouseEvent e) { }

	public void mouseMoved(MouseEvent e) { };
}