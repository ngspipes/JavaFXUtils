package components.mounter;

import javafx.scene.image.ImageView;

import components.IComponent;
import components.animation.magnifier.ImageMagnifier;

public class ImageMounter<T extends ImageView> extends ComponentMounter<T>{

	private final T image;
	
	public ImageMounter(T image) {
		super(image);
		this.image = image;
	}
	
	public ImageMounter(IComponent<T> component) {
		this(component.getNode());
	}

	public ImageMounter<T> imageMagnifier(double magnifyAmp, boolean keepOldHandlers){
		this.components.add(new ImageMagnifier<>(image, magnifyAmp, keepOldHandlers));
		return this;
	}
	
	public ImageMounter<T> imageMagnifier(double magnifyAmp){
		this.components.add(new ImageMagnifier<>(image, magnifyAmp));
		return this;
	}
	
	public ImageMounter<T> imageMagnifier(boolean keepOldHandlers){
		this.components.add(new ImageMagnifier<>(image, keepOldHandlers));
		return this;
	}
	
	public ImageMounter<T> imageMagnifier(){
		this.components.add(new ImageMagnifier<>(image));
		return this;
	}
	
}
