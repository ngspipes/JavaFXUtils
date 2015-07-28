package components.animation;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import components.IComponent;

public class PassAnimation extends Animation{
	
	public PassAnimation(Node node, EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit, boolean keepOldHandlers){
		super(	node, 
				node.getOnMouseEntered(), onEnter,
				node.getOnMouseExited(), onExit,
				node::setOnMouseEntered, node::setOnMouseExited,
				keepOldHandlers	);
	}
	
	public PassAnimation(Node node, EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit){
		this(node, onEnter, onExit, false);
	}
	
	public PassAnimation(IComponent component, EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit, boolean keepOldHandlers){
		this(component.getNode(), onEnter, onEnter, keepOldHandlers);
	}
	
	public PassAnimation(IComponent component, EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit){
		this(component.getNode(), onEnter, onExit);
	}

}
