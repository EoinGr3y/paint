package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import utilities.Drawable;

//Triangle class, subclass of shape and implements drawable interface.
@SuppressWarnings("serial")
public class Triangle extends Shape implements Drawable{
	
	protected int pointX1;
	protected int pointY1;	
	protected int pointX2;
	protected int pointY2;
	protected int pointX3;
	protected int pointY3;
	protected int colour;
	
	public Triangle(int x1, int y1, int x2, int y2, int x3, int y3, int colourSelection){	//Triangle constructor, takes in 3 X and Y coordinates and a colour.
		pointX1 = x1;
		pointY1 = y1;
		pointX2 = x2;
		pointY2 = y2;
		pointX3 = x3;
		pointY3 = y3;
		colour = colourSelection;		
		}

	//Uses draw shape method from drawable interface.
	public void drawShape(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		
		if(colour == 0){
			g2d.setColor(Color.BLACK);
		}
		else if(colour == 1){
			g2d.setColor(Color.BLUE);
		}
		else if(colour == 2){
			g2d.setColor(Color.CYAN);
		}
		else if(colour == 3){
			g2d.setColor(Color.DARK_GRAY);
		}
		else if(colour == 4){
			g2d.setColor(Color.GRAY);
		}
		else if(colour == 5){
			g2d.setColor(Color.GREEN);
		}
		else if(colour == 6){
			g2d.setColor(Color.MAGENTA);
		}
		else if(colour == 7){
			g2d.setColor(Color.ORANGE);
		}
		else if(colour == 8){
			g2d.setColor(Color.PINK);
		}
		else if(colour == 9){
			g2d.setColor(Color.RED);
		}
		else if(colour == 10){
			g2d.setColor(Color.WHITE);
		}
		else if(colour == 11){
			g2d.setColor(Color.YELLOW);
		}	
		int[] xPoints = {pointX1,pointX2,pointX3}; 	//Creates an array of X points, used to fill polygon.
		int[] yPoints = {pointY1,pointY2,pointY3};	//Creates an array of Y points, used to fill polygon.
		
		g2d.drawLine(pointX1,pointY1,pointX2,pointY2);
		g2d.drawLine(pointX2, pointY2, pointX3, pointY3);
		g2d.drawLine(pointX3,pointY3,pointX1,pointY1);		//Draws 3 lines to create triangle.
		g.fillPolygon(xPoints, yPoints, 3);
	}
}
