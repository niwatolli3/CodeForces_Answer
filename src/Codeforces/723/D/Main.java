import javafx.util.Pair;

import java.io.*;
import java.util.*;

public final class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int k = in.nextInt();
		Solver solver = new Solver(n,m,k);
		in.nextLine();
		for(int i=1;i<=n;i++) {
			String s = in.nextLine();
			for(int j=1;j<=m;j++) {
				solver.setMapChar(i,j,s.charAt(j-1));
			}
		}
		solver.solve();
	}
}

class Solver {
	static char CENTINEL = '-';
	static char LAKE = '.';
	static char LAND = '*';
	char[][] map = new char[52][52];
    boolean[][] used = new boolean[52][52];
	int n, m, k;
    int around = 0;
    boolean isLake = true;

    int[] dx = {-1,0,0,1};
    int[] dy = {0,-1,1,0};

	int numRep = 0;
	public Solver(int n, int m, int k) {
		this.n = n;
		this.m = m;
		this.k = k;

		for(int i=0;i<52;i++) {
			for(int j=0;j<52;j++) {
				map[i][j] = CENTINEL;
                used[i][j] = false;
			}
		}
	}

	public void setMapChar(int x, int y, char ch) {
		map[x][y] = ch;
	}

	public void solve() {
        // (x,y), sizeOfLake
		List<Pair<Pair<Integer, Integer>, Integer>> lakes = new ArrayList<Pair<Pair<Integer, Integer>, Integer>>();
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=m;j++) {
				if(used[i][j] == false && map[i][j] == LAKE) {
                    around = 0;
                    isLake = true;
					int sizeOfLake = bfs(i,j);
                    if(isLake) {
                        Pair<Integer, Integer> xy = new Pair<>(i, j);
                        lakes.add(new Pair<Pair<Integer, Integer>, Integer>(xy, sizeOfLake));
                    }
				}
			}
		}

		Collections.sort(lakes, new Comparator<Pair<Pair<Integer, Integer>, Integer>>() {
            @Override
            public int compare(Pair<Pair<Integer, Integer>, Integer> o1, Pair<Pair<Integer, Integer>, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        });

        int num = lakes.size() - k;
        int numChanged = 0;
        for(int i=0;i<num;i++) {
            numChanged += lakes.get(i).getValue();
            fillLakeToLand(lakes.get(i).getKey().getKey(), lakes.get(i).getKey().getValue());
        }

        System.out.println(numChanged);
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++) {
                System.out.print(map[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public void fillLakeToLand(int x, int y) {
        if(map[x][y] != LAKE) return;
        map[x][y] = LAND;

        for(int i=0;i<4;i++) {
            fillLakeToLand(x+dx[i], y+dy[i]);
        }
    }

	public int bfs(int x, int y) {
        if(used[x][y] == true) return 0;
        if(map[x][y] == CENTINEL) isLake = false;
        if(map[x][y] != LAKE) return 0;

        used[x][y] = true;


        int ans = 1;
        for(int i=0;i<4;i++) {
            int nextX = x+dx[i];
            int nextY = y+dy[i];
            if(map[nextX][nextY] == LAND) {
                around |= (1 << i);
            }
            ans += bfs(nextX, nextY);
        }
        return ans;
	}

	// 8+4+2+1
	public boolean isLake() {
        return isLake;
    }
}

