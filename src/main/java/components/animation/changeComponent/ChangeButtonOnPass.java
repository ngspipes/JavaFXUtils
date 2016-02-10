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
import components.animation.PassAnimation;


public class ChangeButtonOnPass<T extends Button> extends PassAnimation<T>{
	
	// Constructors
	
	public ChangeButtonOnPass(T button, ImageView onEnter, ImageView onExit, boolean keepOldHandlers) {
		super(	button, 
				(onEnter==null) ? null : (event)->button.setGraphic(onEnter),
				(onExit==null) ?  null : (event)->button.setGraphic(onExit),
				keepOldHandlers	);
	}
	
	public ChangeButtonOnPass(T button, ImageView onEnter, ImageView onExit) {
		this(button, onEnter, onExit, true);
	}
	
	public ChangeButtonOnPass(T button, Image onEnter, Image onExit, boolean keepOldHandlers) {
		this(button, onEnter==null? null:new ImageView(onEnter) , onExit==null?null:new ImageView(onExit), keepOldHandlers);
	}
	
	public ChangeButtonOnPass(T button, Image onEnter, Image onExit) {
		this(button, onEnter, onExit, true);
	}
	
	public ChangeButtonOnPass(T button, String onEnterPath, String onExitPath, boolean keepOldHandlers) {
		this(button, onEnterPath==null ? null : new Image(onEnterPath), onExitPath==null ? null : new Image(onExitPath), keepOldHandlers);
	}
	
	public ChangeButtonOnPass(T button, String onEnterPath, String onExitPath) {
		this(button, onEnterPath , onExitPath, true);
	}

	public ChangeButtonOnPass(IComponent<T> component, ImageView onEnter, ImageView onExit, boolean keepOldHandlers) {
		this(component.getNode(), onEnter, onExit, keepOldHandlers);
	}
	
	public ChangeButtonOnPass(IComponent<T> component, ImageView onEnter, ImageView onExit) {
		this(component.getNode(), onEnter, onExit);
	}
	
	public ChangeButtonOnPass(IComponent<T> component, Image onEnter, Image onExit, boolean keepOldHandlers) {
		this(component.getNode(), onEnter, onExit, keepOldHandlers);
	}
	
	public ChangeButtonOnPass(IComponent<T> component, Image onEnter, Image onExit) {
		this(component.getNode(), onEnter, onExit);
	}
	
	public ChangeButtonOnPass(IComponent<T> component, String onEnterPath, String onExitPath, boolean keepOldHandlers) {
		this(component.getNode(), onEnterPath, onExitPath, keepOldHandlers);
	}
	
	public ChangeButtonOnPass(IComponent<T> component, String onEnterPath, String onExitPath) {
		this(component.getNode(), onEnterPath, onExitPath);
	}

	
}
