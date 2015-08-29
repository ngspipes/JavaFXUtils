package components.connect.connection;

import java.util.Collection;
import java.util.LinkedList;

import javafx.scene.Node;

import components.IComponent;
import components.connect.Coordinates;

public class NSideConnection<T extends Node> extends Connection<T>{


	private final int topPoints;
	private final int bottomPoints;
	private final int leftPoints;
	private final int rightPoints;

	// Constructors

	public NSideConnection(T node, int topPoints, int bottomPoints, int leftPoints, int rightPoints) {
		super(node);
		this.topPoints = topPoints;
		this.bottomPoints = bottomPoints;
		this.leftPoints = leftPoints;
		this.rightPoints = rightPoints;
	}

	public NSideConnection(T node, int pointPerSide) {
		this(node, pointPerSide, pointPerSide, pointPerSide, pointPerSide);
	}

	public NSideConnection(IComponent<T> component, int topPoints, int bottomPoints, int leftPoints, int rightPoints) {
		this(component.getNode(), topPoints, bottomPoints, leftPoints, rightPoints);
	}

	public NSideConnection(IComponent<T> component, int pointsPerSide) {
		this(component.getNode(), pointsPerSide);
	}


	// Implementation

	@Override
	public Collection<Coordinates> getConnectionPoints(){
		Collection<Coordinates> connectionPoints = new LinkedList<>();

		connectionPoints.addAll(getTopPoints());
		connectionPoints.addAll(getBottomPoints());
		connectionPoints.addAll(getLeftPoints());
		connectionPoints.addAll(getRigthPoints());

		return connectionPoints;
	}

	private Collection<Coordinates> getTopPoints(){
		Collection<Coordinates> connectionPoints = new LinkedList<>();

		double y = this.node.getLayoutY();
		double maxX = this.node.getLayoutX() + this.node.getBoundsInLocal().getWidth();
		double minX = this.node.getLayoutX();
		double space = (maxX - minX)/topPoints;

		for(int i=0; i<topPoints;++i)
			connectionPoints.add(new Coordinates(minX+(space*i), y));

		return connectionPoints;
	}

	private Collection<Coordinates> getBottomPoints(){
		Collection<Coordinates> connectionPoints = new LinkedList<>();

		double y = this.node.getLayoutY() + this.node.getBoundsInLocal().getHeight();
		double maxX = this.node.getLayoutX() + this.node.getBoundsInLocal().getWidth();
		double minX = this.node.getLayoutX();
		double space = (maxX - minX)/bottomPoints;

		for(int i=0; i<bottomPoints;++i)
			connectionPoints.add(new Coordinates(minX+(space*i), y));

		return connectionPoints;
	}

	private Collection<Coordinates> getLeftPoints(){
		Collection<Coordinates> connectionPoints = new LinkedList<>();

		double x = this.node.getLayoutX();
		double maxY = this.node.getLayoutY() + this.node.getBoundsInLocal().getHeight();
		double minY = this.node.getLayoutY();
		double space = (maxY - minY)/leftPoints;

		for(int i=0; i<leftPoints;++i)
			connectionPoints.add(new Coordinates(x, minY+(space*i)));

		return connectionPoints;
	}

	private Collection<Coordinates> getRigthPoints(){
		Collection<Coordinates> connectionPoints = new LinkedList<>();

		double x = this.node.getLayoutX() + this.node.getBoundsInLocal().getWidth();
		double maxY = this.node.getLayoutY() + this.node.getBoundsInLocal().getHeight();
		double minY = this.node.getLayoutY();
		double space = (maxY - minY)/rightPoints;

		for(int i=0; i<rightPoints;++i)
			connectionPoints.add(new Coordinates(x, minY+(space*i)));

		return connectionPoints;
	}

}
