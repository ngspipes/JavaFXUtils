package components.animation.magnifier;

import javafx.scene.image.ImageView;

import components.animation.PassAnimation;


public class ImageMagnifier<T extends ImageView> extends PassAnimation<T>{
	
	private static final double DEFAULT_MAGNIFY_AMP = 1.5;
	
	// Constructors
	
	public ImageMagnifier(T image, double magnifyAmp, boolean keepOldHandlers){
		super(	image,
				(event)->{	image.fitWidthProperty().set(image.fitWidthProperty().get()*magnifyAmp);
							image.fitHeightProperty().set(image.fitHeightProperty().get()*magnifyAmp);	},
				(event)->{	image.fitWidthProperty().set(image.fitWidthProperty().get()/magnifyAmp);
							image.fitHeightProperty().set(image.fitHeightProperty().get()/magnifyAmp);	},
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

}
