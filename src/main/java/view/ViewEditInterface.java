package view;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ViewEditInterface extends Application {

	// --module-path lib --add-modules javafx.controls,javafx.fxml
	// penser à exécuter `mvn javafx:run` avant de run
	public void start(Stage stage) throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Editor.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("PLY Viewer");
        stage.getIcons().add(new Image("/icons/logo.png"));
        stage.setResizable(true);
        
		stage.setX(100);
		stage.setY(50);
        stage.show();
        
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}

