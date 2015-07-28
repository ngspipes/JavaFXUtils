package components.mounter;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import components.animation.changeComponent.ChangeButtonOnPass;
import components.animation.changeComponent.ChangeButtonOnPress;
import components.animation.magnifier.ButtonMagnifier;

public class ButtonMounter extends ControlMounter{
	
	private final Button button;
	
	public ButtonMounter(Button button){
		super(button);
		this.button = button;
	}

	
	public ButtonMounter changeButtonOnPress(ImageView onPress, ImageView onRelease, boolean keepOldHandlers) {
		this.components.add(new ChangeButtonOnPress(button, onPress, onRelease, keepOldHandlers));
		return this;
	}
	
	public ButtonMounter changeButtonOnPress(ImageView onPress, ImageView onRelease) {
		this.components.add(new ChangeButtonOnPress(button, onPress, onRelease));
		return this;
	}
	
	public ButtonMounter changeButtonOnPress(Image onPress, Image onRelease, boolean keepOldHandlers) {
		this.components.add(new ChangeButtonOnPress(button, onPress, onRelease, keepOldHandlers));
		return this;
	}
	
	public ButtonMounter changeButtonOnPress(Image onPress, Image onRelease) {
		this.components.add(new ChangeButtonOnPress(button, onPress, onRelease));
		return this;
	}
	
	public ButtonMounter changeButtonOnPress(String onPressPath, String onReleasePath, boolean keepOldHandlers) {
		this.components.add(new ChangeButtonOnPress(button, onPressPath, onReleasePath, keepOldHandlers));
		return this;
	}
	
	public ButtonMounter changeButtonOnPress(String onPressPath, String onReleasePath) {
		this.components.add(new ChangeButtonOnPress(button, onPressPath, onReleasePath));
		return this;
	}
	
	public ButtonMounter changeButtonOnPass(ImageView onEnter, ImageView onExit, boolean keepOldHandlers) {
		this.components.add(new ChangeButtonOnPass(button, onEnter, onExit, keepOldHandlers));
		return this;
	}
	
	public ButtonMounter changeButtonOnPass(ImageView onEnter, ImageView onExit) {
		this.components.add(new ChangeButtonOnPass(button, onEnter, onExit));
		return this;
	}
	
	public ButtonMounter changeButtonOnPass(Image onEnter, Image onExit, boolean keepOldHandlers) {
		this.components.add(new ChangeButtonOnPass(button, onEnter, onExit, keepOldHandlers));
		return this;
	}
	
	public ButtonMounter changeButtonOnPass(Image onEnter, Image onExit) {
		this.components.add(new ChangeButtonOnPass(button, onEnter, onExit));
		return this;
	}
	
	public ButtonMounter changeButtonOnPass(String onEnterPath, String onExitPath, boolean keepOldHandlers) {
		this.components.add(new ChangeButtonOnPass(button, onEnterPath, onExitPath, keepOldHandlers));
		return this;
	}
	
	public ButtonMounter changeButtonOnPass(String onEnterPath, String onExitPath) {
		this.components.add(new ChangeButtonOnPass(button, onEnterPath, onExitPath));
		return this;
	}

	public ButtonMounter buttonMagnifier(double magnifyAmp, boolean keepOldHandlers){
		this.components.add(new ButtonMagnifier(button, magnifyAmp, keepOldHandlers));
		return this;
	}
	
	public ButtonMounter buttonMagnifier(double magnifyAmp){
		this.components.add(new ButtonMagnifier(button, magnifyAmp));
		return this;
	}
	
	public ButtonMounter buttonMagnifier(boolean keepOldHandlers){
		this.components.add(new ButtonMagnifier(button, keepOldHandlers));
		return this;
	}
	
	public ButtonMounter buttonMagnifier(){
		this.components.add(new ButtonMagnifier(button));
		return this;
	}
	
}
