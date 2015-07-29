package components.mounter;

import java.util.Collection;

import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import javafx.util.Pair;

import components.IComponent;
import components.Tip;
import components.multiOption.MultiOption;
import components.multiOption.Operations;

public class ControlMounter<T extends Control> extends ComponentMounter<T>{

	private final T control;
	
	public ControlMounter(T control) {
		super(control);
		this.control = control;
	}
	
	public ControlMounter(IComponent<T> component) {
		this(component.getNode());
	}
	
	public ControlMounter<T> tip(Tooltip tooltip, int delay){
		this.components.add(new Tip<>(control, tooltip, delay));
		return this;
	}
	
	public ControlMounter<T> tip(Tooltip tooltip){
		this.components.add(new Tip<>(control, tooltip));
		return this;
	}
	
	public ControlMounter<T> tip(String description, int delay){
		this.components.add(new Tip<>(control, description, delay));
		return this;
	}
	
	public ControlMounter<T> tip(String description){
		this.components.add(new Tip<>(control, description));
		return this;
	}
	
	public ControlMounter<T> multiOption(Operations operations) {
		this.components.add(new MultiOption<>(control, operations));
		return this;
	}
	
	public ControlMounter<T> multiOption(Collection<Pair<String, Runnable>> actions) {
		this.components.add(new MultiOption<>(control, actions));
		return this;
	}
		
}
