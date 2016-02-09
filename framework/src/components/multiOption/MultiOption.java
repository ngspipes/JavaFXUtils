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
package components.multiOption;

import java.util.Collection;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.MenuItem;
import javafx.util.Pair;

import components.Component;
import components.IComponent;
import components.multiOption.Operations.Operation;

public class MultiOption<T extends Control> extends Component<T>{
	
	private final Operations operations;
	private final Control control;
	
	// Constructors
	
	public MultiOption(T control, Operations operations) {
		super(control);
		this.operations = operations;
		this.control = control;
	}
	
	public MultiOption(T control, Collection<Pair<String, Runnable>> actions) {
		this(control, new Operations(actions));
	}
	
	public MultiOption(IComponent<T> component, Operations operations) {
		this(component.getNode(), operations);
	}
	
	public MultiOption(IComponent<T> component, Collection<Pair<String, Runnable>> actions) {
		this(component.getNode(), actions);
	}
	
	
	// Implementation
	
	public void mount(){
		ContextMenu menu = new ContextMenu();
		
		MenuItem menuItem;
		for(Operation operation : operations.getOperations()){
			menuItem = new MenuItem(operation.getName());
			menuItem.setOnAction((event) -> operation.getAction().accept(event));
			menu.getItems().add(menuItem);	
		}
		
		control.setContextMenu(menu);
	}

}
