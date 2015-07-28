 package components.animation.changeMouse;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.image.Image;

import components.IComponent;
import components.animation.PressAnimation;

public class ChangeMouseOnPress extends PressAnimation{
	
	// Constructors
	
	public ChangeMouseOnPress(Node node, Cursor onPress, Cursor onRelease, boolean keepOldHandlers) {
		super(	node,
				(onPress==null) ? null : (event)->((Node) event.getSource()).setCursor(onPress),
				(onRelease==null) ? null : (event)->((Node) event.getSource()).setCursor(onRelease),
				keepOldHandlers	);
	}
	
	public ChangeMouseOnPress(Node node, Cursor onPress, Cursor onRelease) {
		this(node, onPress, onRelease, false);
	}
	
	public ChangeMouseOnPress(Node node, Image onPress, Image onRelease, boolean keepOldHandlers) {
		this(node, onPress==null ? null : new ImageCursor(onPress), onRelease==null ? null : new ImageCursor(onRelease), keepOldHandlers);
	}
	
	public ChangeMouseOnPress(Node node, Image onPress, Image onRelease) {
		this(node, onPress, onRelease, false);
	}
	
	public ChangeMouseOnPress(Node node, String onPressPath, String onReleasePath, boolean keepOldHandlers) {
		this(node, onPressPath==null ? null : new Image(onPressPath), onReleasePath==null ? null : new Image(onReleasePath), keepOldHandlers);
	}
	
	public ChangeMouseOnPress(Node node, String onPressPath, String onReleasePath) {
		this(node, onPressPath, onReleasePath, false);
	}
	
	public ChangeMouseOnPress(IComponent component, Cursor onPress, Cursor onRelease, boolean keepOldHandlers) {
		this(component.getNode(), onPress, onRelease, keepOldHandlers);
	}
	
	public ChangeMouseOnPress(IComponent component, Cursor onPress, Cursor onRelease) {
		this(component.getNode(), onPress, onRelease);
	}
	
	public ChangeMouseOnPress(IComponent component, Image onPress, Image onRelease, boolean keepOldHandlers) {
		this(component.getNode(), onPress, onRelease, keepOldHandlers);
	}
	
	public ChangeMouseOnPress(IComponent component, Image onPress, Image onRelease) {
		this(component.getNode(), onPress, onRelease);
	}
	
	public ChangeMouseOnPress(IComponent component, String onPressPath, String onReleasePath, boolean keepOldHandlers) {
		this(component.getNode(), onPressPath, onReleasePath, keepOldHandlers);
	}
	
	public ChangeMouseOnPress(IComponent component, String onPressPath, String onReleasePath) {
		this(component.getNode(), onPressPath, onReleasePath);
	}

}
