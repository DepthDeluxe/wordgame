import java.util.HashMap;


public class Vertex implements Comparable<Vertex> {
	private HashMap<Vertex, Integer> adj;
	private String word;
	
	private int distance;
	private Vertex parent;
	
	public Vertex(String word) {
		adj = new HashMap<Vertex, Integer>();
		this.word = word;
		
		// set distance to infinity and parent to null
		this.distance = Integer.MAX_VALUE;
		this.parent = null;
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
	
	public Vertex[] getAdjList(){
		return adj.keySet().toArray(new Vertex[0]);
	}
	
	@Override
	public int compareTo(Vertex v) {
		// compares distances
		
		if (this.distance < v.getDistance()) {
			return -1;
		}
		else if (this.distance > v.getDistance()) {
			return 1;
		}
		
		return 0;
	}
	
	/*
	 * Setters
	 */
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
}
