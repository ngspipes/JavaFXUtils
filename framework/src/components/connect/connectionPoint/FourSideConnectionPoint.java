package components.connect.connectionPoint;

import java.util.Collection;
import java.util.LinkedList;

import javafx.scene.Node;

import components.IComponent;
import components.connect.Coordinates;

public class FourSideConnectionPoint<T extends Node> extends ConnectionPoint<T>{
	
	private final MiddleConnectionPoint<T> middlePointCalculator;
	
	// Constructors
	
	public FourSideConnectionPoint(T node) {
		super(node);
		this.middlePointCalculator = new MiddleConnectionPoint<>(node);
	}

	public FourSideConnectionPoint(IComponent<T> component) {
		this(component.getNode());
	}
	
	
	// Implementation
	
	@Override
	public Collection<Coordinates> getConnectionPoints() {
		Collection<Coordinates> connectionPoints = new LinkedList<>();
		
		Coordinates middlePoint = middlePointCalculator.getConnectionPoints().iterator().next();
		
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

