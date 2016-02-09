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
