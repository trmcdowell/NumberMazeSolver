NumberMazeSolver is a java program that uses BFS to find the shortest path through input number mazes. Mazes are .txt files that contain a number of rows and columns in line one, followed by one number per line that represents the number of moves that can be made from that spot in the constructed maze. For example, the maze input file could look like the following:

2 <br>
1 <br>
2 <br>
3 <br>
4 <br>

This would result in a number maze that looks like:

 [1, 2] <br>
 [3, 4]
 
 Note that this maze is unsolvable, as there are no valid moves after the first one. This is because we are "moving" from the top left spot to the goal, which is at the bottom left. If on a space, the number of steps on that space must be made within the bounds of the maze and must be done so vertically or horizontally, in other words diagonal movement is not allowed. If a move goes beyond the bounds of the maze, it will be considered invalid and not be considred any farther.

Compile: javac NumberMaze.java <br>
Execute: java NumberMaze maze.txt


## Authors

* **Tom McDowell**
