Two classes: Main and Parse
The Parse class simply just parses the data.txt and converts it to a table of strings. 
The main class - in the main method, it converts the table of strings into an Adjacency List.

Make sure Main and Parse are in the src file. Make sure data.txt is OUTSIDE of the src file.

From the main method, it runs BFS and DFS. You can just run main to run the BFS and DFS methods.
The answers to question 4 are in the console when you run Main, but I'll leave them here too:
Part A: 3
Part B: Yes, because all nodes were traversed.
Part C: The answers keep changing because the starting node could be in a "denser" area with more 
nodes and hence why it would take less steps to find all nodes. If a node is picked that has
a sparse area, it would take more steps to find all nodes.
Part D: 3238 nodes (including source node)
