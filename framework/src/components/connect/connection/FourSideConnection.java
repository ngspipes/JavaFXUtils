package components.connect.connection;

import java.util.Collection;
import java.util.LinkedList;

import javafx.scene.Node;

import components.IComponent;
import components.connect.Coordinates;

public class FourSideConnection<T extends Node> extends Connection<T>{
	
	// Constructors
	
	public FourSideConnection(T node) {
		super(node);
	}

	public FourSideConnection(IComponent<T> component) {
		this(component.getNode());
	}
	
	
	// Implementation
	
	@Override
	public Collection<Coordinates> getConnectionPoints() {
		Collection<Coordinates> connectionPoints = new LinkedList<>();
		
		Coordinates middlePoint = Connection.getMiddlePoint(this.node);
		
		connectionPoints.add(getTopPoint(middlePoint));
		connectionPoints.add(getBottomPoint(middlePoint));
		connectionPoints.add(getRightPoint(middlePoint));
		connectionPoints.add(getLeftPoint(middlePoint));
		
		return connectionPoints;
	}

	private Coordinates getTopPoint(Coordinates middlePoint) {
		double newY = middlePoint.getY() + (this.node.getBoundsInLocal().getHeight()/2);
		
		return new Coordinates(middlePoint.getX(), newY);
	}

	private Coordinates getBottomPoint(Coordinates middlePoint) {
		double newY	 = middlePoint.getY() - (this.node.getBoundsInLocal().getHeight()/2);
		
		return new Coordinates(middlePoint.getX(), newY);
	}

	private Coordinates getRightPoint(Coordinates middlePoint) {
		double newX = middlePoint.getX() - (this.node.getBoundsInLocal().getWidth()/2);
		
		return new Coordinates(newX, middlePoint.getY());
	}

	private Coordinates getLeftPoint(Coordinates middlePoint) {
		double newX = middlePoint.getX() + (this.node.getBoundsInLocal().getWidth()/2);
		
		return new Coordinates(newX, middlePoint.getY());
	}
	
}

