package Day2;

import Common.FileLoader;

import java.io.File;
import java.util.stream.Stream;

/**
 * Generic abstract class that implements annoying boilerplate parsing and stuff.
 */
public abstract class GenericSolution {
    private static final File f = new File("src/main/java/Day2/input.txt");

    /**
     * Loads the {@link File} <code>f</code> variable and returns it as an array of integers.
     * @return the content of <code>f</code>
     */
    protected static Stream<String> parseProblem(){
        return FileLoader.loadFile(f);
    }
}
