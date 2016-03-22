/*-
 * Copyright (c) 2016, NGSPipes Team <ngspipes@gmail.com>
 * All rights reserved.
 *
 * This file is part of NGSPipes <http://ngspipes.github.io/>.
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package components.connect;

import components.Component;
import components.IComponent;
import components.connect.connection.Connection;
import components.connect.connection.IConnection;
import components.connect.connector.Connector;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.shape.Line;

import java.util.Collection;

public class Connectible extends Component<Line>{
	
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
		double height = Math.abs(to.getY() - from.getY());
		double width = Math.abs(to.getX() - from.getX());
		
		return Math.sqrt( Math.pow(height, 2) + Math.pow(width, 2) );
	}
	
	private final Connector connector;
	private final IConnection<?> endPoint;
	private final IConnection<?> initPoint;
	
	// Constructors
	
	public Connectible(Connector connector, IConnection<?> initPoint, IConnection<?> endPoint){
		super(connector.getNode());
		this.connector = connector;
		this.initPoint = initPoint;
		this.endPoint = endPoint;
	}
	
	public Connectible(Connector connector, Node initNode, Node endNode) {
		this(connector, new Connection<>(initNode), new Connection<>(endNode));
	}
	
	public Connectible(Connector connector, IComponent<?> initComponent, IComponent<?> endComponent){
		this(connector, initComponent.getNode(), endComponent.getNode());
	}
	
	public Connectible(Line line, IConnection<?> initPoint, IConnection<?> endPoint){
		this(new Connector(line), initPoint, endPoint);
	}
	
	public Connectible(Line line, Node initNode, Node endNode) {
		this(new Connector(line), initNode, endNode);
	}
	
	public Connectible(Line line, IComponent<?> initComponent, IComponent<?> endComponent){
		this(new Connector(line), initComponent, endComponent);
	}
	
	public Connectible(IComponent<Line> component, IConnection<?> initPoint, IConnection<?> endPoint){
		this(component.getNode(), initPoint, endPoint);
	}
	
	public Connectible(IComponent<Line> component, Node initNode, Node endNode) {
		this(component.getNode(), initNode, endNode);
	}
	
	public Connectible(IComponent<Line> component, IComponent<?> initComponent, IComponent<?> endComponent){
		this(component.getNode(), initComponent, endComponent);
	}
	
	public Connectible(IConnection<?> initPoint, IConnection<?> endPoint){
		this(new Line(), initPoint, endPoint);
	}
	
	public Connectible(Node initNode, Node endNode) {
		this(new Line(), initNode, endNode);
	}
	
	public Connectible(IComponent<?> initComponent, IComponent<?> endComponent){
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
		Coordinates closestPoint = getClosestPoint(endPoint.getCoordinates(), initPoint.getConnectionPoints());
		
		connector.setInit(closestPoint.getX(), closestPoint.getY());
	}
	
	private void setEndBounds(){
		Coordinates closestPoint = getClosestPoint(initPoint.getCoordinates(), endPoint.getConnectionPoints());
		
		connector.setEnd(closestPoint.getX(), closestPoint.getY());
	}
	
	private void registerCoordinateListeners() {
		initPoint.registerCoordinatesListener((newCoordinates)->setBounds());
		endPoint.registerCoordinatesListener((newCoordinates)->setBounds());
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
