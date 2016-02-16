package problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import problems.LawnMowing.YardSquare;

public class LawnMowing implements Comparator<YardSquare> {
	public static final int up = 0, down = 2, right = 3, left = 1;

	public class YardSquare implements Comparable<YardSquare> {
		public int x, y, cost = Integer.MAX_VALUE, direction = down;
		public String instructions;

		public YardSquare() {}

		public YardSquare(YardSquare source, int direction, String instructions, int cost) {
			this.direction = direction;
			x = source.getDirectedX(direction);
			y = source.getDirectedY(direction);
			this.instructions = instructions;
			this.cost = cost;
		}

		public YardSquare(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(YardSquare other) {
			return cost - other.cost;
		}

		public boolean equals(YardSquare other) {
			return x == other.x && y == other.y;
		}

		public int getDirectedX(int direction) {
			return direction % 2 == 0 ? x : x + direction - 2;
		}

		public int getDirectedY(int direction) {
			return direction % 2 == 0 ? y + direction - 1 : y;
		}
	}

	@Override
	public int compare(YardSquare A, YardSquare B) {
		return A.cost - B.cost;
	}

	public PriorityQueue<YardSquare> fringe;
	public boolean[][] completed;
	public int[][] yard;
	public int turnCost, forwardCost, slopeCost, startCol, startRow, finalCost;
	public YardSquare current;
	public String output;

	public LawnMowing(String[] yard, int turnCost, int forwardCost, int slopeCost, int startCol, int startRow) {
		this.yard = new int[yard.length][yard[0].length()];
		for (int i = 0; i < yard.length; ++i) {
			for (int j = 0; j < yard[i].length(); ++j) {
				Character c = yard[i].charAt(j);
				if (c == '.') {
					this.yard[i][j] = -1;
				} else {
					this.yard[i][j] = Integer.parseInt(c + "");
				}
			}
		}
		this.turnCost = turnCost;
		this.forwardCost = forwardCost;
		this.slopeCost = slopeCost;
		this.startCol = startCol;
		this.startRow = startRow;
		completed = new boolean[yard.length][yard[0].length()];
		current = new YardSquare(startCol, startRow);
		current.cost = 0;
		populateFringe();
	}

	public void populateFringe() {
		// Holds squares that have cost calculated for next step, but have already been mowed
		PriorityQueue<YardSquare> innerFringe = new PriorityQueue<YardSquare>(this);
		// Reset fringe for new cost analysis
		fringe = new PriorityQueue<YardSquare>(this);
		innerFringe.add(current);
		// Track all squares that you touch.
		ArrayList<YardSquare> decisionTree = new ArrayList<YardSquare>();
		decisionTree.add(current);
		while (!innerFringe.isEmpty()) {
			YardSquare focus = innerFringe.poll();
			for (int direction = 0; direction < 4; ++direction) {
				if (isValid(focus.getDirectedX(direction), focus.getDirectedY(direction))) {
					YardSquare newSquare = generateStep(focus, direction, decisionTree);
					decisionTree.add(newSquare);
					if (!completed[newSquare.y][newSquare.x]) {
						fringe.add(newSquare);
					} else {
						innerFringe.add(newSquare);
					}
				}
			}
		}
	}

	public void step() {
		// Set current square to mowed.
		completed[current.y][current.x] = true;
		// Choose cheapest square on fringe of mowed lawn.
		current = fringe.poll();
		// Re-evaluate fringe
		populateFringe();
		System.gc();
	}

	public void returnHome() {
		// Reset fringe for new cost analysis
		fringe = new PriorityQueue<YardSquare>(this);
		fringe.add(current);
		// Track all squares that you touch.
		ArrayList<YardSquare> decisionTree = new ArrayList<YardSquare>();
		decisionTree.add(current);
		while (!fringe.isEmpty()) {
			YardSquare focus = fringe.poll();
			for (int direction = 0; direction < 4; ++direction) {
				if (isValid(focus.getDirectedX(direction), focus.getDirectedY(direction))) {
					YardSquare newSquare = generateStep(focus, direction, decisionTree);
					if (newSquare.x == startCol && newSquare.y == startRow) {
						output = newSquare.instructions;
						finalCost = newSquare.cost;
						return;
					}
					decisionTree.add(newSquare);
					fringe.add(newSquare);
				}
			}
		}
	}

	private boolean isValid(int x, int y) {
		return (yard[y][x] != -1);
	}

	private YardSquare generateStep(YardSquare source, int direction, List<YardSquare> check) {
		String instruction = "RRS";
		int x2 = source.getDirectedX(direction), y2 = source.getDirectedY(direction);
		int actualizedSlopeCost = slopeCost * (Math.abs(yard[source.y][source.x] - yard[y2][x2]));
		int stepCost = 2 * turnCost + actualizedSlopeCost;
		if (source.direction == direction) {
			instruction = "S";
			stepCost = actualizedSlopeCost;
		} else if (source.direction == (direction + 1) % 4) {
			instruction = "LS";
			stepCost = turnCost + actualizedSlopeCost;
		} else if (source.direction == (direction + 3) % 4) {
			instruction = "RS";
			stepCost = turnCost + actualizedSlopeCost;
		}
		if (completed[source.y][source.x]) {
			stepCost *= 0.2;
		}
		YardSquare leading = new YardSquare(source, direction, source.instructions + instruction,
				source.cost + stepCost);
		for (YardSquare sq : check) {
			if (sq.x == x2 && sq.y == y2) {
				if (source.cost + stepCost < sq.cost) {
					sq.cost = source.cost + stepCost;
					sq.instructions = source.instructions + instruction;
				}
				return sq;
			}
		}
		return leading;
	}

	public void updateQueue() {
		PriorityQueue<YardSquare> replacement = new PriorityQueue<YardSquare>(fringe.size(), this);
		for (YardSquare sq : fringe) {
			replacement.add(sq);
		}
		fringe = replacement;
		System.gc();
	}

	public static String getMoves(String[] yard, int turnCost, int forwardCost, int slopeCost, int startCol,
			int startRow) {
		LawnMowing mower = new LawnMowing(yard, turnCost, forwardCost, slopeCost, startCol, startRow);
		while (!mower.fringe.isEmpty()) {
			mower.step();
		}
		mower.returnHome();
		return mower.output;
	}

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int N = Integer.parseInt(br.readLine());
			String[] yard = new String[N];
			for (int i = 0; i < N; i++) {
				yard[i] = br.readLine();
				System.out.println(yard[i]);
			}
			int turnCost = Integer.parseInt(br.readLine());
			int forwardCost = Integer.parseInt(br.readLine());
			int slopeCost = Integer.parseInt(br.readLine());
			int startCol = Integer.parseInt(br.readLine());
			int startRow = Integer.parseInt(br.readLine());

			String ret = getMoves(yard, turnCost, forwardCost, slopeCost, startCol, startRow);
			System.out.println(ret);
			System.out.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
