import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Similarity_Score {
    public static ArrayList<String> readFile(String filename) {
        try {
            return new ArrayList<>(Files.readAllLines(Paths.get(filename)));
        } catch (IOException e) {
            System.out.println("Error reading file " + filename);
            return new ArrayList<>();
        }
    }

    public static int similarityScore(String filename) {
        ArrayList<String> list = readFile(filename);
        int total = 0;
        ArrayList<Integer> l1 = new ArrayList<>();
        ArrayList<Integer> l2 = new ArrayList<>();
        for(String line : list){
            String[] nums = line.split(" {3}");
            l1.add(Integer.parseInt(nums[0]));
            l2.add(Integer.parseInt(nums[1]));
        }
        for(int i : l1){
            int count = 0;
            for(int j : l2){
                if(i == j){
                    count++;
                }
            }
            total += i * count;
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(similarityScore("Day_1/data/number_lists.txt"));
    }
}
