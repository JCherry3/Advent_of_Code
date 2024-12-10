import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Dual_Lists {
    public static ArrayList<String> readFile(String filename) {
        try {
            return new ArrayList<>(Files.readAllLines(Paths.get(filename)));
        } catch (IOException e) {
            System.out.println("Error reading file " + filename);
            return new ArrayList<>();
        }
    }

    public static int totalDistance(String filename){
        ArrayList<String> list = readFile(filename);
        int total = 0;
        Comparator<Integer> comp = new Comparator<Integer>() {
            public int compare(Integer int1, Integer int2){
                return int1 < int2 ? 1 : -1;
            }
        };
        PriorityQueue<Integer> l1 = new PriorityQueue<>(comp);
        PriorityQueue<Integer> l2 = new PriorityQueue<>(comp);
        for(String line : list){
            String[] nums = line.split(" {3}");
            l1.add(Integer.parseInt(nums[0]));
            l2.add(Integer.parseInt(nums[1]));
        }
        while(!l1.isEmpty()){
            total = total + Math.abs(l1.poll() - l2.poll());
        }
        return total;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(totalDistance("Day_1/data/number_lists.txt"));
    }
}
