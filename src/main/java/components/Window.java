/*-
 * Copyright (c) 2016, NGSPipes Team <ngspipes@gmail.com>
 * All rights reserved.
 *
 * This file is part of NGSPipes <http://ngspipes.github.io/>.
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package components;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxutils.ComponentException;

public class Window<T extends Parent, A> implements IComponent<T>{
    
    private String windowTitle;
    public String getWindowTitle(){ return windowTitle; }
    public void setWindowTitle(String windowTitle){ this.windowTitle = windowTitle; }
    
    private T root;
    public T getRoot(){ return getNode(); }
    public void setRoot(T root){ this.root=root; }
    
    private FXMLFile<T,A> fXMLFile;
    public FXMLFile<T,A> getFXMLFile(){ return fXMLFile; }
    public void setFXMLFile(FXMLFile<T,A> fXMLFile){ this.fXMLFile = fXMLFile; }
    
    private Stage stage;
    
    // Constructors
    
    public Window(T root, String windowTitle){
    	this.root = root;
        this.windowTitle = windowTitle;
    }
    
    public Window(T root){
    	this(root, "");
    }
    
    public Window(IComponent<T> component, String windowTitle){
    	this(component.getNode(), windowTitle);
    }
    
    public Window(IComponent<T> component){
    	this(component.getNode());
    }
    
    public Window(String fXMLFilePath, A initializableArgument, String windowTitle){
    	fXMLFile = new FXMLFile<>(fXMLFilePath, initializableArgument);
    	this.windowTitle = windowTitle;
    }
    
    public Window(String fXMLFilePath, A initializableArgument){
    	this(fXMLFilePath, initializableArgument, "");
    }
    
    public Window(String fXMLFilePath, String windowTitle){
    	this(fXMLFilePath, null, windowTitle);
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
