package components.connect.connection;

import java.util.Collection;
import java.util.LinkedList;

import javafx.scene.Node;

import components.IComponent;
import components.connect.Coordinates;

public class MiddleConnection<T extends Node> extends Connection<T> {
	
	
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
		connectionPoints.add(MiddleConnection.getMiddlePoint(this.node));
		
		return connectionPoints;
	}
	
}
