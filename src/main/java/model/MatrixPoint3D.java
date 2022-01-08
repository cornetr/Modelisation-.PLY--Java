package model;

/**
 * A Matrix that contains one 3D Points
 * 
 * @author alexis
 *
 */
public class MatrixPoint3D extends Matrix3D {
	public MatrixPoint3D(Point3D point) {
		super(new Matrix( new double[][] {{point.getX()}, {point.getY()}, {point.getZ()}, {1}}));
	}
	
	public MatrixPoint3D() {
		this(new Point3D());
	}
}
