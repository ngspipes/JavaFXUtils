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

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import jfxutils.ComponentException;
import jfxutils.IInitializable;

import java.net.URL;

public class FXMLFile<T extends Node, A/*initializable argument*/> implements IComponent<T> {
    
	private T root;
	
    private final String fXMLFilePath;
    private A initializableArgument;
    
    // Constructors
    
    public FXMLFile(String fXMLFilePath, A initializableArgument){
    	this.fXMLFilePath = fXMLFilePath;
        this.initializableArgument = initializableArgument;
    }
    
    public FXMLFile(String fXMLFilePath){
        this(fXMLFilePath, null);
    }
    
    
    // Implementation
    
    public A getInitializableArgument(){
        return initializableArgument;
    }
    
    public void setInitializableArgument(A initializableArgument){
        this.initializableArgument = initializableArgument;
    }
    
    @Override
    public T getNode(){
        return root;
    }
    
    public T getRoot(){
    	return getNode();
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
        	throw new ComponentException("Loading fxml file " + fXMLFilePath, e);
        }
        
        Object controller = loader.getController();
        if(controller!=null && (controller instanceof IInitializable))
            ((IInitializable<A>)controller).init(initializableArgument);
    }
    
    public FXMLLoader getLoader(){
        URL location = ClassLoader.getSystemResource(fXMLFilePath);
        return  new FXMLLoader(location);
    }

}
