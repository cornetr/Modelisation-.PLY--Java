package model;

import java.util.Arrays;

import controller.UtilityClass;

/**
 * A class to use matrices.
 * It allows basic calculations between matrices such as addition, subtraction and multiplication.
 * 
 * @author Alexis BONAL <a href="mailto:alexis.bonal.etu@univ-lille.fr">alexis.bonal.etu@univ-lille.fr</a>
 * 
 * @version 6 nov. 2021
 */
public class Matrix {
	/**
	 * The number of rows.
	 */
	protected final int nbRow;
	
	/**
	 * The number of columns;
	 */
	protected final int nbCol;
	
	/**
	 * The content of the matrix.
	 */
	protected final double[][] content;



	/**
	 * Construct a matrix with a specified number of rows and columns.
	 * 
	 * @param nbRow The number of rows.
	 * @param nbCol The number of columns.
	 * 
	 * @throws MatrixIncorrectSize In the event of an incorrect entry
	 */
	public Matrix(int nbRow, int nbCol) {
		this.nbRow   = nbRow;
		this.nbCol   = nbCol;
		this.content = new double[nbRow][nbCol];
	}

	/**
	 * Constructs a matrix from an array of doubles.
	 * 
	 * @param content The content of the matrix
	 */
	public Matrix(double[][] content) {
		nbRow = content.length;
		nbCol = biggerRowLength(content);

		this.content = UtilityClass.deepCopyOf(content, nbRow, nbCol);
	}
	
	/**
	 * Construct a copy of a matrix.
	 * 
	 * @param matrix The matrix to copy.
	 */
	public Matrix(Matrix matrix) {
		this(matrix.content);
	}
	
	
	
	/**
	 * Assemble various matrices in succession.
	 * 
	 * @param matrices The matrices to assemble.
	 * 
	 * @return The matrices in succession in one matrix.
	 */
	public static Matrix follow(Matrix ... matrices) {
		int newNbRow = 0;
		int newNbCol = 0;

		for (Matrix matrix : matrices) {
			if (matrix.getNbRow() > newNbRow)
				newNbRow = matrix.getNbRow();

			newNbCol += matrix.getNbCol();
		}

		Matrix result = new Matrix(newNbRow, newNbCol);int newCol = 0;
		
		for (int i = 0; i < matrices.length; i++)
			for (int oldCol = 0; oldCol < matrices[i].getNbCol(); oldCol++) {
				for (int row = 0; row < matrices[i].getNbRow(); row++)
					result.setAt(row, newCol, matrices[i].getAt(row, oldCol));

				newCol++;
			}

		return result;
	}

	

	/**
	 * Get the content of the matrix in a double array.
	 * 
	 * @return A copy of the contents of the matrix.
	 */
	public double[][] getContent() {
		return UtilityClass.deepCopyOf(this.content, this.getNbRow());
	}

	/**
	 * Get the value of a desired cell.
	 * 
	 * @param row The index of the desired row.
	 * @param col The index of the desired column.
	 * 
	 * @return The cell value.
	 */
	public double getAt(int row, int col) {
		double content = 0;

		try {
			content = this.content[row][col];
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();

			outOfBoundsMessage(row, col);

			System.exit(-1);
		}

		return content;
	}

	/**
	 * Set the value of a desired cell.
	 * 
	 * @param row The index of the desired row.
	 * @param col The index of the desired column.
	 * @param value The value to put.
	 */
	public void setAt(int row, int col, double value) {
		try {
			this.content[row][col] = value;
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();

			outOfBoundsMessage(row, col);

			System.exit(-1);
		}
	}

	/**
	 * Get the number of rows.
	 * 
	 * @return The number of rows.
	 */
	public int getNbRow() {
		return nbRow;
	}

	/**
	 * Get the number of columns.
	 * 
	 * @return The number of columns.
	 */
	public int getNbCol() {
		return nbCol;
	}



