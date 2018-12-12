import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random;


public class Hint extends JPanel{
	Font chalkboard = new Font("Chalkboard", Font.BOLD, 30);  //font for drawing the hints to the screen
	String toPrint;  //string which is the hint that we print to the screen
	String[] hints;
	int width;  //width of the screen
	int height;  //height of the screen
	boolean moreHints;  //keeps track if we have more than one hint
	Random rand;  //random instance


	public Hint(int initWidth, int initHeight, String input){  //hint constructor takes in a width and 
		toPrint = input;  //takes in the string and stores it
		width = initWidth;  //stores width and height
		height = initHeight; 
	}

	public Hint(int initWidth, int initHeight, String input, String input2, String input3){  //the user can input 3 hints
		this(initWidth,initHeight,input);  //call other constructor
		hints = new String[3];  //create an array of strings of length 3
		hints[0] = input;   //add in all of the strings to this array
		hints[1] = input2;
		hints[2] = input3;
		moreHints = true;  //set the fact that we have more hints equal to true
		rand = new Random();  //create an instance of random
	}

	public void differentHint(){  // changes the hints
		if(moreHints){ //if we have more than 1 hint than select a random one each time we go into a puzzle
			toPrint = hints[rand.nextInt(3)];
		}
	}

	public void drawHint(Graphics g){  //draws the hints to the screen
			g.setFont(chalkboard);  //sets the font
			FontMetrics metrics = g.getFontMetrics(chalkboard);  //sets up font metrics
			g.setColor(Color.BLACK);  //draws a rectangle to draw the hint on
			g.fillRect(width/2 -metrics.stringWidth(toPrint)/2 -height/140,height/9-height/23,metrics.stringWidth(toPrint) + height/70,height/18);
			g.setColor(Color.WHITE);  //draw the hint onto the screen
			g.drawString(toPrint, width/2-metrics.stringWidth(toPrint)/2, height/9);
	}



}
