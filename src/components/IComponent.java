package components;

import javafx.scene.Node;
import utils.ComponentException;

public interface IComponent {

	public void mount() throws ComponentException;
	
	public Node getNode();
	
}
