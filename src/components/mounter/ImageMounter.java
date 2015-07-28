package components.mounter;

import javafx.scene.image.ImageView;

import components.animation.magnifier.ImageMagnifier;

public class ImageMounter extends ComponentMounter{

	private final ImageView image;
	
	public ImageMounter(ImageView image) {
		super(image);
		this.image = image;
	}

	public ImageMounter imageMagnifier(double magnifyAmp, boolean keepOldHandlers){
		this.components.add(new ImageMagnifier(image, magnifyAmp, keepOldHandlers));
		return this;
	}
	
	public ImageMounter imageMagnifier(double magnifyAmp){
		this.components.add(new ImageMagnifier(image, magnifyAmp));
		return this;
	}
	
	public ImageMounter imageMagnifier(boolean keepOldHandlers){
		this.components.add(new ImageMagnifier(image, keepOldHandlers));
		return this;
	}
	
	public ImageMounter imageMagnifier(){
		this.components.add(new ImageMagnifier(image));
		return this;
	}
	
}
