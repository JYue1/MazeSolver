// James Yue
// 4/5/24

// MazeSolver is a program that solves a maze by using Depth-First and Breadth-First search
// The two algorithms will display their solution by revealing a path from the starting to ending cell

/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */
import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    // A method to return the solution path for the two mazes
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Initializing an ArrayList for the solution path to be stored in
        ArrayList<MazeCell> solution = new ArrayList<>();
        // Initializing a Stack to trace from the end cell to the starting cell
        // Using a Stack because it follows (LIFO) Last In First Out
        Stack<MazeCell> stack = new Stack<>();

        // Storing and starting with the end cell of the maze
        MazeCell currentCell = maze.getEndCell();

        // Continue until the current cell is not equal to null
        while (currentCell != null) {
            // Pushing the current cell into the Stack
            stack.push(currentCell);
            // Setting the current cell to its parent
            currentCell = currentCell.getParent();
        }
        // Continue until the Stack is not empty
        while (!stack.isEmpty()) {
            // Taking the cell out from the Stack and adding it to the ArrayList solution
            solution.add(stack.pop());
        }
        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Initializing an ArrayList to store the depth-first search path
        ArrayList<MazeCell> depthSearch = new ArrayList<>();
        // Initializing a Stack to store cells that need to be visited during the search
        // Using a Stack rather than a Queue because DFS visits the last cell first
        // Since it searches one branch first before moving on following LIFO
        Stack<MazeCell> visitCell = new Stack<>();

        // Starting the search with the first cell in the maze
        MazeCell currentCell = maze.getStartCell();
        // Adding the cell to the Stack
        visitCell.push(currentCell);

        // Continue searching until you have reached the end cell
        while (currentCell != maze.getEndCell()) {
            // Popping the current cell from the Stack and setting it to explored
            currentCell = visitCell.pop();
            currentCell.setExplored(true);
            // Add the current cell to the depth-first search path
            depthSearch.add(currentCell);

            // Storing the row and column indices of the current cell
            int row = currentCell.getRow();
            int col = currentCell.getCol();

            // Checking if there is a neighboring cell located North of the current cell
            if (maze.isValidCell(row - 1, col)) {
                // Making the valid cell set to explored, the current cell to its parent, and adding it to the Stack
                maze.getCell(row - 1, col).setExplored(true);
                maze.getCell(row - 1, col).setParent(currentCell);
                visitCell.push(maze.getCell(row - 1, col));
            }
            // Checking if there is a neighboring cell located East of the current cell
            if (maze.isValidCell(row, col + 1)) {
                // Making the valid cell set to explored, the current cell to its parent, and adding it to the Stack
                maze.getCell(row, col + 1).setExplored(true);
                maze.getCell(row, col + 1).setParent(currentCell);
                visitCell.push(maze.getCell(row, col + 1));
            }
            // Checking if there is a neighboring cell located South of the current cell
            if (maze.isValidCell(row + 1, col)) {
                // Making the valid cell set to explored, the current cell to its parent, and adding it to the Stack
                maze.getCell(row + 1, col).setExplored(true);
                maze.getCell(row + 1, col).setParent(currentCell);
                visitCell.push(maze.getCell(row + 1, col));
            }
            // Checking if there is a neighboring cell located West of the current cell
            if (maze.isValidCell(row, col - 1)) {
                // Making the valid cell set to explored, the current cell to its parent, and adding it to the Stack
                maze.getCell(row, col - 1).setExplored(true);
                maze.getCell(row, col - 1).setParent(currentCell);
                visitCell.push(maze.getCell(row, col - 1));
            }

        }
        // Call and return the getSolution() method
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Initializing an ArrayList to store the breadth-first search path
        ArrayList<MazeCell> breadthSearch = new ArrayList<>();
        // Initializing a Queue to store cells that need to be visited during the search
        // Using a Queue rather than a Stack because it searches all neighboring cells at the same depth before moving on
        // This adheres to the FIFO (First in First Out)
        Queue<MazeCell> visitCell = new LinkedList<>();

        // Starting the search with the first cell in the maze
        MazeCell currentCell = maze.getStartCell();
        // Adding the cell to the stack
        visitCell.add(currentCell);

        // Continue searching until the current cell has reached the end cell
        while (currentCell != maze.getEndCell()) {
            // Removing the current cell from the Queue and setting it to explored
            currentCell = visitCell.remove();
            currentCell.setExplored(true);
            // Add the current cell to the breadth-first search path
            breadthSearch.add(currentCell);

            // Storing the row and column indices of the current cell
            int row = currentCell.getRow();
            int col = currentCell.getCol();

            // Checking if there is a neighboring cell located North of the current cell
            if (maze.isValidCell(row - 1, col)) {
                // Making the current cell its parent of valid cell and adding it to the Queue
                maze.getCell(row - 1, col).setParent(currentCell);
                visitCell.add(maze.getCell(row - 1, col));
            }
            // Checking if there is a neighboring cell located East of the current cell
            if (maze.isValidCell(row, col + 1)) {
                // Making the current cell its parent of valid cell and adding it to the Queue
                maze.getCell(row, col + 1).setParent(currentCell);
                visitCell.add(maze.getCell(row, col + 1));
            }
            // Checking if there is a neighboring cell located South of the current cell
            if (maze.isValidCell(row + 1, col)) {
                // Making the current cell its parent of valid cell and adding it to the Queue
                maze.getCell(row + 1, col).setParent(currentCell);
                visitCell.add(maze.getCell(row + 1, col));
            }
            // Checking if there is a neighboring cell located West of the current cell
            if (maze.isValidCell(row, col - 1)) {
                // Making the current cell its parent of valid cell and adding it to the Queue
                maze.getCell(row, col - 1).setParent(currentCell);
                visitCell.add(maze.getCell(row, col - 1));
            }
        }
        // Call and return the getSolution() method
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
