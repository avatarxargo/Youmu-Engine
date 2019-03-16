package dressup;
import java.awt.Image;

public class ClothingLayer {
	private boolean visible;
	private String displayName;
	private Image image;
	
	public ClothingLayer(String name, Image img) {
		setDisplayName(name);
		setImage(img);
		setVisible(false);
	}
	
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
}
