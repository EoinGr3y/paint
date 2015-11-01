package utilities;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import shapes.Circle;
import shapes.Line;
import shapes.Shape;
import shapes.Square;
import shapes.Triangle;
import shapes.Rectangle;

//Canvas acts as a panel, therefore it needs to implement a mouseListener and motionListener to detect clicks and motion. ActionListener detects menu events.
public class Canvas extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private JMenuItem saveButton, loadButton;
	private JMenuItem undoButton, resetButton;
	private JMenuItem lineButton, circleButton, triangleButton, squareButton, rectangleButton;
	private JMenuItem blackButton, blueButton, cyanButton, darkGrayButton, grayButton, greenButton,
			magentaButton, orangeButton, pinkButton, redButton, whiteButton, yellowButton;

	private Stack<Shape> shapeList = new Stack<Shape>();

	private int pointX1 = 0;
	private int pointY1 = 0;
	private int pointX2 = 0;
	private int pointY2 = 0;
	private int trianglePointX2 = 0;
	private int trianglePointY2 = 0;
	private int pointX3 = 0;
	private int pointY3 = 0;
	private int motionPointX = 0;
	private int motionPointY = 0;
	private int motionTrianglePointX = 0;
	private int motionTrianglePointY = 0;
	private int motionTrianglePoint2X = 0;
	private int motionTrianglePoint2Y = 0;
	private double variableRadius = 0;
	private int variableRadInt = 0;

	private int pointNumber = 0;
	private int selectShape = 0;
	private int shapeColour = 0;

	public JMenuBar createMenuBar() {
		ArrayList<JMenuItem> fileMenuButtons = new ArrayList<JMenuItem>();
		ArrayList<JMenuItem> editMenuButtons = new ArrayList<JMenuItem>();
		ArrayList<JMenuItem> drawMenuButtons = new ArrayList<JMenuItem>();
		ArrayList<JMenuItem> colourMenuButtons = new ArrayList<JMenuItem>();

		JMenuBar menuBar = new JMenuBar();

		saveButton = new JMenuItem("Save Drawing");
		loadButton = new JMenuItem("Load Drawing");

		lineButton = new JMenuItem("Line");
		circleButton = new JMenuItem("Circle");
		triangleButton = new JMenuItem("Triangle");
		squareButton = new JMenuItem("Square");
		rectangleButton = new JMenuItem("Rectangle");

		undoButton = new JMenuItem("Undo");
		resetButton = new JMenuItem("Reset");

		blackButton = new JMenuItem("Black");
		blueButton = new JMenuItem("Blue");
		cyanButton = new JMenuItem("Cyan");
		darkGrayButton = new JMenuItem("Dark Gray");
		grayButton = new JMenuItem("Gray");
		greenButton = new JMenuItem("Green");
		magentaButton = new JMenuItem("Magenta");
		orangeButton = new JMenuItem("Orange");
		pinkButton = new JMenuItem("Pink");
		redButton = new JMenuItem("Red");
		whiteButton = new JMenuItem("White");
		yellowButton = new JMenuItem("Yellow");

		fileMenuButtons.add(saveButton);
		fileMenuButtons.add(loadButton);

		editMenuButtons.add(undoButton);
		editMenuButtons.add(resetButton);

		drawMenuButtons.add(lineButton);
		drawMenuButtons.add(circleButton);
		drawMenuButtons.add(triangleButton);
		drawMenuButtons.add(squareButton);
		drawMenuButtons.add(rectangleButton);

		colourMenuButtons.add(blackButton);
		colourMenuButtons.add(blueButton);
		colourMenuButtons.add(cyanButton);
		colourMenuButtons.add(darkGrayButton);
		colourMenuButtons.add(grayButton);
		colourMenuButtons.add(greenButton);
		colourMenuButtons.add(magentaButton);
		colourMenuButtons.add(orangeButton);
		colourMenuButtons.add(pinkButton);
		colourMenuButtons.add(redButton);
		colourMenuButtons.add(whiteButton);
		colourMenuButtons.add(yellowButton);

		menuBar.add(MenuFormatter.createMenuItem(this, "File", fileMenuButtons));
		menuBar.add(MenuFormatter.createMenuItem(this, "Edit", editMenuButtons));
		menuBar.add(MenuFormatter.createMenuItem(this, "Draw", drawMenuButtons));
		menuBar.add(MenuFormatter.createMenuItem(this, "Colour", colourMenuButtons));

		return menuBar;
	}

	public void actionPerformed(ActionEvent selectedMenuAction) {
		pointX1 = 0;
		pointY1 = 0;
		pointNumber = 0;

		if (selectedMenuAction.getSource().equals(saveButton)) {
			final JFileChooser saveChooser = new JFileChooser();
			int saveFile = saveChooser.showSaveDialog(Canvas.this);
			if (saveFile == JFileChooser.APPROVE_OPTION) {
				File save = saveChooser.getSelectedFile();
				System.out.println("Saving in:" + save.getName());
				try {
					ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream(save));
					for (Shape shapeHolder : shapeList) {
						fileOut.writeObject(shapeHolder);
					}
					fileOut.close();
				} catch (FileNotFoundException e) {
					System.out.println("File not found error" + e.getMessage());
				} catch (IOException e) {
					System.out.println("IO error: " + e.getMessage());
				}
			} else {
				System.out.println("Save command cancelled by user.");
			}
		}

		else if (selectedMenuAction.getSource().equals(loadButton)) {
			shapeList = new Stack<Shape>();
			Shape[] allShapes = new Shape[1000];
			int numberOfShapes = 0;
			int index = 0;
			final JFileChooser loadChooser = new JFileChooser();

			if (loadChooser.showOpenDialog(Canvas.this) == JFileChooser.APPROVE_OPTION) {
				File load = loadChooser.getSelectedFile();
				System.out.println("Loading from:" + load.getName());

				try {
					ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream(load));
					Shape shape = (Shape) fileIn.readObject();
					allShapes[index] = shape;

					index = 1;
					while (shape != null) {
						shape = (Shape) fileIn.readObject();
						allShapes[index] = shape;
						index++;
					}
					fileIn.close();
				} catch (IOException e) {
					if (index > 0) {
						numberOfShapes = index;
					} else {
						System.out.println("IO Error : " + e.getMessage());
					}
				} catch (ClassNotFoundException e) {
					System.out.println("Class Error : " + e.getMessage());
				}
				for (int i = 0; i < numberOfShapes; i++) {
					shapeList.add(allShapes[i]);
				}
				repaint();
			} else {
				System.out.println("Load command cancelled by user.");
			}
		}

		else if (selectedMenuAction.getSource().equals(undoButton) && shapeList != null) {
			shapeList.pop();
			repaint();
		}

		else if (selectedMenuAction.getSource().equals(resetButton)) {
			int option = JOptionPane
					.showConfirmDialog(null, "Clear current drawing, are you sure?");
			if (option == JOptionPane.YES_OPTION) {
				shapeList = new Stack<Shape>();
				pointX1 = 0;
				pointY1 = 0;
				pointX2 = 0;
				pointY2 = 0;
				trianglePointX2 = 0;
				trianglePointY2 = 0;
				pointX3 = 0;
				pointY3 = 0;
				motionPointX = 0;
				motionPointY = 0;
				motionTrianglePointX = 0;
				motionTrianglePointY = 0;
				motionTrianglePoint2X = 0;
				motionTrianglePoint2Y = 0;
				variableRadius = 0;
				variableRadInt = 0;
				pointNumber = 0;
				selectShape = 0;
				shapeColour = 0;
				repaint();
			}
		} else if (selectedMenuAction.getSource().equals(lineButton)) {
			selectShape = 1;
		} else if (selectedMenuAction.getSource().equals(circleButton)) {
			selectShape = 2;
		} else if (selectedMenuAction.getSource().equals(triangleButton)) {
			selectShape = 3;
		} else if (selectedMenuAction.getSource().equals(squareButton)) {
			selectShape = 4;
		} else if (selectedMenuAction.getSource().equals(rectangleButton)) {
			selectShape = 5;
		}

		else if (selectedMenuAction.getSource().equals(blackButton)) {
			shapeColour = 0;
		} else if (selectedMenuAction.getSource().equals(blueButton)) {
			shapeColour = 1;
		} else if (selectedMenuAction.getSource().equals(cyanButton)) {
			shapeColour = 2;
		} else if (selectedMenuAction.getSource().equals(darkGrayButton)) {
			shapeColour = 3;
		} else if (selectedMenuAction.getSource().equals(grayButton)) {
			shapeColour = 4;
		} else if (selectedMenuAction.getSource().equals(greenButton)) {
			shapeColour = 5;
		} else if (selectedMenuAction.getSource().equals(magentaButton)) {
			shapeColour = 6;
		} else if (selectedMenuAction.getSource().equals(orangeButton)) {
			shapeColour = 7;
		} else if (selectedMenuAction.getSource().equals(pinkButton)) {
			shapeColour = 8;
		} else if (selectedMenuAction.getSource().equals(redButton)) {
			shapeColour = 9;
		} else if (selectedMenuAction.getSource().equals(whiteButton)) {
			shapeColour = 10;
		} else if (selectedMenuAction.getSource().equals(yellowButton)) {
			shapeColour = 11;
		}
	}

	public Canvas() {

		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public Dimension getPreferredSize() {
		return new Dimension(1350, 670);
	}

	public void paintComponent(Graphics g) {
		g.setColor(FindColour(shapeColour));
		super.paintComponent(g);
		for (Shape temp : shapeList) {
			temp.drawShape(g);
		}

		if (pointNumber == 1 && selectShape == 1) {
			g.setColor(FindColour(shapeColour));
			g.drawLine(pointX1, pointY1, motionPointX, motionPointY);
		} else if (pointNumber == 1 && selectShape == 2) {
			g.setColor(FindColour(shapeColour));
			g.drawOval(pointX1 - variableRadInt, pointY1 - variableRadInt, variableRadInt * 2,
					variableRadInt * 2);
			g.fillOval(pointX1 - variableRadInt, pointY1 - variableRadInt, variableRadInt * 2,
					variableRadInt * 2);
		} else if ((pointNumber == 1 || pointNumber == 2) && selectShape == 3) {
			if (g.getColor() == (FindColour(shapeColour))) {
				g.setColor(FindColour(shapeColour));
				g.drawLine(pointX1, pointY1, motionTrianglePointX, motionTrianglePointY);
				g.drawLine(trianglePointX2, trianglePointY2, motionTrianglePoint2X,
						motionTrianglePoint2Y);
			} else {
				g.drawLine(trianglePointX2, trianglePointY2, motionTrianglePoint2X,
						motionTrianglePoint2Y);
				g.setColor(FindColour(shapeColour));
				g.drawLine(pointX1, pointY1, motionTrianglePointX, motionTrianglePointY);
			}
		} else if ((pointNumber == 1) && selectShape == 4) {
			g.setColor(FindColour(shapeColour));
			double diagonalSqrd = ((motionPointX - pointX1) * (motionPointX - pointX1))
					+ ((motionPointY - pointY1) * (motionPointY - pointY1));
			double side = Math.sqrt(diagonalSqrd / 2);
			int sideInt = (int) side;

			double motionAngle = (double) Math.toDegrees(Math.atan2(motionPointY - pointY1,
					motionPointX - pointX1));
			double motionRotateAngle;

			if (motionAngle < 0) {
				motionAngle += 360;
			}
			if (pointX1 < motionPointX && pointY1 < motionPointY) {
				motionRotateAngle = (45 - motionAngle);
			} else if (pointX1 < motionPointX && pointY1 > motionPointY) {
				motionRotateAngle = (315 - motionAngle);
			} else if (pointX1 > motionPointX && pointY1 > motionPointY) {
				motionRotateAngle = (225 - motionAngle);
			} else {
				motionRotateAngle = (135 - motionAngle);
			}

			Graphics2D g2d = (Graphics2D) g;
			g2d.rotate(Math.toRadians(-motionRotateAngle), pointX1, pointY1);
			if (pointX1 < motionPointX && pointY1 < motionPointY) {
				g2d.drawRect(pointX1, pointY1, sideInt, sideInt);
				g2d.fillRect(pointX1, pointY1, sideInt, sideInt);
				repaint();
			} else if (pointX1 > motionPointX && pointY1 > motionPointY) {
				g2d.drawRect(pointX1 - sideInt, pointY1 - sideInt, sideInt, sideInt);
				g2d.fillRect(pointX1 - sideInt, pointY1 - sideInt, sideInt, sideInt);
				repaint();
			} else if (pointX1 > motionPointX && pointY1 < motionPointY) {
				g2d.drawRect(pointX1 - sideInt, pointY1, sideInt, sideInt);
				g2d.fillRect(pointX1 - sideInt, pointY1, sideInt, sideInt);
				repaint();
			} else if (pointX1 < motionPointX && pointY1 > motionPointY) {
				g2d.drawRect(pointX1, pointY1 - sideInt, sideInt, sideInt);
				g2d.fillRect(pointX1, pointY1 - sideInt, sideInt, sideInt);
				repaint();
			}
		} else if ((pointNumber == 1) && selectShape == 5) {
			g.setColor(FindColour(shapeColour));
			int width = Math.abs(motionPointX - pointX1);
			int height = Math.abs(motionPointY - pointY1);

			if (pointX1 < motionPointX && pointY1 < motionPointY) {
				g.drawRect(pointX1, pointY1, width, height);
				g.fillRect(pointX1, pointY1, width, height);
			} else if (pointX1 > motionPointX && pointY1 > motionPointY) {
				g.drawRect(pointX1 - width, pointY1 - height, width, height);
				g.fillRect(pointX1 - width, pointY1 - height, width, height);
			} else if (pointX1 > motionPointX && pointY1 < motionPointY) {
				g.drawRect(pointX1 - width, pointY1, width, height);
				g.fillRect(pointX1 - width, pointY1, width, height);
			} else if (pointX1 < motionPointX && pointY1 > motionPointY) {
				g.drawRect(pointX1, pointY1 - height, width, height);
				g.fillRect(pointX1, pointY1 - height, width, height);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		pointNumber++;
		if (pointNumber == 1) {
			pointX1 = e.getX();
			pointY1 = e.getY();
		} else if (pointNumber == 2 && selectShape == 1) {
			pointX2 = e.getX();
			pointY2 = e.getY();
			Line line = new Line(pointX1, pointY1, pointX2, pointY2, shapeColour);
			shapeList.add(line);
			repaint();
			pointNumber = 0;
		} else if (pointNumber == 2 && selectShape == 2) {
			pointX2 = e.getX();
			pointY2 = e.getY();
			Circle circle = new Circle(pointX1, pointY1, pointX2, pointY2, shapeColour);
			shapeList.add(circle);
			repaint();
			pointNumber = 0;
		} else if (pointNumber == 2 && selectShape == 3) {
			trianglePointX2 = e.getX();
			trianglePointY2 = e.getY();
		} else if (pointNumber == 3 && selectShape == 3) {
			pointX3 = e.getX();
			pointY3 = e.getY();
			Triangle triangle = new Triangle(pointX1, pointY1, trianglePointX2, trianglePointY2,
					pointX3, pointY3, shapeColour);
			shapeList.add(triangle);
			repaint();
			pointNumber = 0;
		} else if (pointNumber == 2 && selectShape == 4) {
			pointX2 = e.getX();
			pointY2 = e.getY();

			Square square = new Square(pointX1, pointY1, pointX2, pointY2, shapeColour);
			shapeList.add(square);
			repaint();
			pointNumber = 0;
		} else if (pointNumber == 2 && selectShape == 5) {
			pointX2 = e.getX();
			pointY2 = e.getY();

			Rectangle rectangle = new Rectangle(pointX1, pointY1, pointX2, pointY2, shapeColour);
			shapeList.add(rectangle);
			repaint();
			pointNumber = 0;
		} else {
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (pointNumber == 1 && selectShape == 1) {
			motionPointX = e.getX();
			motionPointY = e.getY();
			repaint();
		} else if (pointNumber == 1 && selectShape == 2) {
			motionPointX = e.getX();
			motionPointY = e.getY();
			variableRadius = Math.round(Math.sqrt(Math.pow((motionPointX - pointX1), 2)
					+ Math.pow((motionPointY - pointY1), 2)));
			variableRadInt = (int) variableRadius;
			repaint();
		} else if (pointNumber == 1 && selectShape == 3) {
			motionTrianglePointX = e.getX();
			motionTrianglePointY = e.getY();
			repaint();
		} else if (pointNumber == 2 && selectShape == 3) {
			motionTrianglePoint2X = e.getX();
			motionTrianglePoint2Y = e.getY();
			repaint();
		} else if (pointNumber == 1 && selectShape == 4) {
			motionPointX = e.getX();
			motionPointY = e.getY();
			repaint();
		} else if (pointNumber == 1 && selectShape == 5) {
			motionPointX = e.getX();
			motionPointY = e.getY();
			repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	public Color FindColour(int selection) {
		if (selection == 0) {
			return Color.BLACK;
		} else if (selection == 1) {
			return Color.BLUE;
		} else if (selection == 2) {
			return Color.CYAN;
		} else if (selection == 3) {
			return Color.DARK_GRAY;
		} else if (selection == 4) {
			return Color.GRAY;
		} else if (selection == 5) {
			return Color.GREEN;
		} else if (selection == 6) {
			return Color.MAGENTA;
		} else if (selection == 7) {
			return Color.ORANGE;
		} else if (selection == 8) {
			return Color.PINK;
		} else if (selection == 9) {
			return Color.RED;
		} else if (selection == 10) {
			return Color.WHITE;
		} else if (selection == 11) {
			return Color.YELLOW;
		} else {
			return Color.BLACK;
		}
	}
}