package controller;

import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import model.Axis3D;
import model.MatrixBenchmark3D;
import model.MatrixFigure3D;
import model.Point3D;
import model.Vector3D;

/**
 * 
 * 
 */
public class ControllerAction {

	protected static void drawFigure(Controller ctrl) {
		if(ctrl.openedFile!=null) {

			ctrl.clearAllCanvas();

			MatrixBenchmark3D resultBenchmark = new MatrixBenchmark3D(ctrl.benchmark.homotetie(ctrl.sliderZoom.getValue()));

			MatrixFigure3D result = new MatrixFigure3D(resultBenchmark.multiplication(ctrl.figure), ctrl.figure.getFaces());

			if (ctrl.checkBoxDisplayFaces.isSelected() || ctrl.checkBoxLuminosity.isSelected())
				UtilityClass.displayFigureFaces(result, ctrl);
			else if (ctrl.checkBoxDisplayWires.isSelected())
				UtilityClass.displayFigureWires(result, ctrl);
			else
				UtilityClass.displayFigurePoints(result, ctrl);

		}
	}
	
	protected static void displayFigureWires(MatrixFigure3D result, Controller ctrl) {
		GraphicsContext gc   = ctrl.render    .getGraphicsContext2D();
		GraphicsContext gcup = ctrl.renderup  .getGraphicsContext2D();
		GraphicsContext gcdo = ctrl.renderdown.getGraphicsContext2D();

		gc  .setLineWidth(0.3);
		gcup.setLineWidth(0.1);
		gcdo.setLineWidth(0.1);

		double[] x = new double[3];
		double[] y = new double[3];
		
		UtilityClass.setWires(ctrl.render, x, y, result, false, false, ctrl);
		
		MatrixFigure3D resultup = new MatrixFigure3D(result.rotation(Math.toRadians(90), Axis3D.X), ctrl.figure.getFaces());
		UtilityClass.setWires(ctrl.renderup, x, y, resultup, true, false, ctrl);
		
		MatrixFigure3D resultdo = new MatrixFigure3D(result.rotation(Math.toRadians(90), Axis3D.Y), ctrl.figure.getFaces());
		UtilityClass.setWires(ctrl.renderdown, x, y, resultdo, true, false, ctrl);
	}
	
	protected static void translat(double x, double y, double z, Controller ctrl) {
		if(ctrl.openedFile!=null) {
			ctrl.benchmark = new MatrixBenchmark3D(ctrl.benchmark.translation( new Vector3D(x, y, z) ));
			ctrl.drawFigure();
		}
	}
	
	protected static void resetTranslat(Controller ctrl) {
		if(ctrl.openedFile != null) {
			for (int i = 0; i < 3; i++)
				ctrl.benchmark.setAt(i, 3, 0);
			ctrl.drawFigure();
		}
	}
	
	protected static void rotate(double value, Axis3D direction, Controller ctrl) {
		if(ctrl.openedFile != null) {
			ctrl.benchmark = new MatrixBenchmark3D(ctrl.benchmark.rotation(value, direction));
			ctrl.drawFigure();
		}
	}
	
