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

public class Coordinates {

	private double x;
	public double getX(){ return x; }
	public void setX(double x){ this.x = x; }
	
	private double y;
	public double getY(){ return y; }
	public void setY(double y){ this.y = y; }
	
	public Coordinates(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public Coordinates() {
		this(0, 0);
	}
	
}
