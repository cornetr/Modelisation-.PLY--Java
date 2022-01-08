package model;

/**
 * A basic point with 3 specification x, y, z
 * 
 * @author alexis
 * @author romann
 *
 */
public class Point3D {
	protected Coordinates3D coordinates;

	public Point3D(double x, double y, double z) {
		coordinates = new Coordinates3D(x, y, z);
	}

	public Point3D() {
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
	 * Calcul the distance between 2 points
	 * 
	 * @param other An other point to calcul the distance
	 * @return A double distance
	 */	
	public double distanceBetween(Point3D other) {
		return Math.sqrt(Math.pow(this.getX() - other.getX(), 2)
					   + Math.pow(this.getY() - other.getY(), 2)
					   + Math.pow(this.getZ() - other.getZ(), 2));
	}

	@Override
	public String toString() {
		return "Point3D:" + coordinates;
	}
}
