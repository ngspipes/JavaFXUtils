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
