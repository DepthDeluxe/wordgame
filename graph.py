class Vertex:
	def __init__(self, word):
		self.adj = []
		self.weights = []

		self.word = word

		if len(word) != 5:
			raise Exception("Error: Words must be of length 5")

	def connect(self, otherVertex, weight):
		""" Connect this vertex to another with the given weight """

		# configure this vertex's adjacency list and weight list
		self.adj.append(otherVertex)
		self.weights.append(weight)

		# configure the other vertex's adjacency list and weight list
		otherVertex.adj.append(self)
		otherVertex.weights.append(weight)

	def edgeExists(self, otherVertex):
		""" Returns True if there is an edge between the two vertices """

		# search the list for the vertex
		for adjacent in self.adj:
			if otherVertex == adjacent:
				return adjacent

		# return None if no edge exists
		return None

	def getWeight(self, otherVertex):
		""" Returns the weight of the edge between two vertices """

		# search the list for the vertex
		for n in range(0, len(self.adj)):
			if self.adj[n] == otherVertex:
				return self.weights[n]

		# return None if no edge exists
		return None

class Graph:
	def __init__(self):
		self.vertices = []

	def addVertex(self, vertex):
		""" Adds a vertex to the graph """
		self.vertices.append(vertex)

	def getVertexByWord(self, word):
		""" Gets the vertex by the word that it represents """
		# search through vertex list
		for vertex in self.vertices:
			if vertex.word == word:
				return vertex

		# returns None if we didn't find the word
		return None