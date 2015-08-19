package components.multiOption;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javafx.util.Pair;


public class Operations {

	public static class Operation{
		
		private final String name;
		private final Runnable action;
		
		public Operation(String name, Runnable action) {
			this.name = name;
			this.action = action;
		}
		
		public String getName(){
			return name;
		}
		
		public Runnable getAction(){
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
