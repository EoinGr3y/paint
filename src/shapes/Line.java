package shapes;

import java.awt.*;

import utilities.Drawable;

@SuppressWarnings("serial")
public class Line extends Shape implements Drawable{
	
	protected int pointX1;
	protected int pointY1;	
	protected int pointX2;
	protected int pointY2;
	protected int colour;
	
	public Line(int x1, int y1, int x2, int y2, int colourSelection){
		pointX1 = x1;
		pointY1 = y1;
		pointX2 = x2;
		pointY2 = y2;
		colour = colourSelection;
		}
		
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
		
        g2d.drawLine(pointX1,pointY1,pointX2,pointY2);
	}
}
