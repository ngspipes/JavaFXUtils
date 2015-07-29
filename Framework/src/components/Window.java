package components;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.ComponentException;

public class Window<T extends Parent, A> implements IComponent<T>{
    
    private final String windowTitle;
    private T root;
    private FXMLFile<T,A> fXMLFile;
    private Stage stage;
    
    // Constructors
    
    public Window(T root, String windowTitle){
    	this.root = root;
        this.windowTitle = windowTitle;
    }
    
    public Window(T root){
    	this(root, "");
    }
    
    public Window(String fXMLFilePath, Class<?> controllerClass, A initializableArgument, String windowTitle){
    	fXMLFile = new FXMLFile<>(fXMLFilePath, controllerClass, initializableArgument);
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
    
    public T getNode(){
    	return root;
    }
    
    @Override
	public void mount() throws ComponentException{
		open();
	}
    
    public void open() throws ComponentException{
    	if(fXMLFile != null){
    		fXMLFile.mount();
    		root = fXMLFile.getNode();
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
