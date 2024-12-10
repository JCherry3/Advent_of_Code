import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Removal {

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
            boolean increasing = list.get(1) > list.get(0);
            int faults = 0;
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
            if(safe){
                total++;
            }else{
                for(int j = 0; j < list.size(); j++) {
                    ArrayList<Integer> list2 = new ArrayList<>(list);
                    list2.remove(j);
                    safe = true;
                    increasing = list2.get(1) > list2.get(0);
                    for(int i = 0; i < list2.size(); i++) {
                        if(i == 0){continue;}
                        int curr = list2.get(i);
                        int prev = list2.get(i - 1);
                        if(curr == prev){safe = false;}
                        if(increasing){
                            if(curr < prev){safe = false;}
                        }else{
                            if(curr > prev){safe = false;}
                        }
                        if(Math.abs(curr - prev) > 3){safe = false;}
                    }
                    if(safe){
                        total++;
                        break;
                    }
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(safeLevels("Day-2/data/levels.txt"));
    }
}
