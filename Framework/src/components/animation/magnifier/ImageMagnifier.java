package components.animation.magnifier;

import javafx.scene.image.ImageView;

import components.animation.PassAnimation;


public class ImageMagnifier extends PassAnimation{
	
	private static final double DEFAULT_MAGNIFY_AMP = 1.5;
	
	// Constructors
	
	public ImageMagnifier(ImageView image, double magnifyAmp, boolean keepOldHandlers){
		super(	image,
				(event)->{	image.fitWidthProperty().set(image.fitWidthProperty().get()*magnifyAmp);
							image.fitHeightProperty().set(image.fitHeightProperty().get()*magnifyAmp);	},
				(event)->{	image.fitWidthProperty().set(image.fitWidthProperty().get()/magnifyAmp);
							image.fitHeightProperty().set(image.fitHeightProperty().get()/magnifyAmp);	},
				keepOldHandlers	);
	}
	
	public ImageMagnifier(ImageView image, double magnifyAmp){
		this(image, magnifyAmp, true);
	}
	
	public ImageMagnifier(ImageView image, boolean keepOldHandlers){
		this(image, DEFAULT_MAGNIFY_AMP, keepOldHandlers);
	}
	
	public ImageMagnifier(ImageView image){
		this(image, true);
	}

}
