package file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * Read the infos in a file, for example, a ply file
 * @author alexis
 * @author romann
 * 
 */
public class FileReader {
	protected double[][] vertices;
	protected int[][]    faces;
	protected String strnbVertex;
	protected String strnbFace;
	protected String path;
	protected String nom;
	
	protected boolean infoOnly;
	
	/**
	 * Read the infos in a file, for example, a ply file
	 * @param path 
	 *   location of the file
	 * @param type
	 *	 type of the file
	 * @param infoOnly
	 *   yes if you want more infos
	 *   
	 */
	public FileReader(String path, FileType type, boolean infoOnly) {
		this.path=path;
		this.nom =Path.of(path).getFileName().toString();
		File file = new File(path);
		List<String> content = null;
		this.infoOnly=infoOnly;
		try {
			content = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			System.err.println("File not found...");
			System.exit(-1);
		}
		
		Iterator<String> lines = content.iterator();
		
		switch (type) {
		case PLY:
			readPly(lines);
			break;
		case OBJ:
			readObj(lines);
			break;
		}
	}
	

	/**
	 * Read the infos in a file, for example, a ply file
	 * @param path 
	 *   location of the file
	 * @param type
	 *	 type of the file
	 *   
	 */
	public FileReader(String path, FileType type) {
		this(path, type, false);
	}

	/**
	 * read a fly file and insert all the data in the variables
	 * @param lines 
	 *   lines of a ply files
	 *   
	 */
	private void readPly(Iterator<String> lines) {
		int nbVertex = 0;
		int nbFace   = 0;
		
		while (lines.hasNext()) {
			String currentLine = notBlank(lines);

			if (nbVertex == 0 ) {
				nbVertex = searchIn("element vertex ", currentLine);
				strnbVertex=String.valueOf(nbVertex);			
			}
			
			if (nbFace == 0 ) {
				nbFace = searchIn("element face ", currentLine);
				strnbFace=String.valueOf(nbFace);
			}

			if (currentLine.contains("end_header") && !infoOnly) {
				readVertices(lines, nbVertex);
				readFaces(lines, nbFace);
			}
		}
		
	}

	private void readObj(Iterator<String> lines) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * read a fly file and insert all the vertices in the variable vertices
	 * @param lines 
	 *   lines of a ply files
	 * @param nbVertex
	 *   the number of vertex of the ply file
	 *   
	 */
	private void readVertices(Iterator<String> lines, int nbVertex) {
		vertices = new double[nbVertex][3];
		for (int i = 0; i < nbVertex; i++) {
			String[] coordinates = notBlank(lines).split(" ");
			for (int j = 0; j < 3; j++)
				vertices[i][j] = Double.parseDouble(coordinates[j]);
		}
	}

	/**
	 * read a fly file and insert all the faces in the variable faces
	 * @param lines 
	 *   lines of a ply files
	 * @param nbFaces
	 *   the number of faces of the ply file
	 *   
	 */
	private void readFaces(Iterator<String> lines, int nbFace) {
		faces = new int[nbFace][3];
		for (int i = 0; i < nbFace; i++) {
			String[] FaceVertices = notBlank(lines).split(" ");
			for (int j = 1; j < 4; j++)
				faces[i][j - 1] = Integer.parseInt(FaceVertices[j]);
		}
	}

	/**
	 * verify that there are no blanks in the file
	 * @param lines 
	 *   lines of a ply files
	 * @return String 
	 *  
	 */
	private String notBlank(Iterator<String> lines) {
		String currentLine;

		do
			currentLine = lines.next();
		while (currentLine.isBlank() && lines.hasNext());

		return currentLine;
	}

	/**
	 * search in the line if the key Phrase is there
	 * @param keyPhrase 
	 *   Phrase to search
	 * @param currentLine
	 *   actual line where to search
	 * @return int
	 */
	private int searchIn(String keyPhrase, String currentLine) {
		if (currentLine.contains(keyPhrase))
			return Integer.parseInt(currentLine.substring(keyPhrase.length()));

		return 0;
	}

	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Nom = " + this.nom + "\n");
		result.append("Path = " + this.path + "\n");
		result.append("Number of vertices = " + strnbVertex + "\n");
		result.append("Number of faces    = " + strnbFace   + "\n");
		result.append("vertices           = " + Arrays.deepToString(vertices) + "\n");
		result.append("faces              = " + Arrays.deepToString(faces)    + "\n");

		return result.toString();
	}

	public int getnbVertex() {
		return vertices.length;
	}

	public int getnbFace() {
		return faces.length;
	}
	
	public String getPath() {
		return path;
	}

	public String getNom() {
		return nom;
	}

	public double[][] getVertices() {
		return vertices;
	}

	public int[][] getFaces() {
		return faces;
	}
	
	public String getStrnbVertex() {
		return strnbVertex;
	}

	public String getStrnbFace() {
		return strnbFace;
	}
}
