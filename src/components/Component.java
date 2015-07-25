package components;

import javafx.scene.Node;


public abstract class Component implements IComponent{

	protected Node node;
	
	public Component(Node node){
		this.node = node;
	}

	@Override
	public Node getNode() {
		return node;
	}

}