	protected static void resetRotate(Controller ctrl) {
		if(ctrl.openedFile != null) {
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++)
					if (i == j)
						ctrl.benchmark.setAt(i, j, 1);
					else
						ctrl.benchmark.setAt(i, j, 0);
			ctrl.drawFigure();
		}
	}
	
	protected static void displayFigureFacesWires(MatrixFigure3D result, Controller ctrl) {
		GraphicsContext gc   = ctrl.render    .getGraphicsContext2D();
		GraphicsContext gcup = ctrl.renderup  .getGraphicsContext2D();
		GraphicsContext gcdo = ctrl.renderdown.getGraphicsContext2D();

		gc  .setLineWidth(0.3);
		gcup.setLineWidth(0.1);
		gcdo.setLineWidth(0.1);	
		
		result.sortFacesAccordingTo(new Point3D(0, 0, 1000));

		double[] x = new double[3];
		double[] y = new double[3];
		UtilityClass.setWires(ctrl.render, x, y, result, false, true, ctrl);


		MatrixFigure3D resultup = new MatrixFigure3D(result.rotation(Math.toRadians(90), Axis3D.X), ctrl.figure.getFaces());
		resultup.sortFacesAccordingTo(new Point3D(0, 0, 1000));

		UtilityClass.setWires(ctrl.renderup, x, y, resultup, true, true, ctrl);


		MatrixFigure3D resultdo = new MatrixFigure3D(result.rotation(Math.toRadians(90), Axis3D.Y), ctrl.figure.getFaces());
		resultdo.sortFacesAccordingTo(new Point3D(0, 0, 1000));

		UtilityClass.setWires(ctrl.renderdown, x, y, resultdo, true, true, ctrl);
	}
	
	protected static void displayFigurePoints(MatrixFigure3D result, Controller ctrl) {
		GraphicsContext gc   = ctrl.render    .getGraphicsContext2D();
		GraphicsContext gcup = ctrl.renderup  .getGraphicsContext2D();
		GraphicsContext gcdo = ctrl.renderdown.getGraphicsContext2D();

		gc  .setLineWidth(0.3);
		gcup.setLineWidth(0.1);
		gcdo.setLineWidth(0.1);

		gc.setFill(ctrl.colorWires);
		gcup.setFill(ctrl.colorWires);
		gcdo.setFill(ctrl.colorWires);

		setPoints(ctrl.render, result, false, ctrl);

		MatrixFigure3D resultup = new MatrixFigure3D(result.rotation(Math.toRadians(90), Axis3D.X), ctrl.figure.getFaces());
		setPoints(ctrl.renderup, resultup, true, ctrl);

		MatrixFigure3D resultdo = new MatrixFigure3D(result.rotation(Math.toRadians(90), Axis3D.Y), ctrl.figure.getFaces());
		setPoints(ctrl.renderdown, resultdo, true, ctrl);
	}
	
	protected static void setPoints(Canvas c, MatrixFigure3D result, boolean little, Controller ctrl) {
		double x;
		double y;
		GraphicsContext gc = c.getGraphicsContext2D();
		for (int i = 0; i < result.getNbCol(); i++) {
			if (little) {
				x = UtilityClass.convertX(result, i, c, ctrl) / 2 + c.getWidth() / 4;
				y = UtilityClass.convertY(result, i, c, ctrl) / 2 + c.getHeight() / 4;
			} else {
				x = UtilityClass.convertX(result, i, c, ctrl);
				y = UtilityClass.convertY(result, i, c, ctrl);	
			}
			gc.fillRect(x, y, 1, 1);
		}
	}
	
	protected static void exit() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Are you sure you want to leave this application ?");
		alert.getButtonTypes();
		// option != null.
		Optional<ButtonType> option = alert.showAndWait();

		if (option.get() == null) {
		} else if (option.get() == ButtonType.OK) {
			Platform.exit();
		} else if (option.get() == ButtonType.CANCEL) {
		} 
	}
	
	protected static void pickFace(Controller ctrl) {
		String webColor = ctrl.colorPickerFace.getValue().toString();
		ctrl.colorFaces = Color.web(webColor);
		ctrl.drawFigure();
	}
	
	protected static void pickWire(Controller ctrl) {
		String webColor = ctrl.colorPickerWire.getValue().toString();
		ctrl.colorWires = Color.web(webColor);
		ctrl.drawFigure();
	}
	
	protected static void pickBackground(Controller ctrl) {
		String hexaColor = ctrl.colorPickerBackground.getValue().toString().substring(2,8);
		ctrl.anchorPaneCanvas.setStyle("-fx-background-color: #" + hexaColor + ";");
	}
	
	protected static void autoRotation(Controller ctrl) {
		if(!ctrl.checkBoxAutoRot.isSelected() && ctrl.threadHorloge.isAlive()) {
			ctrl.shutdown = true;
			ctrl.configureThread();
		} else {		 
			ctrl.shutdown = false;
			ctrl.threadHorloge.start();		
		}
		
	}
}
