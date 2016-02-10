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

