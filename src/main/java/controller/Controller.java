package controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import file.FileReader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Axis3D;
import model.MatrixBenchmark3D;
import model.MatrixFigure3D;

/**
 * 
 * 
 */
public class Controller {
	@FXML
	protected Pane pane;

	@FXML
	protected Slider sliderZoom;

	@FXML
	protected CheckBox checkBoxDisplayWires, checkBoxDisplayFaces, checkBoxLuminosity, checkBoxAutoRot;

	@FXML
	protected Button buttonTranslateUp, buttonTranslateDown,
	buttonTranslateRight, buttonTranslateLeft,
	buttonTranslatePull , buttonTranslatePush,
	buttonReset, buttonOpenFile, buttonDownload, buttonEdit, buttonLibrary;
	
	@FXML
	protected StackPane stackpaneButton;
	
	@FXML
	protected ToggleButton buttonOpenMouvement, buttonOpenOption;
	
	@FXML
	protected HBox hBoxMouvement, hBoxOption;

	@FXML
	protected MenuItem menuFileDownload, menuFileOpen, menuFileClose, menuFileExit,
	mvTransUp, mvTransDown, mvTransRight, mvTransLeft, mvTransPush, mvTransPull, mvTransReset, 
	mvRotPlusX, mvRotPlusY, mvRotPlusZ, mvRotMinusX, mvRotMinusY, mvRotMinusZ, menuHelpAbout,
	mvZoom50, mvZoom100, mvZoom250, mvZoom500;

	@FXML
	protected Label labelFile;

	@FXML 
	protected Canvas render, renderup, renderdown;
	
	@FXML
	protected ColorPicker colorPickerFace, colorPickerWire, colorPickerBackground;
	
	@FXML
	protected AnchorPane anchorPaneCanvas;
	
	@FXML
	protected TableView<FileReader> tableViewFile;
	
	@FXML
	protected TableColumn<FileReader, String> columnName, columnVertex, columnFace;

	protected final FileChooser fileChooser = new FileChooser();
	protected final DirectoryChooser directoryChooser = new DirectoryChooser();
	
	protected       File        openedFile  = null;
	protected String nomFichier;

	protected MatrixFigure3D figure;
	protected MatrixBenchmark3D benchmark;
	
	protected Color colorWires = Color.BLACK;
	protected Color colorFaces = Color.BLUEVIOLET;

	protected double xMin, xMax, yMin, yMax, zMin, zMax;
	protected double zoomValue;
	
	protected Thread threadHorloge;
	protected boolean shutdown = false;

	protected static Label labelEditedName = new Label();
	protected static TextArea textEdited = new TextArea();
	protected static Path pathEditedFile = null;
	
	public void initialize() {
		pickFace();
		pickBackground();
		pickWire();
		ControllerFiles.initialize(this);
		configureThread();
	}
	
	public void chooseLibrary() {
		ControllerFiles.chooseLibrary(this);

	}
	
	protected void configureThread() {
		UtilityClass.configureThread(this);
	}
	
	public void autoRotation() {
		ControllerAction.autoRotation(this);
	}
		
	public void submit() {
		try {
			ControllerFiles.submit(this);
		}catch (Exception e) {
			System.err.println("Impossible d'ouvrir le fichier");
		}
		
	}
	
	protected static void exportEditAttributs(ControllerEdit ctrl, Label ediLabel, TextArea ediText, Path ediPath) {
		ControllerEdit.exportEditAttributs(ctrl, ediLabel, ediText, ediPath);
	}
	
