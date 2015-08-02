 package components.animation.changeMouse;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.image.Image;

import components.IComponent;
import components.animation.PressAnimation;

public class ChangeMouseOnPress<T extends Node> extends PressAnimation<T>{
	
	// Constructors
	
	public ChangeMouseOnPress(T node, Cursor onPress, Cursor onRelease, boolean keepOldHandlers) {
		super(	node,
				(onPress==null) ? null : (event)->((Node) event.getSource()).setCursor(onPress),
				(onRelease==null) ? null : (event)->((Node) event.getSource()).setCursor(onRelease),
				keepOldHandlers	);
	}
	
	public ChangeMouseOnPress(T node, Cursor onPress, Cursor onRelease) {
		this(node, onPress, onRelease, true);
	}
	
	public ChangeMouseOnPress(T node, Image onPress, Image onRelease, boolean keepOldHandlers) {
		this(node, onPress==null ? null : new ImageCursor(onPress), onRelease==null ? null : new ImageCursor(onRelease), keepOldHandlers);
	}
	
	public ChangeMouseOnPress(T node, Image onPress, Image onRelease) {
		this(node, onPress, onRelease, true);
	}
	
	public ChangeMouseOnPress(T node, String onPressPath, String onReleasePath, boolean keepOldHandlers) {
		this(node, onPressPath==null ? null : new Image(onPressPath), onReleasePath==null ? null : new Image(onReleasePath), keepOldHandlers);
	}
	
	public ChangeMouseOnPress(T node, String onPressPath, String onReleasePath) {
		this(node, onPressPath, onReleasePath, true);
	}
	
	public ChangeMouseOnPress(IComponent<T> component, Cursor onPress, Cursor onRelease, boolean keepOldHandlers) {
		this(component.getNode(), onPress, onRelease, keepOldHandlers);
	}
	
	public ChangeMouseOnPress(IComponent<T> component, Cursor onPress, Cursor onRelease) {
		this(component.getNode(), onPress, onRelease);
	}
	
	public ChangeMouseOnPress(IComponent<T> component, Image onPress, Image onRelease, boolean keepOldHandlers) {
		this(component.getNode(), onPress, onRelease, keepOldHandlers);
	}
	
	public ChangeMouseOnPress(IComponent<T> component, Image onPress, Image onRelease) {
		this(component.getNode(), onPress, onRelease);
	}
	
	public ChangeMouseOnPress(IComponent<T> component, String onPressPath, String onReleasePath, boolean keepOldHandlers) {
		this(component.getNode(), onPressPath, onReleasePath, keepOldHandlers);
	}
	
	public ChangeMouseOnPress(IComponent<T> component, String onPressPath, String onReleasePath) {
		this(component.getNode(), onPressPath, onReleasePath);
	}

}
