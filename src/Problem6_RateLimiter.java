import java.util.*;

public class Problem6_RateLimiter {

    static class TokenBucket {
        int tokens;
        int maxTokens;
        long lastRefillTime;

        TokenBucket(int maxTokens) {
            this.maxTokens = maxTokens;
            this.tokens = maxTokens;
            this.lastRefillTime = System.currentTimeMillis();
        }

        synchronized boolean allowRequest() {
            refill();

            if (tokens > 0) {
                tokens--;
                return true;
            }
            return false;
        }

        void refill() {
            long now = System.currentTimeMillis();
            if (now - lastRefillTime > 3600000) { // 1 hour
                tokens = maxTokens;
                lastRefillTime = now;
            }
        }
    }

    private HashMap<String, TokenBucket> clients = new HashMap<>();

    public boolean checkRateLimit(String clientId) {
        clients.putIfAbsent(clientId, new TokenBucket(5)); // small limit for test
        return clients.get(clientId).allowRequest();
    }

    public static void main(String[] args) {
        Problem6_RateLimiter rl = new Problem6_RateLimiter();

        for (int i = 1; i <= 7; i++) {
            System.out.println("Request " + i + ": " +
                    (rl.checkRateLimit("user1") ? "Allowed" : "Denied"));
        }
    }
}