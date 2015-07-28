package utils;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Utils {

	public static EventHandler<? super MouseEvent> chain(EventHandler<? super MouseEvent> oldHandler, EventHandler<? super MouseEvent> newHandler, boolean keepOldHandler){
		if(newHandler==null)
			return oldHandler;
		
		if(oldHandler == null || !keepOldHandler)
			return newHandler;
	
		return (event)->{
					oldHandler.handle(event);
					newHandler.handle(event);
				};
	}
	
}
