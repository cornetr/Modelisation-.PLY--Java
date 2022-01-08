package file;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * TODO doc
 * 
 * @author clement
 *
 */

public class TestFileReader {
	
	private FileReader file = new FileReader("exemples/ply/tetrahedron.ply", FileType.PLY);
	private FileReader falseFile = new FileReader("exemples/ply/false.ply", FileType.PLY);
	
	/**
	 * TODO doc
	 *
	 */
	@Test
	public void testFileReader() {
		
		double[][] vertices = new double[][] {{-1.0, -1.0, -1.0}, {1, 1, -1}, {1, -1, 1}, {-1, 1, 1}};
		int[][] faces = new int[][] {{1, 2, 3}, {1, 0, 2}, {3, 2, 0}, {0, 1, 3}};
		
		
		assertEquals(Arrays.deepToString(file.getVertices()), Arrays.deepToString(vertices));
		assertEquals(Arrays.deepToString(file.getFaces()), Arrays.deepToString(faces));
		
		assertEquals(Arrays.deepToString(falseFile.getVertices()), Arrays.deepToString(vertices));
		assertEquals(Arrays.deepToString(falseFile.getFaces()), Arrays.deepToString(faces));
	
	}
	
	/**
	 * TODO doc
	 *
	 */
	@Test
    public void testToString() {

        StringBuilder result = new StringBuilder();
        result.append("Nom = " + "tetrahedron.ply" + "\n");
        result.append("Path = " + "exemples/ply/tetrahedron.ply" + "\n");
        result.append("Number of vertices = " + "4" + "\n");
        result.append("Number of faces    = " + "4"   + "\n");
        result.append("vertices           = " + "[[-1.0, -1.0, -1.0], [1.0, 1.0, -1.0], [1.0, -1.0, 1.0], [-1.0, 1.0, 1.0]]" + "\n");
        result.append("faces              = " + "[[1, 2, 3], [1, 0, 2], [3, 2, 0], [0, 1, 3]]" + "\n");


        StringBuilder result2 = new StringBuilder();
        result2.append("Nom = " + "false.ply" + "\n");
        result2.append("Path = " + "exemples/ply/false.ply" + "\n");
        result2.append("Number of vertices = " + "4" + "\n");
        result2.append("Number of faces    = " + "4"   + "\n");
        result2.append("vertices           = " + "[[-1.0, -1.0, -1.0], [1.0, 1.0, -1.0], [1.0, -1.0, 1.0], [-1.0, 1.0, 1.0]]" + "\n");
        result2.append("faces              = " + "[[1, 2, 3], [1, 0, 2], [3, 2, 0], [0, 1, 3]]" + "\n");

        assertEquals(file.toString(), result.toString());
        assertEquals(falseFile.toString(), result2.toString());
	}
}
