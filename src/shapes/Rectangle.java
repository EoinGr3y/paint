package shapes;

import java.awt.*;

import utilities.Drawable;

	//Rectangle class, subclass of shape and implements drawable interface.
	@SuppressWarnings("serial")
	public class Rectangle extends Shape implements Drawable{
	
	protected int pointX1;
	protected int pointY1;	
	protected int pointX2;
	protected int pointY2;
	protected int colour;
	protected int width;
	protected int height;
	
	public Rectangle(int x1, int y1, int x2, int y2, int colourSelection){		//Rectangle constructor, takes in 2 X and Y coordinates and a colour.
		pointX1 = x1;
		pointY1 = y1;
		pointX2 = x2;
		pointY2 = y2;
		colour = colourSelection;
				
		width = Math.abs(x2 - x1);		//Finds width and height.
		height = Math.abs(y2 - y1);		
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
		
		//Finds quadrant rectangle is in and draws.
		if(pointX1 < pointX2 && pointY1 < pointY2){
        g2d.drawRect(pointX1,pointY1,width,height);
        g2d.fillRect(pointX1,pointY1,width,height);
		} 
		else if(pointX1 > pointX2 && pointY1 > pointY2){
		g2d.drawRect(pointX1 - width, pointY1 - height, width,height);
		g2d.fillRect(pointX1 - width, pointY1 - height, width,height);
		}
		else if(pointX1 > pointX2 && pointY1 < pointY2){
		g2d.drawRect(pointX1 - width, pointY1, width,height);
		g2d.fillRect(pointX1 - width, pointY1, width,height);
		}
		else if(pointX1 < pointX2 && pointY1 > pointY2){
		g2d.drawRect(pointX1, pointY1 - height, width,height);	
		g2d.fillRect(pointX1, pointY1 - height, width,height);
		}
	}
	
}
