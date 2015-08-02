package demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import utils.ComponentException;
import utils.IInitializable;

public class WindowController implements IInitializable<Void>{
	@FXML
	public Label title;

	@Override
	public void init(Void arg) throws ComponentException {
		this.title.setText("Title set by WindowController");
	}
	
}