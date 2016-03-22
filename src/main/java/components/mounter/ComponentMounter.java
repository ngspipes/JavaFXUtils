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
package components.mounter;

import components.*;
import components.animation.Animation;
import components.animation.PassAnimation;
import components.animation.PressAndPassAnimation;
import components.animation.PressAnimation;
import components.animation.changeMouse.ChangeMouseOnPass;
import components.animation.changeMouse.ChangeMouseOnPress;
import components.multiOption.Menu;
import components.multiOption.Operations;
import components.shortcut.Keys;
import components.shortcut.Shortcut;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.util.Pair;
import jfxutils.ComponentException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

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
	
	public ComponentMounter<T> shortcut(Keys keys, boolean keepOldHandler, boolean receiveChildEvents) {
		this.components.add(new Shortcut<>(this.node, keys, keepOldHandler, receiveChildEvents));
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
	
	public <I> ComponentMounter<T> draggable(Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			Object group, TransferMode mode, Image dragView, boolean receives, boolean sends, boolean permitSelfDrag) {
		this.components.add(new Draggable<>(node, onReceive, afterSend, info, group, mode, dragView, receives, sends, permitSelfDrag));
		return this;
	}

	public <I> ComponentMounter<T> draggable(Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			Object group, TransferMode mode, Image dragView) {
		this.components.add(new Draggable<>(node, onReceive, afterSend, info, group, mode, dragView));
		return this;
	}

	public <I> ComponentMounter<T> draggable(Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			TransferMode mode, Image dragView) {
		this.components.add(new Draggable<>(node, onReceive, afterSend, info, mode, dragView));
		return this;
	}

	public <I> ComponentMounter<T> draggable(Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			Object group, Image dragView) {
		this.components.add(new Draggable<>(node, onReceive, afterSend, info, group, dragView));
		return this;
	}

	public <I> ComponentMounter<T> draggable(Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			Object group, TransferMode mode) {
		this.components.add(new Draggable<>(node, onReceive, afterSend, info, group, mode));
		return this;
	}

	public <I> ComponentMounter<T> draggable(Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			Object group) {
		this.components.add(new Draggable<>(node, onReceive, afterSend, info, group));
		return this;
	}

	public <I> ComponentMounter<T> draggable(Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			TransferMode mode) {
		this.components.add(new Draggable<>(node, onReceive, afterSend, info, mode));
		return this;
	}

	public <I> ComponentMounter<T> draggable(Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			Image dragView) {
		this.components.add(new Draggable<>(node, onReceive, afterSend, info, dragView));
		return this;
	}

	public <I> ComponentMounter<T> draggable(Function<I, Boolean> onReceive, Consumer<I> afterSend, I info) {
		this.components.add(new Draggable<>(node, onReceive, afterSend, info));
		return this;
	}
	
	public ComponentMounter<T> menu(Operations operations) {
		this.components.add(new Menu<>(this.node, operations));
		return this;
	}
	
	public ComponentMounter<T> menu(Collection<Pair<String, Runnable>> actions) {
		this.components.add(new Menu<>(this.node, actions));
		return this;
	}
	
}
