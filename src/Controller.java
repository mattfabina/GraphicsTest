import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class Controller extends Thread implements KeyListener, WindowListener, MouseListener {

	private GraphicsTest gt;
	private GUI gui;
	private boolean quitting;
	private boolean leftDown;
	private boolean rightDown;
	private boolean mouseDone;
//	private boolean mouseThreadRunning;
	
	public Controller(GraphicsTest gt) {
		this.gt = gt;
		this.quitting = false;
		this.leftDown = false;
//		this.mouseThreadRunning = false;
		this.mouseDone = false;
	}

	
//	private synchronized boolean checkAndMark() {
//		if (mouseThreadRunning) return false;
//		mouseThreadRunning = true;
//		return true;
//	}
	
	public void initMouseThread(final double x, final double y) {
		new Thread() {
			public void run() {
				do {
					if (leftDown) {
						if (findX() < 0 || findY() < 0) {
							gt.nullClick();
						} else {
							gt.leftClicked(findX(), findY());
						}
					} else if (rightDown) {
						if (findX() >= 0 && findY() >= 0) {
							gt.rightClicked(findX(), findY());
						}
					}
					mouseDone = true;
				} while ((leftDown || rightDown) && !mouseDone);
				mouseDone = false;
//				mouseThreadRunning = false;
			}
			
			public int findX() {
				if ((x - 15) / 14 - Math.floor((x - 15) / 14) < .22) {
					return (int) Math.floor((x - 15) / 14);
				}
				return -1;
			}
			
			public int findY() {
				if ((y - 39) / 14 - Math.floor((y - 39) / 14) < .22) {
					return (int) Math.floor((y - 39) / 14);
				}
				return -1;
			}
		}.start();
	}
	
	public void run() {
		while (!quitting) {
			//if (gt.hasChange()) {
			gui.repaint();
			//}
	        try {
	        	Thread.sleep(20);
	        } catch (InterruptedException e) {}
		}
	}
	
	public void setGUI(GUI gui) {
		this.gui = gui;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftDown = true;
			initMouseThread(e.getX(), e.getY());
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			rightDown = true;
			initMouseThread(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftDown = false;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			rightDown = false;
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {	
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		this.quitting = true;		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {	
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {	
	}

	@Override
	public void windowIconified(WindowEvent arg0) {	
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}

	@Override
	public void keyPressed(KeyEvent arg0) {	
	}

	@Override
	public void keyReleased(KeyEvent arg0) {	
	}

	@Override
	public void keyTyped(KeyEvent arg0) {	
	}

}
