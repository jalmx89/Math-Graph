
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;



public class Panel extends JPanel{
	static boolean working = true;
	static int Width = 800;
	static int Height = 600;
	static double ratio = Height/20;
	static JFrame g = new JFrame("Graphing Calculator");
	
	
	public static void main(String[] args) throws InterruptedException {
		
		g.setSize(Width, Height);
		g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//g.setUndecorated(true);
		//g.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		//Creates a thread in the background which constantly updates
		Thread background = new Thread(new Updategraph());
		background.start();
		
		//creates a new Panel to have the background graph on
		Panel graph = new Panel();
		g.add(graph);
		g.setVisible(true);
		questions();
		
		

	}

/**	
 * Prompts the user on what type of graph they want
 */
private static void questions() {
		String sequation = "";
		
		Scanner Prompter = new Scanner(System.in);
		System.out.println("What is your equation?");
		boolean empty = true;
		while(empty){
			sequation = Prompter.nextLine();
			
			empty = sequation.equals(""); 
			
		}
		Polishorder equation = new Polishorder(sequation); 
		linegrapher(equation);
	}

/**
 * Takes in a translated equation and converts it to a table.
 * @param equation
 */
private static void linegrapher(Polishorder equation) {
	LinkedList<String> line = equation.getList();
	int varamount = equation.getvariabeamount();
	double[] xtable = new double[100];
	double[] ytable = new double[100];
	
	for(int i = -xtable.length/2; i<xtable.length/2; i++){
		
	}
	
	System.out.println(line);
}

/**
 * paint method for the window
 */
public void paint(Graphics window){
	
	drawnew(window);	
}

	/**
	 * Method that draws the background of the graph
	 * @param window
	 */
	public void drawnew(Graphics window){
		window.setColor(Color.DARK_GRAY);
		for (double i = 0; i < Width; i+= ratio) {
			window.drawLine((int)i, 0, (int)i, Height);
			
		}

		for(double i = 0; i< Height; i+= ratio) {
			window.drawLine(0, (int)i, Width, (int)i);
		}
		window.setColor(Color.BLACK);
		if(Width<Height){
			
			window.drawLine(Width/2, 0, Width/2, Height);
			window.drawLine(0, Width/2, Width, Width/2);
		}
		else{
			window.drawLine(Height/2, 0, Height/2, Height);
			window.drawLine(0, Height/2, Width, Height/2);
		}
	}
	
	/**
	 * Repeating loop that is called by the thread
	 */
	public static void Update(){
		int oldwidth = Width;
		int oldheight = Height;
		Width = g.getWidth();
		Height = g.getHeight();
		if(Width<Height){
			ratio = (double)(Width)/20.0;
		}
		else{
			ratio = (double)(Height)/20.0;
		}
		if(oldwidth != Width && oldheight != Height)
		g.repaint();
		
	}
}
