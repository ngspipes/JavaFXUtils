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

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import utils.Utils;

import components.animation.changeMouse.ChangeMouseOnPass;
import components.animation.changeMouse.ChangeMouseOnPress;

public class Movable<T extends Node> extends Component<T>{
	
	public static class MoveResult{
		
		public final MouseEvent event;
		public final double initX;
		public final double initY;
		public final double endX;
		public final double endY;
		
		public MoveResult(MouseEvent event, double initX, double initY, double endX, double endY){
			this.event = event;
			this.initX = initX;
			this.initY = initY;
			this.endX = endX;
			this.endY = endY;
		}
		
	}
	

	private static class Delta { double x, y, initX, initY; }

	private final BiFunction<Double, Double, Boolean> acceptNewPosition;
	private final Consumer<MouseEvent> onMove;
	private final Consumer<MoveResult> onMoveFinished;
	private final boolean keepOldHandlers; 
	private final Delta dragDelta = new Delta();
	private boolean moving;

	// Constructors
	
	public Movable(T node, BiFunction<Double, Double, Boolean> acceptNewPosition, Consumer<MouseEvent> onMove, Consumer<MoveResult> onMoveFinished, boolean keepOldHandlers){
		super(node);
		this.acceptNewPosition = acceptNewPosition;
		this.onMove = onMove;
		this.keepOldHandlers = keepOldHandlers;
		this.onMoveFinished = onMoveFinished;
		this.moving = false;
	}
	
	public Movable(T node, Consumer<MouseEvent> onMove, boolean keepOldHandlers){
		this(node, (x,y)->true, onMove, (mr)->{}, keepOldHandlers);
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
		setOnPressed();
		setOnReleased();
		setOnDragged();

		new ChangeMouseOnPress<>(this.node, Cursor.CLOSED_HAND, Cursor.OPEN_HAND).mount();
		new ChangeMouseOnPass<>(this.node, Cursor.OPEN_HAND, Cursor.DEFAULT).mount();
	}

	private void setOnPressed(){
		EventHandler<? super MouseEvent> oldHandler = this.node.getOnMousePressed();
		EventHandler<? super MouseEvent> newHandler = (event)->{
															dragDelta.initX = this.node.getLayoutX();
															dragDelta.initY = this.node.getLayoutY();
															dragDelta.x = this.node.getLayoutX() - event.getSceneX();
															dragDelta.y = this.node.getLayoutY() - event.getSceneY();
														};
						
		newHandler = Utils.chain(oldHandler, newHandler, keepOldHandlers);
		
		this.getNode().setOnMousePressed(newHandler);
	}

	private void setOnReleased(){
		EventHandler<? super MouseEvent> oldHandler = this.node.getOnMouseReleased();
		EventHandler<? super MouseEvent> newHandler = (e)->{
			if(moving){
				onMoveFinished.accept(new MoveResult(e, dragDelta.initX, dragDelta.initY, this.node.getLayoutX(), this.node.getLayoutY()));
				moving=false;
			}
		};
		newHandler = Utils.chain(oldHandler, newHandler, keepOldHandlers);
		this.node.setOnMouseReleased(newHandler);
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
															
															if(!moving)
																moving = true;
														};

		newHandler = Utils.chain(oldHandler, newHandler, keepOldHandlers);
		
		this.getNode().setOnMouseDragged(newHandler);
	}
	
	private double getNewX(MouseEvent event){
		double newX = event.getSceneX() + dragDelta.x;
		
		double maxX = this.node.getParent().getLayoutBounds().getMaxX() - this.node.getLayoutBounds().getWidth();
		double minX = this.node.getParent().getLayoutBounds().getMinX();
		
		if(newX>maxX)
			newX = maxX;
		else if(newX<minX)
			newX = minX;
		
		return newX; 
	}
	
	private double getNewY(MouseEvent event){
		double newY = event.getSceneY() + dragDelta.y;
		
		double maxY = this.node.getParent().getLayoutBounds().getMaxY() - this.node.getLayoutBounds().getHeight();
		double minY = this.node.getParent().getLayoutBounds().getMinY();
		
		if(newY>maxY)
			newY = maxY;
		else if(newY<minY)
			newY = minY;
		
		return newY; 
	}
	
}
