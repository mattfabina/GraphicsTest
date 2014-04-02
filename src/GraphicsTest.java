import java.util.ArrayList;

//TODO CHANGE FROM "DRAWME" TO "DRAWSAVED, DRAWCURRENT, DRAWDOT"
public class GraphicsTest {

	public static final int NO_SELECTION = 0;
	public static final int DOT_SELECTED = 1;
	
	private Dot[][] dots;
	private Dot selDot;
	private int testStatus;
	private boolean change;
	
	public GraphicsTest() {
		
		//initialize field of dots
		this.dots = new Dot[51][51];
		for (int i = 0; i < dots.length; i ++) {
			for (int j = 0; j < dots[i].length; j ++) {
				dots[i][j] = new Dot(i, j);
			}
		}
		
		//initial settings
		this.testStatus = NO_SELECTION;
		
		//draw saved polygons
		drawSavedPolygons();
		
		this.change = true;
	}

	/**
	 * 
	 * @return Returns the graphical objects (Drawables) of the GraphicsTest
	 */
	public ArrayList<Drawable2D> getDrawables() {
		ArrayList<Drawable2D> drawables = new ArrayList<Drawable2D>();
		for (int i = 0; i < dots.length; i ++) {
			for (int j = 0; j < dots[i].length; j ++) {
				drawables.add(dots[i][j]);
			}
		}
		return drawables;
	}
	
	public void leftClicked(int x, int y) {
		Dot tarDot = dots[x][y];
		//dot clicked will be made selected
		if (testStatus == NO_SELECTION) {
			testStatus = DOT_SELECTED;
			tarDot.switchSelected();
			selDot = tarDot;
		//dot clicked will be made unselected
		} else if (testStatus == DOT_SELECTED && tarDot.isSelected()) {
			testStatus = NO_SELECTION;
			tarDot.switchSelected();
		//dot clicked will be made target of selected dot
		} else if (testStatus == DOT_SELECTED && !tarDot.isSelected()) {
			if (selDot.hasTarDot() && tarDot == selDot.getTarDot()) {
				testStatus = NO_SELECTION;
				selDot.switchSelected();
				selDot.setTarDot(null);
				System.out.println(selDot.getTarDot());
			} else {
				testStatus = NO_SELECTION;
				selDot.switchSelected();
				selDot.setTarDot(tarDot);
			}
			printCoords();
			printSavedData();
		}
		change = true;
	}

	private void printSavedData() {
		String s = "";
		int counting = 1;
		ArrayList<Dot> connectedDots = new ArrayList<Dot>();
		for (int i = 0; i < dots.length; i ++) {
			for (int j = 0; j < dots[i].length; j ++) {
				if (dots[i][j].hasTarDot()) {
					connectedDots.add(dots[i][j]);
				}
			}
		}
		
		//find all polygons
		while (connectedDots.size() > 0) {
			s += "\nPolygon " + counting + ":\n";
			counting ++;
			Dot originalDot = connectedDots.get(0);
			Dot d = originalDot;
			//find particular polygon
			do {
				s += d.getSaved() + "\n";
				connectedDots.remove(d);
				d = d.getTarDot();
			} while (d.hasTarDot() && d != originalDot);
		}
		
		System.out.println(s);
	}

	public void rightClicked(int x, int y) {
		Dot currentDot = dots[x][y];
		Dot nextDot = currentDot;
				
		if (currentDot.isSelected()) {
			//delete the current polygon
			while (currentDot.hasTarDot()) {
				nextDot = currentDot.getTarDot();
				currentDot.setTarDot(null);
				currentDot = nextDot;
			}
		} else {
			//save the polygon
			while (currentDot.hasTarDot()) {
				nextDot = currentDot.getTarDot();
				currentDot.addSavedConnection(currentDot.getTarDot());
				currentDot.setTarDot(null);
				currentDot = nextDot;
			}
		}
		
		change = true;
	}
	
	public void nullClick() {
		if (testStatus == DOT_SELECTED) {
			this.testStatus = NO_SELECTION;
			selDot.switchSelected();
		}
		change = true;
	}
	
