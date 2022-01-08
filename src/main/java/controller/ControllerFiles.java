package controller;

import java.io.File;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;

import file.FileReader;
import file.FileType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import model.MatrixFigure3D;

public class ControllerFiles {

	protected static void initialize(Controller ctrl) {
		UtilityClass.configuringFileChooser(ctrl.fileChooser);
		ctrl.columnName.setCellValueFactory(new PropertyValueFactory<>("nom"));
		ctrl.columnVertex.setCellValueFactory(new PropertyValueFactory<>("strnbVertex"));
		ctrl.columnFace.setCellValueFactory(new PropertyValueFactory<>("strnbFace"));
	}

	protected static void openFile(Controller ctrl) {
		File file = ctrl.fileChooser.showOpenDialog(null);
		if (file != null) {
			ctrl.closeFile();
			ctrl.openedFile=file;
			ctrl.labelFile.setText(Path.of(file.getAbsolutePath()).getFileName().toString());
			FileReader filereader = new FileReader(ctrl.openedFile.getAbsolutePath(), FileType.PLY);
			ctrl.figure = new MatrixFigure3D(filereader);
			ctrl.resetView();
		}
	}
	
	protected static void configuringFileChooser(FileChooser fileChooser) {
		fileChooser.setTitle("Select PLY to open");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));		
		fileChooser.getExtensionFilters().add( new FileChooser.ExtensionFilter("PLY", "*.ply"));
		//fileChooser.getExtensionFilters().add( new FileChooser.ExtensionFilter("file", new ArrayList<>(Arrays.asList("*.ply","*.obj")) ));
		
	}
	
	protected static ObservableList<FileReader> getFileReaderList(File directory) {
		
	    File[] files = directory.listFiles();
	    
	    ObservableList<FileReader> listFiles=FXCollections.observableArrayList();
	    
	    for (File file : files) {
				listFiles.add(new FileReader(file.getAbsolutePath(), FileType.PLY, true));
		}
	    
	    
	    return listFiles;
	}
	
	public static void chooseLibrary(Controller ctrl) {
		File directory = ctrl.directoryChooser.showDialog(null);
		if (directory != null) {
			ctrl.tableViewFile.setItems(getFileReaderList(directory));
			ctrl.tableViewFile.refresh();
		}
		
	}
	
	protected static void submit(Controller ctrl) {
		FileReader fr = ctrl.tableViewFile.getSelectionModel().getSelectedItem();
		
		if (fr != null) {
			File file = new File(fr.getPath());
			ctrl.closeFile();
			ctrl.openedFile=file;
			ctrl.labelFile.setText(Path.of(file.getAbsolutePath()).getFileName().toString());
			FileReader filereader = new FileReader(ctrl.openedFile.getAbsolutePath(), FileType.PLY);
			ctrl.figure = new MatrixFigure3D(filereader);
			ctrl.resetAll();
		}
	}
	
	protected static void download(Controller ctrl) {
		File directory = ctrl.directoryChooser.showDialog(null);
		
		if(ctrl.openedFile!=null && directory != null) {
			try {
				Date date =Calendar.getInstance().getTime();
				DateFormat f=  new SimpleDateFormat("yyyy-MM-dd-HHmm");
				String nomDossier = f.format(date); 
				File dossier = new File(directory.getAbsolutePath()+File.separator+nomDossier); 
				dossier.mkdir();
				Image snapshotFace =ctrl.render.snapshot(null, null);
				Image snapshotSide =ctrl.renderdown.snapshot(null, null);
				Image snapshotUp   =ctrl.renderup.snapshot(null, null);

				ImageIO.write(SwingFXUtils.fromFXImage(snapshotFace, null), "png", new File(directory.getAbsolutePath()+File.separator+nomDossier+File.separator+ctrl.nomFichier+"_face.png"));
				ImageIO.write(SwingFXUtils.fromFXImage(snapshotSide, null), "png", new File(directory.getAbsolutePath()+File.separator+nomDossier+File.separator+ctrl.nomFichier+"_side.png"));	
				ImageIO.write(SwingFXUtils.fromFXImage(snapshotUp,   null), "png", new File(directory.getAbsolutePath()+File.separator+nomDossier+File.separator+ctrl.nomFichier+"_up.png"));	
			} catch (Exception e) { 		
				System.err.println("Failed to save image: "+e);
			}
		}
	}
	

	
}
