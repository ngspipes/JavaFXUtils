package components.multiOption;

import java.util.Collection;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import utils.Utils;

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
