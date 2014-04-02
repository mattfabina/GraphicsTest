
public class Main {
	
	public static void main(String[] args) {
		GraphicsTest gt = new GraphicsTest();
		Controller cont = new Controller(gt);
		GUI gui = new GUI(cont, gt);
		cont.setGUI(gui);
		cont.start();
		gui.showMe();
		gui.setLocation(0, 0);
		try {
			cont.join();
		} catch (InterruptedException e) {}
		    gui.close();
	}
	
}


