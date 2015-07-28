package components;

import java.lang.reflect.Field;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import utils.ComponentException;

public class Tip extends Component{

	private static final int DEFAULT_DELAY = 50;
	
	private final Tooltip tooltip; 
	private final Control control;
	private final int delay;
	
	// Constructors
	
	public Tip(Control control, Tooltip tooltip, int delay){
		super(control);
		
		this.control = control;
		this.tooltip = tooltip;
		this.delay = delay;
	}
	
	public Tip(Control control, Tooltip tooltip){
		this(control,  tooltip, DEFAULT_DELAY);
	}
	
	public Tip(Control control, String description, int delay){
		this(control, new Tooltip(description), delay);
	}
	
	public Tip(Control control, String description){
		this(control, description, DEFAULT_DELAY);
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
