import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.io.IOException;

public class Main extends JPanel implements KeyListener, MouseListener{
    private static int WIDTH = (int)(1.2*960); //width of screen
    private static int HEIGHT = (int)(1.2*640); //height of screen
    private Graphics g; //instance of graphics
    private int FPS = 10;  //frames per second
    public static Room r;  //insance of room that user is in
    public boolean inPuzzle; //boolean to track if user is in a puzzle
    public boolean mouseInLeft;  //boolean to track if the user hovers mouse over left hitbox
    public boolean mouseInRight;  //boolean to track if the user hovers mouse over right hitbox
    public boolean mouseInBottom;  // boolean to track if the user hovers mouse over bottom hitbox
    public static Main play;  //instance of main... named play for simplicity
    public Point p; //temporary variable to store a point
    public Pair mouseLocation = new Pair(0,0);  //pair that will take in a point and convert to a pair
    public boolean correctPawn = false; //boolean to check if the user solves the chess puzzle correctly
    public MyDS ds = new MyDS(); //data structure that we use to see if the user solves two of the puzzles correctly
    public static JFrame frame;
    public Main(){ 
        addMouseListener(this); //We got information and code about mouselistener from https://www.javatpoint.com/java-mouselistener.  This helped us know the methods and see an example.
        addKeyListener(this);  //adds key listener
        setFocusable(true); //talked to you about this found more information at: https://stackoverflow.com/questions/286727/unresponsive-keylistener-for-jframe
        r = new Room(WIDTH, HEIGHT);  //declares a new instance of the room class with the same width and height
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sets our preferred size
        Thread mainThread = new Thread(new Runner()); //creates a thread of our runner
        mainThread.start();  //starts our runner
    }

    public void keyPressed(KeyEvent e) { //used for a number of reasons called every time a key is pressed
            char c=e.getKeyChar();
            if(c == 'h'){  // if the user hits h, the user wants a hint or  wants to get a hint off of the screen
                if(r.currentArea.hClicked){ //if a hint is already displayed
                    r.currentArea.hClicked = false; //remove that hint from the screen
                    if (r.currentArea.num == 12){ //if we're in a space between our puzzle and our area,
                        inPuzzle =false;  //act like we're not in a puzzle when repainting
                    }
                    repaint();  //repaint
                    if (r.currentArea.num == 12){  //act like we're in a puzzle again
                        inPuzzle =true;
                    }
                }
                else{
                    if (r.currentArea.num == 12){ //do the same thing as we did above but instead display the hint
                        inPuzzle =false;
                    }
                    r.currentArea.hClicked = true;
                    repaint();
                    if (r.currentArea.num == 12){
                        inPuzzle =true;
                    }
                }
            }


        if(r.currentArea.num == 13 && !r.currentArea.isSolved){  //this is for our piano puzzle if the user appends the correct sequence, the user solves the puzzle
            if(c == 'c'){ // if the user clicks a c append a 1
                ds.append(1);
            }
            else if(c == 'e'){  // if user clicks an e append a 2
                ds.append(2);
            }
            else if(c == 'g'){  //if user clicks a g append a 3
                ds.append(3);
            }
            else if(c == 'b'){  // if user clicks a b append 4
                ds.append(4);
            }
            else if(c == 'd'){  //... see above
                ds.append(5);
            }
            else if(c == 'f'){
                ds.append(6);
            }
            else if(c == 'a'){
                ds.append(7);
            }
            else{  //if the user clicks anything else mess up the sequence
                ds.append(8);
            }
            if(ds.checkPiano()){ //checks to see if the user has typed in the right sequence
                r.currentArea.puzzleSolved();  //the current Area's puzzle is solved
                r.supArea[2].puzzleSolved();  //the puzzle with the light is solved and now the user can get the pick
                r.areas[9].puzzleSolved();  // the area with the light is solved and now the light is on
                r.areas[9].rect.remove(2); //change the hitbox of this area because the images are slightly different
                r.areas[9].createHitBox((int)(460*(WIDTH/960.0)),(int)(320*(HEIGHT/640.0)),(int)(90*(WIDTH/960.0)), (int)(95* (HEIGHT/640.0)));
                repaint();  //repaint the screen
            }

        }
                    
        else if(r.currentArea.num == 15 && !r.currentArea.isSolved){  //if we're in the riddle puzzle we want to do a similar thing with the word "candle"
            if(c == 'c'){  //similar to above note that the user can use a combination of clicking and typing the way that the data structure/puzzle works
                ds.append(1);
            }
            else if(c == 'a'){
                ds.append(2);
            }
            else if(c == 'n'){
                ds.append(3);
            }
            else if(c == 'd'){
                ds.append(4);
            }
            else if(c == 'l'){
                ds.append(5);
            }
            else if(c == 'e'){
                ds.append(6);
            }
            else{
                ds.append(8);
            }
            if(ds.checkRiddle()){
                r.currentArea.puzzleSolved(); //the riddle is solved, revealing the code for the keyboard
                repaint();
            }
        }

        

    }

    
    public void keyReleased(KeyEvent e) { //necessary in order to implement keylistener
    
    }


