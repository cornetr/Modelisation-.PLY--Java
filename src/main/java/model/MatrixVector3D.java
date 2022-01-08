package model;

/**
 * A Matrix that contains one Vector
 * 
 * @author alexis
 *
 */
public class MatrixVector3D extends Matrix { 
	public MatrixVector3D(Vector3D vector) {
		super(new double[][] {{vector.getX()},
			                  {vector.getY()},
			                  {vector.getZ()},
			                  {0}});
	}

	public MatrixVector3D() {
		this(new Vector3D());
	}
	
	
}
