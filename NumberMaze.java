
/**
 *  NumberMaze is a class that constructs and solves number mazes that are
 * constructed based on an input .txt file.
 * @Author Thomas McDowell
 * 09/23/2020
 */

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * BFSnode is used to create nodes for our BFS search in NumberMaze.
 * @see NumberMaze
 */
class BFSnode {

    int row;
    int col;
    int moveCount;
    BFSnode parent;

    BFSnode(int row, int col, BFSnode parent, int moveCount) {
        this.row = row;
        this.col = col;
        this.parent = parent;
        this.moveCount = moveCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BFSnode bfSnode = (BFSnode) o;
        return row == bfSnode.row &&
                col == bfSnode.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}

/**
 * NumberMaze builds a number maze from an input text file, solves it, and
 * returns the minimum number of moves required to solve the maze.
 * @see BFSnode
 */
public class NumberMaze {

    // maze will be size x size elements
    static int size;
    // left, right, up, down,
    static int[] rowMoves = { -1, 1, 0, 0 };
    static int[] colMoves = { 0, 0, 1, -1 };
    static int[][] maze;
    static final int numMoves = 4;

    /**
     * validMove checks if a move is valid
     * @param row  The row of a move
     * @param col  The column of a move
     */
    static boolean validMove( int row, int col ) {
        // a move is valid if it is within the bounds of the maze
        return ( row > 0 && row < size ) && ( col >= 0 && col < size );
    }

    /**
     * solveMaze solves an input number maze
     * @param maze  a 2D array representation of a number maze
     */
    static BFSnode solveMaze(int[][] maze) {
        Queue<BFSnode> queue = new LinkedList<BFSnode>();
        Set<BFSnode> visited = new HashSet<BFSnode>();
        BFSnode curr = new BFSnode( 0, 0, null, 0 );
        int distance;
        // unique index string for keeping track of visited nodes
        queue.add( curr );
        visited.add( curr );

        while(queue.size() != 0) {
            curr = queue.poll();
            // path is found if curr is bottom right index
            if ( (curr.row == size - 1) && (curr.col == size - 1) ) return curr;
            distance = maze[curr.row][curr.col];

            // 4 potential moves per location
            for (int i = 0; i < numMoves; i++) {
                int newRow = curr.row + (rowMoves[i] * distance);
                int newCol = curr.col + (colMoves[i] * distance);

                if ( validMove( newRow, newCol ) ) {
                    BFSnode childNode = new BFSnode(newRow, newCol, curr, curr.moveCount);
                    childNode.moveCount++; // we moved, so increment move count

                    if ( !visited.contains(childNode) ) {
                        queue.add(childNode);
                        visited.add(childNode);
                    }
                }
            }
        }
        return null;
    }

    /**
     * construct maze creates a 2D array from a scanner
     * @param sc  a scanner created from the input maze .txt file
     */
    static void constructMaze( Scanner sc ) {
        int counter = 0;
        size = sc.nextInt();
        maze = new int[size][size];
        while( sc.hasNext() ) {
            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++) {
                    maze[i][j] = sc.nextInt();
                }
                System.out.println(Arrays.toString(maze[i]));
            }
        }
    }



    public static void main( String[] args ) {
        if (args.length < 1) {
            System.err.println( "ERROR: missing file name argument\n" );
        } else try {
            constructMaze( new Scanner( new File( args[0] ) ) );
            BFSnode lastNode = solveMaze(maze);
            // print path length. -1 for extra move increment on final node
            System.out.println("Path length: " + (lastNode.moveCount - 1));
        } catch ( FileNotFoundException e) {
            System.err.println( "ERROR: can't open file: " + args[0] + "\n" );
        }
    }
}

