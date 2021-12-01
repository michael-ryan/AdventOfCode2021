package Day1;

import Common.FileLoader;

import java.io.File;

/**
 * Generic abstract class that implements annoying boilerplate parsing and stuff.
 */
public abstract class GenericSolution {
    private static final File f = new File("src/main/java/Day1/input.txt");

    /**
     * Loads the {@link File} <code>f</code> variable and returns it as an array of integers.
     * @return the content of <code>f</code>
     */
    protected static int[] parseProblem(){
        return FileLoader.loadAsIntegers(f)
                .toArray();
    }

    /**
     * Given an array, returns the number of times any element was greater than the previous element.
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
