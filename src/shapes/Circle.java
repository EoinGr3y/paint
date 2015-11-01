package shapes;

import java.awt.*;

import utilities.Drawable;

//Circle class, subclass of shape and implements drawable interface.
@SuppressWarnings("serial")
public class Circle extends Shape implements Drawable{
	
	protected int pointX1;
	protected int pointY1;	
	protected int pointX2;
	protected int pointY2;
	protected double radius;
	protected int radInt;
	protected int colour;
	
	public Circle(int x1, int y1, int x2, int y2, int colourSelection){		//Circle constructor, takes in 2 X and Y coordinates and a colour.
		pointX1 = x1;
		pointY1 = y1;
		pointX2 = x2;
		pointY2 = y2;	
		colour = colourSelection;
		
		radius = Math.round(Math.sqrt(Math.pow((pointX2 - pointX1), 2) + Math.pow((pointY2 - pointY1), 2)));	//Finds radius using distance formula.
		radInt = (int)radius;
	}
	
	//Uses draw shape method from drawable interface.
	public void drawShape(Graphics g){
		Graphics2D g2d = (Graphics2D)g;		//Graphics 2d cast.
		
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
		
        g2d.drawOval(pointX1 - radInt,pointY1 - radInt, radInt*2, radInt*2);    
		g2d.fillOval(pointX1 - radInt,pointY1 - radInt, radInt*2, radInt*2);
	}

}
