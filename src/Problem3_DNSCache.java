import java.util.*;

public class Problem3_DNSCache {

    // Entry class
    static class DNSEntry {
        String ip;
        long expiryTime;

        DNSEntry(String ip, long ttlSeconds) {
            this.ip = ip;
            this.expiryTime = System.currentTimeMillis() + (ttlSeconds * 1000);
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }
    }

    // LRU Cache using LinkedHashMap
    private LinkedHashMap<String, DNSEntry> cache;
    private int capacity;

    // Stats
    private int hits = 0;
    private int misses = 0;

    public Problem3_DNSCache(int capacity) {
        this.capacity = capacity;

        cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, DNSEntry> eldest) {
                return size() > Problem3_DNSCache.this.capacity;
            }
        };
    }

    // Simulate DNS lookup
    private String queryDNS(String domain) {
        // Dummy IP generation
        return "192.168.1." + new Random().nextInt(100);
    }

    // Resolve domain
    public String resolve(String domain) {

        if (cache.containsKey(domain)) {
            DNSEntry entry = cache.get(domain);

            if (!entry.isExpired()) {
                hits++;
                System.out.println("Cache HIT");
                return entry.ip;
            } else {
                cache.remove(domain);
                System.out.println("Cache EXPIRED");
            }
        }

        // Cache miss
        misses++;
        System.out.println("Cache MISS");

        String ip = queryDNS(domain);

        // Store with TTL = 5 sec (example)
        cache.put(domain, new DNSEntry(ip, 5));

        return ip;
    }

    // Stats
    public void getStats() {
        int total = hits + misses;
        double hitRate = total == 0 ? 0 : (hits * 100.0 / total);

        System.out.println("Hits: " + hits);
        System.out.println("Misses: " + misses);
        System.out.println("Hit Rate: " + hitRate + "%");
    }

    public static void main(String[] args) throws InterruptedException {

        Problem3_DNSCache dns = new Problem3_DNSCache(3);

        System.out.println(dns.resolve("google.com"));
        System.out.println(dns.resolve("google.com")); // HIT

        Thread.sleep(6000); // wait for TTL expiry

        System.out.println(dns.resolve("google.com")); // EXPIRED → MISS

        dns.getStats();
    }
}