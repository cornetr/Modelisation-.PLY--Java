package controller;

import java.util.Arrays;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import model.Face3D;
import model.MatrixFigure3D;
import model.Point3D;
import model.Vector3D;

/**
 * 
 * 
 */
public class UtilityClass {
	
	private UtilityClass() {}	
	
	/**
	 * 
	 * 
	 */
	protected static void foundMaxMin(MatrixFigure3D figure, Controller ctrl) {
		ctrl.xMin = figure.getAt(0, 0);
		ctrl.xMax = ctrl.xMin;
		ctrl.yMin = figure.getAt(1, 0);
		ctrl.yMax = ctrl.yMin;
		ctrl.zMin = figure.getAt(2, 0);
		ctrl.zMax = ctrl.zMin;


		for (int i = 1; i < figure.getNbCol(); i++) {
			double cooX = figure.getAt(0, i);
			double cooY = figure.getAt(1, i);
			double cooZ = figure.getAt(2, i);

			if (cooX > ctrl.xMax)
				ctrl.xMax = cooX;
			else if (cooX < ctrl.xMin)
				ctrl.xMin = cooX;
			if (cooY > ctrl.yMax)
				ctrl.yMax = cooY;
			else if (cooY < ctrl.yMin)
				ctrl.yMin = cooY;
			if (cooZ > ctrl.zMax)
				ctrl.zMax = cooZ;
			else if (cooZ < ctrl.zMin)
				ctrl.zMin = cooZ;
		}
	}
	/**
	 * 
	 * 
	 */
	protected static double convertX(MatrixFigure3D figure, int iPoint, Canvas render, Controller ctrl) {
		return (figure.getAt(0, iPoint) - (ctrl.xMax + ctrl.xMin) / 2) * ctrl.zoomValue + render.getWidth() / 2;
	}
	
	/**
	 * 
	 * 
	 */
	protected static double convertY(MatrixFigure3D figure, int iPoint, Canvas render, Controller ctrl) {
		return -(figure.getAt(1, iPoint) - (ctrl.yMax + ctrl.yMin) / 2) * ctrl.zoomValue + render.getHeight() / 2;
	}
	
	/**
	 * 
	 * 
	 */
	protected static void setWires(Canvas c, double[] x, double[] y, MatrixFigure3D result, boolean little, boolean isFacesWires, Controller ctrl) {
		GraphicsContext gc = c.getGraphicsContext2D();
		gc.setStroke(ctrl.colorWires);
		gc.setFill(ctrl.colorFaces);
		Point3D ptLumiere = new Point3D(-1, -1, -1);

		for (int[] face : result.getFaces()) {
			for (int i = 0; i < 3; i++) {
				if (little) {
					x[i] = convertX(result, face[i], c, ctrl) / 2 + c.getWidth() / 4;
					y[i] = convertY(result, face[i], c, ctrl) / 2 + c.getHeight() / 4;
				} else {
					x[i] = convertX(result, face[i], c, ctrl);
					y[i] = convertY(result, face[i], c, ctrl);
				}
			}
			
			if(isFacesWires || ctrl.checkBoxLuminosity.isSelected()) {
				if (ctrl.checkBoxLuminosity.isSelected()) {
					Point3D A = new Point3D(result.getAt(0, face[0]), result.getAt(1, face[0]), result.getAt(2, face[0]));
					Point3D B = new Point3D(result.getAt(0, face[1]), result.getAt(1, face[1]), result.getAt(2, face[1]));
					Point3D C = new Point3D(result.getAt(0, face[2]), result.getAt(1, face[2]), result.getAt(2, face[2]));
					
					Face3D faceToDraw = new Face3D(A, B, C);
					Vector3D normale = faceToDraw.getNormalVector();
					Vector3D vecLumiere = new Vector3D(faceToDraw.getBalancePoint(), ptLumiere);
					double lumiere = vecLumiere.scalar(normale) / (vecLumiere.norm() * normale.norm());
					
					gc.setFill(Color.rgb((int) (ctrl.colorFaces.getRed()   * lumiere * 255),
										 (int) (ctrl.colorFaces.getGreen() * lumiere * 255),
										 (int) (ctrl.colorFaces.getBlue()  * lumiere * 255)));
				}
				
				gc.fillPolygon(x, y, 3);
				if (ctrl.checkBoxDisplayWires.isSelected())
					gc.strokePolyline(x, y, 3);	
				
			} else
				gc.strokePolyline(x, y, 3);	
		}
	}
	
	/**
	 * 
	 * 
	 */
	protected static void clearCanvas(Canvas canvas) {
		canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	
	/**
	 * 
	 * 
	 */
	protected static void displayFigureWires(MatrixFigure3D result, Controller ctrl) {
		ControllerAction.displayFigureWires(result, ctrl);
	}
	
	/**
	 * 
	 * 
	 */
	protected static void displayFigureFaces(MatrixFigure3D result, Controller ctrl) {
		displayFigureFacesWires(result, ctrl);

	}

	/**
	 * 
	 * 
	 */
	protected static void displayFigureFacesWires(MatrixFigure3D result, Controller ctrl) {
		ControllerAction.displayFigureFacesWires(result, ctrl);
	}
	
	/**
	 * 
	 * 
	 */
	protected static void displayFigurePoints(MatrixFigure3D result, Controller ctrl) {
		ControllerAction.displayFigurePoints(result, ctrl);
	}
	
	/**
	 * 
	 * 
	 */
	protected static void configuringFileChooser(FileChooser fileChooser) {
		ControllerFiles.configuringFileChooser(fileChooser);
	}
	
	/**
	 * Make a deep copy of a double table.
	 * 
	 * @param original The table to copy.
	 * @param newLength The length of the copy.
	 * 
	 * @return A copy of the original table.
	 * 
	 * TODO Traduction à valider
	 */
	public static double[][] deepCopyOf(double[][] original, int newLength) {
		double[][] copy = new double[newLength][];
		
		for (int i = 0; i < newLength; i++)
			copy[i] = Arrays.copyOf(original[i], original[i].length);
		
		return copy;
	}

	/**
	 * Make a deep copy of a double table.
	 * 
	 * @param original The table to copy.
	 * @param newLength The length of the copy.
	 * @param newDeepLength The deep length of the copy.
	 * 
	 * @return A copy of the original table.
	 * 
	 * TODO Traduction à valider
	 */
	public static double[][] deepCopyOf(double[][] original, int newLength, int newDeepLength) {
		double[][] copy = new double[newLength][newDeepLength];
		
		for (int i = 0; i < newLength; i++)
			copy[i] = Arrays.copyOf(original[i], newDeepLength);
		
		return copy;
	}
	
	protected static void configureThread(Controller ctrl) {
		ctrl.threadHorloge=new Thread() {
			public void run() {
				while(!ctrl.shutdown){
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							ctrl.rotationPlusY();
				        }
					});
					try {
						Thread.sleep(400);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						ctrl.threadHorloge.interrupt();
					}
					
				}
			};
		};
		ctrl.threadHorloge.setDaemon(true);
	}
	
}
