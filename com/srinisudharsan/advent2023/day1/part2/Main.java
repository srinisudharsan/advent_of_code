import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        long sum = 0;
        String line;
        try {
            line = reader.readLine();
            while(line != null){
                System.out.println("Line: " + line);
                int val = NumberCalculator.CalculateNumber(line);
                sum += val;
                System.out.println("Val: " + val);
                System.out.println("Sum: " + sum);
                line = reader.readLine();
            }
            System.out.println("Result: " + sum);
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            reader.close();
        }
    }
}
