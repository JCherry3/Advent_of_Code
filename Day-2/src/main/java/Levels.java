import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Levels {

    public static ArrayList<String> readFile(String filename) {
        try {
            return new ArrayList<>(Files.readAllLines(Paths.get(filename)));
        } catch (IOException e) {
            System.out.println("Error reading file " + filename);
            return new ArrayList<>();
        }
    }

    public static int safeLevels(String filename) {
        ArrayList<String> levels = readFile(filename);
        int total = 0;
        for(String level : levels) {
            String[] nums = level.split(" ");
            ArrayList<Integer> list = new ArrayList<>();
            for(String num : nums) {
                list.add(Integer.parseInt(num));
            }
            boolean safe = true;
            boolean increasing = false;
            if(list.get(1) > list.get(0)){
                increasing = true;
            }
            for(int i = 0; i < list.size(); i++) {
                if(i == 0){continue;}
                int curr = list.get(i);
                int prev = list.get(i - 1);
                if(curr == prev){safe = false;}
                if(increasing){
                    if(curr < prev){safe = false;}
                }else{
                    if(curr > prev){safe = false;}
                }
                if(Math.abs(curr - prev) > 3){safe = false;}
            }
            if(safe){total++;}
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(safeLevels("Day-2/data/levels.txt"));
    }
}
