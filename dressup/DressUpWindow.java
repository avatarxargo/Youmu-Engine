package dressup;
import java.awt.AWTError;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SpringLayout;
import javax.swing.plaf.basic.BasicSplitPaneDivider;

public class DressUpWindow extends JFrame{
	
	JScrollPane optionWrap;
	JPanel options;
	JSplitPane content;
	YoumuScreen youmuScreen;
	ArrayList<ClothingLayer> clothingLayers;
	
	public DressUpWindow() {
		super("Youmu Dress Up [First Prototype]");
		setSize(700,(int)(700*(550f/600f)));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/konpakuicon512.png"));
		this.setIconImage(icon.getImage());
		//
		this.addComponentListener(new ComponentListener() {
		    public void componentResized(ComponentEvent e) {
		    	DressUpWindow me = (DressUpWindow)e.getSource();
		    	int h = me.getHeight();
		    	int w = (int)(me.getHeight()*(550f/600f));
		    	if(w>me.getWidth()) {
		    		me.setSize(new Dimension(w,h));
		    	}
		    	content.setDividerLocation((int)(content.getHeight()*(424f/600f)));
		    }

			@Override
			public void componentHidden(ComponentEvent arg0) {
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {				
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
			}
		});
		//
		options = new JPanel();
		options.setLayout(new BoxLayout(options,BoxLayout.PAGE_AXIS));
		optionWrap = new JScrollPane(options);
		youmuScreen = new YoumuScreen(this);
		content = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,youmuScreen,optionWrap);
		content.setEnabled(false);
		//content.setResizeWeight(1);
		this.add(content);
		//
		loadClothes();
		//
		//content.add(youmuScreen, BorderLayout.CENTER);
		//content.add(options, BorderLayout.EAST);
		this.setVisible(true);
	}
	
	private void loadClothes() {
		clothingLayers = new ArrayList<>();
		ArrayList<JComponent> toshow = new ArrayList<>();
		Scanner scanner = new Scanner(getClass().getResourceAsStream("/images/youmu.txt"), "UTF-8");
		outer:
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			System.out.println(line);
			if(line.length()==0) {continue;}
			//parse
			boolean add = true;
			boolean sshow = false;
			//combo
			if(line.startsWith(";;")) {
				int comboindex = Integer.parseInt(line.split(";;")[1]);
				ArrayList<ClothingLayer> layerStash = new ArrayList<>(); 
				while(scanner.hasNextLine()) {
					line = scanner.nextLine();
					System.out.println("XXX"+line);
					if(line.startsWith(";;")) {
						//wrap up combobox
						JComboBox<String> chooserCombo = new ChooserCombo(comboindex, layerStash, youmuScreen);
						toshow.add(chooserCombo);
						continue outer;
					} else {
						String[] split = line.split(";");
						ClothingLayer fresh = new ClothingLayer(split[0], loadImage(split[1]));
						layerStash.add(fresh);
						clothingLayers.add(fresh);
					}
				}
			}
		    //combo
			if(line.endsWith("+")) {
				sshow = true;
				line = line.substring(0, line.length()-1);
			}
			if(line.endsWith(";")) {
				add = false;
				line = line.substring(0, line.length()-1);
			}
			//make
			String[] split = line.split(";");
			ClothingLayer fresh = new ClothingLayer(split[0], loadImage(split[1]));
			clothingLayers.add(fresh);
			if(add) {
				ChooserCheck cc = new ChooserCheck(clothingLayers.get(clothingLayers.size()-1),youmuScreen);
				if(sshow) {
					cc.setSelected(true);
				}
				toshow.add(cc);
			}
			if(sshow) {
				fresh.setVisible(true);
			}
		}
		//
		Color mint = new Color(212,255,252);
		for(int i=toshow.size()-1;i>=0;--i) {
			options.add(toshow.get(i));
			toshow.get(i).setBackground(mint);
		}
		// bonus
		SpecialChooserCombo sc = new SpecialChooserCombo(clothingLayers.get(1), clothingLayers.get(clothingLayers.size()-1), clothingLayers.get(0), youmuScreen);
		sc.setBackground(mint);
		options.add(sc);
		options.setBackground(mint);
		//addNoOption("base 1","base1");
		//addCheckOption("Konpaku bra","kbra");
		//addCheckOption("Konpaku panties","kpanties");
	}
	
	private Image loadImage(String path) {
		 InputStream is = new BufferedInputStream(getClass().getResourceAsStream("/images/"+path+".png"));
		 try {
			Image image = ImageIO.read(is);
			is.close();
			return image;
	     } catch (IOException e) {
	    	 System.err.println("Image "+path+" not in resources!!!");
	    	 return null;
	     }
	}
	/*
	private void addNoOption(String name, String path) {
		clothingLayers.add(new ClothingLayer(name, loadImage(path)));
	}
	
	private void addCheckOption(String name, String path) {
		clothingLayers.add(new ClothingLayer(name, loadImage(path)));
		options.add(new ChooserCheck(clothingLayers.get(clothingLayers.size()-1),youmuScreen));
	}*/
}
