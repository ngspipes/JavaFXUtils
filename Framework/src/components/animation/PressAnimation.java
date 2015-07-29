package components.animation;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import components.IComponent;

public class PressAnimation<T extends Node> extends Animation<T> {
	
	public PressAnimation(T node, EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease, boolean keepOldHandlers){
		super(	node, 
				node.getOnMousePressed(), onPress,
				node.getOnMouseReleased(), onRelease,
				node::setOnMousePressed, node::setOnMouseReleased,
				keepOldHandlers	);
	}
	
	public PressAnimation(T node, EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease){
		this(node, onPress, onRelease, true);
	}
	
	public PressAnimation(IComponent<T> component, EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease, boolean keepOldHandlers){
		this(component.getNode(), onPress, onRelease, keepOldHandlers);
	}
	
	public PressAnimation(IComponent<T> component, EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease){
		this(component.getNode(), onPress, onRelease);
	}

}
