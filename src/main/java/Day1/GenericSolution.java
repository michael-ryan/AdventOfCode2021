package Day1;

import Common.Day;

/**
 * Generic abstract class that implements annoying boilerplate parsing and stuff.
 */
public abstract class GenericSolution extends Day {
    @Override
    protected String getInputFile(){
        return "src/main/java/Day1/input.txt";
    }

    /**
     * Given an array, returns the number of times any element was greater than the previous element.
     *
     * @param input the input array
     * @return the number of times any element was greater than the previous element.
     */
    protected static int solve(int[] input){
        int prev = input[0];
        int count = 0;

        for(int current : input){
            if(current > prev){
                count++;
            }
            prev = current;
        }

        return count;
    }
}
