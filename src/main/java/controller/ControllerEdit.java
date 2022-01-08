package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ControllerEdit {
	
	@FXML
	protected Label editedFileName;
	
	@FXML
	protected TextArea fileDescription;
	
	@FXML
	protected Button buttonSaveEdit;
	
	protected Path filePath = null;

	public void initialize() {	
		Controller.exportEditAttributs(this, Controller.labelEditedName, Controller.textEdited, Controller.pathEditedFile);
	}
	
	public void save() {
		try {
			Files.writeString(filePath, fileDescription.getText(), StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected static void exportEditAttributs(ControllerEdit ctrl, Label ediLabel, TextArea ediText, Path ediPath) {
        ctrl.editedFileName.setText(ediPath.getFileName().toString());;
        ctrl.fileDescription.setText(ediText.getText());;
        ctrl.filePath=ediPath;
    }

}
