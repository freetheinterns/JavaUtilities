package problems;

public class DynamicProgrammingChallenge {
	public static int computePathCost(int[][] heightMap) {
		int width = heightMap[0].length, height = heightMap.length;
		int[][] costMap = new int[height][width];
		return costMap[height - 1][width - 1];
	}

	public static void main(String[] args) {
		int[][] testcase = {{1, 2, 10, 8, 3}, {2, 10, 6, 7, 1}, {3, 1, 3, 4, 9}, {4, 2, 3, 4, 1}, {8, 1, 8, 1, 3}};
		System.out.println(computePathCost(testcase));
	}
}
