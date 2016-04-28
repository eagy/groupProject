package mainGame;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * This class creates an instance of Playboardd, the class all of the frame and
 * GUI functionality is established.
 * 
 * @author Daniel Eagy
 * @version 1.0
 */
public class gameDriver {

	public static void main(String[] args) {
		JFrame frame = new PlayBoard ();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Blackjack");
		frame.setPreferredSize(new Dimension(800, 600));
		
		frame.pack();
		frame.setVisible(true);

	}

}
