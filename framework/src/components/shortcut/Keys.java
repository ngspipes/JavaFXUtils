package components.shortcut;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javafx.scene.input.KeyCode;
import javafx.util.Pair;


public class Keys {
	
	public static class Key{
		private final KeyCode code;
		private final Runnable action;
		
		public Key(KeyCode code, Runnable action){
			this.code = code;
			this.action = action;
		}
		
		public KeyCode getCode(){
			return code;
		}
		
		public Runnable getAction(){
			return action;
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
	
	public void remove(Key key){
		ks.remove(key.getCode());
		keys.remove(key);
		codes.remove(key.getCode());
		--length;
	}
	
	public void remove(KeyCode code){
		remove(getKey(code));
	}
	
}
