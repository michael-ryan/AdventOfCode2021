package Day1;

import java.io.File;
import Common.FileLoader;

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
}
