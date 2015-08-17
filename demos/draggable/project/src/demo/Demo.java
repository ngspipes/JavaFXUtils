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

import components.Draggable;





public class Demo extends Application {
	
	private static final String FXML_DOCUMENT_PATH = "resources/FXMLDocument.fxml";
	private static final String TITLE = "Draggable";
	private static final String DESCRIPTION = "Demo to show usage of Draggable component.\nPlease drag one of the buttons bellow into the other and wath the transfered data being printed.";

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
		Label label = new Label();
		Button buttonA = new Button("", A_IMAGE);
		Button buttonB = new Button("", B_IMAGE);
		
		label.setLayoutY(200);
		buttonB.setLayoutX(500);
		
		demoPane.getChildren().add(label);
		demoPane.getChildren().add(buttonA);
		demoPane.getChildren().add(buttonB);
		
		new Draggable<>(buttonA, (item)->true, (item)->label.setText(label.getText() + item.toString()), "A").mount();
		new Draggable<>(buttonB, (item)->true, (item)->label.setText(label.getText() + item.toString()), "B").mount();
	}
	
    public static void main(String[] args) {
        launch(args);
    }
    
}
