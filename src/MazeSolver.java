// James Yue
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
    // Ovearheard to use else if and not else
    // Keep track of: exploredCells, currentCells, cellsNeeded to be explored
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        ArrayList<MazeCell> solution = new ArrayList<>();
        Stack<MazeCell> stack = new Stack<>();

        MazeCell currentCell = maze.getEndCell();

        while (currentCell != null) {
            stack.push(currentCell);
            currentCell = currentCell.getParent();
        }
        while (!stack.isEmpty()) {
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

        ArrayList<MazeCell> depthSearch = new ArrayList<>();
        Stack<MazeCell> visitCell = new Stack<>();

        MazeCell currentCell = maze.getStartCell();
        visitCell.push(currentCell);

        while (currentCell != maze.getEndCell()) {
            currentCell = visitCell.pop();
            currentCell.setExplored(true);
            depthSearch.add(currentCell);

            int row = currentCell.getRow();
            int col = currentCell.getCol();

            // North
            if (maze.isValidCell(row - 1, col)) {
                // set this to is explored
                maze.getCell(row - 1, col).setExplored(true);
                maze.getCell(row - 1, col).setParent(currentCell);
                visitCell.push(maze.getCell(row - 1, col));
            }
            // East
            if (maze.isValidCell(row, col + 1)) {
                maze.getCell(row, col + 1).setExplored(true);
                maze.getCell(row, col + 1).setParent(currentCell);
                visitCell.push(maze.getCell(row, col + 1));
            }
            // South
            if (maze.isValidCell(row + 1, col)) {
                maze.getCell(row + 1, col).setExplored(true);
                maze.getCell(row + 1, col).setParent(currentCell);
                visitCell.push(maze.getCell(row + 1, col));
            }
            // West
            if (maze.isValidCell(row, col - 1)) {
                maze.getCell(row, col - 1).setExplored(true);
                maze.getCell(row, col - 1).setParent(currentCell);
                visitCell.push(maze.getCell(row, col - 1));
            }

        }
      depthSearch = getSolution();
      return depthSearch;
//        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST

        ArrayList<MazeCell> breadthSearch = new ArrayList<>();
        Queue<MazeCell> visitCell = new LinkedList<>();

        MazeCell currentCell = maze.getStartCell();
        visitCell.add(currentCell);

        while (currentCell != maze.getEndCell()) {
            currentCell = visitCell.remove();
            currentCell.setExplored(true);
            breadthSearch.add(currentCell);

            int row = currentCell.getRow();
            int col = currentCell.getCol();

            // North
            if (maze.isValidCell(row - 1, col)) {
                maze.getCell(row - 1, col).setParent(currentCell);
                visitCell.add(maze.getCell(row - 1, col));
            }
            // East
            if (maze.isValidCell(row, col + 1)) {
                maze.getCell(row, col + 1).setParent(currentCell);
                visitCell.add(maze.getCell(row, col + 1));
            }
            // South
            if (maze.isValidCell(row + 1, col)) {
                maze.getCell(row + 1, col).setParent(currentCell);
                visitCell.add(maze.getCell(row + 1, col));
            }
            // West
            if (maze.isValidCell(row, col - 1)) {
                maze.getCell(row, col - 1).setParent(currentCell);
                visitCell.add(maze.getCell(row, col - 1));
            }
        }
        breadthSearch = getSolution();
        return breadthSearch;
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
