package components.animation.changeComponent;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import components.animation.PressAnimation;


public class ChangeButtonOnPress<T extends Button> extends PressAnimation<T>{

	// Constructors
	
	public ChangeButtonOnPress(T button, ImageView onPress, ImageView onRelease, boolean keepOldHandlers) {
		super(	button,
				(onPress==null) ? null : (event)->button.setGraphic(onPress),
				(onRelease==null) ?  null : (event)->button.setGraphic(onRelease), 
				keepOldHandlers	);
	}
	
	public ChangeButtonOnPress(T button, ImageView onPress, ImageView onRelease) {
		this(button, onPress, onRelease, true);
	}
	
	public ChangeButtonOnPress(T button, Image onPress, Image onRelease, boolean keepOldHandlers) {
		this(button, onPress==null ? null : new ImageView(onPress), onRelease==null ? null : new ImageView(onRelease), keepOldHandlers);	
	}
	
	public ChangeButtonOnPress(T button, Image onPress, Image onRelease) {
		this(button, onPress, onRelease, true);
	}
	
	public ChangeButtonOnPress(T button, String onPressPath, String onReleasePath, boolean keepOldHandlers) {
		this(button, onPressPath==null ? null : new Image(onPressPath), onReleasePath==null ? null : new Image(onReleasePath), keepOldHandlers);
	}
	
	public ChangeButtonOnPress(T button, String onPressPath, String onReleasePath) {
		this(button, onPressPath, onReleasePath, true);
	}
	
}
