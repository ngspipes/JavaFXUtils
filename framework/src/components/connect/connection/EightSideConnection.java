package components.connect.connection;

import java.util.Collection;

import javafx.scene.Node;

import components.IComponent;
import components.connect.Coordinates;

public class EightSideConnection<T extends Node> extends FourSideConnection<T>{

	// Constructors
	
	public EightSideConnection(T node) {
		super(node);
	}
	
	public EightSideConnection(IComponent<T> component) {
		super(component);
	}

	
	// Implementation
	
	@Override
	public Collection<Coordinates> getConnectionPoints(){
		Collection<Coordinates> connectionPoints = super.getConnectionPoints();
		
		connectionPoints.add(getTopRightPoint());
		connectionPoints.add(getTopLeftPoint());
		connectionPoints.add(getBottomRightPoint());
		connectionPoints.add(getBottomLeftPoint());
		
		return connectionPoints;
	}
	
	private Coordinates getTopRightPoint(){
		double x = this.node.getLayoutX() + this.node.getBoundsInLocal().getWidth();
		double y = this.node.getLayoutY();
		
		return new Coordinates(x, y);
	}
	
	private Coordinates getTopLeftPoint(){
		double x = this.node.getLayoutX();
		double y = this.node.getLayoutY();
	
		return new Coordinates(x, y);
	}
	
	private Coordinates getBottomRightPoint(){
		double x = this.node.getLayoutX() + this.node.getBoundsInLocal().getWidth();
		double y = this.node.getLayoutY() + this.node.getBoundsInLocal().getHeight();
		
		return new Coordinates(x, y);
	}
	
	private Coordinates getBottomLeftPoint(){
		double x = this.node.getLayoutX();
		double y = this.node.getLayoutY() + this.node.getBoundsInLocal().getHeight();
		
		return new Coordinates(x, y);
	}

}
