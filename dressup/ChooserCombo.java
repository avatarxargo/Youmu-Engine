package dressup;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ChooserCombo extends JComboBox<String>{
	
	private ArrayList<ClothingLayer> callback;
	private int last;
	private YoumuScreen screen;
	
	public ChooserCombo(int index, ArrayList<ClothingLayer> layers, YoumuScreen screen) {
		for(ClothingLayer cl: layers) {
			addItem(cl.getDisplayName());
		}
		setSelectedIndex(index);
		layers.get(index).setVisible(true);
		last = index;
		this.screen = screen;
		setMaximumSize(new Dimension(350, 25));
		callback = layers;
		addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				callback.get(last).setVisible(false);
				callback.get(getSelectedIndex()).setVisible(true);
				last = getSelectedIndex();
				getScreen().repaint();
			}
		});
	}
	
	private YoumuScreen getScreen() {
		return screen;
	}
}