	/**
	 * Perform the addition of two matrices.
	 * 
	 * @param other The matrix to add.
	 * 
	 * @return The result of the addition.
	 */
	public Matrix addition(Matrix other) {
		if (!this.haveSameSize(other))
			throw new RuntimeException("Illegal matrix dimensions.");

		Matrix result = new Matrix(nbRow, nbCol);
		
		for (int row = 0; row < nbRow; row++)
			for (int col = 0; col < nbCol; col++) {
				double calculatedValue = this.getAt(row, col) + other.getAt(row, col);
				result.setAt(row, col, calculatedValue);
			}

		return result;
	}

	/**
	 * Perform the subtraction of the two matrices.
	 * 
	 * @param other The matrix to be subtracted.
	 * 
	 * @return The result of the subtraction.
	 */
	public Matrix subtraction(Matrix other) {
		if (!this.haveSameSize(other))
			throw new RuntimeException("Illegal matrix dimensions.");
		
		Matrix result = new Matrix(nbRow, nbCol);

		for (int row = 0; row < nbRow; row++)
			for (int col = 0; col < nbCol; col++) {
				double calculatedValue = this.getAt(row, col) - other.getAt(row, col);
				result.setAt(row, col, calculatedValue);
			}

		return result;
	}

	/**
	 * Multiply a matrix with a value.
	 * 
	 * @param number The multiplication coefficient.
	 * 
	 * @return The result of the multiplication.
	 */
	public Matrix multiplication(double number) {
		Matrix result = new Matrix(nbRow, nbCol);
		
		for (int row = 0; row < nbRow; row++)
			for (int col = 0; col < nbCol; col++) {
				double calculatedValue = this.getAt(row, col) * number;
				result.setAt(row, col, calculatedValue);
			}

		return result;
	}

	/**
	 * Multiplication of the matrix by another.
	 * 
	 * @param other The other matrix.
	 * 
	 * @return The result of the multiplication.
	 */
	public Matrix multiplication(Matrix other) {
		if (this.nbCol != other.nbRow)
			throw new RuntimeException("Illegal matrix dimensions.");
		
		Matrix result = new Matrix(this.nbRow, other.nbCol);

		for (int row = 0; row < result.nbRow; row++)
			for (int col = 0; col < result.nbCol; col++) {
				double calculatedValue = 0;

				for (int indice = 0; indice < this.getNbCol(); indice++)
					calculatedValue += this.getAt(row, indice) * other.getAt(indice, col);

				result.setAt(row, col, calculatedValue);
			}
		
		return result;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(content);
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
		Matrix other = (Matrix) obj;
		if (!Arrays.deepEquals(content, other.content))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		for (double[] row : content) {
			for (double cell : row)
				result.append(cell + "\t");

			result.append("\n");
		}

		return result.toString();
	}
	
	
	
	/**
	 * Found the bigger row length.
	 * 
	 * @param content A table of double.
	 * 
	 * @return The bigger length.
	 */
	private int biggerRowLength(double[][] content) {
		int nbCol = content[0].length;
		
		for (double[] row : content)
			if (row.length > nbCol)
				nbCol = row.length;
		
		return nbCol;
	}
	
	/**
	 * Message to display on exception ArrayIndexOutOfBoundsException
	 * 
	 * @param row The entered row
	 * @param col The entered column
	 */
	private void outOfBoundsMessage(int row, int col) {
		if (row >= this.getNbRow())
			System.err.println("\t\tThe entered line is too large! (" + row + " ≥ " + this.getNbRow() + ")");
		else if (row < 0)
			System.err.println("\t\tThe entered line is too small! (" + row + " < 0)");

		if (col >= this.getNbCol())
			System.err.println("\t\tThe entered column is too large! (" + col + " ≥ " + this.getNbCol() + ")");
		else if (col < 0)
			System.err.println("\t\tThe entered column is too small! (" + col + " < 0)");
	}
	
	/**
	 * Checking the size of the matrices.
	 * 
	 * @param other An other matrix.
	 * 
	 * @return true if the sizes are the same, false otherwise.
	 */
	private boolean haveSameSize(Matrix other) {
		return this.getNbRow() == other.getNbRow()
			&& this.getNbCol() == other.getNbCol();
	}
}
