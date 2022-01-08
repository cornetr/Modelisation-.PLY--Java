package maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import model.Matrix;

/**
 * TODO doc
 *
 *@author Clément
 */
public class TestMatrix {
	// Matrices 2×3
	private double[][] contentA = new double[][] {{11, 14,  5}, { 1,  9, 14}};
	private double[][] contentB = new double[][] {{-3, 12, -9}, {12, 15, -2}};

	// Matrices 3×2
	private double[][] contentC = new double[][] {{ 9,  5}, {12,  9}, { 7,  6}};

	// Matrices 3×3
	private double[][] contentD = new double[][] {{13,  9,  7}, {14,  9, 14}, { 5, 12,  2}};

	// Initialisation des Matrices
	private Matrix matriceA = new Matrix(contentA);
	private Matrix matriceB = new Matrix(contentB);
	private Matrix matriceC = new Matrix(contentC);
	private Matrix matriceD = new Matrix(contentD);

	/**
	 * TODO doc
	 *
	 */
	@Test
	public void testAddition() {
		// A + B
		double[][] contentAB = new double[][] {{ 8, 26, -4}, {13, 24, 12}};
		Matrix matriceAB = new Matrix(contentAB);

		// A + A
		double[][] contentAA = new double[][] {{22, 28, 10}, { 2, 18, 28}};
		Matrix matriceAA = new Matrix(contentAA);

		// A + B + A
		double[][] contentABA = new double[][] {{19, 40, 1}, {14, 33, 26}};
		Matrix matriceABA = new Matrix(contentABA);

		assertEquals(matriceA.addition(matriceB), matriceAB);
		assertEquals(matriceB.addition(matriceA), matriceAB);
		assertEquals(matriceA.addition(matriceA), matriceAA);
		assertEquals(matriceA.addition(matriceB).addition(matriceA), matriceABA);
		assertEquals(matriceA.addition(matriceB.addition(matriceA)), matriceABA);
	}

	/**
	 * TODO doc
	 *
	 */
	@Test
	public void testSubtraction() {
		// A - B
		double[][] contentAB = new double[][] {{14,  2, 14}, {-11, -6, 16}};
		Matrix mAB = new Matrix(contentAB);

		// A - A
		double[][] contentAA = new double[][] {{0, 0, 0}, {0, 0, 0}};
		Matrix mAA = new Matrix(contentAA);

		// A - B - A
		double[][] contentABA = new double[][] {{3, -12, 9}, {-12, -15, 2}};
		Matrix mABA = new Matrix(contentABA);

		assertEquals(matriceA.subtraction(matriceB), mAB);
		assertEquals(matriceA.subtraction(matriceA), mAA);
		assertEquals(matriceA.subtraction(matriceB).subtraction(matriceA), mABA);
	}

	/**
	 * TODO doc
	 *
	 */
	@Test
	public void testMultiplicationNumber() {
		// A * 3
		double[][] contentAInt = new double[][] {{33,  42, 15}, {3, 27, 42}};
		Matrix mAInt = new Matrix(contentAInt);

		// A * 0.42
		double[][] contentAFloat = new double[][] {{4.62, 5.88, 2.1}, {0.42, 3.78, 5.88}};
		Matrix mAFloat = new Matrix(contentAFloat);

		// A * (-8.42)
		double[][] contentBNegatif = new double[][] {{25.23, -100.92, 75.69}, {-100.92, -126.15, 16.82}};
		Matrix mBNegatif = new Matrix(contentBNegatif);

		assertEquals(matriceA.multiplication(3)    , mAInt);
		assertEquals(matriceA.multiplication(0.42) , mAFloat);
		assertEquals(matriceB.multiplication(-8.41), mBNegatif);
	}

	/**
	 * TODO doc
	 *
	 */
	@Test
	public void testMultiplicationMatrix() {
		// A * C
		double[][] contentAC = new double[][] {{302,  211}, {215, 170}};
		Matrix mAC = new Matrix(contentAC);

		// A * D
		double[][] contentAD = new double[][] {{364, 285, 283}, {209, 258, 161}};
		Matrix mAD = new Matrix(contentAD);

		// D * D
		double[][] contentDD = new double[][] {{330, 282, 231}, {378, 375, 252}, {243, 177, 207}};
		Matrix mDD = new Matrix(contentDD);

		// B * D * C
		double[][] contentBDC = new double[][] {{1335, 951}, {7862, 5491}};
		Matrix mBDC = new Matrix(contentBDC);

		assertEquals(matriceA.multiplication(matriceC), mAC);
		assertEquals(matriceA.multiplication(matriceD), mAD);
		assertEquals(matriceD.multiplication(matriceD), mDD);
		assertEquals(matriceB.multiplication(matriceD).multiplication(matriceC), mBDC);
	}

}
