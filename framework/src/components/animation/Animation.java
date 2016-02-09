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

import java.util.function.Consumer;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import utils.Utils;

import components.Component;
import components.IComponent;

public class Animation<T extends Node> extends Component<T>{
	
	protected final boolean keepOldHandlers;
	private final EventHandler<? super MouseEvent> oldEnterHandler;
	private final EventHandler<? super MouseEvent> newEnterHandler;
	private final EventHandler<? super MouseEvent> oldExitHandler;
	private final EventHandler<? super MouseEvent> newExitHandler;
	private final Consumer<EventHandler<? super MouseEvent>> enterSetter;
	private final Consumer<EventHandler<? super MouseEvent>> exitSetter;
	
	// Constructors
	
	public Animation(	T node, 
						EventHandler<? super MouseEvent> oldEnterHandler, EventHandler<? super MouseEvent> newEnterHandler,
						EventHandler<? super MouseEvent> oldExitHandler, EventHandler<? super MouseEvent> newExitHandler,
						Consumer<EventHandler<? super MouseEvent>> enterSetter, Consumer<EventHandler<? super MouseEvent>> exitSetter,
						boolean keepOldHandlers){
		super(node);
		this.oldEnterHandler = oldEnterHandler;
		this.newEnterHandler = newEnterHandler;
		this.oldExitHandler = oldExitHandler;
		this.newExitHandler = newExitHandler;
		this.enterSetter = enterSetter == null ? (handler)->{} : enterSetter;
		this.exitSetter = exitSetter == null ? (handler)->{} : exitSetter;
		this.keepOldHandlers = keepOldHandlers;
	}
	
	public Animation(	T node, 
						EventHandler<? super MouseEvent> oldEnterHandler, EventHandler<? super MouseEvent> newEnterHandler,
						EventHandler<? super MouseEvent> oldExitHandler, EventHandler<? super MouseEvent> newExitHandler,
						Consumer<EventHandler<? super MouseEvent>> enterSetter, Consumer<EventHandler<? super MouseEvent>> exitSetter	){
		this(node, oldEnterHandler, newEnterHandler, oldExitHandler, newExitHandler, enterSetter, exitSetter, true);
	}
	
	public Animation(	IComponent<T> component, 
						EventHandler<? super MouseEvent> oldEnterHandler, EventHandler<? super MouseEvent> newEnterHandler,
						EventHandler<? super MouseEvent> oldExitHandler, EventHandler<? super MouseEvent> newExitHandler,
						Consumer<EventHandler<? super MouseEvent>> enterSetter, Consumer<EventHandler<? super MouseEvent>> exitSetter,
						boolean keepOldHandlers){
		this(component.getNode(), oldEnterHandler, newEnterHandler, oldExitHandler, newExitHandler, enterSetter, exitSetter, keepOldHandlers);
	}

	public Animation(	IComponent<T> component, 
						EventHandler<? super MouseEvent> oldEnterHandler, EventHandler<? super MouseEvent> newEnterHandler,
						EventHandler<? super MouseEvent> oldExitHandler, EventHandler<? super MouseEvent> newExitHandler,
						Consumer<EventHandler<? super MouseEvent>> enterSetter, Consumer<EventHandler<? super MouseEvent>> exitSetter	){
		this(component.getNode(), oldEnterHandler, newEnterHandler, oldExitHandler, newExitHandler, enterSetter, exitSetter);
	}

	
	// Implementation
	
	@Override
	public void mount(){
		mountEnterAnimation();
		mountExitAnimation();
	}
	
	protected void mountEnterAnimation(){
		EventHandler<? super MouseEvent> oldHandler = oldEnterHandler;
		EventHandler<? super MouseEvent> newHandler = newEnterHandler;
		
		newHandler = Utils.chain(oldHandler, newHandler, keepOldHandlers);
		
		enterSetter.accept(newHandler);
	}
	
	protected void mountExitAnimation(){
		EventHandler<? super MouseEvent> oldHandler = oldExitHandler;
		EventHandler<? super MouseEvent> newHandler = newExitHandler;
		
		newHandler = Utils.chain(oldHandler, newHandler, keepOldHandlers);
		
		exitSetter.accept(newHandler);
	}

}
