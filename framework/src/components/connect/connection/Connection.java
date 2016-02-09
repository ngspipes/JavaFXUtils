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
