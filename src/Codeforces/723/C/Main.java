import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 問題がくそ。下手な英語を使うな
 */
public final class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		List<Integer> ai = new ArrayList<Integer>();

		// key: Id, value: num
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=1;i<=m;i++) {
			map.put(i, 0);
		}
		for (int i = 0; i < n; i++) {
			int d = in.nextInt();
			ai.add(d);
			if(d <= m) {
				if (map.containsKey(d)) {
					map.put(d, map.get(d) + 1);
				} else {
					map.put(d, 1);
				}
			}
		}

		map = MapUtil.sortByValue(map);
		int numRep = 0;
		for(int i=0;i<n;i++) {
			int d = ai.get(i);
			if((map.containsKey(d) && Math.abs(map.get(d) - (int)map.values().toArray()[0])>= 1 ) ||
					(d > m && !check(map) || (d > m && Math.abs((int)map.values().toArray()[0] - (int)map.values().toArray()[map.size()-1]) >= 1))) {
				int id = (int)map.keySet().toArray()[0];
				ai.set(i, id);
				if(map.containsKey(d)) {
					map.put(d, map.get(d) - 1);
				}
				map.put(id, map.get(id) + 1);
				map = MapUtil.sortByValue(map);
				numRep++;
			}
		}

		System.out.println("" + map.values().toArray()[0] + " " + numRep);
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