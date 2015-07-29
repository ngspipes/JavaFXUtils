package components;

import java.lang.reflect.Field;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import utils.ComponentException;

public class Tip<T extends Control> extends Component<T>{

	private static final int DEFAULT_DELAY = 50;
	
	private final Tooltip tooltip; 
	private final Control control;
	private final int delay;
	
	// Constructors
	
	public Tip(T control, Tooltip tooltip, int delay){
		super(control);
		
		this.control = control;
		this.tooltip = tooltip;
		this.delay = delay;
	}
	
	public Tip(T control, Tooltip tooltip){
		this(control,  tooltip, DEFAULT_DELAY);
	}
	
	public Tip(T control, String description, int delay){
		this(control, new Tooltip(description), delay);
	}
	
	public Tip(T control, String description){
		this(control, description, DEFAULT_DELAY);
	}
	
	public Tip(IComponent<T> component, Tooltip tooltip, int delay){
		this(component.getNode(), tooltip, delay);
	}
	
	public Tip(IComponent<T> component, Tooltip tooltip){
		this(component.getNode(), tooltip);
	}
	
	public Tip(IComponent<T> component, String description, int delay){
		this(component.getNode(), description, delay);
	}
	
	public Tip(IComponent<T> component, String description){
		this(component.getNode(), description);
	}
	
	
	// Implementation
	
	@Override
	public void mount() throws ComponentException {
		try {
			Field fieldBehavior = Tooltip.class.getDeclaredField("BEHAVIOR");
			fieldBehavior.setAccessible(true);
			Object objBehavior = fieldBehavior.get(tooltip);

			Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
			fieldTimer.setAccessible(true);
			Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

			objTimer.getKeyFrames().clear();
			objTimer.getKeyFrames().add(new KeyFrame(new Duration(delay)));
			
			control.setTooltip(tooltip);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new ComponentException("Error mounting tooltip delay!", e);
		}
	}
	
}
