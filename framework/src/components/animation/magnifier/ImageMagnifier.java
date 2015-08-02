package components.animation.magnifier;

import javafx.scene.image.ImageView;

import components.IComponent;
import components.animation.PassAnimation;


public class ImageMagnifier<T extends ImageView> extends PassAnimation<T>{
	
	private static final double DEFAULT_MAGNIFY_AMP = 1.5;
	
	// Constructors
	
	public ImageMagnifier(T image, double magnifyAmp, boolean keepOldHandlers){
		super(	image, 
				(event)->{	image.setFitWidth(image.getImage().getWidth()*magnifyAmp);
							image.setFitHeight(image.getImage().getHeight()*magnifyAmp);	},
				(event)->{	image.setFitWidth(image.getImage().getWidth()/magnifyAmp);
							image.setFitHeight(image.getImage().getHeight()/magnifyAmp);	},
				keepOldHandlers	);
	}
	
	public ImageMagnifier(T image, double magnifyAmp){
		this(image, magnifyAmp, true);
	}
	
	public ImageMagnifier(T image, boolean keepOldHandlers){
		this(image, DEFAULT_MAGNIFY_AMP, keepOldHandlers);
	}
	
	public ImageMagnifier(T image){
		this(image, true);
	}
	
	public ImageMagnifier(IComponent<T> component, double magnifyAmp, boolean keepOldHandlers){
		this(component.getNode(), magnifyAmp, keepOldHandlers);
	}
	
	public ImageMagnifier(IComponent<T> component, double magnifyAmp){
		this(component.getNode(), magnifyAmp);
	}
	
	public ImageMagnifier(IComponent<T> component, boolean keepOldHandlers){
		this(component.getNode(), keepOldHandlers);
	}
	
	public ImageMagnifier(IComponent<T> component){
		this(component.getNode());
	}


}
