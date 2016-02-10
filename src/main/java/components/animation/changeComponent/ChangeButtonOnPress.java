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
package components.animation.changeComponent;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import components.IComponent;
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

	public ChangeButtonOnPress(IComponent<T> component, ImageView onPress, ImageView onRelease, boolean keepOldHandlers) {
		this(component.getNode(), onPress, onRelease, keepOldHandlers);
	}
	
	public ChangeButtonOnPress(IComponent<T> component, ImageView onPress, ImageView onRelease) {
		this(component.getNode(), onPress, onRelease);
	}
	
	public ChangeButtonOnPress(IComponent<T> component, Image onPress, Image onRelease, boolean keepOldHandlers) {
		this(component.getNode(), onPress, onRelease, keepOldHandlers);
	}
	
	public ChangeButtonOnPress(IComponent<T> component, Image onPress, Image onRelease) {
		this(component.getNode(), onPress, onRelease);
	}
	
	public ChangeButtonOnPress(IComponent<T> component, String onPressPath, String onReleasePath, boolean keepOldHandlers) {
		this(component.getNode(), onPressPath, onReleasePath, keepOldHandlers);
	}
	
	public ChangeButtonOnPress(IComponent<T> component, String onPressPath, String onReleasePath) {
		this(component.getNode(), onPressPath, onReleasePath);
	}
	
	
}