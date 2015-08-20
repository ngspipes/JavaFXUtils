package components;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import utils.Utils;

import components.animation.changeMouse.ChangeMouseOnPass;
import components.animation.changeMouse.ChangeMouseOnPress;

public class Movable<T extends Node> extends Component<T>{

	private static class Delta { double x, y; }

	private Bounds parentBounds;
	private final BiFunction<Double, Double, Boolean> acceptNewPosition;
	private final Consumer<MouseEvent> onMove;
	private final boolean keepOldHandlers; 
	private final Delta dragDelta = new Delta();

	// Constructors
	
	public Movable(T node, BiFunction<Double, Double, Boolean> acceptNewPosition, Consumer<MouseEvent> onMove, boolean keepOldHandlers){
		super(node);
		this.acceptNewPosition = acceptNewPosition;
		this.onMove = onMove;
		this.keepOldHandlers = keepOldHandlers;
	}
	
	public Movable(T node, Consumer<MouseEvent> onMove, boolean keepOldHandlers){
		this(node, (x,y)->true, onMove, keepOldHandlers);
	}

	public Movable(T node, Consumer<MouseEvent> onMove){
		this(node, onMove, true);
	}

	public Movable(T node, BiConsumer<Double, Double> onMove, boolean keepOldHandlers){
		this(node, (event)->onMove.accept(event.getSceneX(), event.getSceneY()), keepOldHandlers);	
	}
	
	public Movable(T node, BiConsumer<Double, Double> onMove){
		this(node, onMove, true);	
	}

	public Movable(T node, boolean keepOldHandlers) {
		this(node, (event)->{}, keepOldHandlers);
	}
	
	public Movable(T node) {
		this(node, true);
	}
	
	public Movable(IComponent<T> component, Consumer<MouseEvent> onMove, boolean keepOldHandlers){
		this(component.getNode(), onMove, keepOldHandlers);
	}

	public Movable(IComponent<T> component, Consumer<MouseEvent> onMove){
		this(component.getNode(), onMove);
	}

	public Movable(IComponent<T> component, BiConsumer<Double, Double> onMove, boolean keepOldHandlers){
		this(component.getNode(), onMove, keepOldHandlers);
	}
	
	public Movable(IComponent<T> component, BiConsumer<Double, Double> onMove){
		this(component.getNode(), onMove);
	}

	public Movable(IComponent<T> component, boolean keepOldHandlers) {
		this(component.getNode(), keepOldHandlers);
	}
	
	public Movable(IComponent<T> component) {
		this(component.getNode());
	}


	// Implementation

	@Override
	public void mount() {
		setParentBounds();
		this.node.parentProperty().addListener((event)->setParentBounds());
		
		setOnPressed();
		setOnDragged();
		
		new ChangeMouseOnPress<>(this.node, Cursor.CLOSED_HAND, Cursor.OPEN_HAND).mount();
		new ChangeMouseOnPass<>(this.node, Cursor.OPEN_HAND, Cursor.DEFAULT).mount();
	}
	
	private void setParentBounds(){
		if(this.node.getParent()!=null)
			parentBounds = this.node.getParent().getLayoutBounds();
	}

	private void setOnPressed(){
		EventHandler<? super MouseEvent> oldHandler = this.node.getOnMousePressed();
		EventHandler<? super MouseEvent> newHandler = (event)->{
															dragDelta.x = node.getLayoutX() - event.getSceneX();
															dragDelta.y = node.getLayoutY() - event.getSceneY();
														};
						
		newHandler = Utils.chain(oldHandler, newHandler, keepOldHandlers);
		
		this.getNode().setOnMousePressed(newHandler);
	}

	private void setOnDragged(){
		EventHandler<? super MouseEvent> oldHandler = this.node.getOnMouseDragged();
		EventHandler<? super MouseEvent> newHandler = (event)->{
															double newX = getNewX(event);
															double newY = getNewY(event);
															
															if(acceptNewPosition.apply(newX, newY)){
																node.setLayoutX(newX);
																node.setLayoutY(newY);
																onMove.accept(event);
															}
														};

		newHandler = Utils.chain(oldHandler, newHandler, keepOldHandlers);
		
		this.getNode().setOnMouseDragged(newHandler);
	}
	
	private double getNewX(MouseEvent event){
		double newX = event.getSceneX() + dragDelta.x;
		
		double maxX = parentBounds.getMaxX() - this.node.getLayoutBounds().getWidth();
		double minX = parentBounds.getMinX();
		
		if(newX>maxX)
			newX = maxX;
		else if(newX<minX)
			newX = minX;
		
		return newX; 
	}
	
	private double getNewY(MouseEvent event){
		double newY = event.getSceneY() + dragDelta.y;
		
		double maxY = parentBounds.getMaxY() - this.node.getLayoutBounds().getHeight();
		double minY = parentBounds.getMinY();
		
		if(newY>maxY)
			newY = maxY;
		else if(newY<minY)
			newY = minY;
		
		return newY; 
	}
	
}
