package components.connect.connector;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import components.Component;
import components.IComponent;


public class Connector extends Component<Line>{
	
	public static final Node NO_TIP = null;

	protected final Line line;
	protected final ConnectorTips tips;
	
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
		setLineParentListner();
		setCoordenateListners();
		
		if(line.getParent() != null)
			addTipsToLineParent();
	}
	
	private void setLineParentListner(){
		line.parentProperty().addListener((a)->{
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
	
	private void setCoordenateListners(){
		if(tips.getInit() != NO_TIP){
			line.startXProperty().addListener((a)->setInitTipCoordinates());
			line.startYProperty().addListener((a)->setInitTipCoordinates());	
		}
		
		if(tips.getEnd() != NO_TIP){
			line.endXProperty().addListener((a)->setEndTipCoordinates());
			line.endYProperty().addListener((a)->setEndTipCoordinates());	
		}
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


