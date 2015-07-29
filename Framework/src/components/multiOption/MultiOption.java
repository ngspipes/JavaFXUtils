package components.multiOption;

import java.util.Collection;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.MenuItem;
import javafx.util.Pair;

import components.Component;
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
	
	
	// Implementation
	
	public void mount(){
		ContextMenu menu = new ContextMenu();
		
		MenuItem menuItem;
		for(Operation operation : operations.getOperations()){
			menuItem = new MenuItem(operation.getName());
			menuItem.setOnAction((event) -> operation.getAction().run());
			menu.getItems().add(menuItem);	
		}
		
		control.setContextMenu(menu);
	}

}
