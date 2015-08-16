package components.connect;

import java.util.Collection;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.shape.Line;

import components.Component;
import components.IComponent;
import components.connect.connection.Connection;
import components.connect.connection.IConnection;
import components.connect.connector.Connector;

public class Connectable extends Component<Line>{
	
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
	
	private final Connector connector;
	private final IConnection<?> endPoint;
	private final IConnection<?> initPoint;
	
	// Constructors
	
	public Connectable(Connector connector, IConnection<?> initPoint, IConnection<?> endPoint){
		super(connector.getNode());
		this.connector = connector;
		this.initPoint = initPoint;
		this.endPoint = endPoint;
	}
	
	public Connectable(Connector connector, Node initNode, Node endNode) {
		this(connector, new Connection<>(initNode), new Connection<>(endNode));
	}
	
	public Connectable(Connector connector, IComponent<?> initComponent, IComponent<?> endComponent){
		this(connector, initComponent.getNode(), endComponent.getNode());
	}
	
	public Connectable(Line line, IConnection<?> initPoint, IConnection<?> endPoint){
		this(new Connector(line), initPoint, endPoint);
	}
	
	public Connectable(Line line, Node initNode, Node endNode) {
		this(new Connector(line), initNode, endNode);
	}
	
	public Connectable(Line line, IComponent<?> initComponent, IComponent<?> endComponent){
		this(new Connector(line), initComponent, endComponent);
	}
	
	public Connectable(IComponent<Line> component, IConnection<?> initPoint, IConnection<?> endPoint){
		this(component.getNode(), initPoint, endPoint);
	}
	
	public Connectable(IComponent<Line> component, Node initNode, Node endNode) {
		this(component.getNode(), initNode, endNode);
	}
	
	public Connectable(IComponent<Line> component, IComponent<?> initComponent, IComponent<?> endComponent){
		this(component.getNode(), initComponent, endComponent);
	}
	
	public Connectable(IConnection<?> initPoint, IConnection<?> endPoint){
		this(new Line(), initPoint, endPoint);
	}
	
	public Connectable(Node initNode, Node endNode) {
		this(new Line(), initNode, endNode);
	}
	
	public Connectable(IComponent<?> initComponent, IComponent<?> endComponent){
		this(new Line(), initComponent, endComponent);
	}

	
	// Implementation
	
	@Override
	public void mount() {
		connector.mount();
		setBounds();
		registerCoordinateListeners();
	}
	
	public void connect() {
		mount();
	}
	
	private void setBounds() {
		//After initPoint and endPoint movement, width and height may have not been calculated so we will give some time to execute this operation
		Platform.runLater(()->{
			setInitBounds(); 
			setEndBounds();	
		});
	}
	
	private void setInitBounds(){
		Coordinates closestPoint = getClosestPoint(endPoint.getCordinates(), initPoint.getConnectionPoints());
		
		connector.setInit(closestPoint.getX(), closestPoint.getY());
	}
	
	private void setEndBounds(){
		Coordinates closestPoint = getClosestPoint(initPoint.getCordinates(), endPoint.getConnectionPoints());
		
		connector.setEnd(closestPoint.getX(), closestPoint.getY());
	}
	
	private void registerCoordinateListeners() {
		initPoint.registerCoordinatesListner((newCoordinates)->setBounds());
		endPoint.registerCoordinatesListner((newCoordinates)->setBounds());		
	}

	public Connector getConnector(){
		return connector;
	}
	
	public IConnection<?> getInitPoint(){
		return initPoint;
	}
	
	public IConnection<?> getEndPoint(){
		return endPoint;
	}
	
}
