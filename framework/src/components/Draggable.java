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
package components;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import utils.Pair;
import utils.Utils;

public class Draggable<T extends Node, I/*Info*/> extends Component<T> {

	private static final Map<Integer, Map<Integer, Pair<Object, Boolean>>> CHANNELS = new HashMap<>();
	private static final Object WITHOUT_GROUP = new Object();
	private static final String DRAG_N_DROP_STRING = "Draggable_DRAG_N_DROP";


	private static Integer extractKey(String str){
		return Integer.parseInt(str.split("-")[2]);
	}

	private static Integer extractGroup(String str){
		return Integer.parseInt(str.split("-")[1]);
	}



	private I info;
	public I getInfo(){ return info; }
	public void setInfo(I info){ this.info = info; }

	private boolean receives;
	public boolean getReceives(){ return receives; }
	public void setReceives(boolean receives){ this.receives = receives; }

	private boolean sends;
	public boolean getSends(){ return sends; }
	public void setSends(boolean sends){ this.sends = sends; }

	private boolean permitSelfDrag;
	public boolean getPermitSelfDrag(){ return permitSelfDrag; }
	public void setPermitSelfDrag(boolean permitSelfDrag){ this.permitSelfDrag = permitSelfDrag; }

	private final Map<Integer, Pair<Object, Boolean>> groupChannel;
	private final Integer groupKey;
	private final Integer key;
	private final String dragString;

	private BiFunction<DragEvent, I, Boolean> onReceive;
	public BiFunction<DragEvent, I, Boolean> getOnReceive() { return onReceive; }
	public void setOnReceive(BiFunction<DragEvent, I, Boolean> onReceive) { this.onReceive = onReceive; }

	private Consumer<I> afterSend;
	public Consumer<I> getAfterSend() { return afterSend; }
	public void setAfterSend(Consumer<I> afterSend) { this.afterSend = afterSend; }

	
	private Image dragView;
	public Image getDragView(){ return dragView; }
	public void setDragView(Image dragView){ this.dragView = dragView; }
	
	private final TransferMode mode;
	


	// CONSTRUCTORS

	public Draggable(T node, BiFunction<DragEvent, I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			Object group, TransferMode mode, Image dragView, boolean receives, boolean sends, boolean permitSeflDrag) {
		super(node);
		this.info = info;
		this.onReceive = onReceive == null? (e, i)->true : onReceive;
		this.afterSend = afterSend == null ? (i)->{} : afterSend;
		this.receives = receives;
		this.sends = sends;
		this.permitSelfDrag = permitSeflDrag;
		this.mode = mode;
		this.dragView = dragView;

		this.groupKey = new Integer(group.hashCode());
		this.key = new Integer(this.hashCode());
		this.dragString = DRAG_N_DROP_STRING + "-" + groupKey + "-" + key;

		if(!CHANNELS.containsKey(groupKey))
			CHANNELS.put(groupKey, new HashMap<>());

		this.groupChannel = CHANNELS.get(groupKey);
	}

	public Draggable(T node, Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
					Object group, TransferMode mode, Image dragView, boolean receives, boolean sends, boolean permitSeflDrag) {
		this(node, (e,i)->onReceive.apply(i), afterSend, info, group, mode, dragView, receives, sends, permitSeflDrag);
	}

	public Draggable(T node, Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			Object group, TransferMode mode, Image dragView) {
		this(node, onReceive, afterSend, info, group, mode, dragView, true, true, true);
	}

	public Draggable(T node, Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			TransferMode mode, Image dragView) {
		this(node, onReceive, afterSend, info, WITHOUT_GROUP, mode, dragView);
	}

	public Draggable(T node, Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			Object group, Image dragView) {
		this(node, onReceive, afterSend, info, group, TransferMode.LINK, dragView);
	}

	public Draggable(T node, Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			Object group, TransferMode mode) {
		this(node, onReceive, afterSend, info, group, mode, null);
	}

	public Draggable(T node, Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			Object group) {
		this(node, onReceive, afterSend, info, group, TransferMode.LINK, null);
	}

	public Draggable(T node, Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			TransferMode mode) {
		this(node, onReceive, afterSend, info, WITHOUT_GROUP, mode, null);
	}

	public Draggable(T node, Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			Image dragView) {
		this(node, onReceive, afterSend, info, WITHOUT_GROUP, TransferMode.LINK, dragView);
	}

	public Draggable(T node, Function<I, Boolean> onReceive, Consumer<I> afterSend, I info) {
		this(node, onReceive, afterSend, info, WITHOUT_GROUP, TransferMode.LINK, null);
	}

	public Draggable(IComponent<T> component, Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			Object group, TransferMode mode, Image dragView) {
		this(component.getNode(), onReceive, afterSend, info, group, mode, dragView);
	}

	public Draggable(IComponent<T> component, Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			TransferMode mode, Image dragView) {
		this(component.getNode(), onReceive, afterSend, info, mode, dragView);
	}

	public Draggable(IComponent<T> component, Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			Object group, Image dragView) {
		this(component.getNode(), onReceive, afterSend, info, group, dragView);
	}

	public Draggable(IComponent<T> component, Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			Object group, TransferMode mode) {
		this(component.getNode(), onReceive, afterSend, info, group, mode);
	}

	public Draggable(IComponent<T> component, Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			Object group) {
		this(component.getNode(), onReceive, afterSend, info, group);
	}

	public Draggable(IComponent<T> component, Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			TransferMode mode) {
		this(component.getNode(), onReceive, afterSend, info, mode);
	}

