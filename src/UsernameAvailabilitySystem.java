import java.util.*;

public class UsernameAvailabilitySystem {

    // username -> userId
    private HashMap<String, Integer> users = new HashMap<>();

    // username -> attempt count
    private HashMap<String, Integer> attempts = new HashMap<>();

    // Check availability
    public boolean checkAvailability(String username) {
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);
        return !users.containsKey(username);
    }

    // Register username
    public void registerUser(String username, int userId) {
        if (checkAvailability(username)) {
            users.put(username, userId);
            System.out.println(username + " registered successfully.");
        } else {
            System.out.println(username + " is already taken.");
        }
    }

    // Suggest alternatives
    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();

        // add numbers
        for (int i = 1; i <= 5; i++) {
            String suggestion = username + i;
            if (!users.containsKey(suggestion)) {
                suggestions.add(suggestion);
            }
        }

        // replace underscore
        suggestions.add(username.replace("_", "."));

        return suggestions;
    }

    // Most attempted username
    public String getMostAttempted() {
        String maxUser = null;
        int maxCount = 0;

        for (String user : attempts.keySet()) {
            if (attempts.get(user) > maxCount) {
                maxCount = attempts.get(user);
                maxUser = user;
            }
        }

        return maxUser;
    }

    public static void main(String[] args) {

        UsernameAvailabilitySystem system = new UsernameAvailabilitySystem();

        // Register existing user
        system.registerUser("john_doe", 1);

        // Check availability
        System.out.println("Is 'john_doe' available? " + system.checkAvailability("john_doe"));
        System.out.println("Is 'jane_smith' available? " + system.checkAvailability("jane_smith"));

        // Suggestions
        System.out.println("Suggestions for 'john_doe': " +
                system.suggestAlternatives("john_doe"));

        // Simulate attempts
        system.checkAvailability("admin");
        system.checkAvailability("admin");
        system.checkAvailability("admin");

        // Most attempted
        System.out.println("Most attempted username: " +
                system.getMostAttempted());
    }
}