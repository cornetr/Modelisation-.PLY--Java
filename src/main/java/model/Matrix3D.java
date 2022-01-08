package model;

/**
 * Use Matrix in 3 dimensions
 * 
 * @author alexis
 *
 */
public class Matrix3D extends Matrix {
	public Matrix3D(int nbCol) {
		super(4, nbCol);
	}
	
	public Matrix3D(Matrix matrix) {
		super(matrix);
	}
	
	/**
	 * Construct a matrix with a specified number of rows and columns.
	 * 
	 * @param vector The the vector that generate the translation
	 * 
	 * @return The new Matrix3D with the translation
	 *
	 */
	public Matrix3D translation(Vector3D vector) {
		Matrix3D result = new Matrix3D(this);
		
		if (vector.isVectorNull())
			return result;
		
		Point3D  o = new Point3D(vector.getX(), vector.getY(), vector.getZ());
		Vector3D i = new Vector3D(1, 0, 0);
		Vector3D j = new Vector3D(0, 1, 0);
		Vector3D k = new Vector3D(0, 0, 1);
		
		MatrixBenchmark3D translation = new MatrixBenchmark3D(o, i, j, k);
		
		result = new Matrix3D( translation.multiplication(this) );
		
		return result;
	}
	
	/**
	 * Construct a matrix with a specified number of rows and columns.
	 * 
	 * @param coeff The coef of the homotecie
	 * 
	 * @return The new Matrix3D with the homotecie
	 */
	public Matrix3D homotetie(double coeff) {
		Matrix3D result = new Matrix3D(this);
		
		final int HOMOTETIE = 1;
		if (coeff == HOMOTETIE)
			return result;
		
		Point3D  o = new Point3D();
		Vector3D i = new Vector3D(coeff,     0,     0);
		Vector3D j = new Vector3D(    0, coeff,     0);
		Vector3D k = new Vector3D(    0,     0, coeff);
		
		MatrixBenchmark3D homotetie = new MatrixBenchmark3D(o, i, j, k);
		
		result = new Matrix3D( homotetie.multiplication(this) );
		
		return result;
	}
	
	/**
	 * Construct a matrix with a specified number of rows and columns.
	 * 
	 * @param rad Coef of rotattion in rad
	 * @param axis The axe where the rotation will be 
	 * 
	 * @return The new Matrix3D with the rotation
	 */
	public Matrix3D rotation(double rad, Axis3D axis) {
		Matrix3D result = new Matrix3D(this);
		rad %= 2 * Math.PI;
		
		if (rad == 0)
			return result;
		
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);
		
		Point3D  o = new Point3D();
		Vector3D i = null;
		Vector3D j = null;
		Vector3D k = null;
		
		switch (axis) {
		case X:
			i = new Vector3D(   1,    0,    0);
			j = new Vector3D(   0,  cos,  sin);
			k = new Vector3D(   0, -sin,  cos);
			break;

		case Y:
			i = new Vector3D( cos,    0,  sin);
			j = new Vector3D(   0,    1,    0);
			k = new Vector3D(-sin,    0,  cos);
			break;

		case Z:
			i = new Vector3D( cos,  sin,    0);
			j = new Vector3D(-sin,  cos,    0);
			k = new Vector3D(   0,    0,    1);
			break;
		}
		
		MatrixBenchmark3D rotation = new MatrixBenchmark3D(o, i, j, k);
		
		result = new Matrix3D( rotation.multiplication(this) );
		
		return result;
	}
}
