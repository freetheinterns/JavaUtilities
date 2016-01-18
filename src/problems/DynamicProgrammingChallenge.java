package problems;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// This class is an implementation of dynamic programming to solve a cost optimization problem.
// Problem: Traverse a height map (upper left to lower right) where the cost of each step is the difference in height.
public class DynamicProgrammingChallenge {
	// This class is a wrapper for a location on the matrix.
	public static class Point {
		public int x, y;

		public Point() {}

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object other) {
			if (other instanceof Point) {
				Point otherpoint = (Point) other;
				return x == otherpoint.x && y == otherpoint.y;
			}
			return false;
		}
	}

	// used for bound checking
	private int width, height;
	// Queue of points to go to next when iterating across matrix
	private Queue<Point> nextPoints;
	public int[][] heightMatrix, costMatrix;

	public DynamicProgrammingChallenge(int[][] heights) {
		heightMatrix = heights;
		width = heightMatrix[0].length;
		height = heightMatrix.length;

		// Initialize cost matrix
		costMatrix = new int[height][width];
		for (int[] line : costMatrix) {
			Arrays.fill(line, -1);
		}
		// Set starting cost to 0
		costMatrix[0][0] = 0;
		// Initialize queue with the neighbors of the first square.
		nextPoints = new LinkedList<Point>();
		queueNeighbors(new Point(0, 0));

		// Calculate the cost of getting to each square.
		populateCostMatrix();
	}

	// This method iterates across the height matrix, while populating the cost matrix with the cheapest path cost to
	// each location.
	private void populateCostMatrix() {
		// Loop until there are no more locations to parse.
		// Loop Invariants:
		// 1: Point in top of queue has path costs defined above and to the left (if such paths exist)
		// 2: All points bordering known paths are in queue
		while (!nextPoints.isEmpty()) {
			Point current = nextPoints.poll();
			queueNeighbors(current);
			int pathAbove = Integer.MAX_VALUE, pathLeft = Integer.MAX_VALUE;
			if (current.y - 1 >= 0) {
				pathAbove = costMatrix[current.y - 1][current.x]
						+ Math.abs(heightMatrix[current.y][current.x] - heightMatrix[current.y - 1][current.x]);
			}
			if (current.x - 1 >= 0) {
				pathLeft = costMatrix[current.y][current.x - 1]
						+ Math.abs(heightMatrix[current.y][current.x] - heightMatrix[current.y][current.x - 1]);
			}
			costMatrix[current.y][current.x] = Integer.min(pathLeft, pathAbove);
		}
	}

	// Returns the lowest path cost of the lower right matrix location.
	public int returnPathCost() {
		return costMatrix[height - 1][width - 1];
	}

	// Adds the points below and to the right of the current point to the queue.
	private void queueNeighbors(Point current) {
		Point pointBelow = new Point(current.x, current.y + 1);
		Point pointRight = new Point(current.x + 1, current.y);
		if (pointBelow.y < height && !pointInQueue(pointBelow)) {
			nextPoints.add(pointBelow);
		}
		if (pointRight.x < height && !pointInQueue(pointRight)) {
			nextPoints.add(pointRight);
		}
	}

	// Checks to see if the requested point is already in queue.
	private boolean pointInQueue(Point loc) {
		for (Point p : nextPoints) {
			if (p.equals(loc)) return true;
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] testcase = {{1, 2, 10, 8, 3}, {2, 10, 6, 7, 1}, {3, 1, 3, 4, 9}, {4, 2, 3, 4, 1}, {8, 1, 8, 1, 3}};
		DynamicProgrammingChallenge challenge = new DynamicProgrammingChallenge(testcase);
		System.out.println(challenge.returnPathCost());
		System.out.println(Arrays.deepToString(challenge.heightMatrix));
		System.out.println(Arrays.deepToString(challenge.costMatrix));
	}
}
