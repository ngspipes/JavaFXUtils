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
package components.animation;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import components.IComponent;

public class PressAnimation<T extends Node> extends Animation<T> {
	
	public PressAnimation(T node, EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease, boolean keepOldHandlers){
		super(	node, 
				node.getOnMousePressed(), onPress,
				node.getOnMouseReleased(), onRelease,
				node::setOnMousePressed, node::setOnMouseReleased,
				keepOldHandlers	);
	}
	
	public PressAnimation(T node, EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease){
		this(node, onPress, onRelease, true);
	}
	
	public PressAnimation(IComponent<T> component, EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease, boolean keepOldHandlers){
		this(component.getNode(), onPress, onRelease, keepOldHandlers);
	}
	
	public PressAnimation(IComponent<T> component, EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease){
		this(component.getNode(), onPress, onRelease);
	}

}
