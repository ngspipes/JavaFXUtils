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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.util.Pair;


public class Operations {

	public static class Operation{
		
		private final String name;
		private final Consumer<ActionEvent> action;
		
		public Operation(String name, Consumer<ActionEvent> action) {
			this.name = name;
			this.action = action;
		}
		
		public Operation(String name, Runnable action) {
			this(name, (event)->action.run());
		}
		
		public String getName(){
			return name;
		}
		
		public Consumer<ActionEvent> getAction(){
			return action;
		}
		
	}
	
	
	private final Map<String, Operation> ops = new HashMap<>();
	private final Collection<Operation> operations = new LinkedList<>();
	private final Collection<String> names = new LinkedList<>();
	private int length = 0;
	
	//Constructors 
	
	public Operations(){}
	
	public Operations(Collection<Pair<String, Runnable>> actions){
		for(Pair<String , Runnable> action : actions)
			add(action.getKey(), action.getValue());
	}
	
	
	// Implementation
	
	public Collection<Operation> getOperations(){
		return operations;
	}
	
	public Collection<String> getNames(){
		return names;
	}
	
	public Operation getOperation(String name){
		return ops.get(name);
	}
	
	public int getLength(){
		return length;
	}
	
	public boolean contains(Operation operation){
		return operations.contains(operation);
	}
	
	public boolean contains(String name){
		return names.contains(name);
	}
	
	public void add(Operation operation){
		ops.put(operation.getName(), operation);
		operations.add(operation);
		names.add(operation.getName());
		++length;
	}
	
	public void add(String name, Consumer<ActionEvent> action){
		add(new Operation(name, action));
	}
	
	public void add(String name, Runnable action){
		add(new Operation(name, action));
	}
	
	public void remove(Operation operation){
		ops.remove(operation.getName());
		operations.remove(operation);
		names.remove(operation.getName());
		--length;
	}
	
	public void remove(String name){
		remove(getOperation(name));
	}
	
	public void clear(){
		ops.clear();
		operations.clear();
		names.clear();
		length = 0;
	}
	
}
