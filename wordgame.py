#!/bin/python
import sys

from graph import *

def findWeight(vertex1, vertex2):
	# assume lengths will be the same
	int numDiff = 0
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
	else
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