    public void keyTyped(KeyEvent e) {   //again necessary in order to implement keylistener

    }

    public void mouseClicked(MouseEvent e) { //learned about mouse events on the website mentioned above
        int x = e.getX();  //gets the x location of the mouse when the mouse is clicke
        int y = e.getY();  //gets the y location of the mouse when the mouse is clicked
        Pair toCheck = new Pair(x,y);  //makes a new pair to check if the mouse is in any hitboxes
        if(checkPair(toCheck,r)){  //if the mouse is in any of the hitboxes
            if(inPuzzle  && r.currentArea.num!= 12){  //if the mouse is in a puzzle
                if(checkBottom(toCheck,r)){  //check to see if the user wants out of the puzzle (bottom hitbox)
                    r.currentArea = r.previousArea;
                    inPuzzle=false;  //not in a puzzle anymore
                    repaint();  //paint the new screen
                }
                else if(r.currentArea.num == 13 && !r.currentArea.isSolved){  //if the user is interacting witht the keyboard puzzle
                        if(checkKeyboard(toCheck,r,2)){  //we want to do a similar thing as above with the data structure but check whether or not the user is pressing the correct keys
                            ds.append(1);
                        }
                        else if(checkKeyboard(toCheck,r,4)){
                            ds.append(2);
                        }
                        else if(checkKeyboard(toCheck,r,6)){
                            ds.append(3);
                        }
                        else if(checkKeyboard(toCheck,r,8)){
                            ds.append(4);
                        }
                        else if(checkKeyboard(toCheck,r,3)){
                            ds.append(5);
                        }
                        else if(checkKeyboard(toCheck,r,5)){
                            ds.append(6);
                        }
                        else if(checkKeyboard(toCheck,r,7)){
                            ds.append(7);
                        }
                        else{
                            ds.append(8);
                        }
                        if(ds.checkPiano()){  //again check to see if the user punched the keys in correctly
                                r.currentArea.puzzleSolved();  //if they did they solved the current puzzle
                                r.supArea[2].puzzleSolved();  //they also revealed the pick
                                r.areas[9].puzzleSolved(); //they also lit the light
                                r.areas[9].rect.remove(2); //change the hitbox of this area because the images are slightly different
                                r.areas[9].createHitBox((int)(460*(WIDTH/960.0)),(int)(320*(HEIGHT/640.0)),(int)(90*(WIDTH/960.0)), (int)(95* (HEIGHT/640.0)));
                                repaint();
                        }


                }
                else if(checkHitBox(toCheck,r)){  //else check if we've clicked in a hitbox
                    inPuzzle = true;  //we say that we've clicked in a hitbox and move to the puzzle associated with an area
                    repaint();  //we now repaint
                }
            }
            else if(checkLeft(toCheck,r)){  //if the user has clicked in the left we want to change the area we're in to the leftneighbor
                r.currentArea = r.currentArea.leftNeighbor;
                repaint();

            }
            else if(checkRight(toCheck,r)){  // if the user has clicked in the right we want to change to the area that is the right neighbor
                r.currentArea = r.currentArea.rightNeighbor;
                repaint();
            }
            else if(checkHitBox(toCheck,r)){  //if the user clicks in the hitbox, we want to change the current area to the puzzle
                inPuzzle = true;
                if(r.currentArea.num !=12){ //if we're not in the intermediate area
                    r.previousArea = r.currentArea; //change the previous area to the current area because we are moving
                }
                r.currentArea =r.currentArea.puzzle; //change to the puzzle
                r.currentArea.myHint.differentHint();
                if(r.currentArea.num == 14 && r.currentArea.getSolved()){ //if the user goes to this puzzle after it is unlocked give user the lock
                    Area.myInventory.lockSolved = true;
                }
                repaint();
            }

        }

    }  
    public static void listenToMouse(){
        try{  //try and catch if the mouse is out of screen don't want a nullpointer so we send to middle of screen
            int x = -frame.getX() + MouseInfo.getPointerInfo().getLocation().x;  //mouseInfo.getPointerinfo.getlocation returns the absolute value of the mouse so we have to adjust for the jframe
            int y = -frame.getY() + MouseInfo.getPointerInfo().getLocation().y;  //found very similar code to this on https://stackoverflow.com/questions/12700520/how-to-get-mouse-pointer-location-relative-to-frame, which we adjusted for our specific program
            play.p = new Point(x,y);
        }
        catch(NullPointerException e){
            play.p = new Point(WIDTH/2,HEIGHT/2);
        }
        play.mouseLocation =  new Pair(play.p.x,play.p.y - 22);  //the initial mouse location is 50(because of the way the terminal pops up on the screen) so we have to adjust for that
        if(!play.inPuzzle && play.r.currentArea.num!=16){ //if we're not in a puzzle
            if(play.checkRight(play.mouseLocation,r)){  //we want to check if the mouse is in the right
                play.mouseInRight = true; //if it's in the right we want to set this to true and draw right arrow
                play.repaint();
            }
            else if(play.checkLeft(play.mouseLocation,r)){  //same thing for the left
                play.mouseInLeft = true;
                play.repaint();
            }
            else{
                play.repaint();
            }
        }
        else if(play.inPuzzle){ //if the user is in the puzzle
            if(play.checkBottom(play.mouseLocation,r)){  //check to see if the user has placed the mouse in the bottom of the puzzle
                play.mouseInBottom = true;  //if they have repaint and print the screen
                play.repaint();
            }
            else{
                play.repaint();  //if not do reprint it without bottom area
            }
        }

    }
    public void mouseEntered(MouseEvent e) {  //taken from website above necessary to implement mouselistener

    }  
    public void mouseExited(MouseEvent e) {  //necessary to implement mouselistener

    }  
    public void mousePressed(MouseEvent e) {   //used for the chess puzzle
        int x = e.getX(); //integer to store x value of where mouse was pressed
        int y = e.getY();  //integer to store y value of where mouse was pressed
        Pair toCheck = new Pair(x,y);   //pair that stores these values
        if(r.currentArea.num == 16){  //if we're currently in the chess puzzle
            if(!r.currentArea.getSolved() && checkChess(toCheck,r)){  //the player has correctly pressed the pawn
                correctPawn = true;
            }
        }
    }  
    public void mouseReleased(MouseEvent e) {  //used for the chess puzzle if the player drags correctly
        int x = e.getX();  //same thing as above in terms of getting mouse position
        int y = e.getY();  
        Pair toCheck = new Pair(x,y);
        if(r.currentArea.num == 16){  //if we're in the chess puzzle
            if(!r.currentArea.getSolved() && checkChess2(toCheck,r) && correctPawn){  //check to see if the player dragged the pawn to the correct location
                r.currentArea.puzzleSolved();  //say that we've solved the chess puzzle changing the image appearing
                r.areas[7].puzzleSolved(); //solved window rooms changing the images
                r.areas[8].puzzleSolved();
                r.areas[7].rect.remove(2);  //no longer allow the user to access the chess puzzle
                r.areas[8].rect.remove(2);
                Area.myInventory.pickSolved = true;  //print the pick to the screen
                repaint();
            }

        } 
       
    }  

