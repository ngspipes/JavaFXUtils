package components.connect.connectionPoint;

import java.util.Collection;
import java.util.LinkedList;

import javafx.scene.Node;

import components.IComponent;
import components.connect.Coordinates;

public class MiddleConnectionPoint<T extends Node> extends ConnectionPoint<T> {
	
	// Constructors
	
	public MiddleConnectionPoint(T node) {
		super(node);
	}
	
	public MiddleConnectionPoint(IComponent<T> component) {
		this(component.getNode());
	}
	
	
	// Implementation
	
	@Override
	public Collection<Coordinates> getConnectionPoints() {
		Collection<Coordinates> connectionPoints = new LinkedList<>();
		connectionPoints.add(new Coordinates(getX(), getY()));
		
		return connectionPoints;
	}

	private double getX(){
		double initX = this.node.getLayoutX();
		double endX = initX + this.node.getBoundsInLocal().getWidth();
		
		return initX + ((endX-initX)/2);	
	}
	
	private double getY(){
		double initY = this.node.getLayoutY();
		double endY = initY + this.node.getBoundsInLocal().getHeight();
		
		return initY + ((endY-initY)/2);
	}
	
}
