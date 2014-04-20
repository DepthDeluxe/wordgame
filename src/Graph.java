import java.util.HashMap;


public class Graph {
	private HashMap<String, Vertex> vertices;
	
	public Graph() {
		vertices = new HashMap<String, Vertex>();
	}
	
	/*
	 * Main Methods
	 */
	
	public void addVertex(Vertex vertex) {
		vertices.put(vertex.getWord(), vertex);
	}
	
	public Vertex getVertex(String word) {
		return vertices.get(word);
	}
	
	/*
	 * Getters
	 */
	
	public HashMap<String, Vertex> getVertexMap() {
		return vertices;
	}
}
