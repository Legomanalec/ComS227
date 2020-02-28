package hw3;

import maze.MazeObserver;
import maze.Status;

/**
 * A Maze consists of MazeCells arranged in a 2D grid.
 * 
 */
public class Maze {
	/**
	 * Observer to be notified in case of changes to cells in this maze.
	 */
	private MazeObserver observer;

	
	/**
	 * Array of the maze rows
	 */
	private String[] mazeRows;

	/**
	 * The starting cell
	 */
	private MazeCell start;
	
	/**
	 * The goal cell
	 */
	private MazeCell goal;

	/**
	 * 2D array built in the constructor
	 */
	private MazeCell[][] mazeCellArray;

	/**
	 * Constructs a maze based on a 2D grid
	 * 
	 * @param rows
	 */

	public Maze(String[] rows) {
		mazeRows = rows;

		mazeCellArray = new MazeCell[rows.length][rows[0].length()];
		// runs through each position and generates a 2D array
		for (int i = 0; i < rows[0].length(); i++) {
			for (int j = 0; j < rows.length; j++) {
				MazeCell m = new MazeCell(j, i);
				mazeCellArray[j][i] = m;
				m.setOwner(this);
			}
		}

		// Runs through each cell of the maze and determines its status

		for (int k = 0; k < rows[0].length(); k++) {
			for (int l = 0; l < rows.length; l++) {
				if (rows[l].charAt(k) == '#') {
					mazeCellArray[l][k].setStatus(Status.WALL);
				}

				else if (rows[l].charAt(k) == 'S' || rows[l].charAt(k) == ' ') {
					mazeCellArray[l][k].setStatus(Status.UNVISITED);

					if (rows[l].charAt(k) == 'S') {
						start = mazeCellArray[l][k];
					}
				}

				else if (rows[l].charAt(k) == '$') {
					mazeCellArray[l][k].setStatus(Status.GOAL);
					goal = mazeCellArray[l][k];

				}
			}
		}

		// Sets the neighbors of each cell
		for (int y = 0; y < mazeCellArray.length; y++) {
			for (int x = 0; x < mazeCellArray[y].length; x++) {
				if ((mazeCellArray[y][x].getStatus() == Status.UNVISITED
						|| mazeCellArray[y][x].getStatus() == Status.GOAL)) {
					if (y != 0 && mazeCellArray[y - 1][x].getStatus() != Status.WALL) {
						mazeCellArray[y][x].addNeighbor(mazeCellArray[y - 1][x]);
					}

					if (x != 0 && mazeCellArray[y][x - 1].getStatus() != Status.WALL) {
						mazeCellArray[y][x].addNeighbor(mazeCellArray[y][x - 1]);
					}

					if (y != mazeCellArray.length - 1 && mazeCellArray[y + 1][x].getStatus() != Status.WALL) {
						mazeCellArray[y][x].addNeighbor(mazeCellArray[y + 1][x]);
					}

					if (x != mazeCellArray[y].length - 1 && mazeCellArray[y][x + 1].getStatus() != Status.WALL) {
						mazeCellArray[y][x].addNeighbor(mazeCellArray[y][x + 1]);
					}

				}

			}
		}

	}

	/**
	 * Returns the cell at the given position
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public MazeCell getCell(int row, int col) {
		return mazeCellArray[row][col];
	}

	/**
	 * Returns the number of columns in the maze
	 * 
	 * @return
	 */

	public int getColumns() {
		return mazeRows[0].length();
	}

	/**
	 * Returns the number of rows in the maze
	 * 
	 * @return
	 */

	public int getRows() {
		return mazeRows.length;
	}

	/**
	 * Return the start cell for the maze
	 * 
	 * @return
	 */

	public MazeCell getStart() {
		return start;
	}

	/**
	 * Returns the goal cell for the maze
	 * 
	 * @return
	 */

	public MazeCell getGoal() {
		return goal;
	}

	/**
	 * Sets the observer for the cells of this maze.
	 * 
	 * @param obs
	 */
	public void setObserver(MazeObserver obs) {
		observer = obs;
	}

	/**
	 * Updates the status 
	 * @param cell
	 */
	public void updateStatus(MazeCell cell) {
		if (observer != null) {
			observer.updateStatus(cell);
		}
	}

}
