package components;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import utils.ComponentException;
import utils.IInitializable;

public class FXMLFile<A/*initializable argument*/> implements IComponent {
    
	private Node root;
	
    private final String fXMLFilePath;
    private final boolean isInitializable;
    private A initializableArgument;
    
    // Constructors
    
    public FXMLFile(String fXMLFilePath, Class<?> controllerClass, A initializableArgument){
    	this.fXMLFilePath = fXMLFilePath;
        this.isInitializable = controllerClass == null || IInitializable.class.isAssignableFrom(controllerClass);
        this.initializableArgument = initializableArgument;
    }
    
    public FXMLFile(String fXMLFilePath, Class<?> controllerClass){
        this(fXMLFilePath, controllerClass, null);
    }
    
    public FXMLFile(String fXMLFilePath){
        this(fXMLFilePath, null, null);
    }
    
    
    // Implementation
    
    public A getInitializableArgument(){
        return initializableArgument;
    }
    
    public void setInitializableArgument(A initializableArgument){
        this.initializableArgument = initializableArgument;
    }
    
    @Override
    public Node getNode(){
        return root;
    }
    
    public void build() throws ComponentException{
    	mount();
    }
  
    @Override
	public void mount() throws ComponentException {
        FXMLLoader loader = getLoader();
        
        try{
        	root = loader.load();	
        }catch(Exception e){
        	throw new ComponentException("Loading fxml file " + fXMLFilePath);
        }
        
        if(isInitializable){
            IInitializable<A> controller = loader.getController();
            controller.init(initializableArgument);   
        }
    }
    
    public FXMLLoader getLoader(){
        URL location = ClassLoader.getSystemResource(fXMLFilePath);
        return  new FXMLLoader(location);
    }

}
