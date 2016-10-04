import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		Solver solver = new Solver();

		final char CENTINEL = '_';
		char[][] map = new char[60][60];
		for(int i=0;i<60;i++) {
			for(int j=0;j<60;j++) {
				map[i][j] = CENTINEL;
			}
		}

		int n, m, k;

		n = in.nextInt();
		m = in.nextInt();
		k = in.nextInt();
		in.nextLine();
		for(int i=1;i<=n;i++) {
			String s = in.nextLine();
			for(int j=1;j<=m;j++) {
					map[i][j] = s.charAt(j-1);
			}
		}

		for(int i=0;i<=n;i++) {
			for(int j=0;j<=m;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println("");
		}

		// 湖の１ first: pos(x,y), second: size of lake
		List<Pair<Pair<Integer, Integer>, Integer>> lakes = new ArrayList<Pair<Pair<Integer, Integer>, Integer>>();
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				if(map[i][j] == '.') {
					int size = searchLake(map, i, j, 0, 0);
					if(size != 0) { // lake found
						System.out.println(size);
					}
				}
			}
		}

		for(int i=0;i<=n;i++) {
			for(int j=0;j<=m;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println("");
		}
	}

	/**
	 * return is lake?
	 * @param map
	 * @param x
	 * @param y
	 * @return
	 */
	public static int searchLake(char[][] map, int x, int y, int size, int around) {
		if(map[x][y] != '.') return size;
		map[x][y] = '*';
		size += 1;

		int dx[] = {-1,0,0,1};
		int dy[] = {0,-1,1,0};
		for(int i=0;i<4;i++) {
			if(map[x+dx[i]][y+dy[i]] == '.')
				around |= (1 << i);
		}
		int ans = 0;
		for(int i=0;i<4;i++) {
			ans += searchLake(map, x + dx[i], y + dy[i], size, around);
		}
		return ans;
	}
}

class Solver {
}

