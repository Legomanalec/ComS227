package hw3;

import java.awt.Point;
import java.util.ArrayList;

import maze.Status;

/**
 * Implementation of MazeCell that has a location in a 2D plane.
 */
public class MazeCell {
	/**
	 * The maze to which this MazeCell belongs.
	 */
	private Maze owner;
	/**
	 * Status of this cell.
	 */
	private Status status;

	/**
	 * Neighbors array
	 */
	private java.util.ArrayList<MazeCell> neighbors;

	/**
	 * MazeCell parent
	 */
	private MazeCell parents;

	/**
	 * Given timeStamp
	 */
	private int timeStamp;

	/**
	 * The row and column for the cell
	 */
	private int mazeCellRow;
	private int mazeCellCol;

	/**
	 * Constructs a maze cell whose location is specified by the given row and
	 * column, whose status is WALL by default, and whose parent is null.
	 * 
	 * @param rows
	 */

	public MazeCell(int row, int col) {
		mazeCellRow = row;
		mazeCellCol = col;
		timeStamp = 0;
		status = Status.WALL;
		parents = null;
		neighbors = new ArrayList<MazeCell>();

	}

	/**
	 * Returns the cells location as a point, which contains its row and column
	 * 
	 * @return
	 */
	public java.awt.Point getLocation() {
		Point p = new Point(mazeCellRow, mazeCellCol);

		return p;
	}

	/**
	 * Adds a neighbor to this cell.
	 * 
	 * @param m
	 */

	public void addNeighbor(MazeCell m) {
		neighbors.add(m);
	}

	/**
	 * Returns the neighbors of the current cell. If a cell has no neighbor, the
	 * method must still return an ArrayList, which is empty.
	 * 
	 * @return
	 */

	public java.util.ArrayList<MazeCell> getNeighbors() {

		return neighbors;
	}

	/**
	 * Returns a string representation of this cell's row and column numbers
	 * enclosed by a pair of parenthesis, e.g., (3, 4), or (10, 0).
	 * 
	 * @return
	 */
	public java.lang.String toString() {

		return "(" + mazeCellRow + ", " + mazeCellCol + ")";

	}

	/**
	 * Gets the parent of this cell. This method returns null if the current cell
	 * has no parent.
	 * 
	 * @return
	 */

	public MazeCell getParent() {
		if (parents != null) {
			return parents;
		} else {
			return null;
		}
	}

	/**
	 * Sets the parent of this cell with the given parent.
	 * 
	 * @param parent
	 */
	public void setParent(MazeCell parent) {
		parents = parent;
	}

	/**
	 * Returns the time stamp of this cell
	 * 
	 * @return
	 */
	public int getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Sets the time stamp of this cell
	 * 
	 * @param time
	 */
	public void setTimeStamp(int time) {
		timeStamp = time;
	}

	/**
	 * Computes the Manhattan distance between this cell and other cell. The
	 * distance between two points measured along axes at right angles. In a plane
	 * with p1 at (x1, y1) and p2 at (x2, y2), it is |x1 - x2| + |y1 - y2|.
	 * 
	 * @param other
	 * @return
	 */
	public int distance(MazeCell other) {
		return Math.abs(other.mazeCellRow - this.mazeCellRow) + Math.abs(other.mazeCellCol - this.mazeCellCol);
	}

	/**
	 * Adds an observer for changes in this cell's status.
	 * 
	 * @param obs
	 */

	public void setOwner(Maze maze) {
		owner = maze;
	}

	/**
	 * Update the status of this cell and notifies the owner that this current
	 * cell's status has changed
	 * 
	 * @param cell
	 */
	public void setStatus(Status s) {
		status = s;
		if (owner != null) {
			owner.updateStatus(this);
		}
	}

	/**
	 * return the status of the current cell
	 * 
	 * @return status
	 */
	public Status getStatus() {
		return status;
	}

}
