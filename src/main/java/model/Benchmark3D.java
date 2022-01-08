package model;

/**
 * create a 3 dimensions benchmark
 * @author alexis
 *
 */
public class Benchmark3D {
	
	protected Point3D  origin;
	protected Vector3D vectorI;
	protected Vector3D vectorJ;
	protected Vector3D vectorK;
	
	/**
	 * create a 3 dimensions benchmark
	 * @param origine 
	 *   origin point of the benchmark
	 * @param vectorI
	 *   first vector of the benchmark
	 * @param vectorJ
	 *   second vector of the benchmark
	 * @param vectorK
	 *   third vector of the benchmark
	 */
	public Benchmark3D(Point3D origin, Vector3D i, Vector3D j, Vector3D k) {
		this.origin = origin;
		this.vectorI= i;
		this.vectorJ= j;
		this.vectorK= k;
	}
	
	/**
	 * create a 3 dimensions benchmark with standards point and vectors
	 */
	public Benchmark3D() {
		this(new Point3D(), new Vector3D(1, 0, 0), new Vector3D(0, 1, 0), new Vector3D(0, 0, 1));
	}
}
