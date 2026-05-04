import java.util.*;

public class Problem5_AnalyticsDashboard {

    // page → total visits
    private HashMap<String, Integer> pageViews = new HashMap<>();

    // page → unique users
    private HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();

    // source → count
    private HashMap<String, Integer> trafficSources = new HashMap<>();

    // Process event
    public void processEvent(String url, String userId, String source) {

        // Count page views
        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        // Track unique users
        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(userId);

        // Track source
        trafficSources.put(source, trafficSources.getOrDefault(source, 0) + 1);
    }

    // Get top pages
    public void showDashboard() {

        System.out.println("\n--- DASHBOARD ---");

        // Sort pages by visits
        List<Map.Entry<String, Integer>> list = new ArrayList<>(pageViews.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("\nTop Pages:");
        int count = 0;
        for (Map.Entry<String, Integer> entry : list) {
            if (count == 10) break;

            String page = entry.getKey();
            int visits = entry.getValue();
            int unique = uniqueVisitors.get(page).size();

            System.out.println((count + 1) + ". " + page +
                    " - " + visits + " views (" + unique + " unique)");

            count++;
        }

        // Traffic sources
        System.out.println("\nTraffic Sources:");
        int total = trafficSources.values().stream().mapToInt(i -> i).sum();

        for (Map.Entry<String, Integer> entry : trafficSources.entrySet()) {
            double percent = (entry.getValue() * 100.0) / total;
            System.out.println(entry.getKey() + ": " + String.format("%.2f", percent) + "%");
        }
    }

    public static void main(String[] args) {

        Problem5_AnalyticsDashboard system = new Problem5_AnalyticsDashboard();

        // Simulated events
        system.processEvent("/news", "user1", "google");
        system.processEvent("/news", "user2", "facebook");
        system.processEvent("/sports", "user1", "google");
        system.processEvent("/news", "user1", "google");
        system.processEvent("/tech", "user3", "direct");
        system.processEvent("/sports", "user4", "google");

        system.showDashboard();
    }
}