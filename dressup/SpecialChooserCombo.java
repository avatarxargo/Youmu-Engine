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

public class SpecialChooserCombo extends JComboBox<String>{
	
	private ClothingLayer a1, a2, b;
	private YoumuScreen screen;
	
	public SpecialChooserCombo(ClothingLayer a1,  ClothingLayer a2,  ClothingLayer b, YoumuScreen screen) {
		addItem("Base: Youmu & Myon");
		addItem("Base: Only Youmu");
		setSelectedIndex(0);
		a1.setVisible(true);
		a2.setVisible(true);
		this.screen = screen;
		this.a1 = a1;
		this.a2 = a2;
		this.b = b;
		setMaximumSize(new Dimension(350, 25));
		addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(getSelectedIndex() == 0) {
					b().setVisible(false);
					a1().setVisible(true);
					a2().setVisible(true);
				} else {
					b().setVisible(true);
					a1().setVisible(false);
					a2().setVisible(false);
				}
			}
		});
	}
	
	private ClothingLayer a1() {
		return a1;
	}
	private ClothingLayer a2() {
		return a2;
	}
	private ClothingLayer b() {
		return b;
	}
}
