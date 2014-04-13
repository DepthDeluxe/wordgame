class Vertex:
	def __init__(self, word):
		self.adj = []
		self.weights = []

		self.word = word

		if len(word) != 5:
			raise new Exception("Error: Words must be of length 5")

	def connect(self, otherVertex, weight):
		# configure this vertex's adjacency list and weight list
		self.adj.append(otherVertex)
		self.weights.append(weight)

		# configure the other vertex's adjacency list and weight list
		otherVertex.adj.append(self)
		otherVertex.weights.append(weight)

class Graph:
	def __init__(self):
		self.vertices = []

	def addVertex(self, vertex):
		self.vertices.append(vertex)

	def getVertexByWord(self, word):
		# search through vertex list
		for vertex in self.vertices:
			if vertex.word == word:
				return vertex

		# returns None if we didn't find the word
		return None