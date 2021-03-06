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

import components.Movable;
import components.connect.Connectable;

public class Demo extends Application {

	private static final String FXML_DOCUMENT_PATH = "resources/FXMLDocument.fxml";
	private static final String TITLE = "Connectable";
	private static final String DESCRIPTION = "Demo to show usage of Connectable component.\nDrag buttons below around and watch connectors following them.";

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

		setup(demoPane, buttonA, buttonB);
		
		Connectable connectable = new Connectable(buttonA, buttonB);
		connectable.mount();
		
		demoPane.getChildren().add(connectable.getNode());
	}

	public static void setup(AnchorPane demoPane, Button bA, Button bB) {
		new Movable<>(bA).mount();
		new Movable<>(bB).mount();

		demoPane.getChildren().add(bA);
		demoPane.getChildren().add(bB);

		bB.setLayoutX(500);
	}

    public static void main(String[] args) {
        launch(args);
    }

}

