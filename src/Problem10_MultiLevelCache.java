import java.util.*;

public class Problem10_MultiLevelCache {

    private LinkedHashMap<String, String> L1 = new LinkedHashMap<>(10, 0.75f, true) {
        protected boolean removeEldestEntry(Map.Entry<String, String> e) {
            return size() > 3;
        }
    };

    private HashMap<String, String> L2 = new HashMap<>();

    public String get(String key) {

        if (L1.containsKey(key)) {
            System.out.println("L1 HIT");
            return L1.get(key);
        }

        if (L2.containsKey(key)) {
            System.out.println("L2 HIT → Promote to L1");
            String val = L2.get(key);
            L1.put(key, val);
            return val;
        }

        System.out.println("DB HIT");
        String val = "VideoData_" + key;

        L2.put(key, val);
        return val;
    }

    public static void main(String[] args) {
        Problem10_MultiLevelCache cache = new Problem10_MultiLevelCache();

        cache.get("A");
        cache.get("A");
        cache.get("B");
        cache.get("A");
    }
}