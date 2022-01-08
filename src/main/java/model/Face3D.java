package model;

/**
 * create a face in 3 dimensions 
 * @author alexis
 * @author romann
 *
 */
public class Face3D {
	
	protected Point3D pointA;
	protected Point3D pointB;
	protected Point3D pointC;

	/**
	 * create a face in 3 dimensions
	 * @param pointA
	 *   first point of the face
	 * @param pointB
	 *   second point of the face
	 * @param pointC
	 *   third point of the face
	 */
	public Face3D(Point3D pointA, Point3D pointB, Point3D pointC) {
		this.pointA     = pointA;
		this.pointB     = pointB;
		this.pointC     = pointC;
	}

	public Point3D[] getVertices() {
		return new Point3D[] {pointA, pointB, pointC};
	}

	public void setA(Point3D pointA) {
		this.pointA = pointA;
	}

	public void setB(Point3D pointB) {
		this.pointB = pointB;
	}

	public void setC(Point3D pointC) {
		this.pointC = pointC;
	}
	
	/**
	 * get the center (balance point) of the face
	 * @return Point3D with new values
	 */
	public Point3D getBalancePoint() {
		return new Point3D((pointA.getX() + pointB.getX() + pointC.getX()) / 3,
						   (pointA.getY() + pointB.getY() + pointC.getY()) / 3,
						   (pointA.getZ() + pointB.getZ() + pointC.getZ()) / 3);
	}
	
	/**
	 * get the normal vector of the face
	 * @return Vector3D normal
	 */	
	public Vector3D getNormalVector() {
		Vector3D u = new Vector3D(pointA, pointB);
		Vector3D v = new Vector3D(pointA, pointC);
		
		return u.vectorProduct(v);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		for (Point3D point : this.getVertices())
			result.append(point + "\n");

		return result.toString();
	}
}
