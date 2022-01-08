package model;

/**
 * A Vector in 3 Dimension
 * 
 * @author alexis
 *
 */
public class Vector3D {
	
	protected Coordinates3D coordinates;
	
	public Vector3D(double x, double y, double z) {
		this.coordinates = new Coordinates3D(x, y, z);
	}
	
	public Vector3D(Point3D A, Point3D B) {
		this(B.getX() - A.getX(), B.getY() - A.getY(), B.getZ() - A.getZ());
	}
	
	public Vector3D() {
		this(0, 0, 0);
	}

	public double getX() {
		return coordinates.getAbscissas();
	}

	public void setX(double x) {
		coordinates.setAbscissas(x);
	}

	public double getY() {
		return coordinates.getOrdinates();
	}

	public void setY(double y) {
		coordinates.setOrdinates(y);
	}

	public double getZ() {
		return coordinates.getDimension();
	}

	public void setZ(double z) {
		coordinates.setDimension(z);
	}

	/**
	 * Check that the vector isn't null	 * 
	 * @return boolean
	 */
	public boolean isVectorNull() {
		return getX() == 0 
			&& getY() == 0 
			&& getZ() == 0;
	}
	
	/**
	 * Scalar of 2 Vector3D
	 * 
	 *  @param other The second Vector
	 *  @return The double result of the scalar
	 *
	 */
	public double scalar(Vector3D other) {
		double x = Math.abs(this.getX()) * Math.abs(other.getX());
		double y = Math.abs(this.getY()) * Math.abs(other.getY());
		double z = Math.abs(this.getZ()) * Math.abs(other.getZ());

		return x + y + z;
	}
	
	/**
	 * Norm of the Vector3D
	 * 
	 *  @return The double result of the norm
	 *
	 */
	public double norm() {
		double x = Math.pow(getX(), 2);
		double y = Math.pow(getY(), 2);
		double z = Math.pow(getZ(), 2);

		return Math.sqrt(x + y + z);
	}
	
	/**
	 * Vector product
	 * @param other The second Vector
	 * @return The calculated vector
	 */
	public Vector3D vectorProduct(Vector3D other) {
		double x = det(this.getY(), other.getY(), this.getZ(), other.getZ());
		double y = det(this.getZ(), other.getZ(), this.getX(), other.getX());
		double z = det(this.getX(), other.getX(), this.getY(), other.getY());
		
		return new Vector3D(x, y, z);
	}
	
	private double det(double a, double b, double c, double d) {
		return a * d - b * c;
	}

	@Override
	public String toString() {
		return coordinates.toString();
	}
}
