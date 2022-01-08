package controller;

import javafx.scene.paint.Color;
import model.MatrixBenchmark3D;
import model.MatrixFigure3D;
import model.Vector3D;

public class ControllerCanvas {

	protected static void optimizeViewCanvas(Controller ctrl) {
		double zoomHauteur = ctrl.render.getHeight() / (ctrl.yMax - ctrl.yMin);
		double zoomLargeur = ctrl.render.getWidth() / (ctrl.xMax - ctrl.xMin);

		ctrl.zoomValue = (zoomLargeur < zoomHauteur) ? zoomLargeur : zoomHauteur;
		ctrl.zoomValue = ctrl.zoomValue * 9 / 10;
	}
	
	protected static void centerFigure(Controller ctrl) {
		Vector3D toCenter = new Vector3D();

		toCenter.setX( -ctrl.xMin - (ctrl.xMax - ctrl.xMin) / 2 );
		toCenter.setY( -ctrl.yMin - (ctrl.yMax - ctrl.yMin) / 2 );
		toCenter.setZ( -ctrl.zMin - (ctrl.zMax - ctrl.zMin) / 2 );

		ctrl.figure = new MatrixFigure3D(ctrl.figure.translation(toCenter), ctrl.figure.getFaces());
	}
	
	protected static void reduceFigure(Controller ctrl) {
		double coeff = 0;

		if (ctrl.xMax > ctrl.yMax && ctrl.xMax > ctrl.zMax)
			coeff = 1 / ctrl.xMax;
		else if (ctrl.yMax > ctrl.zMax)
			coeff = 1 / ctrl.yMax;
		else
			coeff = 1 / ctrl.zMax;

		ctrl.figure = new MatrixFigure3D(ctrl.figure.homotetie(coeff), ctrl.figure.getFaces());
	}
	
	protected static void resetView(Controller ctrl) {
		if(ctrl.openedFile!=null) {
			ctrl.benchmark = new MatrixBenchmark3D();
			UtilityClass.foundMaxMin(ctrl.figure, ctrl);
			ctrl.centerFigure();
			ctrl.reduceFigure();
			ctrl.optimizeView();

			ctrl.resetZoom();

			ctrl.drawFigure();
		} else {
			ctrl.resetZoom();
		}
	}
	
	protected static void resetAll(Controller ctrl) {
		ctrl.checkBoxDisplayWires.setSelected(true);
		ctrl.checkBoxDisplayFaces.setSelected(false);
		ctrl.checkBoxLuminosity.setSelected(false);
		ctrl.checkBoxAutoRot.setSelected(false);
		ctrl.colorPickerBackground.setValue(Color.LIGHTGREY);
		ctrl.colorPickerFace.setValue(Color.FIREBRICK);
		ctrl.colorPickerWire.setValue(Color.BLACK);
		ctrl.pickBackground();

		if(ctrl.openedFile!=null) {
			ctrl.benchmark = new MatrixBenchmark3D();
			UtilityClass.foundMaxMin(ctrl.figure, ctrl);
			ctrl.centerFigure();
			ctrl.reduceFigure();
			ctrl.optimizeView();
			ctrl.pickWire();
			ctrl.pickFace();

			ctrl.resetZoom();

			ctrl.drawFigure();
		} else {
			ctrl.resetZoom();
		}
	}
}

