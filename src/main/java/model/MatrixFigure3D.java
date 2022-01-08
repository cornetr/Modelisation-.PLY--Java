package model;

import file.FileReader;

/**
 * All the point of the figure dispathed in one Matrix
 * 
 * @author alexis
 *
 */
public class MatrixFigure3D extends Matrix3D {
	protected int[][] faces;
	
	public MatrixFigure3D(FileReader ply) {
		super(ply.getnbVertex());
		
		for (int col = 0; col < ply.getnbVertex(); col++) {
			for (int row = 0; row < 3; row++)
				super.setAt(row, col, ply.getVertices()[col][row]);
			super.setAt(3, col, 1);
		}
		
		faces = ply.getFaces();
	}
	
	public MatrixFigure3D(Matrix matrix, int[][] faces) {
		super(matrix);
		this.faces = faces.clone();
	}
	
	public int[][] getFaces() {
		return faces;
	}
	
	/**
	 * 
	 * @param p The initial point which will be the reference of the sort
	 *
	 */	
	public void sortFacesAccordingTo(Point3D p) {
		double[] distances = new double[faces.length];
		int i, j;
		double tmpDistance;
		int[] tmpFace;
		Point3D a = new Point3D();
		Point3D b = new Point3D();
		Point3D c = new Point3D();
		Face3D face = new Face3D(a, b, c);

		a.setX(getAt(0, faces[0][0]));
		a.setY(getAt(1, faces[0][0]));
		a.setZ(getAt(2, faces[0][0]));
		b.setX(getAt(0, faces[0][1]));
		b.setY(getAt(1, faces[0][1]));
		b.setZ(getAt(2, faces[0][1]));
		c.setX(getAt(0, faces[0][2]));
		c.setY(getAt(1, faces[0][2]));
		c.setZ(getAt(2, faces[0][2]));
		
		distances[0] = p.distanceBetween(face.getBalancePoint());
		//distances[0] = Math.min(Math.min(a.distanceBetween(p), b.distanceBetween(p)), c.distanceBetween(p));

		for (i = 1; i < distances.length; i++) {
			a.setX(getAt(0, faces[i][0]));
			a.setY(getAt(1, faces[i][0]));
			a.setZ(getAt(2, faces[i][0]));
			b.setX(getAt(0, faces[i][1]));
			b.setY(getAt(1, faces[i][1]));
			b.setZ(getAt(2, faces[i][1]));
			c.setX(getAt(0, faces[i][2]));
			c.setY(getAt(1, faces[i][2]));
			c.setZ(getAt(2, faces[i][2]));
			
			distances[i] = p.distanceBetween(face.getBalancePoint());	
			//distances[i] = Math.min(Math.min(a.distanceBetween(p), b.distanceBetween(p)), c.distanceBetween(p));

			j = i;
			while (j >= 1 && distances[j] > distances[j-1]) {
				tmpDistance    = distances[j-1];
				distances[j-1] = distances[j];
				distances[j]   = tmpDistance;

				tmpFace    = faces[j-1];
				faces[j-1] = faces[j];
				faces[j]   = tmpFace;
				j--;
			}
		}
	}

}
