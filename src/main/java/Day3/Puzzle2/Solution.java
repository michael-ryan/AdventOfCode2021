package Day3.Puzzle2;

import Day3.GenericSolution;

import java.util.stream.Stream;

public class Solution extends GenericSolution {

    private Solution(){
    }

    private static String findOxygenGeneratorRating(String[] binary){
        int activeBitPosition = 0;

        while(binary.length > 1){
            int pos = activeBitPosition;
            if(isZeroMostCommon(binary, pos)){
                binary = Stream.of(binary).filter(s -> s.charAt(pos) == '0').toArray(String[]::new);
            } else {
                binary = Stream.of(binary).filter(s -> s.charAt(pos) == '1').toArray(String[]::new);
            }
            activeBitPosition++;
        }

        return binary[0];
    }

    private static String findCo2ScrubberRating(String[] binary){
        int activeBitPosition = 0;

        while(binary.length > 1){
            int pos = activeBitPosition;
            if(isZeroMostCommon(binary, pos)){
                binary = Stream.of(binary).filter(s -> s.charAt(pos) == '1').toArray(String[]::new);
            } else {
                binary = Stream.of(binary).filter(s -> s.charAt(pos) == '0').toArray(String[]::new);
            }
            activeBitPosition++;
        }

        return binary[0];
    }

    private static boolean isZeroMostCommon(String[] binary, int position){
        int[] counts = countBitsInNthPosition(binary, position);

        return counts[0] > counts[1];
    }

    private static int[] countBitsInNthPosition(String[] binary, int n){
        int zeroes = 0;
        int ones = 0;
        for(String number : binary){
            if(number.charAt(n) == '0'){
                zeroes += 1;
            } else {
                ones += 1;
            }
        }

        return new int[]{zeroes, ones};
    }

    public static void main(String[] args){
        new Solution().run();
    }

    private void run(){
        String[] input = parseAsStringArray();

        String oxygen = findOxygenGeneratorRating(input);
        String co2 = findCo2ScrubberRating(input);

        System.out.println(binaryStringToInt(oxygen) * binaryStringToInt(co2));
    }
}
