package demo;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.ComponentException;

import components.Window;

public class Demo extends Application {
	
	private static final String FXML_DOCUMENT_PATH = "resources/FXMLDocument.fxml";
	private static final String FXML_WINDOW_PATH = "resources/FXMLWindow.fxml";
	private static final String TITLE = "Window";
	private static final String DESCRIPTION = "Demo to show usage of Window component.\nPlease click on button below to open Window.";
	
	
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
		Button button = new Button("Open");
		
		button.setOnMouseClicked((event)->openWindow(demoPane));
		
		demoPane.getChildren().add(button);
	}
	
	private static void openWindow(AnchorPane demoPane){
		try {
			new Window<AnchorPane, Void>(FXML_WINDOW_PATH).open();
		} catch (ComponentException e) {
			Label error = new Label();
			error.setText(e.getMessage());
			demoPane.getChildren().add(error);
		}
		
	}

    public static void main(String[] args) {
        launch(args);
    }
    
}