package components.animation.magnifier;

import javafx.scene.control.Button;

import components.animation.PassAnimation;


public class ButtonMagnifier extends PassAnimation{
	
	private static final double DEFAULT_MAGNIFY_AMP = 1.5;
	
	//Constructors
	
	public ButtonMagnifier(Button button, double magnifyAmp, boolean keepOldHandlers){
		super(	button,
				(event)->{	button.setMinHeight(button.getHeight()*magnifyAmp);
		        			button.setMinWidth(button.getWidth()*magnifyAmp);	},
				(event)->{	button.setMinHeight(button.getHeight()/magnifyAmp);
		        			button.setMinWidth(button.getWidth()/magnifyAmp);	},
				keepOldHandlers);
	}
	
	public ButtonMagnifier(Button button, double magnifyAmp){
		this(button, magnifyAmp, false);
	}
	
	public ButtonMagnifier(Button button, boolean keepOldHandlers){
		this(button, DEFAULT_MAGNIFY_AMP, keepOldHandlers);
	}
	
	public ButtonMagnifier(Button button){
		this(button, false);
	}
	
}
