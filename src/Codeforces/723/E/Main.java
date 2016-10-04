import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		Solver solver = new Solver();
		solver.initTable();

		int n = in.nextInt();
		in.nextLine();
		for(int i=0;i<n;i++) {
			String str = in.nextLine();
			String[] arr = str.split(" ");
			String blackOrWhite = arr[0];
			int x = new Integer(""+arr[1]);
			int y = new Integer(""+arr[2]);

			solver.put(blackOrWhite, x, y);
		}

		solver.printAns();
	}

}

class Solver {
}

