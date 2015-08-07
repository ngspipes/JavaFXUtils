package demo;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.ComponentException;

import components.Movable;
import components.connect.Connectable;

public class Demo extends Application {
	
	private static final String FXML_DOCUMENT_PATH = "resources/FXMLDocument.fxml";
	private static final String TITLE = "Connectable";
	private static final String DESCRIPTION = "Demo to show usage of Connectable component.\nDrag buttons below around and watch connectors following them.";
	
	private static final ImageView A_IMAGE = new ImageView(new Image("resources/A.png"));
	private static final ImageView B_IMAGE = new ImageView(new Image("resources/B.png"));
	
	@Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = getLoader();
        
        init(stage, loader);
        
        mount(loader.getController());
    }
	
	public static FXMLLoader getLoader(){
		URL location = ClassLoader.getSystemResource(FXML_DOCUMENT_PATH);
		return new FXMLLoader(location);
	}

	private static void init(Stage stage, FXMLLoader loader) throws Exception{
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();	
	}
	
	private static void mount(Controller controller) throws Exception{
		controller.title.setText(TITLE);
		controller.description.setText(DESCRIPTION);
		controller.description.editableProperty().set(false);
		mountDemo(controller.demoPane);
	}
	
	////////////////////////////////////////////////////////
	//                                                    //
	//                       DEMO                         //
	//                                                    //
	//    mountDemo(SubScene) -> relevant method          // 
	//                           to see Component         //
	//                           utilization              //
	//                                                    //
 	//                                                    //
	////////////////////////////////////////////////////////

	private static void mountDemo(AnchorPane demoPane) {
		Button buttonA = new Button("", A_IMAGE);
		Button buttonB = new Button("", B_IMAGE);
		
		try {
			setup(demoPane, buttonA, buttonB);
		} catch (Exception e) {
			demoPane.getChildren().add(new Label(e.getMessage()));
			return;
		}

		Connectable connectable = new Connectable(buttonA, buttonB);

		demoPane.getChildren().add(connectable.getNode());
		
		connectable.mount();
	}
	
	public static void setup(AnchorPane demoPane, Button bA, Button bB) throws ComponentException{
		new Movable<>(bA).mount();
		new Movable<>(bB).mount();
		
		demoPane.getChildren().add(bA);
		demoPane.getChildren().add(bB);
		
		bB.setLayoutX(500);
	}
	
    public static void main(String[] args) {
        launch(args);
    }
    
}

