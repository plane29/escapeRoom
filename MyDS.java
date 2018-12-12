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




public class MyDS implements OrderedCollection{  //this was inspired/mostly taken from lab 7, which was the data structures lab

	Node end;

	public MyDS(){ //constructor of DS sets end to null
		end = null;
	}	

	
class Node{  //class node as seen in lab and in class
	int num;
	Node next;
	public Node(int num){  //each node has an int
		this.num = num;
	}
}


  	public boolean checkPiano(){  //checks piano to see if the user played keys correctly
		String toReturn = "";  //creates a new string to check
		Node n = end;  
		int nodepos = 0;  //keeps track of the node position
		while (nodepos < 7 && (n!=null)){  //loops until node position is 7 and we have reached end
			toReturn = n.num + " " + toReturn;  //adds node num to the string
			n = n.next;
			nodepos = nodepos + 1;  //iterates node position
		}

		if(toReturn.equals("1 2 3 4 5 6 7 ")) {  //if the user has played the correct sequence return true
			return true;
		}
		else{ return false;  //if not return false
		}

    }

   	public boolean checkRiddle(){  //this code does the same with checking to see if the user put in the correct input to the riddle puzzle
		String toReturn = "";  
		Node n = end;
		int nodepos = 0;
		while (nodepos < 6 && (n!=null)){
			toReturn = n.num + " " + toReturn;
			n = n.next;
			nodepos = nodepos + 1;
		}

		if(toReturn.equals("1 2 3 4 5 6 ")) {
			return true;
		}
		else{ return false;
		}

    }



    public void append(int toAppend){  //appends a node to the end of our linked list as seen in class
		Node toAdd = new Node(toAppend);
		toAdd.next = end;
		end = toAdd;
    }

    public int peek(){  //returns end of our data structure
    	return end.num;
    }

    public int pop(){  //pops off the end node
    	int toReturn = end.num;
		end = end.next;
		return toReturn;
    }

    public String toString(){  //converts our node to a string
		String toReturn = "";
		Node n = end;
		while (n != null){
			toReturn = n.num + " " + toReturn;
			n = n.next;
		}
		return toReturn;
    }

    public int length(){  //gets the length of our data structure
    	int toReturn = 0;
    	Node n = end;
    	while (n != null){
    		toReturn = toReturn + 1;
    		n = n.next;
    	}

    	return toReturn;

    }
}