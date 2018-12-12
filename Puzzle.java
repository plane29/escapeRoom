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

public class Puzzle extends Area{  //our puzzle class is a child class of area with a few differences
	public boolean isSolved;  //boolean for if solved
	public Image solvedImage;  //image for solved image

	public Puzzle(Graphics g, String filename, int index, String solvedFilename){  //constructor takes in similar arguments to second area constructor
		super(g,filename, index);  //calls area constructor without solvedFilename
    	isSolved = false;  //sets isSolved to false
    	solvedImage = loadArea(g, solvedFilename);  //loads up the area associated with the solved image

	}

	@Override
	public void puzzleSolved(){  //if the puzzle is solved we do something different than we do in area
		image = solvedImage;  //we set our image equal to a different image which is similar
		isSolved = true;
		for(int i = 0; i<rect.size()-1; i++){  //however we get rid of all of the hitboxes except for the bottom one to prevent anything from getting sticky
			rect.remove(i);
		}
	}

	public boolean getSolved(){  //returns isSolved
		return isSolved;
	}
}