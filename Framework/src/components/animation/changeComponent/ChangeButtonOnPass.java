package components.animation.changeComponent;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import components.animation.PassAnimation;


public class ChangeButtonOnPass extends PassAnimation{
	
	// Constructors
	
	public ChangeButtonOnPass(Button button, ImageView onEnter, ImageView onExit, boolean keepOldHandlers) {
		super(	button, 
				(onEnter==null) ? null : (event)->button.setGraphic(onEnter),
				(onExit==null) ?  null : (event)->button.setGraphic(onExit),
				keepOldHandlers	);
	}
	
	public ChangeButtonOnPass(Button button, ImageView onEnter, ImageView onExit) {
		this(button, onEnter, onExit, true);
	}
	
	public ChangeButtonOnPass(Button button, Image onEnter, Image onExit, boolean keepOldHandlers) {
		this(button, onEnter==null? null:new ImageView(onEnter) , onExit==null?null:new ImageView(onExit), keepOldHandlers);
	}
	
	public ChangeButtonOnPass(Button button, Image onEnter, Image onExit) {
		this(button, onEnter, onExit, true);
	}
	
	public ChangeButtonOnPass(Button button, String onEnterPath, String onExitPath, boolean keepOldHandlers) {
		this(button, onEnterPath==null ? null : new Image(onEnterPath), onExitPath==null ? null : new Image(onExitPath), keepOldHandlers);
	}
	
	public ChangeButtonOnPass(Button button, String onEnterPath, String onExitPath) {
		this(button, onEnterPath , onExitPath, true);
	}

}
