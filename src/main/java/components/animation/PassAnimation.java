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

import components.IComponent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class PassAnimation<T extends Node> extends Animation<T>{
	
	public PassAnimation(T node, EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit, boolean keepOldHandlers){
		super(	node, 
				node.getOnMouseEntered(), onEnter,
				node.getOnMouseExited(), onExit,
				node::setOnMouseEntered, node::setOnMouseExited,
				keepOldHandlers	);
	}
	
	public PassAnimation(T node, EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit){
		this(node, onEnter, onExit, true);
	}
	
	public PassAnimation(IComponent<T> component, EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit, boolean keepOldHandlers){
		this(component.getNode(), onEnter, onExit, keepOldHandlers);
	}
	
	public PassAnimation(IComponent<T> component, EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit){
		this(component.getNode(), onEnter, onExit);
	}

}
