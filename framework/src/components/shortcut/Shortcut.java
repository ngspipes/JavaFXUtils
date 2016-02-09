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
package components.shortcut;

import java.util.Collection;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;
import utils.Utils;

import components.Component;
import components.IComponent;
import components.shortcut.Keys.Key;


public class Shortcut<T extends Node> extends Component<T>{
	
	private final Keys keys;
	private final boolean keepOldHandler;
	private final boolean receiveChildEvents; 
	
	// Constructors
	
	public Shortcut(T node, Keys keys, boolean keepOldHandler, boolean receiveChildEvents) {
		super(node);
		this.keys = keys;
		this.keepOldHandler = keepOldHandler;
		this.receiveChildEvents = receiveChildEvents;
	}
	
	public Shortcut(T node, Keys keys, boolean keepOldHandler) {
		this(node, keys, keepOldHandler, false);
	}
	
	public Shortcut(T node, Keys keys) {
		this(node, keys, true);
	}
	
	public Shortcut(T node, Collection<Pair<KeyCode, Runnable>> actions, boolean keepOldHandler) {
		this(node, new Keys(actions), keepOldHandler);
	}
	
	public Shortcut(T node, Collection<Pair<KeyCode, Runnable>> actions) {
		this(node, actions, true);
	}
	
	public Shortcut(IComponent<T> component, Keys keys, boolean keepOldHandler) {
		this(component.getNode(), keys, keepOldHandler);
	}

	public Shortcut(IComponent<T> component, Keys keys) {
		this(component, keys, true);
	}
	
	public Shortcut(IComponent<T> component, Collection<Pair<KeyCode, Runnable>> actions, boolean keepOldHandler) {
		this(component.getNode(), actions, keepOldHandler);
	}
	
	public Shortcut(IComponent<T> component, Collection<Pair<KeyCode, Runnable>> actions) {
		this(component, actions, true);
	}
	
	
	// Implementation
	
	public void mount(){
		EventHandler<? super KeyEvent> oldHandler = this.node.getOnKeyPressed();
		EventHandler<? super KeyEvent> newHandler = (event)->{
														if(this.node.hashCode()!=event.getTarget().hashCode() && !receiveChildEvents)
															return;
			
														if(keys.contains(event.getCode())){
															Key key = keys.getKey(event.getCode());
															
															if(key.match(event))
																key.getAction().accept(event);	
														}
													};
						
		newHandler = Utils.chain(oldHandler, newHandler, keepOldHandler);
		
		this.node.setOnKeyPressed(newHandler);
	}

}
