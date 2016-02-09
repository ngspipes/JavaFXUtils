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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Consumer;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;


public class Keys {
	
	public static enum CommandKey {
		SHIFT("Shift"),
		CONTROL("Control"),
		ALT("Alt");
		
		private CommandKey(String key){}
		
	}
	
	public static class Key{
		private final CommandKey[] commandKeys;
		private final KeyCode code;
		private final Consumer<KeyEvent> action;
		
		public Key(KeyCode code, Consumer<KeyEvent> action, CommandKey... commandKeys){
			this.code = code;
			this.action = action;
			this.commandKeys = commandKeys;
		}
		
		public Key(KeyCode code, Runnable action, CommandKey... commandKeys){
			this(code, (e)->action.run(), commandKeys);
		}
		
		public CommandKey[] getCommandKeys(){
			return commandKeys;
		}
		
		public KeyCode getCode(){
			return code;
		}
		
		public Consumer<KeyEvent> getAction(){
			return action;
		}
	
		public boolean match(KeyEvent event){
			if(!event.getCode().equals(code))
				return false;
			
			for(CommandKey commandKey : commandKeys){
				if(commandKey.equals(CommandKey.SHIFT) && !event.isShiftDown())
					return false;
				if(commandKey.equals(CommandKey.CONTROL) && !event.isControlDown())
					return false;
				if(commandKey.equals(CommandKey.ALT) && !event.isAltDown())
					return false;	
			}
			
			return true;
		}
		
	}

	
	private final Map<KeyCode, Key> ks = new HashMap<>();
	private final Collection<Key> keys = new LinkedList<>();
	private final Collection<KeyCode> codes = new LinkedList<>();
	private int length = 0;
	
	//Constructors 
	
	public Keys(){}
	
	public Keys(Collection<Pair<KeyCode, Runnable>> actions){
		for(Pair<KeyCode , Runnable> action : actions)
			add(action.getKey(), action.getValue());
	}
	
	
	
	// Implementation
	
	public Collection<Key> getKeys(){
		return keys;
	}
	
	public Collection<KeyCode> getCodes(){
		return codes;
	}
	
	public Key getKey(KeyCode code){
		return ks.get(code);
	}
	
	public int getLength(){
		return length;
	}

	public boolean contains(KeyCode code){
		return codes.contains(code);
	}
	
	public boolean contains(Key key){
		return keys.contains(key);
	}
	
	public void add(Key key){
		ks.put(key.getCode(), key);
		keys.add(key);
		codes.add(key.getCode());
		++length;
	}
	
	public void add(KeyCode code, Runnable action){
		add(new Key(code, action));
	}
	
	public void add(KeyCode code, Consumer<KeyEvent> action){
		add(new Key(code, action));
	}
	
	public void add(KeyCode code, Runnable action, CommandKey... commandKeys){
		add(new Key(code, action, commandKeys));
	}
	
	public void add(KeyCode code, Consumer<KeyEvent> action, CommandKey... commandKeys){
		add(new Key(code, action, commandKeys));
	}
	
	public void remove(Key key){
		ks.remove(key.getCode());
		keys.remove(key);
		codes.remove(key.getCode());
		--length;
	}
	
	public void remove(KeyCode code){
		remove(getKey(code));
	}
	
	public void clear(){
		ks.clear();
		keys.clear();
		codes.clear();
		length = 0;
	}
	
}
