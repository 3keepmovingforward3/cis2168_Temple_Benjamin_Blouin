import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JPanel;

public class MazeGridPanel extends JPanel {
    private int rows;
    private int cols;

    // Cell is name of Java Panel (JPanel)
    // maze is object that contains
    private Cell[][] maze;

    // extra credit
    //
    public void genDFSMaze() {
        boolean[][] visited;
        Stack<Cell> stack = new Stack<Cell>();
        Cell start = maze[0][0];
        stack.push(start);
    }

    //homework
    public void solveMaze() {
        //
        // This is where I put where I want to go ??????
        //
        Stack<Cell> stack = new Stack<Cell>();

        // This is me
        //
        Cell start = maze[0][0];
        Cell current;
        start.setBackground(Color.GREEN);

        // Actual finish location
        //
        Cell finish = maze[rows - 1][cols - 1];
        finish.setBackground(Color.RED);
		stack.push(start);
        // First move to (0,0)

        while (!stack.empty() && finish.getBackground()!=Color.GREEN) {
            current = stack.peek(); // My Newest Current Place in Maze
            System.out.println(current.row + ", " + current.col);

            // Iff the current cell DOES NOT have a North wall
            // AND
            // The cell one row above has not been visited
            //
            if (!current.northWall && !visited(current.row - 1, current.col)) {
                System.out.println("Can go North");
                maze[current.row - 1][current.col].setBackground(Color.green);
                stack.push(maze[current.row - 1][current.col]);
            }


            // Iff the current cell DOES NOT have a South wall
            // AND
            // The cell one row below has not been visited
            //
            else if (!current.southWall && !visited(current.row + 1, current.col) ) {
                    System.out.println("Can go South");
                    stack.push(maze[current.row + 1][current.col]);
                    maze[current.row + 1][current.col].setBackground(Color.green);


            }
            // Iff the current cell DOES NOT have an east wall
            // AND
            // The cell one row above has not been visited
            else if (!current.eastWall && !visited(current.row, current.col + 1)) {
                    System.out.println("Can go East");

                    maze[current.row][current.col + 1].setBackground(Color.green);
                    stack.push(maze[current.row][current.col + 1]);

            }

            // Iff the current cell DOES NOT have a west wall
            // AND
            // The cell one row above has not been visited
            else if (!current.westWall && !visited(current.row, current.col - 1) && current.col != 0) {
                    System.out.println("Can go West");
                    maze[current.row - 1][current.col - 1].setBackground(Color.green);
                    stack.push(maze[current.row][current.col - 1]);
                }
            else {
                // change background color back to white when moving backwards
                //
                current.setBackground(Color.gray);
                stack.pop();
            }
        }
    }

    public boolean visited(int row, int col) {
        if ((row < 0) | (col < 0)) return false;
        Cell c = maze[row][col];
        Color status = c.getBackground();
        if (status.equals(Color.WHITE) || status.equals(Color.RED)) {
            return false;
        }
        return true;
    }

    // generate new maze
    //
    public void genNWMaze() {

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                if (row == 0 && col == 0) {
                    continue;
                } else if (row == 0) {
                    maze[row][col].westWall = false;
                    maze[row][col - 1].eastWall = false;
                } else if (col == 0) {
                    maze[row][col].northWall = false;
                    maze[row - 1][col].southWall = false;
                } else {
                    boolean north = Math.random() < 0.5;
                    if (north) {
                        maze[row][col].northWall = false;
                        maze[row - 1][col].southWall = false;
                    } else {  // remove west
                        maze[row][col].westWall = false;
                        maze[row][col - 1].eastWall = false;
                    }
                    maze[row][col].repaint();
                }
            }
        }
        this.repaint();

    }

    public MazeGridPanel(int rows, int cols) {
        this.setPreferredSize(new Dimension(800, 800)); // window dimensions
        this.rows = rows;  // local
        this.cols = cols; // local
        this.setLayout(new GridLayout(rows, cols)); // builtin function
        this.maze = new Cell[rows][cols]; //
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                maze[row][col] = new Cell(row, col);
                this.add(maze[row][col]);
            }

        }
        this.genNWMaze();
        this.solveMaze();

    }

}
