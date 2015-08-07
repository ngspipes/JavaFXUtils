package components.connect.connector;

import javafx.scene.Node;


public class ConnectorTips {
	
	private final Node init;
	
	private final Node end;
	
	public ConnectorTips(Node init, Node end){
		this.init = init; 
		this.end = end;
	}
	
	public Node getInit(){
		return init;
	}
	
	public Node getEnd(){
		return end;
	}
	
}
