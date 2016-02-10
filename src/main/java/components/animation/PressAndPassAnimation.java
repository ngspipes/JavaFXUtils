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

import components.Component;
import components.IComponent;

public class PressAndPassAnimation<T extends Node> extends Component<T>{
	
	private final PressAnimation<T> pressAnimation;
	private final PassAnimation<T> passAnimation;
	
	// Constructors
	
	public PressAndPassAnimation(	T node, 
									EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease,
									EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit,
									boolean keepOldHandlers	){
		super(node);
		this.pressAnimation = new PressAnimation<>(node, onPress, onRelease, keepOldHandlers);
		this.passAnimation = new PassAnimation<>(node, onEnter, onExit, keepOldHandlers);	
	}
	
	public PressAndPassAnimation(	T node, 
									EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease,
									EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit	){
		this(node, onPress, onRelease, onEnter, onExit, true);
	}

	public PressAndPassAnimation(	IComponent<T> component, 
									EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease,
									EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit,
									boolean keepOldHandlers	){
		this(component.getNode(), onPress, onRelease, onEnter, onExit, keepOldHandlers);	
	}

	public PressAndPassAnimation(	IComponent<T> component, 
									EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease,
									EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit	){
		this(component.getNode(), onPress, onRelease, onEnter, onExit);
	}

	
	// Implementation
	
	@Override
	public void mount() {
		pressAnimation.mount();
		passAnimation.mount();
	}

}
