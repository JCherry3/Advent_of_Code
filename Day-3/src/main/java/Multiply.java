import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Multiply {

    public static ArrayList<String> readFile(String filename) {
        try {
            return new ArrayList<>(Files.readAllLines(Paths.get(filename)));
        } catch (IOException e) {
            System.out.println("Error reading file " + filename);
            return new ArrayList<>();
        }
    }

    public static int multiply(String filename){
        ArrayList<String> lines = readFile(filename);
        int total = 0;
        for(String line : lines){ //                         0 1 2 3 4 5 6 7
            char[] chars = line.toCharArray(); // structure: m u l ( 3 , 3 )
            for(int i = 0 ; i < chars.length ; i++){
                boolean broken = false;
                int val1 = 0;
                int val2 = 0;
                if(chars[i] == 'm'){
                    if(chars[i+1] == 'u'){
                        if(chars[i+2] == 'l'){
                            if(chars[i+3] == '('){
                                int j = i + 4;
                                while(chars[j] != ',') {
                                    val1 = val1 * 10;
                                    if(Character.getNumericValue(chars[j]) > 9 || Character.getNumericValue(chars[j]) < 0){
                                        broken = true;
                                        System.out.println(chars[j]);
                                    }
                                    val1 = val2 + Character.getNumericValue(chars[j]);
                                    j++;
                                }
                                j++;
                                while(chars[j] != ')') {
                                    val2 = val2 * 10;
                                    if(Character.getNumericValue(chars[j]) > 9 || Character.getNumericValue(chars[j]) < 0){
                                        broken = true;
                                        System.out.println(chars[j]);
                                    }
                                    val2 = val2 + Character.getNumericValue(chars[j]);
                                    j++;
                                }
                            }
                        }
                    }
                }
//                System.out.println(broken);
//                System.out.println(val1);
//                System.out.println(val2);
                if(!broken){
                    total += val1 * val2;
                }
//                System.out.println(total);
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(multiply("Day-3/data/corrupted_data.txt"));
    }
}
