import java.util.*;

public class Problem8_ParkingLot {

    String[] parking;
    int size;

    public Problem8_ParkingLot(int size) {
        this.size = size;
        parking = new String[size];
    }

    int hash(String plate) {
        return Math.abs(plate.hashCode()) % size;
    }

    public void park(String plate) {
        int index = hash(plate);

        while (parking[index] != null) {
            index = (index + 1) % size;
        }

        parking[index] = plate;
        System.out.println(plate + " parked at " + index);
    }

    public void exit(String plate) {
        for (int i = 0; i < size; i++) {
            if (plate.equals(parking[i])) {
                parking[i] = null;
                System.out.println(plate + " exited from " + i);
                return;
            }
        }
    }

    public static void main(String[] args) {
        Problem8_ParkingLot p = new Problem8_ParkingLot(10);

        p.park("ABC123");
        p.park("XYZ999");
        p.exit("ABC123");
    }
}