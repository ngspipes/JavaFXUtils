package components;

import java.util.function.Consumer;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import utils.Utils;

public class DoubleClickable extends Component{
	
	private final Consumer<MouseEvent> action;
	private final boolean keepOldHandler;
	
	// Constructors
	
	public DoubleClickable(Node node, Consumer<MouseEvent> action, boolean keepOldHandler) {
		super(node);
		this.action = action;
		this.keepOldHandler = keepOldHandler;
	}
	
	public DoubleClickable(Node node, Runnable action, boolean keepOldHandler) {
		this(node, (event)->action.run(), keepOldHandler);
	}
	
	public DoubleClickable(IComponent component, Consumer<MouseEvent> action, boolean keepOldHandler) {
		this(component.getNode(), action, keepOldHandler);
	}
	
	public DoubleClickable(IComponent component, Runnable action, boolean keepOldHandler) {
		this(component.getNode(), (event)->action.run(), keepOldHandler);
	}
	
	public DoubleClickable(Node node, Consumer<MouseEvent> action) {
		this(node, action, false);
	}
	
	public DoubleClickable(IComponent component, Consumer<MouseEvent> action) {
		this(component.getNode(), action);
	}
	
	public DoubleClickable(Node node, Runnable action) {
		this(node, (event)->action.run());
	}
	
	public DoubleClickable(IComponent component, Runnable action) {
		this(component.getNode(), action);
	}

	
	// Implementation
	
	@Override
	public void mount() {
		EventHandler<? super MouseEvent> oldHandler = this.node.getOnMouseClicked();
		EventHandler<? super MouseEvent> newHandler = (event)->{	
															if(event.getClickCount() == 2)
																action.accept(event);
														};
		
		newHandler = Utils.chain(oldHandler, newHandler, keepOldHandler);
														
		this.getNode().setOnMouseClicked(newHandler);
	}
	
}
