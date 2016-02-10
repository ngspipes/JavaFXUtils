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
package components.connect.connector;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import jfxutils.Utils;

import components.Component;
import components.IComponent;




public class Connector extends Component<Line>{
	
	public static final Node NO_TIP = null;

	protected final Line line;
	public final ConnectorTips tips;
	
	// Constructors
	
	public Connector(Line line, ConnectorTips tips) {
		super(line);
		this.line = line;
		this.tips = tips;
	}
	
	public Connector(Line line){
		this(line, new ConnectorTips(NO_TIP, NO_TIP));
	}
	
	public Connector(IComponent<Line> component, ConnectorTips tips){
		this(component.getNode(), tips);
	}
	
	public Connector(IComponent<Line> component){
		this(component.getNode());
	}
	
	public Connector(ConnectorTips tips){
		this(new Line(), tips);	
	}
	
	public Connector() {
		this(new Line(), new ConnectorTips(NO_TIP, NO_TIP));
	}
	
	
	// Implementation

	@Override
	public void mount() {
		setLineParentListener();
		setCoordenateListeners();
		setVisibilityListener();
		setOnTipsMouseClicked();
		
		if(line.getParent() != null)
			addTipsToLineParent();
	}
	
	private void setLineParentListener(){
		line.parentProperty().addListener((a, oldParent, newParent)->{
			// INCONSISTENT STATE
			if(newParent==null && ((Pane)oldParent).getChildren().contains(line)){
				Platform.runLater(this::removeTipsFromLineParent);
				return;
			}
			
			removeTipsFromLineParent();

			if(line.getParent() != null)
				addTipsToLineParent();
		});
	}

	private void removeTipsFromLineParent(){
		if(tips.getInit() != NO_TIP && tips.getInit().getParent() != null){
			Pane parent = (Pane)tips.getInit().getParent();
			parent.getChildren().remove(tips.getInit());
		}
			
		if(tips.getEnd() != NO_TIP && tips.getEnd().getParent() != null){
			Pane parent = (Pane)tips.getEnd().getParent();
			parent.getChildren().remove(tips.getEnd());
		}
	}
	
	private void addTipsToLineParent(){
		Pane parent = (Pane)line.getParent();
		
		if(tips.getInit() != NO_TIP){
			parent.getChildren().add(tips.getInit());
			setInitTipCoordinates();
		}
			
		if(tips.getEnd() != NO_TIP){
			parent.getChildren().add(tips.getEnd());
			setEndTipCoordinates();	
		}
	}

	private void setInitTipCoordinates(){
		double translatedX = line.getStartX() - (tips.getInit().getBoundsInLocal().getWidth()/2);
		double translatedY = line.getStartY() - (tips.getInit().getBoundsInLocal().getHeight()/2);
		
		tips.getInit().setLayoutX(translatedX);
		tips.getInit().setLayoutY(translatedY);
	}
	
	private void setEndTipCoordinates(){
		double translatedX = line.getEndX() - (tips.getEnd().getBoundsInLocal().getWidth()/2);
		double translatedY = line.getEndY() - (tips.getEnd().getBoundsInLocal().getHeight()/2);
		
		tips.getEnd().setLayoutX(translatedX);
		tips.getEnd().setLayoutY(translatedY);
	}
	
	private void setCoordenateListeners(){
		if(tips.getInit() != NO_TIP){
			line.startXProperty().addListener((a)->setInitTipCoordinates());
			line.startYProperty().addListener((a)->setInitTipCoordinates());	
		}
		
		if(tips.getEnd() != NO_TIP){
			line.endXProperty().addListener((a)->setEndTipCoordinates());
			line.endYProperty().addListener((a)->setEndTipCoordinates());	
		}
	}
	
	private void setVisibilityListener(){
		if(tips.getInit() != NO_TIP)
			this.node.visibleProperty().addListener((obs, wasVisible, isVisible)->tips.getInit().setVisible(isVisible));
		
		if(tips.getEnd() != NO_TIP)
			this.node.visibleProperty().addListener((obs, wasVisible, isVisible)->tips.getEnd().setVisible(isVisible));
	}
	
	private void setOnTipsMouseClicked(){
		if(tips.getInit() != NO_TIP)
			fireLineMouseClicked(tips.getInit());
		if(tips.getEnd() != NO_TIP)
			fireLineMouseClicked(tips.getEnd());
	}
	
	public void fireLineMouseClicked(Node node){
		EventHandler<? super MouseEvent> oldHandler = node.getOnMouseClicked();
		EventHandler<? super MouseEvent> newHandler = (event)->{
			line.fireEvent(event);
			event.consume();
		};
		newHandler = Utils.chain(oldHandler, newHandler);
		node.setOnMouseClicked(newHandler);
	}
	
	public void setInit(double x, double y){
		setInitX(x);
		setInitY(y);
	}
	
	public void setEnd(double x, double y){
		setEndX(x);
		setEndY(y);
	}
	
	public void setInitX(double x){
		line.setStartX(x);
	}
	
	public void setInitY(double y){
		line.setStartY(y);
	}
	
	public void setEndX(double x){
		line.setEndX(x);
	}
	
	public void setEndY(double y){
		line.setEndY(y);
	}
	
	public double getInitX(){
		return line.getStartX();
	}
	
	public double getInitY(){
		return line.getStartY();
	}
	
	public double getEndX(){
		return line.getEndX();
	}
	
	public double getEndY(){
		return line.getEndY();
	}
	
}


