package dressup;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Launcher {
	
	public static void main(String[] args) {
		DressUpWindow dw = new DressUpWindow();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		dw.setLocation(screen.width/2-dw.getWidth()/2, screen.height/2-dw.getHeight()/2);
		dw.setVisible(true);
	}
}
