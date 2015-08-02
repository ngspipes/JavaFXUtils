package components.animation.magnifier;

import javafx.scene.control.Button;

import components.IComponent;
import components.animation.PassAnimation;


public class ButtonMagnifier<T extends Button> extends PassAnimation<T>{
	
	private static final double DEFAULT_MAGNIFY_AMP = 1.5;
	
	//Constructors
	
	public ButtonMagnifier(T button, double magnifyAmp, boolean keepOldHandlers){
		super(	button,
				(event)->{	button.setMinHeight(button.getHeight()*magnifyAmp);
		        			button.setMinWidth(button.getWidth()*magnifyAmp);	},
				(event)->{	button.setMinHeight(button.getHeight()/magnifyAmp);
		        			button.setMinWidth(button.getWidth()/magnifyAmp);	},
				keepOldHandlers	);
	}
	
	public ButtonMagnifier(T button, double magnifyAmp){
		this(button, magnifyAmp, true);
	}
	
	public ButtonMagnifier(T button, boolean keepOldHandlers){
		this(button, DEFAULT_MAGNIFY_AMP, keepOldHandlers);
	}
	
	public ButtonMagnifier(T button){
		this(button, true);
	}
	
	public ButtonMagnifier(IComponent<T> component, double magnifyAmp, boolean keepOldHandlers){
		this(component.getNode(), magnifyAmp, keepOldHandlers);
	}
	
	public ButtonMagnifier(IComponent<T> component, double magnifyAmp){
		this(component.getNode(), magnifyAmp);
	}
	
	public ButtonMagnifier(IComponent<T> component, boolean keepOldHandlers){
		this(component.getNode(), keepOldHandlers);
	}
	
	public ButtonMagnifier(IComponent<T> component){
		this(component.getNode());
	}
	
	
}
