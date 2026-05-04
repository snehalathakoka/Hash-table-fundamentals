import java.util.*;

public class Problem2_InventoryManager {

    // productId -> stock
    private HashMap<String, Integer> inventory = new HashMap<>();

    // productId -> waiting list (FIFO)
    private HashMap<String, Queue<Integer>> waitingList = new HashMap<>();

    // Initialize product
    public void addProduct(String productId, int stock) {
        inventory.put(productId, stock);
        waitingList.put(productId, new LinkedList<>());
    }

    // Check stock
    public int checkStock(String productId) {
        return inventory.getOrDefault(productId, 0);
    }

    // Purchase item (synchronized for safety)
    public synchronized void purchaseItem(String productId, int userId) {

        int stock = inventory.getOrDefault(productId, 0);

        if (stock > 0) {
            inventory.put(productId, stock - 1);
            System.out.println("User " + userId +
                    " purchased " + productId +
                    ". Remaining: " + (stock - 1));
        } else {
            Queue<Integer> queue = waitingList.get(productId);
            queue.add(userId);
            System.out.println("User " + userId +
                    " added to waiting list. Position: " + queue.size());
        }
    }

    // Show waiting list
    public void showWaitingList(String productId) {
        Queue<Integer> queue = waitingList.get(productId);
        System.out.println("Waiting List: " + queue);
    }

    public static void main(String[] args) {

        Problem2_InventoryManager system = new Problem2_InventoryManager();

        system.addProduct("IPHONE15_256GB", 3);

        // Check stock
        System.out.println("Stock: " +
                system.checkStock("IPHONE15_256GB"));

        // Simulate purchases
        system.purchaseItem("IPHONE15_256GB", 101);
        system.purchaseItem("IPHONE15_256GB", 102);
        system.purchaseItem("IPHONE15_256GB", 103);

        // Stock finished → waiting list starts
        system.purchaseItem("IPHONE15_256GB", 104);
        system.purchaseItem("IPHONE15_256GB", 105);

        // Show waiting list
        system.showWaitingList("IPHONE15_256GB");
    }
}