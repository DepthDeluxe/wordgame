import java.util.HashMap;


public class Vertex {
	private HashMap<Vertex, Integer> adj;
	private String word;
	
	private int distance;
	
	public Vertex(String word) {
		adj = new HashMap<Vertex, Integer>();
		this.word = word;
	}
	
	/*
	 * Main Methods
	 */
	
	public void connect(Vertex otherVertex, int weight) {
		adj.put(otherVertex, weight);
	}
	
	public boolean edgeExists(Vertex otherVertex) {
		return adj.containsKey(otherVertex);
	}
	
	public int getWeight(Vertex otherVertex) {
		return adj.get(otherVertex);
	}
	
	/*
	 * Getters
	 */
	
	public String getWord() {
		return word;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public HashMap<Vertex, Integer> getAdjList(){
		return adj;
	}
}
