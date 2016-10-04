import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		Solver solver = new Solver();
		int n = in.nextInt();
		in.nextLine();
		String s = in.nextLine();
		solver.solve(s);
	}

}

class Solver {
	public void solve(String s) {
		int maxOut = 0;
		int numIn = 0;

		boolean isIn = false;
		s = "_" + s + "_";

		String buf = "";
		for(int i=0;i<s.length();i++) {
			char ch = s.charAt(i);
			if(ch == '_') {
				if(isIn) {
					if(buf.length() != 0) numIn++;
				} else {
					maxOut = Math.max(maxOut, buf.length());
				}
				buf = "";
			} else if(Character.isAlphabetic(ch)) {
				buf += ch;
			} else if(ch == '(') {
				maxOut = Math.max(maxOut, buf.length());
				buf = "";
				isIn = true;
			} else if(ch == ')') {
				if(buf.length() != 0) numIn++;
				buf = "";
				isIn = false;
			}
		}

		System.out.println(String.format("%d %d", maxOut, numIn));
	}
}

