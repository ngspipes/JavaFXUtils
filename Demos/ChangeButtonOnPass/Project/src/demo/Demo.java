package demo;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import components.animation.changeComponent.ChangeButtonOnPass;

public class Demo extends Application {
	
	private static final String FXML_DOCUMENT_PATH = "resources/FXMLDocument.fxml";
	private static final String TITLE = "ChangeButtonOnPass";
	private static final String DESCRIPTION = "Demo to show usage of ChangeButtonOnPass component.\nPlase pass mouse over the button below and observe button changing image on MouseEnter and MouseExit.";
	private static final ImageView IN_IMAGE = new ImageView(new Image("resources/In.png"));
	private static final ImageView OUT_IMAGE = new ImageView(new Image("resources/Out.png"));
	
	
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

	private static void mountDemo(AnchorPane demoPane){
		Button button = new Button();
		button.setGraphic(IN_IMAGE);
		
		new ChangeButtonOnPass<>(button, OUT_IMAGE, IN_IMAGE).mount();
		
		demoPane.getChildren().add(button);
	}
	
    public static void main(String[] args) {
        launch(args);
    }
    
}