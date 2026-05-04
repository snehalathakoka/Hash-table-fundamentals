import java.util.*;

public class Problem9_TwoSum {

    public static void findTwoSum(int[] arr, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : arr) {
            int complement = target - num;

            if (map.containsKey(complement)) {
                System.out.println("Pair: " + num + " + " + complement);
            }

            map.put(num, 1);
        }
    }

    public static void main(String[] args) {
        int[] arr = {500, 300, 200};
        findTwoSum(arr, 500);
    }
}