	public Draggable(IComponent<T> component, Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
			Image dragView) {
		this(component.getNode(), onReceive, afterSend, info, dragView);
	}

	public Draggable(IComponent<T> component, Function<I, Boolean> onReceive, Consumer<I> afterSend, I info) {
		this(component.getNode(), onReceive, afterSend, info);
	}


	// IMPLEMENTATION

	public void mount() {
		if(sends){
			setOnDragDetected();
			setOnDragDone();	
		}
		if(receives){
			setOnDragEntered();
			setOnDragExited();
			setOnDragOver();
			setOnDragDropped();	
		}		
	}


	private void setOnDragDetected(){
		EventHandler<? super MouseEvent> oldHandler = this.node.getOnDragDetected();
		EventHandler<? super MouseEvent> newHandler = this::onDragDetected;
		newHandler = Utils.chain(oldHandler, newHandler);
		this.node.setOnDragDetected(newHandler);
	}

	private void setOnDragDone(){
		EventHandler<? super DragEvent> oldHandler = this.node.getOnDragDone();
		EventHandler<? super DragEvent> newHandler = this::onDragDone;
		newHandler = Utils.chain(oldHandler, newHandler);
		this.node.setOnDragDone(newHandler);
	}

	private void setOnDragEntered(){
		EventHandler<? super DragEvent> oldHandler = this.node.getOnDragEntered();
		EventHandler<? super DragEvent> newHandler = this::onDragEntered;
		newHandler = Utils.chain(oldHandler, newHandler);
		this.node.setOnDragEntered(newHandler);
	}

	private void setOnDragExited(){
		EventHandler<? super DragEvent> oldHandler = this.node.getOnDragExited();
		EventHandler<? super DragEvent> newHandler = this::onDragExited;
		newHandler = Utils.chain(oldHandler, newHandler);
		this.node.setOnDragExited(newHandler);
	}

	private void setOnDragOver(){
		EventHandler<? super DragEvent> oldHandler = this.node.getOnDragOver();
		EventHandler<? super DragEvent> newHandler = this::onDragOver;
		newHandler = Utils.chain(oldHandler, newHandler);
		this.node.setOnDragOver(newHandler);
	}

	private void setOnDragDropped(){
		EventHandler<? super DragEvent> oldHandler = this.node.getOnDragDropped();
		EventHandler<? super DragEvent> newHandler = this::onDragDropped;
		newHandler = Utils.chain(oldHandler, newHandler);
		this.node.setOnDragDropped(newHandler);
	}


	private void onDragDetected(MouseEvent event){
		Dragboard db = this.node.startDragAndDrop(mode);
		ClipboardContent content = new ClipboardContent();
		content.putString(dragString);
		db.setContent(content);
		db.setDragView(dragView);

		putInfo(key, false);

		event.consume();
	}

	private void onDragDone(DragEvent event){
		if(isAKnownTransference(event)){
			Integer otherKey = extractKey(event.getDragboard().getString());

			if(!groupChannel.containsKey(key)){

				I info = removeInfo(otherKey);
				afterSend.accept(info);

			} else {

				boolean isSelfDrag = otherKey.equals(key);
				if(isSelfDrag && permitSelfDrag && groupChannel.get(key).getValue())	
					afterSend.accept(info);

				groupChannel.remove(key);

			}

			event.consume();	
		}
	}

	private void onDragOver(DragEvent event) {
		if (isAKnownTransference(event))  
			event.acceptTransferModes(mode);

		event.consume();
	}

	private void onDragEntered(DragEvent event) {
		if(isAKnownTransference(event))
			this.node.setStyle(this.node.getStyle()+";-fx-border-color : green");

		event.consume();
	}

	private void onDragExited(DragEvent event) {
		if(isAKnownTransference(event))
			this.node.setStyle(this.node.getStyle().replace(";-fx-border-color : green", ""));

		event.consume();        
	}

	private void onDragDropped(DragEvent event) {	
		if (isAKnownTransference(event)){

			Integer otherKey = extractKey(event.getDragboard().getString());
			boolean isSelfDrag = otherKey.equals(key);

			if(isSelfDrag){
				treatSelfDrag(event);
				event.setDropCompleted(true);
				return;
			}

			I otherInfo = getInfo(otherKey);
			if(onReceive.apply(event, otherInfo)){
				groupChannel.remove(otherKey);

				putInfo(key, true);

				ClipboardContent content = new ClipboardContent();
				content.putString(dragString);
				event.getDragboard().setContent(content);

				event.setDropCompleted(true);	
			}

		}
	}

	private void treatSelfDrag(DragEvent event){
		if(!permitSelfDrag)
			groupChannel.get(key).setValue(false);	
		else
			if(onReceive.apply(event, info))
				groupChannel.get(key).setValue(true);
			else
				groupChannel.get(key).setValue(false);
	}

	private boolean isAKnownTransference(DragEvent event){
		return 	event.getDragboard().hasString() && 
				event.getDragboard().getString().startsWith(DRAG_N_DROP_STRING) &&
				extractGroup(event.getDragboard().getString()).equals(groupKey);
	}


	private void putInfo(Integer key, Boolean bool){
		groupChannel.put(key, new Pair<>(info, bool));
	}

	@SuppressWarnings("unchecked")
	private I getInfo(Integer key){
		return (I)(((Pair<Object, Boolean>)(groupChannel.get(key))).getKey());
	}

	@SuppressWarnings("unchecked")
	private I removeInfo(Integer key){
		return (I)(((Pair<Object, Boolean>)(groupChannel.remove(key))).getKey());
	}

}




