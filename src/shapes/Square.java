package shapes;

import java.awt.*;
import java.awt.geom.AffineTransform;

import utilities.Drawable;

	//Square class, subclass of shape and implements drawable interface.
	@SuppressWarnings("serial")
	public class Square extends Shape implements Drawable{
	
	protected int pointX1;
	protected int pointY1;	
	protected int pointX2;
	protected int pointY2;
	protected int colour;
	protected int diagonalSqrd;
	protected double side;
	protected int sideInt;
	protected double angle;
	protected double rotateAngle;
	
	public Square(int x1, int y1, int x2, int y2, int colourSelection){		//Square constructor, takes in 2 X and Y coordinates and a colour.
		pointX1 = x1;
		pointY1 = y1;
		pointX2 = x2;
		pointY2 = y2;
		colour = colourSelection;
		
		diagonalSqrd = ((pointX2 - pointX1)*(pointX2 - pointX1)) + ((pointY2 - pointY1)*(pointY2 - pointY1));
		side = Math.sqrt(diagonalSqrd/2);
		sideInt = (int)side;	//Finds square's diagonal and uses it to get side length from Pythagoras.
		
		angle = (double) Math.toDegrees(Math.atan2(pointY2 - pointY1, pointX2 - pointX1));	//Finds angle between 2 points

		    if(angle < 0){
		        angle += 360;
		    }
		
		//Finds the angle the the square will need to be rotated by.
		if(pointX1 < pointX2 && pointY1 < pointY2){		
			rotateAngle = (45 - angle);
			}
		else if(pointX1 < pointX2 && pointY1 > pointY2){
			rotateAngle = (315 - angle);
			}
		else if(pointX1 > pointX2 && pointY1 > pointY2){
			rotateAngle = (225 - angle);
		    }
		else {
			rotateAngle = (135 - angle);
			}
						
		}
	
	//Uses draw shape method from drawable interface.
	public void drawShape(Graphics g){
		Graphics2D g2d = (Graphics2D)g;		//Grahpics 2d cast allows rotation.
		
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
		
		AffineTransform saveAT = g2d.getTransform();	//Affine transform stores current transform of the graphics.
		g2d.rotate(Math.toRadians(-rotateAngle), pointX1, pointY1);		//Rotates graphics.
		
		//Finds quadrant square is in and draws.
		if(pointX1 < pointX2 && pointY1 < pointY2){
        g2d.drawRect(pointX1,pointY1,sideInt,sideInt);
		g2d.fillRect(pointX1, pointY1, sideInt, sideInt);
		} 
		else if(pointX1 > pointX2 && pointY1 > pointY2){
		g2d.drawRect(pointX1 - sideInt, pointY1 - sideInt, sideInt,sideInt);
		g2d.fillRect(pointX1 - sideInt, pointY1 - sideInt, sideInt, sideInt);
		}
		else if(pointX1 > pointX2 && pointY1 < pointY2){
		g2d.drawRect(pointX1 - sideInt, pointY1, sideInt,sideInt);
		g2d.fillRect(pointX1 - sideInt, pointY1, sideInt, sideInt);
		}
		else if(pointX1 < pointX2 && pointY1 > pointY2){
		g2d.drawRect(pointX1, pointY1 - sideInt, sideInt,sideInt);
		g2d.fillRect(pointX1, pointY1 - sideInt, sideInt, sideInt);
		}
		System.out.println("Angle =" + angle);
		System.out.println("Rotate Angle =" + rotateAngle);
		
		g2d.setTransform(saveAT);	//Transforms to original graphics orientation.
	}
	
}
