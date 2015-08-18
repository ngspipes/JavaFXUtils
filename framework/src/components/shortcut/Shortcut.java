package components.shortcut;

import java.util.Collection;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;
import utils.Utils;

import components.Component;
import components.IComponent;
import components.shortcut.Keys.Key;


public class Shortcut<T extends Node> extends Component<T>{
	
	private final Keys keys;
	private final boolean keepOldHandler;
	
	// Constructors
	
	public Shortcut(T node, Keys keys, boolean keepOldHandler) {
		super(node);
		this.keys = keys;
		this.keepOldHandler = keepOldHandler;
	}
	
	public Shortcut(T node, Keys keys) {
		this(node, keys, true);
	}
	
	public Shortcut(T node, Collection<Pair<KeyCode, Runnable>> actions, boolean keepOldHandler) {
		this(node, new Keys(actions), keepOldHandler);
	}
	
	public Shortcut(T node, Collection<Pair<KeyCode, Runnable>> actions) {
		this(node, actions, true);
	}
	
	public Shortcut(IComponent<T> component, Keys keys, boolean keepOldHandler) {
		this(component.getNode(), keys, keepOldHandler);
	}

	public Shortcut(IComponent<T> component, Keys keys) {
		this(component, keys, true);
	}
	
	public Shortcut(IComponent<T> component, Collection<Pair<KeyCode, Runnable>> actions, boolean keepOldHandler) {
		this(component.getNode(), actions, keepOldHandler);
	}
	
	public Shortcut(IComponent<T> component, Collection<Pair<KeyCode, Runnable>> actions) {
		this(component, actions, true);
	}
	
	
	// Implementation
	
	public void mount(){
		EventHandler<? super KeyEvent> oldHandler = this.node.getOnKeyPressed();
		EventHandler<? super KeyEvent> newHandler = (event)->{
														if(keys.contains(event.getCode())){
															Key key = keys.getKey(event.getCode());
															
															if(key.match(event))
																key.getAction().run();	
														}
													};
						
		newHandler = Utils.chain(oldHandler, newHandler, keepOldHandler);
		
		this.node.setOnKeyPressed(newHandler);
	}

}
