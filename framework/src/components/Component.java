package components;

import javafx.scene.Node;


public abstract class Component<T extends Node> implements IComponent<T>{

	protected T node;
	
	public Component(T node){
		this.node = node;
	}

	@Override
	public T getNode() {
		return node;
	}

}
