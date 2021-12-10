package Day3.Puzzle2;

import Day3.GenericSolution;

import java.util.Arrays;
import java.util.function.Predicate;

public class Solution extends GenericSolution {

    public Solution(){
    }

    private static String findRating(String[] binary, boolean isOxygen){
        char zeroCommonWantedBit;
        char otherwise;

        if(isOxygen){
            zeroCommonWantedBit = '0';
            otherwise = '1';
        } else {
            zeroCommonWantedBit = '1';
            otherwise = '0';
        }

        int activeBitPosition = 0;

        while(binary.length > 1){
            int pos = activeBitPosition;
            if(isZeroMostCommon(binary, pos)){
                binary = filterStringArray(binary, s -> s.charAt(pos) == zeroCommonWantedBit);
            } else {
                binary = filterStringArray(binary, s -> s.charAt(pos) == otherwise);
            }
            activeBitPosition++;
        }

        return binary[0];
    }

    private static String[] filterStringArray(String[] input, Predicate<? super String> predicate){
        return Arrays.stream(input).filter(predicate).toArray(String[]::new);
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

    public Number run(){
        String[] input = parseAsStringArray();

        String oxygen = findRating(input, true);
        String co2 = findRating(input, false);

        return binaryStringToInt(oxygen) * binaryStringToInt(co2);
    }
}
