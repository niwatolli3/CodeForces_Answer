import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		Solver solver = new Solver();
		int[] pos = new int[3];
		for(int i=0;i<3;i++) {
			pos[i] = in.nextInt();
		}

		System.out.println(solver.solve(pos));
	}

}

class Solver {
	public int solve(int[] pos) {
		int ans = Integer.MAX_VALUE;
		for(int i=0;i<3;i++) {
			int dist = 0;
			int target = pos[i];
			for(int j=0;j<3;j++) {
				dist += Math.abs(target - pos[j]);
			}
			ans = Math.min(ans, dist);
		}
		return ans;
	}
}

