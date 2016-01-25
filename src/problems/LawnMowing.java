package problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LawnMowing {
	public static String getMoves(String[] yard, int turnCost, int forwardCost, int slopeCost, int startCol, int startRow) {
		return "";
	}

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int N = Integer.parseInt(br.readLine());
			String[] yard = new String[N];
			for (int i = 0; i < N; i++) {
				yard[i] = br.readLine();
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
