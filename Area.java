
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

public class Area extends JPanel{
	public static final int BOX_WIDTH = (int)(1.2*960);  //BOX_WIDTH variable taken from some other lab
	public static final int BOX_HEIGHT = (int)(1.2*640);  //BOX_HEIGHT variable taken from another lab
    public Image image;  //image stored w/ area
    public Image image2;  //second image stored w/ area to be used for printing different image once an area is solved
    public static Graphics g;  //instance of graphics mostly for debugging
    public int num;  //number associated with each area
    public Area leftNeighbor;  //left neighbor for each area
    public Area rightNeighbor;  //right neighbor for each area
    public Area puzzle;  //puzzle for an area
    public Area myArea;  //area associated w another area mostly puzzles
    public boolean isSolved;  //boolean to tell if an area is solved
    public boolean hClicked = false;  //boolean to keep track of whether or not to display a hint
    public Hint myHint = new Hint(960,640,"no hint");  //hint initially each one gets a hint of no hint because we don't have a hint associated yet
    public static Inventory myInventory = new Inventory(BOX_WIDTH,BOX_HEIGHT, "pick.png", "lock.png");  //one inventory for all of the areas with images pick and lock
    public ArrayList<Rectangle> rect;  //an array list of rectangles used mostly so there is no set size

    // constructor of Area
    public Area(Graphics g, String filename, int index){ //learned about downloading an image and imageIO and image class on oracle and on https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html
        this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));  //and https://stackoverflow.com/questions/13038411/how-to-fit-image-size-to-jframe-size
        image = loadArea(g, filename);  //loads in an image to store with an area
        rect = new ArrayList<Rectangle>(2);  //creates a new array list of rectangles
        rect.add(0, new Rectangle(0,0, BOX_WIDTH/9, BOX_HEIGHT));//creates left hitbox
        rect.add(1, new Rectangle(BOX_WIDTH-BOX_WIDTH/9,0,BOX_WIDTH/9,BOX_HEIGHT));//creates right hitbox
        num = index;
    }

    //second constructor of Area
    public Area(Graphics g, String filename, int index, String filename2) {  //this constructor is used for when an area is impacted by a puzzle being solved because the puzzle's image changes therefore changing the area image
        this(g, filename, index);  //call the constructor below
        image2 = loadArea(g, filename2);  //loads in a second image (solved image) 

    }


    public Image loadArea(Graphics g, String filename){  //we were inspired by code above for this but basically it loads in an image
    	Image image;
    	try{  //in case the image does not exist we do not want the program to crash
        	image = ImageIO.read(new File(filename));  //reads in image
    	} 
    	catch (IOException e) { 
    		image = createImage(100, 100);  //if no such image exists we create a blank image
    	}
    	return image;
    }

    public void drawArea(Graphics g){  //draws the area
    	g.drawImage(image,0,0,BOX_WIDTH,BOX_HEIGHT,null);  //draws the image to the width and height of the screen
    	myInventory.drawInventory(g);  //draws the inventory
        if(hClicked){  // if the user wants a hint draw the hint
            myHint.drawHint(g);
        }
    }

    public void setRightNeighbor(Area neighbor){  //sets the right neighbor for an area
        this.rightNeighbor = neighbor;
    }

    public void setLeftNeighbor(Area neighbor){  //sets the left neighbor for an area
        this.leftNeighbor = neighbor;
    }


    public void createHitBox(int x, int y, int width, int height){  //creates a hitbox by adding a new rectangle to the end of rect
    	rect.add(new Rectangle(x, y, width, height));
    }

    public void puzzleSolved(){ //once a puzzle is solved change over the image
        image = image2;  //change image
        isSolved = true;
    }
    public boolean getSolved(){  //returns whether or not the puzzle is solved
        return true;
    }

    public void setBottomRect(){  //adds a bottom hitbox which is important for puzzles
        createHitBox(0,(int)(540*(BOX_HEIGHT/640.0)),BOX_WIDTH, (int)(100* (BOX_HEIGHT/640.0)));
    }



}

