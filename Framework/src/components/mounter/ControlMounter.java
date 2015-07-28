package components.mounter;

import java.util.Collection;

import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import javafx.util.Pair;

import components.Tip;
import components.multiOption.MultiOption;
import components.multiOption.Operations;

public class ControlMounter extends ComponentMounter{

	private final Control control;
	
	public ControlMounter(Control control) {
		super(control);
		this.control = control;
	}
	
	public ControlMounter tip(Tooltip tooltip, int delay){
		this.components.add(new Tip(control, tooltip, delay));
		return this;
	}
	
	public ControlMounter tip(Tooltip tooltip){
		this.components.add(new Tip(control, tooltip));
		return this;
	}
	
	public ControlMounter tip(String description, int delay){
		this.components.add(new Tip(control, description, delay));
		return this;
	}
	
	public ControlMounter tip(String description){
		this.components.add(new Tip(control, description));
		return this;
	}
	
	public ControlMounter multiOption(Operations operations) {
		this.components.add(new MultiOption(control, operations));
		return this;
	}
	
	public ControlMounter multiOption(Collection<Pair<String, Runnable>> actions) {
		this.components.add(new MultiOption(control, actions));
		return this;
	}
		
}
