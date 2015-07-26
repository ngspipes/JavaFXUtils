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
    private A arg;
    
    // Constructors
    
    public FXMLFile(String fXMLComponentPath, Class<?> controllerClass, A arg){
    	this.fXMLFilePath = fXMLComponentPath;
        this.isInitializable = controllerClass == null || IInitializable.class.isAssignableFrom(controllerClass);
        this.arg = arg;
    }
    
    public FXMLFile(String fXMLComponentPath, Class<?> controllerClass){
        this(fXMLComponentPath, controllerClass, null);
    }
    
    public FXMLFile(String fXMLComponentPath){
        this(fXMLComponentPath, null, null);
    }
    
    
    // Implementation
    
    public A getArg(){
        return arg;
    }
    
    public void setArg(A arg){
        this.arg = arg;
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
            controller.init(arg);   
        }
    }
    
    public FXMLLoader getLoader(){
        URL location = ClassLoader.getSystemResource(fXMLFilePath);
        return  new FXMLLoader(location);
    }

}
