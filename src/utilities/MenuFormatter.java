package utilities;

import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuFormatter {

	private static JMenu menu;

	public static JMenu createMenuItem(Canvas canvas, String menuName,
			ArrayList<JMenuItem> menuButtons) {
		menu = new JMenu(menuName);

		for (JMenuItem menuButton : menuButtons) {
			menu.add(menuButton);
			menuButton.addActionListener(canvas);
		}

		return menu;
	}

}
