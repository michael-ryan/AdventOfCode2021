package Day3.Puzzle1;

import Day3.GenericSolution;

import java.util.HashMap;
import java.util.Map;

public class Solution extends GenericSolution {

    private int lineCount;
    private int[] onesInEachPosition;

    private Solution(){
    }

    private static String computeGamma(int lineCount, int[] onesInEachPosition){
        float threshold = lineCount / 2f;
        StringBuilder gamma = new StringBuilder();

        for(int i : onesInEachPosition){
            if(i > threshold){
                gamma.append('1');
            } else if(i < threshold){
                gamma.append('0');
            } else {
                throw new RuntimeException("Panic! Counted ones equals threshold (" + i + ")");
            }
        }

        return gamma.toString();
    }

    private static String computeEpsilon(String gamma){
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '1');
        map.put('1', '0');

        StringBuilder epsilon = new StringBuilder();

        for(char c : gamma.toCharArray()){
            epsilon.append(map.get(c));
        }

        return epsilon.toString();
    }

    public static void main(String[] args){
        new Solution().run();
    }

    private void run(){
        parseAsStringStream().forEach(this::readLine);

        String gamma = computeGamma(lineCount, onesInEachPosition);
        String epsilon = computeEpsilon(gamma);

        System.out.println(binaryStringToInt(gamma) * binaryStringToInt(epsilon));
    }

    private void readLine(String line){
        if(lineCount == 0){
            setUp(line);
        }

        lineCount++;
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) == '1'){
                onesInEachPosition[i] += 1;
            }
        }
    }

    private void setUp(String line){
        if(onesInEachPosition == null){
            onesInEachPosition = new int[line.length()];
        }
    }
}
