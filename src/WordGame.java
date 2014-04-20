import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class WordGame {
	public static int findWeight(Vertex v1, Vertex v2) {
		int numDiff = 0;
		String word1 = v1.getWord();
		String word2 = v2.getWord();
		
		// assume words are both the same length
		for (int n = 0; n < word1.length(); n++) {
			char c1 = word1.charAt(n);
			char c2 = word2.charAt(n);
			
			if(c1 != c2){
				numDiff += 1;
			}
			if(numDiff > 2){
				return 0;
			}
		}
		
		// compute the weight from the word differences
		if(numDiff == 1){
			return 1;
		}
		else if (numDiff == 2){
			return 5;
		}
		else{
			return 0;
		}
	}
	
	public static String readWord(FileReader reader) throws IOException {
		String word = "";
		
		while(true) {
			// get the next character
			int byteRead = reader.read();
			if (byteRead < 0) {
				return "eof";
			}
			
			char c = (char)byteRead;
			if (c == ' ' || c == '\n') {
				break;
			}
			
			// add the character to the word
			word += c;
		}
		
		// don't return a word if the length is not 5
		if (word.length() != 5) {
			return null;
		}
		
		return word;
	}
	
	public static void buildDijstra(Graph graph, String startWord) {
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		
		// the start vertex has a distance of zero
		Vertex startVertex = graph.getVertex(startWord);
		startVertex.setDistance(0);
		
		// put the first vertex in the queue
		queue.add(startVertex);
		
		// loop until the queue is empty
		while (!queue.isEmpty()) {
			// get the next vertex
			Vertex vertex = queue.poll();
			
			// iterate through the adjacency list
			Vertex[] adjList = vertex.getAdjList();
			for (Vertex adjVertex : adjList) {

				// compute the distance to the adjacent vertex using this shortest path
				int vertexDist = vertex.getDistance();
				int edgeWeight = vertex.getWeight(adjVertex);
				int adjVertexDist = vertexDist + edgeWeight;
				
				// set the new distance if the current distance is greater than the newly calculated one
				if (adjVertex.getDistance() > adjVertexDist) {
					
					// set that vertex's distance and parent
					adjVertex.setDistance(adjVertexDist);
					adjVertex.setParent(vertex);
					
					// add that vertex to the queue
					queue.add(adjVertex);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// make sure there are proper number of arguments
		if(args.length != 1){
			System.out.println("Word game program usage:");
			System.out.println("java WordGame [filename]");
			System.exit(0);
			}
		
		// get the file contents and close immediately
		FileReader fileReader = new FileReader(args[0]);
		
		//
		// build the graph
		//
		
		// add all the vertices
		Graph graph = new Graph();
		ArrayList<String> words = new ArrayList<String>();
		while(true) {
			
			// read the next word
			String word = readWord(fileReader);
			
			// break out of the loop if the end of the file has been reached
			if (word == "eof") {
				break;
			}
			
			// get the vertex from word
			Vertex vertex = new Vertex(word);
			words.add(word);
			graph.addVertex(vertex);
			int weight;
			
			// iterate through all the other words
			for(int n = 0; n < words.size(); n++){
				weight = findWeight(graph.getVertex(words.get(n)), vertex);
				
				// add edge if valid
				if (weight == 1 || weight == 5){
					graph.getVertex(words.get(n)).connect(vertex, weight);
					vertex.connect(graph.getVertex(words.get(n)), weight);
				}
			}
		}
		
		// close the file
		fileReader.close();
		
		// items we will use in the main program loop
		HashMap<String,Vertex> vertices = graph.getVertexMap();
		Console console = System.console();
		
		// main program loop
		while(true){
			String firstWord = console.readLine("Enter the first five-letter word: ");
			firstWord = firstWord.toUpperCase();
			
			String secondWord = console.readLine("Enter the second five-letter word: ");
			secondWord = secondWord.toUpperCase();
			
			// check to see if the vertex is in the graph
			Vertex firstVertex = graph.getVertex(firstWord);
			Vertex secondVertex = graph.getVertex(secondWord);
			if(firstVertex == null || secondVertex == null){
				System.out.println("One of the two words doesn't exist in the graph, please try again.");
				continue;
			}
			
			// perform Dijstra first using the first word as the source
			buildDijstra(graph, firstWord);
			
			// get the distance out of the graph
			int distance = secondVertex.getDistance();
			
			System.out.println("The best score for " + firstWord + " to " + secondWord + " is " + String.valueOf(distance) + " points");
			
			// build the search path
			ArrayList<Vertex> searchPath = new ArrayList<Vertex>();
			Vertex curVertex = secondVertex;
			while (curVertex.getParent() != null) {
				searchPath.add(curVertex);
				curVertex = curVertex.getParent();
			}
			
			// we want start to finish not finish to start
			Collections.reverse(searchPath);
			
			// print the result
			System.out.print("\n\t");
			for (Vertex v : searchPath) {
				System.out.print(v.getWord() + " ");
			}
			System.out.println();
		}
	}
}
