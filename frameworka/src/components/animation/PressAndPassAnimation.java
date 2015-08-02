package components.animation;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import components.Component;
import components.IComponent;

public class PressAndPassAnimation<T extends Node> extends Component<T>{
	
	private final PressAnimation<T> pressAnimation;
	private final PassAnimation<T> passAnimation;
	
	// Constructors
	
	public PressAndPassAnimation(	T node, 
									EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease,
									EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit,
									boolean keepOldHandlers	){
		super(node);
		this.pressAnimation = new PressAnimation<>(node, onPress, onRelease, keepOldHandlers);
		this.passAnimation = new PassAnimation<>(node, onEnter, onExit, keepOldHandlers);	
	}
	
	public PressAndPassAnimation(	T node, 
									EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease,
									EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit	){
		this(node, onPress, onRelease, onEnter, onExit, true);
	}

	public PressAndPassAnimation(	IComponent<T> component, 
									EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease,
									EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit,
									boolean keepOldHandlers	){
		this(component.getNode(), onPress, onRelease, onEnter, onExit, keepOldHandlers);	
	}

	public PressAndPassAnimation(	IComponent<T> component, 
									EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease,
									EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit	){
		this(component.getNode(), onPress, onRelease, onEnter, onExit);
	}

	
	// Implementation
	
	@Override
	public void mount() {
		pressAnimation.mount();
		passAnimation.mount();
	}

}
