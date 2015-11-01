package shapes;

import java.awt.Graphics;
import java.io.Serializable;

import utilities.Drawable;
//Shape superclass implements drawable interface, used for draw shape method and serializable used for saving/loading.
@SuppressWarnings("serial")
public class Shape implements Drawable, Serializable{
	
public Shape(){
		
	}
//Dummy method.
public void drawShape(Graphics g) {
	
}

}
