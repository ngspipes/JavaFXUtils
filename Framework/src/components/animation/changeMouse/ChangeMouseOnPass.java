package components.animation.changeMouse;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.image.Image;

import components.IComponent;
import components.animation.PassAnimation;

public class ChangeMouseOnPass<T extends Node> extends PassAnimation<T>{
	
	// Constructors
	
	public ChangeMouseOnPass(T node, Cursor onEnter, Cursor onExit, boolean keepOldHandlers) {
		super(	node,
				(onEnter==null) ? null : (event)->((Node) event.getSource()).setCursor(onEnter),
				(onExit==null) ? null : (event)->((Node) event.getSource()).setCursor(onExit),
				keepOldHandlers	);
	}
	
	public ChangeMouseOnPass(T node, Cursor onEnter, Cursor onExit) {
		this(node, onEnter, onExit, true);
	}
	
	public ChangeMouseOnPass(T node, Image onEnter, Image onExit, boolean keepOldHandlers) {
		this(node, onEnter==null ? null : new ImageCursor(onEnter), onExit==null ? null : new ImageCursor(onExit), keepOldHandlers);
	}
	
	public ChangeMouseOnPass(T node, Image onEnter, Image onExit) {
		this(node, onEnter, onExit, true);
	}
	
	public ChangeMouseOnPass(T node, String onEnterPath, String onExitPath, boolean keepOldHandlers) {
		this(node, onEnterPath==null ? null : new Image(onEnterPath), onExitPath==null ? null : new Image(onExitPath), keepOldHandlers);
	}
	
	public ChangeMouseOnPass(T node, String onEnterPath, String onExitPath) {
		this(node, onEnterPath, onExitPath, true);
	}
	
	public ChangeMouseOnPass(IComponent<T> component, Cursor onEnter, Cursor onExit, boolean keepOldHandlers) {
		this(component.getNode(), onEnter, onExit, keepOldHandlers);	
	}
	
	public ChangeMouseOnPass(IComponent<T> component, Cursor onEnter, Cursor onExit) {
		this(component.getNode(), onEnter, onExit);
	}
	
	public ChangeMouseOnPass(IComponent<T> component, Image onEnter, Image onExit, boolean keepOldHandlers) {
		this(component.getNode(), onEnter, onExit, keepOldHandlers);
	}
	
	public ChangeMouseOnPass(IComponent<T> component, Image onEnter, Image onExit) {
		this(component.getNode(), onEnter, onExit);
	}
	
	public ChangeMouseOnPass(IComponent<T> component, String onEnterPath, String onExitPath, boolean keepOldHandlers) {
		this(component.getNode(), onEnterPath, onExitPath, keepOldHandlers);
	}
	
	public ChangeMouseOnPass(IComponent<T> component, String onEnterPath, String onExitPath) {
		this(component.getNode(), onEnterPath, onExitPath);
	}
	
}
