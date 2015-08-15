package components.connect.connection;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Consumer;

import javafx.scene.Node;

import components.IComponent;
import components.connect.Coordinates;

public class Connection<T extends Node> implements IConnection<T>{

	public static Coordinates getMiddlePoint(Node node){
		return new Coordinates(getMiddleX(node), getMiddleY(node));
	}
	
	private static double getMiddleX(Node node){
		double initX = node.getLayoutX();
		double endX = initX + node.getBoundsInLocal().getWidth();
		
		return initX + ((endX-initX)/2);	
	}
	
	private static double getMiddleY(Node node){
		double initY = node.getLayoutY();
		double endY = initY + node.getBoundsInLocal().getHeight();
		
		return initY + ((endY-initY)/2);
	}
	
	
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
		return Connection.getMiddlePoint(this.node);
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
