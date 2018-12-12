
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Font;
import java.awt.FontMetrics;


public class Room{  //main inspiration came from world in pong/keyboard spheres this helps facilitate operations within our room
    Graphics g;  //instance of graphics
    int height;  //height of room
    int width;  //width of room
    public Area currentArea;  //stores the current area of the room
    public Area areas[];  //an array of areas that make up the outside of the room
    Image leftArrow;  //image that is the left arrow for screen
    Image rightArrow;  //image that is right arrow for screen
    Image downArrow;  //image that is downArrow for screen
    int numAreas = 12;  //size of our area array
    public Area supArea[];  //supplementary areas which are puzzles and intermediate steps
    public Area previousArea;  //stores the area that we were previously in

    public Room(double initWidth, double initHeight){  //constructor takes in a width and a height for the screen
        height = (int)initHeight;  //sets height to the height that we sent in
        width = (int)initWidth;  //sets width to the width that we sent in
        areas = new Area[numAreas]; //declares the size of the array we're in
        areas[0] = new Area(g, "LockedDoor.jpg",0, "DoorUnlocked.jpg");  //this is where we bring in all of the areas and set hints with each one of them.
        //more of brute forcing through  getting all of the images organized and areas organized
        areas[0].myHint = new Hint(width, height, "Uh oh! You're trapped! Press h to bring up and hide hints.");
        areas[0].hClicked = true;
        areas[1] = new Area(g, "DoorLeft.png",1);
        areas[1].myHint = new Hint(width, height, "Keep looking.");
        areas[2] = new Area(g, "FireplaceRight.png",2);
        areas[2].myHint = new Hint(width, height, "Keep looking.");
        areas[3] = new Area(g, "Fireplace.jpg",3);
        areas[3].myHint = new Hint(width, height, "What's on that Keyboard?");
        areas[4] = new Area(g, "FireplaceLeft.jpg",4);
        areas[4].myHint = new Hint(width, height, "Really, you should tap that Keyboard.");
        areas[5] = new Area(g, "Sofa.jpg", 5);
        areas[5].myHint = new Hint(width, height, "That notebook looks out of place.");
        areas[6] = new Area(g, "WindowRight.jpg",6);
        areas[6].myHint = new Hint(width, height, "Keep looking.");
        areas[7] = new Area(g, "Window.jpg",7, "WindowSolved.jpg");
        areas[7].myHint = new Hint(width, height, "What's on the window?");
        areas[8] = new Area(g, "WindowLeft.jpg",8 , "WindowLeftSolved.jpg");
        areas[8].myHint = new Hint(width, height, "Chess?");
        areas[9] = new Area(g, "ArmChairOff.jpg",9, "ArmChairOn.jpg");
        areas[9].myHint = new Hint(width, height, "Hey what's that light?");
        areas[10] = new Area(g, "Wall.jpg",10);
        areas[10].myHint = new Hint(width, height, "Keep looking.");
        areas[11] = new Area(g, "Desk.jpg",11);
        areas[11].myHint = new Hint(width, height, "Keep looking.");


        int k; //two variables for what is below
        int j;
        for(int i = 0; i<areas.length; i++){ //this loop goes through and sets all of the neighbors using a mod function
            k = (i+1)%numAreas;
            j = ((i-1)+numAreas)%numAreas;
            areas[i].setRightNeighbor(areas[j]);
            areas[i].setLeftNeighbor(areas[k]);
        }    

        //here we brute force the hitboxes for the areas which are basically the puzzles that the user can click on.  We make sure to make them proportional to the screen
        areas[3].createHitBox((int)(120*(initWidth/960.0)),(int)(380*(initHeight/640.0)),(int)(200*(initWidth/960.0)), (int)(150* (initHeight/640.0)));
        areas[4].createHitBox((int)(650*(initWidth/960.0)),(int)(400*(initHeight/640.0)),(int)(220*(initWidth/960.0)), (int)(200* (initHeight/640.0)));
        areas[5].createHitBox((int)(350*(initWidth/960.0)),(int)(360*(initHeight/640.0)),(int)(100*(initWidth/960.0)), (int)(80* (initHeight/640.0)));
        areas[7].createHitBox((int)(160*(initWidth/960.0)),(int)(310*(initHeight/640.0)),(int)(75*(initWidth/960.0)), (int)(70* (initHeight/640.0)));
        areas[8].createHitBox((int)(440*(initWidth/960.0)),(int)(350*(initHeight/640.0)),(int)(75*(initWidth/960.0)), (int)(75* (initHeight/640.0)));
        areas[9].createHitBox((int)(530*(initWidth/960.0)),(int)(385*(initHeight/640.0)),(int)(90*(initWidth/960.0)), (int)(95* (initHeight/640.0)));
        
        supArea = new Area[6];  //here we declare the size of the supplementary areas array

        //again basically brute forcing the construction of all of our puzzles/ intermediate steps
        supArea[0] = new Area(g, "ChessPuzzle.jpg",12);
        supArea[0].myHint = new Hint(width, height, "Ah yes! Chess!  Click on it.");
        areas[8].puzzle = supArea[0];
        areas[7].puzzle = supArea[0];
        supArea[1] = new Puzzle(g, "PianoPuzzle.jpg",13, "Keyboard.png");
        supArea[1].myHint = new Hint(width, height, "Type in or click the sequence. That notebook could help you.", "The notebook between the chairs.", "Type or click in the right order.");
        areas[3].puzzle = supArea[1];

        //here we create the hitboxes for the keyboard puzzle which are used in the main method to tell if the user has entered the correct sequence
        supArea[1].createHitBox((int)(225*(initWidth/960.0)),(int)(440*(initHeight/640.0)),(int)(25*(initWidth/960.0)), (int)(90* (initHeight/640.0)));
        supArea[1].createHitBox((int)(250*(initWidth/960.0)),(int)(440*(initHeight/640.0)),(int)(25*(initWidth/960.0)), (int)(90* (initHeight/640.0)));
        supArea[1].createHitBox((int)(275*(initWidth/960.0)),(int)(440*(initHeight/640.0)),(int)(25*(initWidth/960.0)), (int)(90* (initHeight/640.0)));
        supArea[1].createHitBox((int)(300*(initWidth/960.0)),(int)(440*(initHeight/640.0)),(int)(25*(initWidth/960.0)), (int)(90* (initHeight/640.0)));
        supArea[1].createHitBox((int)(325*(initWidth/960.0)),(int)(440*(initHeight/640.0)),(int)(25*(initWidth/960.0)), (int)(90* (initHeight/640.0)));
        supArea[1].createHitBox((int)(350*(initWidth/960.0)),(int)(440*(initHeight/640.0)),(int)(25*(initWidth/960.0)), (int)(90* (initHeight/640.0)));
        supArea[1].createHitBox((int)(375*(initWidth/960.0)),(int)(440*(initHeight/640.0)),(int)(25*(initWidth/960.0)), (int)(90* (initHeight/640.0)));
        supArea[1].setBottomRect(); //when we set bottom rect we are setting the bottom hitbox for the puzzles

        //more brute forcing of puzzles
        areas[4].puzzle = supArea[1];
        supArea[2] = new Puzzle(g, "LightbulbOff.jpg", 14, "LightbulbOn.jpg");
        supArea[2].myHint = new Hint(width, height, "Somehow that computer over there turns on this light.", "Go check out that computer.", "Go over and check out that computer.");
        areas[9].puzzle = supArea[2];
        supArea[2].setBottomRect();
        supArea[3] = new Puzzle(g, "RiddleQuestion.jpg",15, "RiddleCode.jpg");
        supArea[3].myHint = new Hint(width, height, "Wax on wax off. Type your answer.", "Wicked cool.", "Type your answer.");
        areas[5].puzzle = supArea[3];
        supArea[3].setBottomRect();
        currentArea = areas[0];
        supArea[4] = new Puzzle(g, "ChessUnsolved.jpg",16, "ChessSolved.jpg");
        supArea[4].myHint = new Hint(width, height, "Drags a pawn.  Checkmate... En Passant.",  "Dark just moved a pawn. Try dragging.", "Don't forget about En passant");
        supArea[0].puzzle = supArea[4];
        supArea[0].createHitBox((int)(395*(initWidth/960.0)),(int)(360*(initHeight/640.0)),(int)(150*(initWidth/960.0)), (int)(100* (initHeight/640.0)));
        supArea[0].setBottomRect();

        //creating hitboxes for the chess puzzle
        supArea[4].createHitBox((int)(430*(initWidth/960.0)),(int)(210*(initHeight/640.0)),(int)(50*(initWidth/960.0)), (int)(75* (initHeight/640.0)));
        supArea[4].createHitBox((int)(480*(initWidth/960.0)),(int)(195*(initHeight/640.0)),(int)(50*(initWidth/960.0)), (int)(40* (initHeight/640.0)));
        supArea[4].setBottomRect();

        //the following are for drawing the arrows to the screen
        leftArrow = currentArea.loadArea(g,"LeftArrow.png");
        rightArrow = currentArea.loadArea(g, "RightArrow.png");
        downArrow = currentArea.loadArea(g, "DownArrow.png");

        }

        public boolean isIn(Pair p){  //checks to see if the pair sent in is in any of the rectangles
            for(int i =0; i<currentArea.rect.size();i++){
                if(currentArea.rect.get(i).isIn(p)) return true;
            }
            return false;
        }

        public void drawRoom(Graphics g){  //draws the current area
            currentArea.drawArea(g);
        }

    public void drawLeftArrow(Graphics g){  //draws left arrow
        g.drawImage(leftArrow,(int)(30*(width/960.0)),(int)(260*(height/640.0)),(int)(90*(width/960.0)), (int)(90* (height/640.0)), null);
    }

    public void drawRightArrow(Graphics g){  //draws right arrow
        g.drawImage(rightArrow,(int)(840*(width/960.0)),(int)(260*(height/640.0)),(int)(90*(width/960.0)), (int)(90* (height/640.0)), null);

    }

    public void drawBottomArrow(Graphics g){  //draws bottom arrow
        g.drawImage(downArrow,(int)(435*(width/960.0)),(int)(520*(height/640.0)),(int)(90*(width/960.0)), (int)(90* (height/640.0)), null);

    }




}
