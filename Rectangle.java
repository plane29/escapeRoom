import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;


public class Rectangle extends JPanel{
	public int x;  //x location
	public int y;  //y location
	public int width;  //width of rectangle
	public int height;  //height of rectangleb

	public Rectangle(int inX, int inY, int inWidth, int inHeight){  //main purpose of class is to create hitboxes
		x=inX;  //set these equal to arguments sent in
		y=inY;
		width=inWidth;
		height=inHeight;
	}



	public boolean isIn(Pair point){ //main inspiration came from MonteCarlo basically checks if a pair is within a rectangle
		boolean in;
		if((point.x<=x+width && point.x>=x) && (point.y<=y+height && point.y>=y)){  //must be both within the x range and the y range 
			in = true;
		}
		else{
			in = false;
		}
		return in;
	}
}