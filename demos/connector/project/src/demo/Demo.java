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

import components.Movable;
import components.connect.Connector;

public class Demo extends Application {
	
	private static final String FXML_DOCUMENT_PATH = "resources/FXMLDocument.fxml";
	private static final String TITLE = "Connector";
	private static final String DESCRIPTION = "Demo to show usage of Connector component.\nDrag buttons below around and watch connectors following them.";
	
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
			new Movable<>(buttonA).mount();
			new Movable<>(buttonB).mount();
		} catch (Exception e) {
			Label error = new Label();
			error.setText(e.getMessage());
			demoPane.getChildren().add(error);
			return;
		}
		
		demoPane.getChildren().add(buttonA);
		demoPane.getChildren().add(buttonB);
		
		buttonB.setLayoutX(500);
		
		Connector connector = new Connector(buttonA, buttonB);
		connector.mount();
		
		demoPane.getChildren().add(connector.getNode());
	}
	
    public static void main(String[] args) {
        launch(args);
    }
    
}