    @Override    
    public void paintComponent(Graphics g) { //main idea of paint component taken from keyboard spheres
        super.paintComponent(g);
        if(Area.myInventory.lockSolved && Area.myInventory.pickSolved && (r.currentArea.num == 9 || r.currentArea.num == 8 || r.currentArea.num == 7)){ //if the user has everything they need to unlock door and we're right after light
            for(int i = 1; i<r.areas.length;i++){  //change all hints to you have everything you need to unlock door
                r.areas[i].myHint = new Hint(WIDTH, HEIGHT, "You have all you need to unlock the door.");
            }
            r.areas[0].puzzleSolved(); //open the door
            r.areas[0].myHint = new Hint(WIDTH,HEIGHT, "Congrats! You have escaped.  Feel free to look around."); //print out that the user has won
            r.areas[0].hClicked = true; //make sure that this hint appears
        }
        r.drawRoom(g);
        if(mouseInLeft){  //calls the draw left arrow method if the mouse was in left of screen
            r.drawLeftArrow(g);
            play.mouseInLeft = false;
        }
        if(mouseInRight){  //same thing for the right arrow
            r.drawRightArrow(g);
            play.mouseInRight = false;
        }
        if(mouseInBottom){  //same thing for the bottom arrow
            if(r.currentArea.num != 12){  //if the user is in the intermediate step, the user cannot move back
                r.drawBottomArrow(g);
            }
            play.mouseInBottom = false;
        }

    }

