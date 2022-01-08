package model;

/**
 * create coordinate in 2 dimensions
 * @author alexis
 *
 */
public class Coordinates2D {
	protected double abscissas;
	protected double ordinates;
	
	/**
	 * create coordinate in 2 dimensions
	 * @param abscissas 
	 *   abcissas of the point
	 * @param ordinates
	 *   ordinates of the point
	 */
	public Coordinates2D(double abscissas, double ordinates) {
		this.abscissas = abscissas;
		this.ordinates = ordinates;
	}
	
	/**
	 * create coordinate in 2 dimensions with standard coordinate (0,0)
	 */	
	public Coordinates2D() {
		this(0, 0);
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(abscissas);
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
		Coordinates2D other = (Coordinates2D) obj;
		if (Double.doubleToLongBits(abscissas) != Double.doubleToLongBits(other.abscissas))
			return false;
		if (Double.doubleToLongBits(ordinates) != Double.doubleToLongBits(other.ordinates))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "(" + abscissas + ", " + ordinates + ")";
	}
}