	private void drawSavedPolygons() {
		dots[0][15].addSavedConnection(dots[10][0]);
		dots[10][0].addSavedConnection(dots[30][0]);
		dots[30][0].addSavedConnection(dots[40][15]);
		dots[40][15].addSavedConnection(dots[30][30]);
		dots[30][30].addSavedConnection(dots[10][30]);
		dots[10][30].addSavedConnection(dots[0][15]);
		
		//place code here
		//body
//		dots[0][11].addSavedConnection(dots[1][7]);
//		dots[1][7].addSavedConnection(dots[2][4]);
//		dots[2][4].addSavedConnection(dots[3][2]);
//		dots[3][2].addSavedConnection(dots[4][1]);
//		dots[4][1].addSavedConnection(dots[6][0]);
//		dots[6][0].addSavedConnection(dots[10][0]);
//		dots[10][0].addSavedConnection(dots[12][1]);
//		dots[12][1].addSavedConnection(dots[13][2]);
//		dots[13][2].addSavedConnection(dots[15][3]);
//		dots[15][3].addSavedConnection(dots[21][3]);
//		dots[21][3].addSavedConnection(dots[23][2]);
//		dots[23][2].addSavedConnection(dots[24][1]);
//		dots[24][1].addSavedConnection(dots[26][0]);
//		dots[26][0].addSavedConnection(dots[30][0]);
//		dots[30][0].addSavedConnection(dots[32][1]);
//		dots[32][1].addSavedConnection(dots[33][2]);
//		dots[33][2].addSavedConnection(dots[34][4]);
//		dots[34][4].addSavedConnection(dots[35][7]);
//		dots[35][7].addSavedConnection(dots[36][11]);
//		dots[36][11].addSavedConnection(dots[36][15]);
//		dots[36][15].addSavedConnection(dots[35][19]);
//		dots[35][19].addSavedConnection(dots[34][22]);
//		dots[34][22].addSavedConnection(dots[33][24]);
//		dots[33][24].addSavedConnection(dots[32][25]);
//		dots[32][25].addSavedConnection(dots[30][26]);
//		dots[30][26].addSavedConnection(dots[26][26]);
//		dots[26][26].addSavedConnection(dots[24][25]);
//		dots[24][25].addSavedConnection(dots[23][24]);
//		dots[23][24].addSavedConnection(dots[22][22]);
//		dots[22][22].addSavedConnection(dots[22][19]);
//		dots[22][19].addSavedConnection(dots[23][17]);
//		dots[23][17].addSavedConnection(dots[24][16]);
//		dots[24][16].addSavedConnection(dots[26][15]);
//		dots[26][15].addSavedConnection(dots[27][14]);
//		dots[27][14].addSavedConnection(dots[28][12]);
//		dots[28][12].addSavedConnection(dots[28][10]);
//		dots[28][10].addSavedConnection(dots[27][8]);
//		dots[27][8].addSavedConnection(dots[26][7]);
//		dots[26][7].addSavedConnection(dots[24][6]);
//		dots[24][6].addSavedConnection(dots[23][6]);
//		dots[23][6].addSavedConnection(dots[21][7]);
//		dots[21][7].addSavedConnection(dots[20][8]);
//		dots[20][8].addSavedConnection(dots[18][9]);
//		dots[18][9].addSavedConnection(dots[16][8]);
//		dots[16][8].addSavedConnection(dots[15][7]);
//		dots[15][7].addSavedConnection(dots[13][6]);
//		dots[13][6].addSavedConnection(dots[12][6]);
//		dots[12][6].addSavedConnection(dots[10][7]);
//		dots[10][7].addSavedConnection(dots[9][8]);
//		dots[9][8].addSavedConnection(dots[8][10]);
//		dots[8][10].addSavedConnection(dots[8][12]);
//		dots[8][12].addSavedConnection(dots[9][14]);
//		dots[9][14].addSavedConnection(dots[10][15]);
//		dots[10][15].addSavedConnection(dots[12][16]);
//		dots[12][16].addSavedConnection(dots[13][17]);
//		dots[13][17].addSavedConnection(dots[14][19]);
//		dots[14][19].addSavedConnection(dots[14][22]);
//		dots[14][22].addSavedConnection(dots[13][24]);
//		dots[13][24].addSavedConnection(dots[12][25]);
//		dots[12][25].addSavedConnection(dots[10][26]);
//		dots[10][26].addSavedConnection(dots[6][26]);
//		dots[6][26].addSavedConnection(dots[4][25]);
//		dots[4][25].addSavedConnection(dots[3][24]);
//		dots[3][24].addSavedConnection(dots[2][22]);
//		dots[2][22].addSavedConnection(dots[1][19]);
//		dots[1][19].addSavedConnection(dots[0][15]);
//		dots[0][15].addSavedConnection(dots[0][11]);
//
//		//arm 1 cap
//		dots[3][20].addSavedConnection(dots[4][18]);
//		dots[4][18].addSavedConnection(dots[5][17]);
//		dots[5][17].addSavedConnection(dots[7][16]);
//		dots[7][16].addSavedConnection(dots[8][16]);
//		dots[8][16].addSavedConnection(dots[10][17]);
//		dots[10][17].addSavedConnection(dots[11][18]);
//		dots[11][18].addSavedConnection(dots[12][20]);
//		dots[12][20].addSavedConnection(dots[14][22]);
//		dots[14][22].addSavedConnection(dots[11][22]);
//		dots[11][22].addSavedConnection(dots[13][24]);
//		dots[13][24].addSavedConnection(dots[10][23]);
//		dots[10][23].addSavedConnection(dots[12][25]);
//		dots[12][25].addSavedConnection(dots[9][24]);
//		dots[9][24].addSavedConnection(dots[10][26]);
//		dots[10][26].addSavedConnection(dots[7][24]);
//		dots[7][24].addSavedConnection(dots[8][26]);
//		dots[8][26].addSavedConnection(dots[5][24]);
//		dots[5][24].addSavedConnection(dots[6][26]);
//		dots[6][26].addSavedConnection(dots[4][24]);
//		dots[4][24].addSavedConnection(dots[3][22]);
//		dots[3][22].addSavedConnection(dots[3][20]);
//
//		//arm 1 cap stud
//		dots[4][21].addSavedConnection(dots[5][19]);
//		dots[5][19].addSavedConnection(dots[6][18]);
//		dots[6][18].addSavedConnection(dots[8][17]);
//		dots[8][17].addSavedConnection(dots[10][18]);
//		dots[10][18].addSavedConnection(dots[11][19]);
//		dots[11][19].addSavedConnection(dots[12][21]);
//		dots[12][21].addSavedConnection(dots[10][22]);
//		dots[10][22].addSavedConnection(dots[9][23]);
//		dots[9][23].addSavedConnection(dots[6][23]);
//		dots[6][23].addSavedConnection(dots[4][22]);
//		dots[4][22].addSavedConnection(dots[4][21]);
//
//		//arm 2 cap
//		dots[22][22].addSavedConnection(dots[24][20]);
//		dots[24][20].addSavedConnection(dots[25][18]);
//		dots[25][18].addSavedConnection(dots[26][17]);
//		dots[26][17].addSavedConnection(dots[28][16]);
//		dots[28][16].addSavedConnection(dots[29][16]);
//		dots[29][16].addSavedConnection(dots[31][17]);
//		dots[31][17].addSavedConnection(dots[32][18]);
//		dots[32][18].addSavedConnection(dots[33][20]);
//		dots[33][20].addSavedConnection(dots[33][22]);
//		dots[33][22].addSavedConnection(dots[32][24]);
//		dots[32][24].addSavedConnection(dots[30][26]);
//		dots[30][26].addSavedConnection(dots[31][24]);
//		dots[31][24].addSavedConnection(dots[28][26]);
//		dots[28][26].addSavedConnection(dots[29][24]);
//		dots[29][24].addSavedConnection(dots[26][26]);
//		dots[26][26].addSavedConnection(dots[27][24]);
//		dots[27][24].addSavedConnection(dots[24][25]);
//		dots[24][25].addSavedConnection(dots[25][23]);
//		dots[25][23].addSavedConnection(dots[23][24]);
//		dots[23][24].addSavedConnection(dots[25][22]);
//		dots[25][22].addSavedConnection(dots[22][22]);
//
//		//arm 2 cap stud
//		dots[24][21].addSavedConnection(dots[25][19]);
//		dots[25][19].addSavedConnection(dots[26][18]);
//		dots[26][18].addSavedConnection(dots[28][17]);
//		dots[28][17].addSavedConnection(dots[30][18]);
//		dots[30][18].addSavedConnection(dots[31][19]);
//		dots[31][19].addSavedConnection(dots[32][21]);
//		dots[32][21].addSavedConnection(dots[32][22]);
//		dots[32][22].addSavedConnection(dots[30][23]);
//		dots[30][23].addSavedConnection(dots[27][23]);
//		dots[27][23].addSavedConnection(dots[26][22]);
//		dots[26][22].addSavedConnection(dots[24][21]);
//
//		//arm 1 brace
//		dots[1][11].addSavedConnection(dots[2][7]);
//		dots[2][7].addSavedConnection(dots[3][4]);
//		dots[3][4].addSavedConnection(dots[6][8]);
//		dots[6][8].addSavedConnection(dots[5][10]);
//		dots[5][10].addSavedConnection(dots[5][12]);
//		dots[5][12].addSavedConnection(dots[6][14]);
//		dots[6][14].addSavedConnection(dots[7][15]);
//		dots[7][15].addSavedConnection(dots[5][16]);
//		dots[5][16].addSavedConnection(dots[3][18]);
//		dots[3][18].addSavedConnection(dots[2][20]);
//		dots[2][20].addSavedConnection(dots[2][18]);
//		dots[2][18].addSavedConnection(dots[1][15]);
//		dots[1][15].addSavedConnection(dots[1][11]);
//
//		//arm 1 brace stud
//		dots[2][11].addSavedConnection(dots[3][6]);
//		dots[3][6].addSavedConnection(dots[5][8]);
//		dots[5][8].addSavedConnection(dots[4][10]);
//		dots[4][10].addSavedConnection(dots[4][12]);
//		dots[4][12].addSavedConnection(dots[5][14]);
//		dots[5][14].addSavedConnection(dots[6][15]);
//		dots[6][15].addSavedConnection(dots[4][16]);
//		dots[4][16].addSavedConnection(dots[3][17]);
//		dots[3][17].addSavedConnection(dots[2][15]);
//		dots[2][15].addSavedConnection(dots[2][11]);
//
//		//arm 2 brace
//		dots[29][15].addSavedConnection(dots[30][14]);
//		dots[30][14].addSavedConnection(dots[31][12]);
//		dots[31][12].addSavedConnection(dots[31][10]);
//		dots[31][10].addSavedConnection(dots[30][8]);
//		dots[30][8].addSavedConnection(dots[33][4]);
//		dots[33][4].addSavedConnection(dots[34][7]);
//		dots[34][7].addSavedConnection(dots[35][11]);
//		dots[35][11].addSavedConnection(dots[35][15]);
//		dots[35][15].addSavedConnection(dots[34][18]);
//		dots[34][18].addSavedConnection(dots[34][20]);
//		dots[34][20].addSavedConnection(dots[33][18]);
//		dots[33][18].addSavedConnection(dots[31][16]);
//		dots[31][16].addSavedConnection(dots[29][15]);
//
//		//arm 2 brace stud
//		dots[30][15].addSavedConnection(dots[31][14]);
//		dots[31][14].addSavedConnection(dots[32][12]);
//		dots[32][12].addSavedConnection(dots[32][10]);
//		dots[32][10].addSavedConnection(dots[31][8]);
//		dots[31][8].addSavedConnection(dots[33][6]);
//		dots[33][6].addSavedConnection(dots[34][11]);
//		dots[34][11].addSavedConnection(dots[34][15]);
//		dots[34][15].addSavedConnection(dots[33][17]);
//		dots[33][17].addSavedConnection(dots[32][16]);
//		dots[32][16].addSavedConnection(dots[30][15]);
//		
//		//left body brace
//		dots[4][3].addSavedConnection(dots[5][2]);
//		dots[5][2].addSavedConnection(dots[6][3]);
//		dots[6][3].addSavedConnection(dots[7][0]);
//		dots[7][0].addSavedConnection(dots[8][3]);
//		dots[8][3].addSavedConnection(dots[9][0]);
//		dots[9][0].addSavedConnection(dots[10][3]);
//		dots[10][3].addSavedConnection(dots[11][2]);
//		dots[11][2].addSavedConnection(dots[12][3]);
//		dots[12][3].addSavedConnection(dots[14][4]);
//		dots[14][4].addSavedConnection(dots[11][5]);
//		dots[11][5].addSavedConnection(dots[9][6]);
//		dots[9][6].addSavedConnection(dots[8][7]);
//		dots[8][7].addSavedConnection(dots[7][9]);
//		dots[7][9].addSavedConnection(dots[7][7]);
//		dots[7][7].addSavedConnection(dots[4][3]);
//
//		//right body brace
//		dots[22][4].addSavedConnection(dots[24][3]);
//		dots[24][3].addSavedConnection(dots[25][2]);
//		dots[25][2].addSavedConnection(dots[26][3]);
//		dots[26][3].addSavedConnection(dots[27][0]);
//		dots[27][0].addSavedConnection(dots[28][3]);
//		dots[28][3].addSavedConnection(dots[29][0]);
//		dots[29][0].addSavedConnection(dots[30][3]);
//		dots[30][3].addSavedConnection(dots[31][2]);
//		dots[31][2].addSavedConnection(dots[32][3]);
//		dots[32][3].addSavedConnection(dots[29][7]);
//		dots[29][7].addSavedConnection(dots[29][9]);
//		dots[29][9].addSavedConnection(dots[28][7]);
//		dots[28][7].addSavedConnection(dots[27][6]);
//		dots[27][6].addSavedConnection(dots[25][5]);
//		dots[25][5].addSavedConnection(dots[22][4]);
	}

	public void printCoords() {
		String s = "";
		int counting = 1;
		ArrayList<Dot> connectedDots = new ArrayList<Dot>();
		for (int i = 0; i < dots.length; i ++) {
			for (int j = 0; j < dots[i].length; j ++) {
				if (dots[i][j].hasTarDot()) {
					connectedDots.add(dots[i][j]);
				}
			}
		}
		
		//find all polygons
		while (connectedDots.size() > 0) {
			s += "\nPolygon " + counting + ":\n";
			counting ++;
			Dot originalDot = connectedDots.get(0);
			Dot d = originalDot;
			//find particular polygon
			do {
				s += d.toString(counting) + "\n";
				connectedDots.remove(d);
				d = d.getTarDot();
			} while (d.hasTarDot() && d != originalDot);
		}
		
		System.out.println(s);
	}
	
}
