package main;

import javax.swing.*;

import utilities.Canvas;

//Driver Class
public class Paint{
	
	private static void createAndShowGUI() {
        JFrame frame = new JFrame("Paint");
        Canvas canvas = new Canvas();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setJMenuBar(canvas.createMenuBar());
        frame.add(canvas);
        frame.pack();				
        frame.setVisible(true);	
    }
	public static void main(String args[])
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createAndShowGUI();
			}
		});
	}	
}
