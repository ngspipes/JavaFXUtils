package components.connect;

import java.util.Collection;

import javafx.scene.Node;
import javafx.scene.shape.Line;

import components.Component;
import components.IComponent;
import components.connect.connectionPoint.ConnectionPoint;
import components.connect.connectionPoint.IConnectionPoint;

public class Connector extends Component<Line>{
	
	private static Coordinates getClosestPoint(Coordinates from, Collection<Coordinates> to){
		if(to.size()==1)
			return to.iterator().next();
		
		double currentDistance, smallestDistance = -1;
		Coordinates closestPoint = null;
		
		for(Coordinates currentPoint : to){
			currentDistance = getDistance(from, currentPoint);
			
			if(smallestDistance==-1 || currentDistance<smallestDistance){
				smallestDistance = currentDistance;
				closestPoint = currentPoint;
			}
		}
		
		return closestPoint;
	}
	
	private static double getDistance(Coordinates from, Coordinates to){
		double heigth = Math.abs(to.getY() - from.getY());
		double width = Math.abs(to.getX() - from.getX());
		
		return Math.sqrt( Math.pow(heigth, 2) + Math.pow(width, 2) );
	}
	
	private final Line line;
	
	private final IConnectionPoint<?> endPoint;
	private final IConnectionPoint<?> initPoint;
	
	// Constructors
	
	public Connector(Line line, IConnectionPoint<?> initPoint, IConnectionPoint<?> endPoint){
		super(line);
		this.line = this.node;
		this.initPoint = initPoint;
		this.endPoint = endPoint;
	}
	
	public Connector(Line line, Node initNode, Node endNode) {
		this(line, new ConnectionPoint<>(initNode), new ConnectionPoint<>(endNode));
	}
	
	public Connector(Line line, IComponent<?> initComponent, IComponent<?> endComponent){
		this(line, initComponent.getNode(), endComponent.getNode());
	}
	
	public Connector(IComponent<Line> component, IConnectionPoint<?> initPoint, IConnectionPoint<?> endPoint){
		this(component.getNode(), initPoint, endPoint);
	}
	
	public Connector(IComponent<Line> component, Node initNode, Node endNode) {
		this(component.getNode(), initNode, endNode);
	}
	
	public Connector(IComponent<Line> component, IComponent<?> initComponent, IComponent<?> endComponent){
		this(component.getNode(), initComponent, endComponent);
	}
	
	public Connector(IConnectionPoint<?> initPoint, IConnectionPoint<?> endPoint){
		this(new Line(), initPoint, endPoint);
	}
	
	public Connector(Node initNode, Node endNode) {
		this(new Line(), initNode, endNode);
	}
	
	public Connector(IComponent<?> initComponent, IComponent<?> endComponent){
		this(new Line(), initComponent, endComponent);
	}

	
	// Implementation
	
	@Override
	public void mount() {
		setBounds();
		registerCoordinateListeners();
	}

	private void setBounds() {
		setInitBounds(); 
		setEndBounds();
	}
	
	private void setInitBounds(){
		Coordinates closestPoint = getClosestPoint(endPoint.getCordinates(), initPoint.getConnectionPoints());
		
		line.setStartX(closestPoint.getX());
		line.setStartY(closestPoint.getY());
	}
	
	private void setEndBounds(){
		Coordinates closestPoint = getClosestPoint(initPoint.getCordinates(), endPoint.getConnectionPoints());
		
		line.setEndX(closestPoint.getX());
		line.setEndY(closestPoint.getY());
	}
	
	private void registerCoordinateListeners() {
		initPoint.registerCoordinatesListner((newCoordinates)->setBounds());
		endPoint.registerCoordinatesListner((newCoordinates)->setBounds());		
	}

}
