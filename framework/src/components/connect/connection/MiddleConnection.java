package components.connect.connection;

import java.util.Collection;
import java.util.LinkedList;

import javafx.scene.Node;

import components.IComponent;
import components.connect.Coordinates;

public class MiddleConnection<T extends Node> extends Connection<T> {
	
	// Constructors
	
	public MiddleConnection(T node) {
		super(node);
	}
	
	public MiddleConnection(IComponent<T> component) {
		this(component.getNode());
	}
	
	
	// Implementation
	
	@Override
	public Collection<Coordinates> getConnectionPoints() {
		Collection<Coordinates> connectionPoints = new LinkedList<>();
		connectionPoints.add(Connection.getMiddlePoint(this.node));
		
		return connectionPoints;
	}
	
}
