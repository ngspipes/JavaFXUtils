package utils;

import java.util.function.Consumer;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Utils {

	public static void setMouseHandler(	EventHandler<? super MouseEvent> oldHandler, EventHandler<? super MouseEvent> newHandler, 
										Consumer<EventHandler<? super MouseEvent>> eventSetter, boolean keepOldHandler){
		if(!keepOldHandler || oldHandler == null){	
			eventSetter.accept(newHandler);
		} else {
			eventSetter.accept((event)->{
									oldHandler.handle(event);
									newHandler.handle(event);
								});
		}
	}
	
}
