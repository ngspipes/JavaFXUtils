package components;

import java.util.HashMap;
import java.util.Map;
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
import utils.Utils;

public class Draggable<T extends Node, I/*Info*/> extends Component<T> {
	
	private static final Map<Integer, Map<Integer, Object>> channel = new HashMap<>();
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
	
	private boolean receives;
	public boolean getReceives(){ return receives; }
	public void setReceives(boolean receives){ this.receives = receives; }
	
	private boolean sends;
	public boolean getSends(){ return sends; }
	public void setSends(boolean sends){ this.sends = sends; }
	
	private final Map<Integer, Object> groupChannel;
	private final Integer groupKey;
	private final Integer key;
	private final String dragString;
	
	private final Function<I, Boolean> onReceive;
	private final Consumer<I> afterSend;
	private final TransferMode mode;
	private final Image dragView;
	
	

	// CONSTRUCTORS
	
	public Draggable(T node, Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
						Object group, TransferMode mode, Image dragView, boolean receives, boolean sends) {
		super(node);
		this.info = info;
		this.onReceive = onReceive == null? (i)->true : onReceive;
		this.afterSend = afterSend == null ? (i)->{} : afterSend;
		this.receives = receives;
		this.sends = sends;
		this.mode = mode;
		this.dragView = dragView;
		
		this.groupKey = new Integer(group.hashCode());
		this.key = new Integer(this.hashCode());
		this.dragString = DRAG_N_DROP_STRING + "-" + groupKey + "-" + key;
		
		if(!channel.containsKey(groupKey))
		channel.put(groupKey, new HashMap<>());
		
		this.groupChannel = channel.get(groupKey);
	}
	
	public Draggable(T node, Function<I, Boolean> onReceive, Consumer<I> afterSend, I info, 
						Object group, TransferMode mode, Image dragView) {
		this(node, onReceive, afterSend, info, group, mode, dragView, true, true);
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
		newHandler = Utils.chain(oldHandler, newHandler, true);
		this.node.setOnDragDetected(newHandler);
	}
	
	private void setOnDragDone(){
		EventHandler<? super DragEvent> oldHandler = this.node.getOnDragDone();
		EventHandler<? super DragEvent> newHandler = this::onDragDone;
		newHandler = Utils.chain(oldHandler, newHandler, true);
		this.node.setOnDragDone(newHandler);
	}
	
	private void setOnDragEntered(){
		EventHandler<? super DragEvent> oldHandler = this.node.getOnDragEntered();
		EventHandler<? super DragEvent> newHandler = this::onDragEntered;
		newHandler = Utils.chain(oldHandler, newHandler, true);
		this.node.setOnDragEntered(newHandler);
	}
	
	private void setOnDragExited(){
		EventHandler<? super DragEvent> oldHandler = this.node.getOnDragExited();
		EventHandler<? super DragEvent> newHandler = this::onDragExited;
		newHandler = Utils.chain(oldHandler, newHandler, true);
		this.node.setOnDragExited(newHandler);
	}
	
	private void setOnDragOver(){
		EventHandler<? super DragEvent> oldHandler = this.node.getOnDragOver();
		EventHandler<? super DragEvent> newHandler = this::onDragOver;
		newHandler = Utils.chain(oldHandler, newHandler, true);
		this.node.setOnDragOver(newHandler);
	}
	
	private void setOnDragDropped(){
		EventHandler<? super DragEvent> oldHandler = this.node.getOnDragDropped();
		EventHandler<? super DragEvent> newHandler = this::onDragDropped;
		newHandler = Utils.chain(oldHandler, newHandler, true);
		this.node.setOnDragDropped(newHandler);
	}
		
	
	private void onDragDetected(MouseEvent event){
		Dragboard db = this.node.startDragAndDrop(mode);
		ClipboardContent content = new ClipboardContent();
		content.putString(dragString);
		db.setContent(content);
		db.setDragView(dragView);
		
		groupChannel.put(key, info);
		
		event.consume();
	}
	
	@SuppressWarnings("unchecked")
	private void onDragDone(DragEvent event){
		if(isAKnownTransference(event)){
			Integer otherKey = extractKey(event.getDragboard().getString());
			
			if(!groupChannel.containsKey(key)){
				I info = (I)groupChannel.remove(otherKey);
				afterSend.accept(info);
			} else {
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
			this.node.setStyle("-fx-border-color : green");

		event.consume();
	}

	private void onDragExited(DragEvent event) {
		if(isAKnownTransference(event))
			this.node.setStyle("-fx-border-color : transparent");
		
		event.consume();        
	}

	@SuppressWarnings("unchecked")
	private void onDragDropped(DragEvent event) {	
		if (isAKnownTransference(event)){
			
			Integer otherKey = extractKey(event.getDragboard().getString());
			I otherInfo = (I)groupChannel.get(otherKey);
			
			if(onReceive.apply(otherInfo)){
				groupChannel.remove(otherKey);
				
				groupChannel.put(key, info);
				
				ClipboardContent content = new ClipboardContent();
				content.putString(dragString);
				event.getDragboard().setContent(content);
				
				event.setDropCompleted(true);	
			}
		}
	}

	private boolean isAKnownTransference(DragEvent event){
		return 	event.getDragboard().hasString() && 
				event.getDragboard().getString().startsWith(DRAG_N_DROP_STRING) &&
				extractGroup(event.getDragboard().getString()).equals(groupKey);
	}

}




