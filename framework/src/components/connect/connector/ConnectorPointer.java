package components.connect.connector;

import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

import components.IComponent;

public class ConnectorPointer extends Connector {
	
	private static Polygon getEndTip(){
		Polygon polygon = new Polygon();
		polygon.getPoints().addAll(new Double[]{
		    0.0, 0.0,
		    0.0, 15.0,
		    15.0, 7.5 });
		
		return polygon;
	}
	
	
	private double currentAngle = 0;
	
	public ConnectorPointer(){
		super(new ConnectorTips(Connector.NO_TIP, getEndTip()));
		setListners();
	}
	
	public ConnectorPointer(Line line){
		super(line, new ConnectorTips(Connector.NO_TIP, getEndTip()));
		setListners();
	}
	
	public ConnectorPointer(IComponent<Line> line){
		super(line, new ConnectorTips(Connector.NO_TIP, getEndTip()));
		setListners();
	}
	
	
	

	private void setListners(){
		this.line.endXProperty().addListener((a)-> rotate());
		this.line.endYProperty().addListener((a)-> rotate());		
		this.line.startXProperty().addListener((a)-> rotate());
		this.line.startYProperty().addListener((a)-> rotate());
	}
	
	private void rotate(){
		double nextAngle = getNextAngle();
		
		if(Double.isNaN(nextAngle))
			return;
		
		rotate(nextAngle-currentAngle);
		
		currentAngle = nextAngle;
	}
	
	private double getNextAngle(){
		double initX = this.getInitX();
		double endX = this.getEndX();
		double initY = this.getInitY();
		double endY = this.getEndY();
		
		double ca = endX - initX;
		double co = endY - initY;
		
		double angle = Math.toDegrees(Math.atan2(co, ca));
		
		return angle;
	}
	
	private void rotate(double angle){
		// (7.5, 7.5) are coordinates of middle point of triangle
		this.tips.getEnd().getTransforms().add(new Rotate(angle, 7.5, 7.5));
	}
	
}
