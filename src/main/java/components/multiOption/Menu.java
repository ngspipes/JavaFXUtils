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

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import jfxutils.Utils;

import components.Component;
import components.IComponent;
import components.multiOption.Operations.Operation;

public class Menu<T extends Node> extends Component<T>{
	
	private final Operations operations;
	private ContextMenu menu;
	
	// Constructors
	
	public Menu(T node, Operations operations) {
		super(node);
		this.operations = operations;
	}
	
	public Menu(T node, Collection<Pair<String, Runnable>> actions) {
		this(node, new Operations(actions));
	}
	
	public Menu(IComponent<T> component, Operations operations) {
		this(component.getNode(), operations);
	}
	
	public Menu(IComponent<T> component, Collection<Pair<String, Runnable>> actions) {
		this(component.getNode(), actions);
	}
	
	
	// Implementation
	
	public void mount(){
		EventHandler<? super MouseEvent> oldHandler = this.node.getOnMouseClicked();
		EventHandler<? super MouseEvent> newHandler = (event) -> {
			if(!event.getButton().equals(MouseButton.SECONDARY))
				return;
			
			if(menu!=null){
				menu.setAnchorX(event.getScreenX());
				menu.setAnchorY(event.getScreenY());
				return;
			}
				
			menu = getMenu();
			menu.show(this.node, event.getScreenX(), event.getScreenY());
			menu.setOnHiding((a)->menu = null);
		};
		
		newHandler = Utils.chain(oldHandler, newHandler);
		this.node.setOnMouseClicked(newHandler);
	}
	
	private ContextMenu getMenu(){
		ContextMenu menu = new ContextMenu();
		
		MenuItem menuItem;
		for(Operation operation : operations.getOperations()){
			menuItem = new MenuItem(operation.getName());
			menuItem.setOnAction((e) -> operation.getAction().accept(e));
			menu.getItems().add(menuItem);	
		}
		
		return menu;
	}

}
