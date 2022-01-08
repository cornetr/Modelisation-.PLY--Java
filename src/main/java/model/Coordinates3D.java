package model;

/**
 * create coordinate in 3 dimensions
 * @author alexis
 *
 */
public class Coordinates3D {
	protected double abscissas;
	protected double ordinates;
	protected double dimension;
	
	/**
	 * create coordinate in 3 dimensions
	 * @param abscissas 
	 *   abcissas of the point
	 * @param ordinates
	 *   ordinates of the point
	 * @param dimension
	 *   dimension of the point
	 */
	public Coordinates3D(double abscissas, double ordinates, double dimension) {
		this.abscissas = abscissas;
		this.ordinates = ordinates;
		this.dimension = dimension;
	}
	
	/**
	 * create coordinate in 3 dimensions with standard coordinate (0,0,0)
	 */	
	public Coordinates3D() {
		this(0, 0, 0);
	}

	public double getAbscissas() {
		return abscissas;
	}

	public void setAbscissas(double abscissas) {
		this.abscissas = abscissas;
	}

	public double getOrdinates() {
		return ordinates;
	}

	public void setOrdinates(double ordinates) {
		this.ordinates = ordinates;
	}

	public double getDimension() {
		return dimension;
	}

	public void setDimension(double dimension) {
		this.dimension = dimension;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(abscissas);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(dimension);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(ordinates);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates3D other = (Coordinates3D) obj;
		if (Double.doubleToLongBits(abscissas) != Double.doubleToLongBits(other.abscissas))
			return false;
		if (Double.doubleToLongBits(dimension) != Double.doubleToLongBits(other.dimension))
			return false;
		if (Double.doubleToLongBits(ordinates) != Double.doubleToLongBits(other.ordinates))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + abscissas + ", " + ordinates + ", " + dimension + ")";
	}

}
