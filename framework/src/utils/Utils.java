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
package utils;

import javafx.event.Event;
import javafx.event.EventHandler;


public class Utils {

	public static <E extends Event> EventHandler<? super E> chain(EventHandler<? super E> oldHandler, EventHandler<? super E> newHandler, boolean keepOldHandler){
		if(newHandler==null)
			return oldHandler;
		
		if(oldHandler == null || !keepOldHandler)
			return newHandler;
	
		return (event)->{
					oldHandler.handle(event);
					newHandler.handle(event);
				};
	}
	
	public static <E extends Event> EventHandler<? super E> chain(EventHandler<? super E> oldHandler, EventHandler<? super E> newHandler){
		return chain(oldHandler, newHandler, true);
	}

}