    public static void main(String[] args) {  //main idea taken from keyboard spheres/draw to screen
        System.out.println("Many thanks to the Mead Art Museum for letting us use these pictures.");
        System.out.println("Rotherwas Room(17th Century)");  //attribution of images
        System.out.println("Mead Art Museum.");
        System.out.println("AC 1945.494");
        play = new Main();  //create a new instance of main
        frame = new JFrame("Escape Room");  //make a new instance of JFrame 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setContentPane(play);  //taken from keyboard spheres and labs etc.
        frame.pack();
        frame.setVisible(true);  //set visible
    }  

    public boolean checkPair(Pair p, Room r){  //check to see if the mouse was clicked in any of the rectangles
        return r.isIn(p);
    }

    public boolean checkLeft(Pair p, Room r){ //check to see if pair was in left hitbox
        if(!inPuzzle){
          return r.currentArea.rect.get(0).isIn(p);
        }
        return false;
    }

    public boolean checkRight(Pair p, Room r){  //same thing for right hitbox
        if(!inPuzzle){
          return r.currentArea.rect.get(1).isIn(p);
        }
        return false;
    }

    public boolean checkHitBox(Pair p, Room r){  //same thing for some hitbox on the screen
        if(r.currentArea.rect.size()>2){    
            return r.currentArea.rect.get(2).isIn(p);
        }
        return false;
    }

    public boolean checkBottom(Pair p, Room r){  //same thing for bottom hitbox
        return r.currentArea.rect.get(r.currentArea.rect.size()-1).isIn(p);
    }

    public boolean checkChess(Pair p, Room r){  //this checks to see if the user pressed in the right location for the pawn
        return r.currentArea.rect.get(2).isIn(p);
    }

    public boolean checkChess2(Pair p, Room r){  //checks to see if the user dragged the pawn to the right location
        return r.currentArea.rect.get(3).isIn(p);
    }

    public boolean checkKeyboard(Pair p, Room r, int i){  //checks to see if the user clicked in the right location takes in an index for the hitbox number
        if(r.currentArea.rect.size()>i){    
            return r.currentArea.rect.get(i).isIn(p);
        }
        return false;
    }




}
