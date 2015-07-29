package components.animation;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import components.IComponent;

public class PressAnimation extends Animation {
	
	public PressAnimation(Node node, EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease, boolean keepOldHandlers){
		super(	node, 
				node.getOnMousePressed(), onPress,
				node.getOnMouseReleased(), onRelease,
				node::setOnMousePressed, node::setOnMouseReleased,
				keepOldHandlers	);
	}
	
	public PressAnimation(Node node, EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease){
		this(node, onPress, onRelease, true);
	}
	
	public PressAnimation(IComponent component, EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease, boolean keepOldHandlers){
		this(component.getNode(), onPress, onRelease, keepOldHandlers);
	}
	
	public PressAnimation(IComponent component, EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease){
		this(component.getNode(), onPress, onRelease);
	}

}
