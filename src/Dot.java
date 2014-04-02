import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

//TODO REMOVE DOT1 CONNECTION IF THIRD CONNECTION IS MADE
public class Dot implements Drawable2D {
	
	private ArrayList<Dot> savedConnections;
	private Dot tarDot;
	private int row;
	private int column;
	private boolean selected;
	private boolean targeting;
	
	public Dot(int row, int column) {
		savedConnections = new ArrayList<Dot>();
		this.row = row;
		this.column = column;
		this.selected = false;
	}
	
	public void switchSelected() {
		this.selected = !selected;
	}
		
	public boolean isSelected() {
		return selected;
	}
	
	public void setTarDot(Dot tarDot) {
		if (tarDot == null) {
			targeting = false;
		} else {
			this.tarDot = tarDot;
			targeting = true;
		}
	}

	public Dot getTarDot() {
		return tarDot;
	}
	
	public int getCenterX() {
		return ((row + 1) * 14) + 2;
	}
	
	
	public int getCenterY() {
		return ((column + 1) * 14) + 2;
	}
	
	
	public boolean hasTarDot() {
		return targeting;
	}
	
	public void addSavedConnection(Dot d) {
		savedConnections.add(d);
	}
	
	@Override
	public void drawMe(Graphics2D g) {
		for (Dot d: savedConnections) {
			g.setColor(Color.YELLOW);
			g.drawLine(getCenterX(), getCenterY(), d.getCenterX(), d.getCenterY());
		}
		
		if (targeting) {
			if (selected) {
				g.setColor(Color.MAGENTA);
			} else {
				g.setColor(Color.BLUE);
			}
			g.drawLine(getCenterX(), getCenterY(), tarDot.getCenterX(), tarDot.getCenterY());
		}
		
		if (selected) {
			g.setColor(Color.GREEN);
		} else if (targeting) {
			g.setColor(Color.MAGENTA);
		} else {
			g.setColor(Color.BLACK);
		}
		g.fillOval((row + 1) * 14, (column + 1) * 14, 4, 4);
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public String toString(int counting) {
//		return "chassisPolygons.get(" + counting + ").addPoint(originX + " + (row * 2) + ", originY + " + (column * 2) + ");";
		return "p.addPoint(" + (row * 2) + ", " + (column * 2) + ");";
	}

	public String getSaved() {
		return "dots[" + row + "][" + column + "].addSavedConnection(dots[" 
				+ tarDot.getRow() + "][" + tarDot.getColumn() + "]);";

	}
	
}