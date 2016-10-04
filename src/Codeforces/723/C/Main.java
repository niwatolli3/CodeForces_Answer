import javafx.util.Pair;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * AC. 問題の読解が難しすぎてReadforcesだった
 */
public final class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		List<Integer> ai = new ArrayList<Integer>();
		Map<Integer, Integer> bj = new TreeMap<>();
		// init
		for(int i=1;i<=m;i++) {
			bj.put(i, 0);
		}


		for(int i=0;i<n;i++) {
			int d = in.nextInt();
			ai.add(d);
			if(d <= m) {
				bj.put(d, bj.get(d) + 1);
			}
		}

		int minVal = (int)Math.floor(n/m);
		int numRep = 0;

		bj = MapUtil.sortByValue(bj); // sort by num of songs
		int bjIdx = 0;
		for(int i=0;i<n;i++) {
			int replaceFromId = ai.get(i);
			int replaceToId = (int)bj.keySet().toArray()[bjIdx];
			if(bj.get(replaceToId) >= minVal) {
				bjIdx++;
			}
			if(bjIdx >= m) break;
			replaceToId = (int)bj.keySet().toArray()[bjIdx];
			if(bj.get(replaceToId) >= minVal) break; // no need more replace
			if(replaceFromId == replaceToId) continue;

			if(replaceFromId <= m && bj.get(replaceFromId) <= minVal) {
				continue;
			}

			// replace
			ai.set(i, replaceToId);
			if(replaceFromId <= m)
                bj.put(replaceFromId, bj.get(replaceFromId) - 1);
			bj.put(replaceToId, bj.get(replaceToId) + 1);
			numRep++;
		}

		System.out.println(minVal + " " + numRep);
		for(int i=0;i<n;i++) {
			System.out.print(ai.get(i) + " ");
		}
		System.out.println("");
	}

	public static boolean check(Map<Integer, Integer> map) {
		for(int d : map.values()) {
			if(d == 0) return false;
		}
		return true;
	}
}

/**
 * Refer: http://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values-java
 */
class MapUtil
{
	public static <K, V extends Comparable<? super V>> Map<K, V>
	sortByValue( Map<K, V> map )
	{
		List<Map.Entry<K, V>> list =
				new LinkedList<Map.Entry<K, V>>( map.entrySet() );
		Collections.sort( list, new Comparator<Map.Entry<K, V>>()
		{
			public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
			{
				return (o1.getValue()).compareTo( o2.getValue() );
			}
		} );

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list)
		{
			result.put( entry.getKey(), entry.getValue() );
		}
		return result;
	}
}