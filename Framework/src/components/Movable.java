package components;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import utils.ComponentException;
import utils.Utils;

import components.mounter.ComponentMounter;

public class Movable extends Component{

	private static class Delta { double x, y; }

	private final Consumer<MouseEvent> onMove;
	private final boolean keepOldHandlers; 
	private final Delta dragDelta = new Delta();

	// Constructors
	
	public Movable(Node node, Consumer<MouseEvent> onMove, boolean keepOldHandlers){
		super(node);
		this.onMove = onMove;
		this.keepOldHandlers = keepOldHandlers;
	}

	public Movable(Node node, BiConsumer<Double, Double> onMove, boolean keepOldHandlers){
		this(node, (event)->onMove.accept(event.getSceneX(), event.getSceneY()), keepOldHandlers);	
	}

	public Movable(Node node, boolean keepOldHandlers) {
		this(node, (event)->{}, keepOldHandlers);
	}

	public Movable(IComponent component, Consumer<MouseEvent> onMove, boolean keepOldHandlers){
		this(component.getNode(), onMove, keepOldHandlers);
	}

	public Movable(IComponent component, BiConsumer<Double, Double> onMove, boolean keepOldHandlers){
		this(component.getNode(), onMove, keepOldHandlers);
	}

	public Movable(IComponent component, boolean keepOldHandlers) {
		this(component.getNode(), keepOldHandlers);
	}

	public Movable(Node node, Consumer<MouseEvent> onMove){
		this(node, onMove, true);
	}

	public Movable(Node node, BiConsumer<Double, Double> onMove){
		this(node, onMove, true);	
	}

	public Movable(Node node) {
		this(node, true);
	}

	public Movable(IComponent component, Consumer<MouseEvent> onMove){
		this(component.getNode(), onMove);
	}

	public Movable(IComponent component, BiConsumer<Double, Double> onMove){
		this(component.getNode(), onMove);
	}

	public Movable(IComponent component) {
		this(component.getNode());
	}


	// Implementation

	@Override
	public void mount() throws ComponentException {
		setOnPressed();
		setOnDragged();

		new ComponentMounter(this.node)
				.changeMouseOnPress(Cursor.CLOSED_HAND, Cursor.OPEN_HAND)
				.changeMouseOnPass(Cursor.OPEN_HAND, Cursor.DEFAULT)
				.mount();
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
															node.setLayoutX(event.getSceneX() + dragDelta.x);
															node.setLayoutY(event.getSceneY() + dragDelta.y);
															onMove.accept(event);
														};

		newHandler = Utils.chain(oldHandler, newHandler, keepOldHandlers);
		
		this.getNode().setOnMouseDragged(newHandler);
	}

}
