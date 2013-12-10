import java.awt.event.*;

public abstract class KeyMouseListener implements KeyListener, MouseListener, MouseMotionListener
{
    public void keyPressed(KeyEvent e)
    {}

    public void keyReleased(KeyEvent e)
    {}

    public void mouseEntered(MouseEvent e)
    {}

    public void mouseExited(MouseEvent e)
    {}

    public void mouseMoved(MouseEvent e)
    {};
}