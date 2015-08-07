package components.connect.connection;

import java.util.Collection;
import java.util.function.Consumer;

import javafx.scene.Node;

import components.connect.Coordinates;

public interface IConnection<T extends Node>{
	
	public T getNode();
	
	public Coordinates getCordinates();
	
	public void registerCoordinatesListner(Consumer<Coordinates> coordenatesConsumer);
	
	public Collection<Coordinates> getConnectionPoints();
	
}
