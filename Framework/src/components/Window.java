package components;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.ComponentException;

public class Window<A> implements IComponent{
    
    private final String windowTitle;
    private Parent root;
    private FXMLFile<A> fXMLFile;
    private Stage stage;
    
    // Constructors
    
    public Window(Parent root, String windowTitle){
    	this.root = root;
        this.windowTitle = windowTitle;
    }
    
    public Window(Parent root){
    	this(root, "");
    }
    
    public Window(String fXMLFilePath, Class<?> controllerClass, A initializableArgument, String windowTitle){
    	fXMLFile = new FXMLFile<A>(fXMLFilePath, controllerClass, initializableArgument);
    	this.windowTitle = windowTitle;
    }
    
    public Window(String fXMLFilePath, Class<?> controllerClass, A initializableArgument){
    	this(fXMLFilePath, controllerClass, initializableArgument, "");
    }
    
    public Window(String fXMLFilePath, Class<?> controllerClass, String windowTitle){
        this(fXMLFilePath, controllerClass, null, windowTitle);
    }
    
    public Window(String fXMLFilePath, Class<?> controllerClass){
        this(fXMLFilePath, controllerClass, "");
    }
    
    public Window(String fXMLFilePath, String windowTitle){
    	this(fXMLFilePath, null, null, windowTitle);
    }
    
    public Window(String fXMLFilePath){
    	this(fXMLFilePath, "");
    }
    
    
    // Implementation
    
    public Node getNode(){
    	return root;
    }
    
    @Override
	public void mount() throws ComponentException{
		open();
	}
    
    public void open() throws ComponentException{
    	if(fXMLFile != null){
    		fXMLFile.mount();
    		root = (Parent)fXMLFile.getNode();
    	}
    	
		stage = new Stage();
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public void close(){
    	if(stage != null)
            stage.close();  
    }
    
}