	public void editFile() throws IOException {				 
		FileReader fr = tableViewFile.getSelectionModel().getSelectedItem();
		String content = null;
		if (fr != null) {

	        try {
	        	pathEditedFile=Paths.get(fr.getPath());
	            content = Files.readString(pathEditedFile);
	            textEdited.setText(content);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			
	        Stage stage = new Stage();
	    	FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/fxml/Editor.fxml"));
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.setTitle("PLY Viewer");
	        stage.getIcons().add(new Image("/icons/logo.png"));
	        stage.setResizable(false);
	        
			stage.setX(100);
			stage.setY(50);
	        stage.show();
		}
		
	}

	public void resetZoom() {
		sliderZoom.adjustValue(1);
		drawFigure();
	}
	
	public void resetRotation() {
		ControllerAction.resetRotate(this);
	}
	
	public void resetTranslation() {
		ControllerAction.resetTranslat(this);
	}

	public void drawFigure() {
		ControllerAction.drawFigure(this);
	}

	protected void optimizeView() {
		ControllerCanvas.optimizeViewCanvas(this);
	}
	
	protected void centerFigure() {
		ControllerCanvas.centerFigure(this);
		UtilityClass.foundMaxMin(figure, this);
	}

	protected void reduceFigure() {
		ControllerCanvas.reduceFigure(this);
		UtilityClass.foundMaxMin(figure, this);
	}
	
	public void translateUp() {
		ControllerAction.translat(0.0, 0.1, 0.0, this);
	}
	
	public void translateDown() {
		ControllerAction.translat(0.0, -0.1, 0.0, this);
	}

	public void translateLeft() {
		ControllerAction.translat(-0.1, 0.0, 0.0, this);
	}

	public void translateRight() {
		ControllerAction.translat(0.1, 0.0, 0.0, this);
	}

	public void translatePull() {
		ControllerAction.translat(0.0, 0.0, 0.1, this);
	}

	public void translatePush() {
		ControllerAction.translat(0.0, 0.0, -0.1, this);
	}

	public void resetView() {
		ControllerCanvas.resetView(this);
	}

	public void exit() {
		ControllerAction.exit();
		System.err.println("EXIT");
		Thread.currentThread().interrupt();
		Thread.currentThread().interrupt();
	}

	public void help() {
		System.out.println("Webhelp javadoc");
	}

	public void openFile() {
		ControllerFiles.openFile(this);
		resetAll();
	}

	public void closeFile() {
		clearAllCanvas();
		labelFile.setText("");
		openedFile=null;
	}

	public void download() {
		ControllerFiles.download(this);
	}

	protected void clearAllCanvas() {
		UtilityClass.clearCanvas(render);
		UtilityClass.clearCanvas(renderup);
		UtilityClass.clearCanvas(renderdown);
	}
	
	public void resetAll() {
		ControllerCanvas.resetAll(this);
		shutdown = true;
		configureThread();
	}
	
	public void openOption() {
		hBoxMouvement.setVisible(false);
		hBoxOption.setVisible(true);
	}
	
	public void openMouvement() {
		hBoxMouvement.setVisible(true);
		hBoxOption.setVisible(false);
	}
	
	public void rotationPlusX() {
		ControllerAction.rotate(0.1, Axis3D.X, this);
	}
	public void rotationMinusX() {
		ControllerAction.rotate(-0.1, Axis3D.X, this);
	}
	public void rotationPlusY() {
		ControllerAction.rotate(0.1, Axis3D.Y, this);
	}
	public void rotationMinusY() {
		ControllerAction.rotate(-0.1, Axis3D.Y, this);
	}
	public void rotationPlusZ() {
		ControllerAction.rotate(0.1, Axis3D.Z, this);
	}
	public void rotationMinusZ() {
		ControllerAction.rotate(-0.1, Axis3D.Z, this);
	}
	public void pickFace() {
		ControllerAction.pickFace(this);
	}
	public void pickWire() {
		ControllerAction.pickWire(this);
	}
	public void pickBackground() {
		ControllerAction.pickBackground(this);
	}
	
	public void menuZoom100(){
		sliderZoom.adjustValue(1);
		drawFigure();
	}
	public void menuZoom50(){
		sliderZoom.adjustValue(0.5);
		drawFigure();
	}
	public void menuZoom250(){
		sliderZoom.adjustValue(2.5);
		drawFigure();
	}
	public void menuZoom500(){
		sliderZoom.adjustValue(5);
		drawFigure();
	}
}
