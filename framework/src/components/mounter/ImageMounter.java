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
package components.mounter;

import javafx.scene.image.ImageView;

import components.IComponent;
import components.animation.magnifier.ImageMagnifier;

public class ImageMounter<T extends ImageView> extends ComponentMounter<T>{

	private final T image;
	
	public ImageMounter(T image) {
		super(image);
		this.image = image;
	}
	
	public ImageMounter(IComponent<T> component) {
		this(component.getNode());
	}

	public ImageMounter<T> imageMagnifier(double magnifyAmp, boolean keepOldHandlers){
		this.components.add(new ImageMagnifier<>(image, magnifyAmp, keepOldHandlers));
		return this;
	}
	
	public ImageMounter<T> imageMagnifier(double magnifyAmp){
		this.components.add(new ImageMagnifier<>(image, magnifyAmp));
		return this;
	}
	
	public ImageMounter<T> imageMagnifier(boolean keepOldHandlers){
		this.components.add(new ImageMagnifier<>(image, keepOldHandlers));
		return this;
	}
	
	public ImageMounter<T> imageMagnifier(){
		this.components.add(new ImageMagnifier<>(image));
		return this;
	}
	
}
