import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class GUI extends JPanel {
	
	private JFrame frame;
	private GraphicsTest gt;
	private Controller cont;
	
	private int width;
	private int height;
	
	public GUI(Controller cont, GraphicsTest gt) {
		super();
		this.frame = new JFrame("Graphics Test");
		this.gt = gt;
		this.cont = cont;
		//constructs a container, and puts the content pane of the java frame within
		Container container = frame.getContentPane();
		//adds the GUI (within the JPanel superclass to the container)
		container.add(this);
		frame.addMouseListener(this.cont);
		this.width = 730;
		this.height = 730;
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		this.update(graphics);
	}
	
	public void update(Graphics gS) {
		Graphics2D g = (Graphics2D)gS;
		
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 730, 730);
		
		g.setColor(Color.RED);
		g.drawLine(width / 2 + 1, 0, width / 2 + 1, height);
		g.drawLine(0, height / 2 + 1, width, height / 2 + 1);
		
		int x = 5;
		int y = 5;
//		Polygon p = new Polygon();
//		p.addPoint(x + 5, y);
//		p.addPoint(x + 9, y + 1);
//		p.addPoint(x + 10, y + 4);
//		p.addPoint(x + 10, y + 6);
//		p.addPoint(x + 8, y + 8);
//		p.addPoint(x + 7, y + 14);
//		p.addPoint(x + 5, y + 20);
//		p.addPoint(x + 3, y + 14);
//		p.addPoint(x + 2, y + 8);
//		p.addPoint(x, y + 6);
//		p.addPoint(x, y + 4);
//		p.addPoint(x + 1, y + 1);
//		g.setColor(Color.BLUE);
//		g.fillPolygon(p);
		
		for (Drawable2D d: gt.getDrawables()) {
			d.drawMe(g);
		}
		
	}
	
	public void close() {
		frame.dispose();
	}
	
	public void showMe() {
		Dimension dimension = new Dimension(width, height);
		// Set the size of the contents of the window so that we get the right size
		Container container = frame.getContentPane();
		container.setMaximumSize(dimension);
		container.setPreferredSize(dimension);
		container.setMinimumSize(dimension);
		// And now we can show the window
		frame.pack();
		frame.setVisible(true);
		this.repaint();
	}
	
	public void setLocation(int x, int y) {
	      frame.setLocation(x, y);
	}
	
}
