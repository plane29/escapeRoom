class Pair{ //taken from keyboard spheres but converts everything to ints
    public int x; //x value
    public int y; //y value
    
    public Pair(int initX, int initY){    //creates a pair by having an x and a y value
	   x = initX;  
	   y = initY;  
    }

    public Pair add(Pair toAdd){   //adds two pairs together
	   return new Pair(x + toAdd.x, y + toAdd.y);
    }

    public Pair divide(int denom){   //scalar multiplication of some inverse of an element in Z
	   return new Pair((int)(x / denom), (int)(y / denom));
    }

    public Pair times(int val){  //scalar multiplication
	   return new Pair(x * val, y * val);
    }

    public void flipX(){  //makes x value have opposite parity  
	   x = -x;
    }
    
    public void flipY(){  //makes y value have opposite parity
	   y = -y;
    }
}