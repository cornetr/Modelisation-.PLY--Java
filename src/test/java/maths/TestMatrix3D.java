package maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import model.*;

/**
 * 
 * Test class for the Matrix3D class
 * @author clement
 * 
 */
public class TestMatrix3D {
	
	private double[][] contentA = new double[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
	
	private double[][] contentB = new double[][] {{11, 14,  5}, { 1,  9, 14}, {11, 14,  5}, {1, 1, 1}};
	
	private double[][] contentC = new double[][] {{3, -2, 1}, {-7, 9, 0}, {0, 0, 0}, {1, 1, 1}};
	
	private Matrix3D matriceA = new Matrix3D(new Matrix(contentA));
	private Matrix3D matriceB = new Matrix3D(new Matrix(contentB));
	private Matrix3D matriceC = new Matrix3D(new Matrix(contentC));	
	
	/**
	 * 
	 * Test translation of Matrix3D
	 * Its a moove in the benchmark
	 * 
	 */
	@Test
	public void testTranslation() {
		
		Vector3D vectorA = new Vector3D(1, 1, 1);
		Vector3D vectorB = new Vector3D(0, 0, 0);
		Vector3D vectorC = new Vector3D(-6, 4, 13);
		
		//translate mA by vA
		double[][] contentmAvA = new double[][] {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}, {1, 1, 1}};
		Matrix3D mAvA = new Matrix3D(new Matrix(contentmAvA));
		
		//translate mB by vB
		double[][] contentmBvB = new double[][] {{11, 14,  5}, { 1,  9, 14}, {11, 14,  5}, {1, 1, 1}};
		Matrix3D mBvB = new Matrix3D(new Matrix(contentmBvB));
		
		//translate mC by vC
		double[][] contentmCvC = new double[][] {{-3, -8, -5}, {-3, 13, 4}, {13, 13, 13}, {1, 1, 1}};
		Matrix3D mCvC = new Matrix3D(new Matrix(contentmCvC));
				
		assertEquals(matriceA.translation(vectorA), mAvA);
		assertEquals(matriceB.translation(vectorB), mBvB);
		assertEquals(matriceC.translation(vectorC), mCvC);
	}
	
	/**
	 * 
	 * Test homotecie of Matrix3D
	 * Its a zoom fonction
	 * 
	 */
	
