In order to compile and run our program, simply download the zip file and unzip it so that it is a folder.  Once you have done that, it is very important that the images are in the same folder as the programs in order to allow our programs to use the images.  So with everything in the same folder, use the terminal to navigate to the title of the folder which is escapeRoom.  Once you are there, use javac *.java & java Main to run the program. 

Directions:

Our project is a virtual escape room in which the user moves throughout the room using the mouse.  To move to the right drag your mouse to the right side of the screen(JFrame) and an arrow should appear indicating that you can click to move right.  Note if you do not click directly on the arrow it is ok, you will still move right if you click on the right side of the screen.  The same goes for moving left.  If you would like to move left move your mouse to the left of the screen, an arrow should appear, and you can click.

If you would like to display or hide hints at any time throughout the program press 'h' on your keyboard.  If there is currently a hint displayed, press h and the hint will go away.  If there is no hint displayed press h and a hint will pop up.

There are a number of hitboxes scattered throughout the room with which a user can interact.  If you see an object that looks suspicious, feel free to click on it.  When you click, a closer image of that object should be brought up.  Now a user will have the option to interact with that object or go back to the screen they were just in.  In order to move back to the screen, a user should move the mouse to the bottom of the screen.  An arrow will appear and clicking will bring a user back to the previous arrow.  If the user is in the intermediate step between the window image and the chess puzzle, the user must click on the chess puzzle in order to go back to the previous area.

The puzzles use a combination of clicking, dragging, and typing to allow the user to attempt to solve the puzzles.  The following puzzles are hidden throughout the room (warning the following contains solutions to the puzzles):

Chess Puzzle:  

When a user is by the window, the user can click on the chess board in the window sill.  This will bring up a closer image of the chess puzzle.  The user must then click on that board again.  An image of the chess puzzle will be brought up that shows a chess board where dark has just moved.  Dragging the pawn on D5 to E6 will result in the user solving the puzzle.  The user then gets part of a lock picking kit for the user’s inventory.  The user will then click the bottom of the screen moving back to the picture of the window sill which will now display the solved chess board.  The user can no longer interact with the chess puzzle.


Riddle Puzzle:
When the user is in front of the sofa, they have the opportunity to click the book sitting on the sofa. After doing so, the user is brought to a puzzle with an open book and a riddle written inside. To solve the riddle “I’m tall when I’m young and I’m short when I’m old. What am I?” the user simply has to type in “candle” on the keyboard. This will then reveal the secret code on the next page of the book, which says CEGBDFA. Now, the user must go over to the piano keyboard located on the bench in the center of the room. 

Piano Puzzle:
If the user sees a bench with cushion and a piano/computer on it, the user should click that piano or the computer.  Here, the user must either type the code CEGBDFA in on their keyboard or click the piano keys themselves. They must do so in this exact order, but can do so with a mixture of typing and clicking. After they do so, the laptop will print a screen with a picture of the light bulb in the room illuminated. From here, the user should make their way over to the now lit-up light bulb area, and they will receive the second piece of the lockpicking set. 


Light Puzzle:
After solving the Keyboard puzzle, the user should now navigate back to the area with the chair and the light.  The light will now be on.  The user should click on this light and another element of the lock picking set should appear in the user’s inventory.  The user now has the ability to escape the room.  Once out of this puzzle if the user clicks ‘h’ on the user’s keyboard, a hint will appear that the user can now escape the room.  Going to the image of the door, the door should now be open and the user has won the game.  The user can now look around the room, looking at puzzles and hints as well as the beautiful Rotherwas Room in the Mead Museum.

One of the best ways in which a user could go about solving the escape room is to solve the chess puzzle, then the riddle puzzle, then the piano puzzle, then the light puzzle, then go to the door.

After the user has collected both of the lock picking implements, they should head back to the original door they started in front of, which will be open. Note: these puzzles can be solved in any order, but we recommend going chess puzzle, riddle puzzle, piano puzzle.


Note: We now are permitted to use the bookcase that is hidden behind our photoshopped bookcase.  It took a bit of time to get permission from the artist.  We will likely do this for future portfolio use.  Written permission to use the images from the Rotherwas Room in the Mead Museum can be found in the updated program specification.

Sources:
Rotherwas Room(17th Century)
Mead Art Museum.
AC 1945.494
https://pixabay.com/en/conference-room-table-office-768441/
https://way2java.com/multimedia/drawing-images/
https://docs.oracle.com/javase/7/docs/api/java/awt/Toolkit.html
https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html
https://docs.oracle.com/javase/7/docs/api/javax/imageio/ImageIO.html
https://stackoverflow.com/questions/13038411/how-to-fit-image-size-to-jframe-size
https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html
https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent/clientX
https://docs.oracle.com/javase/7/docs/api/java/awt/event/MouseEvent.html
https://www.javatpoint.com/java-mouselistener
https://stackoverflow.com/questions/8755812/array-length-in-java
https://www.pexels.com/photo/kitchen-and-dining-area-1080721/
https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
https://www.pexels.com/photo/laptop-computer-884453/
https://www.pinterest.com/pin/423338433693867144/?lp=true
https://www.pexels.com/photo/books-on-bookshelves-1166657/
https://pixabay.com/en/arrow-left-point-pointer-sharp-40169/
https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyListener.html
https://stackoverflow.com/questions/13216148/java-what-throws-an-ioexception
https://stackoverflow.com/questions/12700520/how-to-get-mouse-pointer-location-relative-to-frame
https://stackoverflow.com/questions/18356516/how-can-i-get-the-relative-and-absolute-cursor-position
