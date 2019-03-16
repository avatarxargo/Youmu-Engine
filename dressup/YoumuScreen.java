package dressup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class YoumuScreen extends JPanel{

	DressUpWindow dw;
	
	public YoumuScreen(DressUpWindow parent) {
		dw = parent;
		setBackground(Color.DARK_GRAY);
		//setPreferredSize(new Dimension(600,424));
	}
	
	@Override
    public void paintComponent(Graphics g) {
		for(ClothingLayer cl: dw.clothingLayers) {
			if(cl.isVisible()) {
				drawImage(cl.getImage(), g);
			}
		}
    }
	
	private void drawImage(Image img,Graphics g) {
		float scale = this.getHeight()/600f;
        g.drawImage(img.getScaledInstance((int)(scale*424), (int)(scale*600), Image.SCALE_SMOOTH),0,0,this);
	}
	
	 @Override
	  public Dimension getPreferredSize() {
		 ProportionalDimension pd = new ProportionalDimension(super.getPreferredSize(), 424./600.);
		 setMinimumSize(pd);
	    return pd;
	  }
	
	class ProportionalDimension extends Dimension {
		  public ProportionalDimension(Dimension d, double proportion) {
		    double x = d.width;
		    double y = d.height;
		    if (x / y < proportion) {
		      width = (int) (y * proportion);
		      height = (int) y;
		    } else {
		      width = (int) x;
		      height = (int) (x / proportion);
		    }
		  }
		}
}
