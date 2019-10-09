import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Maze extends JFrame {

	public Maze() {
		// Maze object to be displayed
		this.add(new MazeGridPanel(5,5));
		this.setSize(800, 800);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //option
		this.pack();
		this.setVisible(true);  //display
	}
	
	public static void main(String[] args) {
			new Maze();

	}
}
