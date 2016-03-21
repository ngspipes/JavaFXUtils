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
package components.connect.connector;

import components.IComponent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

public class ConnectorPointer extends Connector {
	
	private static Polygon getEndTip(){
		Polygon polygon = new Polygon();
		polygon.getPoints().addAll(
				0.0, 0.0,
				0.0, 15.0,
				15.0, 7.5);
		
		return polygon;
	}
	
	
	private double currentAngle = 0;
	
	public ConnectorPointer(){
		super(new ConnectorTips(Connector.NO_TIP, getEndTip()));
		setListeners();
	}
	
	public ConnectorPointer(Line line){
		super(line, new ConnectorTips(Connector.NO_TIP, getEndTip()));
		setListeners();
	}
	
	public ConnectorPointer(IComponent<Line> line){
		super(line, new ConnectorTips(Connector.NO_TIP, getEndTip()));
		setListeners();
	}
	
	
	

	private void setListeners(){
		this.line.endXProperty().addListener((a)-> rotate());
		this.line.endYProperty().addListener((a)-> rotate());		
		this.line.startXProperty().addListener((a)-> rotate());
		this.line.startYProperty().addListener((a)-> rotate());
	}
	
	private void rotate(){
		double nextAngle = getNextAngle();
		
		if(Double.isNaN(nextAngle))
			return;
		
		rotate(nextAngle-currentAngle);
		
		currentAngle = nextAngle;
	}
	
	private double getNextAngle(){
		double initX = this.getInitX();
		double endX = this.getEndX();
		double initY = this.getInitY();
		double endY = this.getEndY();
		
		double ca = endX - initX;
		double co = endY - initY;
		
		double angle = Math.toDegrees(Math.atan2(co, ca));
		
		return angle;
	}
	
	private void rotate(double angle){
		// (7.5, 7.5) are coordinates of middle point of triangle
		this.tips.getEnd().getTransforms().add(new Rotate(angle, 7.5, 7.5));
	}
	
}
