package model;

/**
 * The benchmark that matches the Matrix3D
 * 
 * @author alexis
 *
 */
public class MatrixBenchmark3D extends Matrix3D {
	public MatrixBenchmark3D(Point3D origin, Vector3D i, Vector3D j, Vector3D k) {
		super(4);
		
		MatrixPoint3D  originMatrix = new MatrixPoint3D(origin);
		MatrixVector3D iMatrix      = new MatrixVector3D(i);
		MatrixVector3D jMatrix      = new MatrixVector3D(j);
		MatrixVector3D kMatrix      = new MatrixVector3D(k);
		
		Matrix result = Matrix.follow(iMatrix, jMatrix, kMatrix, originMatrix);
		
		for (int row = 0; row < this.getNbRow(); row++)
			for (int col = 0; col < this.getNbCol(); col++)
				this.setAt(row, col, result.getAt(row, col));
	}
	
	public MatrixBenchmark3D(Matrix matrix) {
		super(matrix);
	}
	
	public MatrixBenchmark3D(Benchmark3D benchmark) {
		this(benchmark.origin, benchmark.vectorI, benchmark.vectorJ, benchmark.vectorK);
	}
	
	public MatrixBenchmark3D() {
		this(new Point3D(), new Vector3D(1, 0, 0), new Vector3D(0, 1, 0), new Vector3D(0, 0, 1));
	}
}
