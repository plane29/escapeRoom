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




public class MyDS implements OrderedCollection{

	Node end;

	public MyDS(){
		end = null;
	}	

	
class Node{
	int num;
	Node next;
	public Node(int num){
		this.num = num;
	}
}


  	public boolean checkPiano(){
	String toReturn = "";
	Node n = end;
	int nodepos = 0;
	while (nodepos < 7 && (n!=null)){
		toReturn = n.num + " " + toReturn;
		n = n.next;
		nodepos = nodepos + 1;
	}

	if(toReturn.equals("1 2 3 4 5 6 7 ")) {
		return true;
	}
	else{ return false;
	}

    }

   	public boolean checkRiddle(){
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



    public void append(int toAppend){
	Node toAdd = new Node(toAppend);
	toAdd.next = end;
	end = toAdd;
    }

    public int peek(){
    return end.num;
    }

    public int pop(){
    int toReturn = end.num;
	end = end.next;
	return toReturn;
    }

    public String toString(){
	String toReturn = "";
	Node n = end;
	while (n != null){
		toReturn = n.num + " " + toReturn;
		n = n.next;
	}
	return toReturn;
    }

    public int length(){
    int toReturn = 0;
    Node n = end;
    while (n != null){
    	toReturn = toReturn + 1;
    	n = n.next;
    }

    return toReturn;

    }
}