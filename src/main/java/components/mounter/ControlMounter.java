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
package components.mounter;

import java.util.Collection;

import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import javafx.util.Pair;

import components.IComponent;
import components.Tip;
import components.multiOption.MultiOption;
import components.multiOption.Operations;

public class ControlMounter<T extends Control> extends ComponentMounter<T>{

	private final T control;
	
	public ControlMounter(T control) {
		super(control);
		this.control = control;
	}
	
	public ControlMounter(IComponent<T> component) {
		this(component.getNode());
	}
	
	public ControlMounter<T> tip(Tooltip tooltip, int delay){
		this.components.add(new Tip<>(control, tooltip, delay));
		return this;
	}
	
	public ControlMounter<T> tip(Tooltip tooltip){
		this.components.add(new Tip<>(control, tooltip));
		return this;
	}
	
	public ControlMounter<T> tip(String description, int delay){
		this.components.add(new Tip<>(control, description, delay));
		return this;
	}
	
	public ControlMounter<T> tip(String description){
		this.components.add(new Tip<>(control, description));
		return this;
	}
	
	public ControlMounter<T> multiOption(Operations operations) {
		this.components.add(new MultiOption<>(control, operations));
		return this;
	}
	
	public ControlMounter<T> multiOption(Collection<Pair<String, Runnable>> actions) {
		this.components.add(new MultiOption<>(control, actions));
		return this;
	}
		
}
