package components.animation;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import components.IComponent;

public class PassAnimation<T extends Node> extends Animation<T>{
	
	public PassAnimation(T node, EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit, boolean keepOldHandlers){
		super(	node, 
				node.getOnMouseEntered(), onEnter,
				node.getOnMouseExited(), onExit,
				node::setOnMouseEntered, node::setOnMouseExited,
				keepOldHandlers	);
	}
	
	public PassAnimation(T node, EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit){
		this(node, onEnter, onExit, true);
	}
	
	public PassAnimation(IComponent<T> component, EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit, boolean keepOldHandlers){
		this(component.getNode(), onEnter, onEnter, keepOldHandlers);
	}
	
	public PassAnimation(IComponent<T> component, EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit){
		this(component.getNode(), onEnter, onExit);
	}

}
