package components;

import javafx.scene.Node;
import utils.ComponentException;

public interface IComponent<T extends Node> {

	public void mount() throws ComponentException;
	
	public T getNode();
	
}
