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


public class Inventory extends JPanel{
	Font arial = new Font("Arial", Font.BOLD, 30);  //loads in a font to draw the word inventory
	int x;  //x position
	int y;  //y position
	int width;  //width of screen
	int height;  //height of screen
	Image pick;  //image for the pick which can be added to your inventory
	Image lock;  //image for lock which can be added to your inventory
	Graphics g;  //instance of graphics
	boolean pickSolved = false;  //boolean to track if the user has the pick
	boolean lockSolved = false;  //boolean to track if the user has the lock

	public Inventory(int initWidth, int initHeight, String filename, String filename2){  //constructor for inventory which takes in width, height and width and two filenames for the two images
		pick = loadInventory(g, filename);  //sets image of pick to first string sent in
		lock = loadInventory(g, filename2);  //sets image for lock equal to second
		width = initWidth;  //sets height and width equal to those sent in
		height = initHeight;
	}

    public Image loadInventory(Graphics g, String filename){
    	Image image; //see Area code, but learned about how to import an image and image class from https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html
    	try{
        	image = ImageIO.read(new File(filename));
    	} 
    	catch (IOException e) { 
    		image = createImage(100, 100);
    	}
    	return image;
    }



	public void drawInventory(Graphics g){  //draws the inventory to the screen
		g.setFont(arial);  //sets the font 
		FontMetrics metrics = g.getFontMetrics(arial);  //imports the metrics for that font
		g.setColor(Color.WHITE);  //sets color to white
		g.fillRect(width/9,height-height/6,metrics.stringWidth("Inventory"),height/20);  //draws a box on which we will write inventory
		g.setColor(Color.BLACK);  //draws the word inventory
		g.drawString("Inventory", width/9, height-height/8);
		if(pickSolved){  //if the user has the pick draw the pick
			g.drawImage(pick,(int)(100*(width/960.0)),(int)(578*(height/640.0)),(int)(112*(width/960.0)), (int)(57* (height/640.0)), null);
		}
		if(lockSolved){  //if the user has the lock draw the lock
			g.drawImage(lock, (int)(200*(width/960.0)),(int)(575*(height/640.0)),(int)(90*(width/960.0)), (int)(48* (height/640.0)), null);
		}
	}



}