	@Test
	public void testHomotetie() {
		
		//Coef
		double coefA = 1;
		double coefB = 0.5;
		double coefC = -15;
		double coefD = 7.2;
		double coefE = 0;

		//mA by vA
		double[][] contentmAcA = new double[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
		Matrix3D mAcA = new Matrix3D(new Matrix(contentmAcA));
		
		//mA by cE
		double[][] contentmAcE = new double[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {1, 1, 1}};
		Matrix3D mAcE = new Matrix3D(new Matrix(contentmAcE));
		
		//mA by cD
		double[][] contentmAcD = new double[][] {{7.2, 7.2, 7.2}, {7.2, 7.2, 7.2}, {7.2, 7.2, 7.2}, {1, 1, 1}};
		Matrix3D mAcD = new Matrix3D(new Matrix(contentmAcD));
		
		//mC by cC
		double[][] contentmCcC = new double[][] {{-45, 30, -15}, {105, -135, 0}, {0, 0, 0}, {1, 1, 1}};
		Matrix3D mCcC = new Matrix3D(new Matrix(contentmCcC));
		
		//mB by cB
		double[][] contentmBcB = new double[][] {{5.5, 7, 2.5}, {0.5, 4.5, 7}, {5.5, 7, 2.5}, {1, 1, 1}};
		Matrix3D mBcB = new Matrix3D(new Matrix(contentmBcB));
				
		assertEquals(matriceA.homotetie(coefA), mAcA);
		assertEquals(matriceA.homotetie(coefE), mAcE);
		assertEquals(matriceA.homotetie(coefD), mAcD);
		assertEquals(matriceC.homotetie(coefC), mCcC);
		assertEquals(matriceB.homotetie(coefB), mBcB);
		
	}
	
	/**
	 * Test rotation in the X axe
	 */
	@Test
	public void testRotationX() {
		
		//Axe of rotation
		Axis3D axe = Axis3D.X;
		
		//Coef of rotation
		double cA = Math.toRadians(90);
		double cB = Math.toRadians(0);
		double cC = Math.toDegrees(Math.PI/2);
		
		//mA by cA
		double[][] contentmAcA = new double[][] {{1, 1, 1}, {-0.9999999999999999, -0.9999999999999999, -0.9999999999999999}, {1, 1, 1}, {1, 1, 1}};
		Matrix3D mAcA = new Matrix3D(new Matrix(contentmAcA));
		
		//mB by cB
		double[][] contentmBcB = new double[][] {{11, 14, 5}, {1, 9, 14}, {11, 14, 5}, {1, 1, 1}};
		Matrix3D mBcB = new Matrix3D(new Matrix(contentmBcB));
		
		//mC by cC
		double[][] contentmCcC = new double[][] {{3, -2, 1}, {3.1365153129042125, -4.032662545162559, 0}, {-6.257976645203895, 8.045969972405008, 0}, {1, 1, 1}};
		Matrix3D mCcC = new Matrix3D(new Matrix(contentmCcC));
		
		assertEquals(matriceA.rotation(cA, axe), mAcA);
		assertEquals(matriceB.rotation(cB, axe), mBcB);
		assertEquals(matriceC.rotation(cC, axe), mCcC);
	}
	
	/**
	 * Test rotation in the Y axe
	 */
	@Test
	public void testRotationY() {

		//Axe of rotation
		Axis3D axe = Axis3D.Y;
		
		//Coef of rotation
		double cA = Math.toRadians(90);
		double cB = Math.toRadians(0);
		double cC = Math.toDegrees(Math.PI/4);
		
		//mA by cA
		double[][] contentmAcA = new double[][] {{-0.9999999999999999, -0.9999999999999999, -0.9999999999999999}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
		Matrix3D mAcA = new Matrix3D(new Matrix(contentmAcA));
		
		//mB by cB
		double[][] contentmBcB = new double[][] {{11, 14, 5}, {1, 9, 14}, {11, 14, 5}, {1, 1, 1}};
		Matrix3D mBcB = new Matrix3D(new Matrix(contentmBcB));
		
		//mC by cC
		double[][] contentmCcC = new double[][] {{1.5759659664531849, -1.0506439776354566, 0.5253219888177283}, {-7, 9, 0}, {2.552710573602358, -1.7018070490682387, 0.8509035245341193}, {1, 1, 1}};
		Matrix3D mCcC = new Matrix3D(new Matrix(contentmCcC));
		
		assertEquals(matriceA.rotation(cA, axe), mAcA);
		assertEquals(matriceB.rotation(cB, axe), mBcB);
		assertEquals(matriceC.rotation(cC, axe), mCcC);

	}
	
	/**
	 * Test rotation in the Z axe
	 */
	@Test
	public void testRotationZ() {

		//Axe of rotation
		Axis3D axe = Axis3D.Z;
		
		//Coef of rotation
		double cA = Math.toRadians(90);
		double cB = Math.toRadians(0);
		double cC = Math.toDegrees((11*Math.PI)/6);
		
		//mA by cA
		double[][] contentmAcA = new double[][] {{-0.9999999999999999, -0.9999999999999999, -0.9999999999999999}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
		Matrix3D mAcA = new Matrix3D(new Matrix(contentmAcA));
		
		//mB by cB
		double[][] contentmBcB = new double[][] {{11, 14, 5}, {1, 9, 14}, {11, 14, 5}, {1, 1, 1}};
		Matrix3D mBcB = new Matrix3D(new Matrix(contentmBcB));
		
		//mC by cC
		double[][] contentmCcC = new double[][] {{-3.9002678697038675, 3.173832306359591, -0.9911988217552051}, {6.541246864670042, -8.656026137385918, -0.13238162920546456}, {0, 0, 0}, {1, 1, 1}};
		Matrix3D mCcC = new Matrix3D(new Matrix(contentmCcC));
		
		assertEquals(matriceA.rotation(cA, axe), mAcA);
		assertEquals(matriceB.rotation(cB, axe), mBcB);
		assertEquals(matriceC.rotation(cC, axe), mCcC);

	}
}
