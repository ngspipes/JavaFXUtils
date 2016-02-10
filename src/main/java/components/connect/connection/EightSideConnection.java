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
