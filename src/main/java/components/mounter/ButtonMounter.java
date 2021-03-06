/*-
 * Copyright (c) 2016, NGSPipes Team <ngspipes@gmail.com>
 * All rights reserved.
 *
 * This file is part of NGSPipes <http://ngspipes.github.io/>.
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package components.mounter;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import components.IComponent;
import components.animation.changeComponent.ChangeButtonOnPass;
import components.animation.changeComponent.ChangeButtonOnPress;
import components.animation.magnifier.ButtonMagnifier;

public class ButtonMounter<T extends Button> extends ControlMounter<T>{
	
	private final T button;
	
	public ButtonMounter(T button){
		super(button);
		this.button = button;
	}
	
	public ButtonMounter(IComponent<T> component){
		this(component.getNode());
	}

	
	public ButtonMounter<T> changeButtonOnPress(ImageView onPress, ImageView onRelease, boolean keepOldHandlers) {
		this.components.add(new ChangeButtonOnPress<>(button, onPress, onRelease, keepOldHandlers));
		return this;
	}
	
	public ButtonMounter<T> changeButtonOnPress(ImageView onPress, ImageView onRelease) {
		this.components.add(new ChangeButtonOnPress<>(button, onPress, onRelease));
		return this;
	}
	
	public ButtonMounter<T> changeButtonOnPress(Image onPress, Image onRelease, boolean keepOldHandlers) {
		this.components.add(new ChangeButtonOnPress<>(button, onPress, onRelease, keepOldHandlers));
		return this;
	}
	
	public ButtonMounter<T> changeButtonOnPress(Image onPress, Image onRelease) {
		this.components.add(new ChangeButtonOnPress<>(button, onPress, onRelease));
		return this;
	}
	
	public ButtonMounter<T> changeButtonOnPress(String onPressPath, String onReleasePath, boolean keepOldHandlers) {
		this.components.add(new ChangeButtonOnPress<>(button, onPressPath, onReleasePath, keepOldHandlers));
		return this;
	}
	
	public ButtonMounter<T> changeButtonOnPress(String onPressPath, String onReleasePath) {
		this.components.add(new ChangeButtonOnPress<>(button, onPressPath, onReleasePath));
		return this;
	}
	
	public ButtonMounter<T> changeButtonOnPass(ImageView onEnter, ImageView onExit, boolean keepOldHandlers) {
		this.components.add(new ChangeButtonOnPass<>(button, onEnter, onExit, keepOldHandlers));
		return this;
	}
	
	public ButtonMounter<T> changeButtonOnPass(ImageView onEnter, ImageView onExit) {
		this.components.add(new ChangeButtonOnPass<>(button, onEnter, onExit));
		return this;
	}
	
	public ButtonMounter<T> changeButtonOnPass(Image onEnter, Image onExit, boolean keepOldHandlers) {
		this.components.add(new ChangeButtonOnPass<>(button, onEnter, onExit, keepOldHandlers));
		return this;
	}
	
	public ButtonMounter<T> changeButtonOnPass(Image onEnter, Image onExit) {
		this.components.add(new ChangeButtonOnPass<>(button, onEnter, onExit));
		return this;
	}
	
	public ButtonMounter<T> changeButtonOnPass(String onEnterPath, String onExitPath, boolean keepOldHandlers) {
		this.components.add(new ChangeButtonOnPass<>(button, onEnterPath, onExitPath, keepOldHandlers));
		return this;
	}
	
	public ButtonMounter<T> changeButtonOnPass(String onEnterPath, String onExitPath) {
		this.components.add(new ChangeButtonOnPass<>(button, onEnterPath, onExitPath));
		return this;
	}

	public ButtonMounter<T> buttonMagnifier(double magnifyAmp, boolean keepOldHandlers){
		this.components.add(new ButtonMagnifier<>(button, magnifyAmp, keepOldHandlers));
		return this;
	}
	
	public ButtonMounter<T> buttonMagnifier(double magnifyAmp){
		this.components.add(new ButtonMagnifier<>(button, magnifyAmp));
		return this;
	}
	
	public ButtonMounter<T> buttonMagnifier(boolean keepOldHandlers){
		this.components.add(new ButtonMagnifier<>(button, keepOldHandlers));
		return this;
	}
	
	public ButtonMounter<T> buttonMagnifier(){
		this.components.add(new ButtonMagnifier<>(button));
		return this;
	}
	
}
