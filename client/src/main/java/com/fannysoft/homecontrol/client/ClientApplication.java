package com.fannysoft.homecontrol.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientApplication extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(ApplicationController.class.getResource("mainWindow.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());

		stage.setScene(scene);
		stage.setTitle("Agents");

		stage.setOnCloseRequest(e -> Platform.exit());
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}


}
