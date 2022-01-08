package view;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ViewInterface extends Application {

	// --module-path lib --add-modules javafx.controls,javafx.fxml
	public void start(Stage stage) throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/InterfaceV3.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("3Dioptres");
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
