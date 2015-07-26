package components;

import java.util.function.Consumer;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import utils.ComponentException;

public class DoubleClickable extends Component{
	
	private final Consumer<MouseEvent> action;
	private final boolean keepOldHendler;
	
	// Constructors
	
	public DoubleClickable(Node elem, Consumer<MouseEvent> action, boolean keepOldHandler) {
		super(elem);
		this.action = action;
		this.keepOldHendler = keepOldHandler;
	}
	
	public DoubleClickable(Node elem, Runnable action, boolean keepOldHandler) {
		this(elem, (event)->action.run(), keepOldHandler);
	}
	
	public DoubleClickable(IComponent component, Consumer<MouseEvent> action, boolean keepOldHandler) {
		this(component.getNode(), action, keepOldHandler);
	}
	
	public DoubleClickable(IComponent component, Runnable action, boolean keepOldHandler) {
		this(component.getNode(), (event)->action.run(), keepOldHandler);
	}
	
	public DoubleClickable(Node elem, Consumer<MouseEvent> action) {
		this(elem, action, false);
	}
	
	public DoubleClickable(IComponent component, Consumer<MouseEvent> action) {
		this(component.getNode(), action);
	}
	
	public DoubleClickable(Node elem, Runnable action) {
		this(elem, (event)->action.run());
	}
	
	public DoubleClickable(IComponent component, Runnable action) {
		this(component.getNode(), action);
	}

	
	// Implementation
	
	@Override
	public void mount() throws ComponentException {
		EventHandler<? super MouseEvent> oldHandler = this.node.getOnMouseClicked();
		EventHandler<? super MouseEvent> newHandler = null;
		
		if(!keepOldHendler || oldHandler == null){	
			newHandler = (event)->{	
							if(event.getClickCount() == 2)
								action.accept(event);
							};
		}else{
			newHandler = (event)->{
							oldHandler.handle(event);
							
							if(event.getClickCount() == 2)
								action.accept(event);
							};
		}
		
		this.getNode().setOnMouseClicked(newHandler);
	}
	
}
