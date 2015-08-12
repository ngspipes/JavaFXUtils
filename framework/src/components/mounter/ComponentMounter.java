package components.mounter;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import utils.ComponentException;

import components.Component;
import components.DoubleClickable;
import components.IComponent;
import components.Movable;
import components.animation.Animation;
import components.animation.PassAnimation;
import components.animation.PressAndPassAnimation;
import components.animation.PressAnimation;
import components.animation.changeMouse.ChangeMouseOnPass;
import components.animation.changeMouse.ChangeMouseOnPress;
import components.shortcut.Keys;
import components.shortcut.Shortcut;

public class ComponentMounter<T extends Node> extends Component<T>{

	protected final Collection<IComponent<T>> components;
	
	// Constructors
	
	public ComponentMounter(T node) {
		super(node);
		this.components = new LinkedList<>();
	}
	
	public ComponentMounter(IComponent<T> component) {
		this(component.getNode());
	}

	
	// Implementation
	
	@Override
	public void mount() throws ComponentException {
		for(IComponent<T> component : components)
			component.mount();
	}
	
	public ComponentMounter<T> movable(Consumer<MouseEvent> onMove, boolean keepOldHandlers){
		this.components.add(new Movable<>(this.node, onMove, keepOldHandlers));
		return this;
	}

	public ComponentMounter<T> movable(BiConsumer<Double, Double> onMove, boolean keepOldHandlers){
		this.components.add(new Movable<>(this.node, onMove, keepOldHandlers));
		return this;
	}

	public ComponentMounter<T> movable(boolean keepOldHandlers) {
		this.components.add(new Movable<>(this.node, keepOldHandlers));
		return this;
	}

	public ComponentMounter<T> movable(Consumer<MouseEvent> onMove){
		this.components.add(new Movable<>(this.node, onMove));
		return this;
	}

	public ComponentMounter<T> movable(BiConsumer<Double, Double> onMove){
		this.components.add(new Movable<>(this.node, onMove));
		return this;	
	}

	public ComponentMounter<T> movable() {
		this.components.add(new Movable<>(this.node));
		return this;
	}

	public ComponentMounter<T> doubleClickable(Consumer<MouseEvent> action, boolean keepOldHandler) {
		this.components.add(new DoubleClickable<>(this.node, action, keepOldHandler));
		return this;
	}
	
	public ComponentMounter<T> doubleClickable(Runnable action, boolean keepOldHandler) {
		this.components.add(new DoubleClickable<>(this.node, action, keepOldHandler));
		return this;
	}
	
	public ComponentMounter<T> doubleClickable(Consumer<MouseEvent> action) {
		this.components.add(new DoubleClickable<>(this.node, action));
		return this;
	}
	
	public ComponentMounter<T> doubleClickable(Runnable action) {
		this.components.add(new DoubleClickable<>(this.node, action));
		return this;
	}

	public ComponentMounter<T> animation(	EventHandler<? super MouseEvent> oldEnterHandler, EventHandler<? super MouseEvent> newEnterHandler,
											EventHandler<? super MouseEvent> oldExitHandler, EventHandler<? super MouseEvent> newExitHandler,
											Consumer<EventHandler<? super MouseEvent>> enterSetter, Consumer<EventHandler<? super MouseEvent>> exitSetter,
											boolean keepOldHandlers	){
		this.components.add(new Animation<>(this.node, oldEnterHandler, newEnterHandler, oldExitHandler, newExitHandler, enterSetter, exitSetter, keepOldHandlers));
		return this;
	}

	public ComponentMounter<T> animation(	EventHandler<? super MouseEvent> oldEnterHandler, EventHandler<? super MouseEvent> newEnterHandler,
											EventHandler<? super MouseEvent> oldExitHandler, EventHandler<? super MouseEvent> newExitHandler,
											Consumer<EventHandler<? super MouseEvent>> enterSetter, Consumer<EventHandler<? super MouseEvent>> exitSetter	){
		this.components.add(new Animation<>(this.node, oldEnterHandler, newEnterHandler, oldExitHandler, newExitHandler, enterSetter, exitSetter));
		return this;
	}

	public ComponentMounter<T> passAnimation(EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit, boolean keepOldHandlers){
		this.components.add(new PassAnimation<>(this.node, onEnter, onExit, keepOldHandlers));
		return this;
	}
	
	public ComponentMounter<T> passAnimation(EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit){
		this.components.add(new PassAnimation<>(this.node, onEnter, onExit));
		return this;
	}

	public ComponentMounter<T> pressAnimation(EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease, boolean keepOldHandlers){
		this.components.add(new PressAnimation<>(this.node, onPress, onRelease, keepOldHandlers));
		return this;
	}
	
