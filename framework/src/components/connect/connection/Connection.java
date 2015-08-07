package components.connect.connection;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Consumer;

import javafx.scene.Node;

import components.IComponent;
import components.connect.Coordinates;

public class Connection<T extends Node> implements IConnection<T>{

	protected final T node;
	
	// Constructors
	
	public Connection(T node) {
		this.node = node;
	}
	
	public Connection(IComponent<T> component){
		this(component.getNode());
	}
	
	
	// Implementation
	
	@Override
	public T getNode() {
		return node;
	}
	
	@Override
	public Coordinates getCordinates() {
		return new Coordinates(this.node.getLayoutX(), this.node.getLayoutY());
	}
	
	@Override
	public void registerCoordinatesListner(Consumer<Coordinates> coordenatesConsumer) {
		this.node.layoutXProperty().addListener((a)-> coordenatesConsumer.accept(getCordinates()));
		this.node.layoutYProperty().addListener((a)-> coordenatesConsumer.accept(getCordinates()));
	}

	@Override
	public Collection<Coordinates> getConnectionPoints() {
		Collection<Coordinates> connectionPoints = new LinkedList<>();
		connectionPoints.add(getCordinates());
		return connectionPoints;
	}

}
