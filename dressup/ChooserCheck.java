package dressup;

import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ChooserCheck extends JCheckBox{
	
	private ClothingLayer callback;
	private YoumuScreen screen;
	
	public ChooserCheck(ClothingLayer layer, YoumuScreen screen) {
		super(layer.getDisplayName());
		setSelected(layer.isVisible());
		this.screen = screen;
		callback = layer;
		addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				callback.setVisible(isSelected());
				getScreen().repaint();
			}
	    });
	}
	
	private YoumuScreen getScreen() {
		return screen;
	}
}
