#!/bin/python
import sys

from graph import *

def findWeight(vertex1, vertex2):
	""" Finds the weight between two vertices in accordance to specs given in homework """

	# assume lengths will be the same
	numDiff = 0
	word1 = vertex1.word
	word2 = vertex2.word
	for n in range(0, len(vertex1.word)):
		if word1[n] != word2[n]:
			numDiff += 1

		if numDiff > 2:
			return None

	# weight the edges 
	if numDiff == 1:
		return 1
	elif numDiff == 2:
		return 5
	else:
		return 0

if __name__ == "__main__":
	# make sure there are the proper number of arguments
	if len(sys.argv) != 2:
		print("Word game program usage:")
		print("wordgame.py [filename]")
		exit()

	# get the file contents and close immediately
	filename = sys.argv[1]
	fh = open(filename, "r")
	fileContent = fh.read()
	fh.close()

	# build the graph #

	# add all the vertices
	graph = Graph()
	for word in fileContent.split():
		# create a new vertex and add it to the graph
		vertex = Vertex(word)
		graph.addVertex(vertex)

	# compare every combination of vertices
	for n in range(0, len(graph.vertices)):
		for m in range(n, len(graph.vertices)):
			weight = findWeight(graph.vertices[n], graph.vertices[m])

			# add the edge if it is valid
			if weight == 1 or weight == 5:
				graph.vertices[n].connect(graph.vertices[m], weight)

	# main program loop
	while True:
		# propt user for a five-letter word and convert to upper case
		inputStr = input("Enter a five-letter word: ")
		inputStr = inputStr.upper()

		# get the vertex from graph
		vertex = graph.getVertexByWord(inputStr)
		if vertex is None:
			print("The word you entered doesn't exist in the graph, please try again")
			continue

		print("The neighbors of " + inputStr + " are :")
		counter = 1
		for n in range(0, len(vertex.adj)):
			adj = vertex.adj[n]
			weight = vertex.weights[n]
			
			end = ""
			if counter == 6:
				end = "\n"
				counter = 0

			# print out the adjacent element
			print("\t" + adj.word + " (" + str(weight) + ")", end=end)
			counter += 1

		# add a newline at the end
		print()