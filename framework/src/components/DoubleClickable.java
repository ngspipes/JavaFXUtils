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
package components;

import java.util.function.Consumer;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import utils.Utils;

public class DoubleClickable<T extends Node> extends Component<T>{
	
	private final Consumer<MouseEvent> action;
	private final boolean keepOldHandler;
	
	// Constructors
	
	public DoubleClickable(T node, Consumer<MouseEvent> action, boolean keepOldHandler) {
		super(node);
		this.action = action;
		this.keepOldHandler = keepOldHandler;
	}
	
	public DoubleClickable(T node, Consumer<MouseEvent> action) {
		this(node, action, true);
	}
	
	public DoubleClickable(T node, Runnable action, boolean keepOldHandler) {
		this(node, (event)->action.run(), keepOldHandler);
	}
	
	public DoubleClickable(T node, Runnable action) {
		this(node, (event)->action.run());
	}
			
	public DoubleClickable(IComponent<T> component, Consumer<MouseEvent> action, boolean keepOldHandler) {
		this(component.getNode(), action, keepOldHandler);
	}
	
	public DoubleClickable(IComponent<T> component, Consumer<MouseEvent> action) {
		this(component.getNode(), action);
	}
		
	public DoubleClickable(IComponent<T> component, Runnable action, boolean keepOldHandler) {
		this(component.getNode(), (event)->action.run(), keepOldHandler);
	}
	
	public DoubleClickable(IComponent<T> component, Runnable action) {
		this(component.getNode(), action);
	}

	
	// Implementation
	
	@Override
	public void mount() {
		EventHandler<? super MouseEvent> oldHandler = this.node.getOnMouseClicked();
		EventHandler<? super MouseEvent> newHandler = (event)->{	
															if(event.getClickCount() == 2)
																action.accept(event);
														};
		
		newHandler = Utils.chain(oldHandler, newHandler, keepOldHandler);
														
		this.getNode().setOnMouseClicked(newHandler);
	}
	
}
