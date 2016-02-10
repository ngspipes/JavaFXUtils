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
package components.animation.magnifier;

import javafx.scene.control.Button;

import components.IComponent;
import components.animation.PassAnimation;


public class ButtonMagnifier<T extends Button> extends PassAnimation<T>{
	
	private static final double DEFAULT_MAGNIFY_AMP = 1.5;
	
	//Constructors
	
	public ButtonMagnifier(T button, double magnifyAmp, boolean keepOldHandlers){
		super(	button,
				(event)->{	button.setMinHeight(button.getHeight()*magnifyAmp);
		        			button.setMinWidth(button.getWidth()*magnifyAmp);	},
				(event)->{	button.setMinHeight(button.getHeight()/magnifyAmp);
		        			button.setMinWidth(button.getWidth()/magnifyAmp);	},
				keepOldHandlers	);
	}
	
	public ButtonMagnifier(T button, double magnifyAmp){
		this(button, magnifyAmp, true);
	}
	
	public ButtonMagnifier(T button, boolean keepOldHandlers){
		this(button, DEFAULT_MAGNIFY_AMP, keepOldHandlers);
	}
	
	public ButtonMagnifier(T button){
		this(button, true);
	}
	
	public ButtonMagnifier(IComponent<T> component, double magnifyAmp, boolean keepOldHandlers){
		this(component.getNode(), magnifyAmp, keepOldHandlers);
	}
	
	public ButtonMagnifier(IComponent<T> component, double magnifyAmp){
		this(component.getNode(), magnifyAmp);
	}
	
	public ButtonMagnifier(IComponent<T> component, boolean keepOldHandlers){
		this(component.getNode(), keepOldHandlers);
	}
	
	public ButtonMagnifier(IComponent<T> component){
		this(component.getNode());
	}
	
	
}