	public ComponentMounter<T> pressAnimation(EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease){
		this.components.add(new PressAnimation<>(this.node, onPress, onRelease));
		return this;
	}
	
	public ComponentMounter<T> pressAndPassAnimation(	EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease,
														EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit,
														boolean keepOldHandlers	){
		this.components.add(new PressAndPassAnimation<>(this.node, onPress, onRelease, onEnter, onExit, keepOldHandlers));
		return this;
	}

	public ComponentMounter<T> pressAndPassAnimation(	EventHandler<? super MouseEvent> onPress, EventHandler<? super MouseEvent> onRelease,
														EventHandler<? super MouseEvent> onEnter, EventHandler<? super MouseEvent> onExit	){
		this.components.add(new PressAndPassAnimation<>(this.node, onPress, onRelease, onEnter, onExit));
		return this;
	}

	public ComponentMounter<T> changeMouseOnPass(Cursor onEnter, Cursor onExit, boolean keepOldHandlers) {
		this.components.add(new ChangeMouseOnPass<>(this.node, onEnter, onExit, keepOldHandlers));
		return this;
	}
	
	public ComponentMounter<T> changeMouseOnPass(Cursor onEnter, Cursor onExit) {
		this.components.add(new ChangeMouseOnPass<>(this.node, onEnter, onExit));
		return this;
	}
	
	public ComponentMounter<T> changeMouseOnPass(Image onEnter, Image onExit, boolean keepOldHandlers) {
		this.components.add(new ChangeMouseOnPass<>(this.node, onEnter, onExit, keepOldHandlers));
		return this;
	}
	
	public ComponentMounter<T> changeMouseOnPass(Image onEnter, Image onExit) {
		this.components.add(new ChangeMouseOnPass<>(this.node, onEnter, onExit));
		return this;
	}
	
	public ComponentMounter<T> changeMouseOnPass(String onEnterPath, String onExitPath, boolean keepOldHandlers) {
		this.components.add(new ChangeMouseOnPass<>(this.node, onEnterPath, onExitPath, keepOldHandlers));
		return this;
	}
	
	public ComponentMounter<T> changeMouseOnPass(String onEnterPath, String onExitPath) {
		this.components.add(new ChangeMouseOnPass<>(this.node, onEnterPath, onExitPath));
		return this;
	}
	
	public ComponentMounter<T> changeMouseOnPress(Cursor onPress, Cursor onRelease, boolean keepOldHandlers) {
		this.components.add(new ChangeMouseOnPress<>(this.node, onPress, onRelease, keepOldHandlers));
		return this;
	}
	
	public ComponentMounter<T> changeMouseOnPress(Cursor onPress, Cursor onRelease) {
		this.components.add(new ChangeMouseOnPress<>(this.node, onPress, onRelease));
		return this;
	}
	
	public ComponentMounter<T> changeMouseOnPress(Image onPress, Image onRelease, boolean keepOldHandlers) {
		this.components.add(new ChangeMouseOnPress<>(this.node, onPress, onRelease, keepOldHandlers));
		return this;
	}
	
	public ComponentMounter<T> changeMouseOnPress(Image onPress, Image onRelease) {
		this.components.add(new ChangeMouseOnPress<>(this.node, onPress, onRelease));
		return this;
	}
	
	public ComponentMounter<T> changeMouseOnPress(String onPressPath, String onReleasePath, boolean keepOldHandlers) {
		this.components.add(new ChangeMouseOnPress<>(this.node, onPressPath, onReleasePath, keepOldHandlers));
		return this;
	}
	
	public ComponentMounter<T> changeMouseOnPress(String onPressPath, String onReleasePath) {
		this.components.add(new ChangeMouseOnPress<>(this.node, onPressPath, onReleasePath));
		return this;
	}
	
	public ComponentMounter<T> shortcut(Keys keys, boolean keepOldHandler) {
		this.components.add(new Shortcut<>(this.node, keys, keepOldHandler));
		return this;
	}
	
	public ComponentMounter<T> shortcut(Keys keys) {
		this.components.add(new Shortcut<>(this.node, keys));
		return this;
	}
	
	public ComponentMounter<T> shortcut(Collection<Pair<KeyCode, Runnable>> actions, boolean keepOldHandler) {
		this.components.add(new Shortcut<>(this.node, actions, keepOldHandler));
		return this;
	}
	
	public ComponentMounter<T> shortcut(Collection<Pair<KeyCode, Runnable>> actions) {
		this.components.add(new Shortcut<>(this.node, actions));
		return this;
	}
	
}
