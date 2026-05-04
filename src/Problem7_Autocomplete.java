import java.util.*;

public class Problem7_Autocomplete {

    private HashMap<String, Integer> map = new HashMap<>();

    public void addQuery(String query) {
        map.put(query, map.getOrDefault(query, 0) + 1);
    }

    public List<String> search(String prefix) {

        List<Map.Entry<String, Integer>> list = new ArrayList<>();

        for (Map.Entry<String, Integer> e : map.entrySet()) {
            if (e.getKey().startsWith(prefix)) {
                list.add(e);
            }
        }

        list.sort((a, b) -> b.getValue() - a.getValue());

        List<String> result = new ArrayList<>();
        for (int i = 0; i < Math.min(10, list.size()); i++) {
            result.add(list.get(i).getKey());
        }

        return result;
    }

    public static void main(String[] args) {
        Problem7_Autocomplete ac = new Problem7_Autocomplete();

        ac.addQuery("java");
        ac.addQuery("javascript");
        ac.addQuery("java tutorial");
        ac.addQuery("java");
        ac.addQuery("java download");

        System.out.println(ac.search("jav"));
    }
}
