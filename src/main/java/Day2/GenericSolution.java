package Day2;

import Common.FileLoader;

import java.io.File;
import java.util.stream.Stream;

/**
 * Generic abstract class that implements annoying boilerplate parsing and stuff.
 */
public abstract class GenericSolution {

    private static final File f = new File("src/main/java/Day2/input.txt");
    protected int distanceForward;
    protected int depth;

    /**
     * Loads the {@link File} <code>f</code> variable and returns it as an array of integers.
     *
     * @return the content of <code>f</code>
     */
    private static Stream<String> parseProblem(){
        return FileLoader.loadFile(f);
    }

    protected abstract void handleInstruction(String instruction, int magnitude);

    protected void run(){
        parseProblem().forEach(this::solve);

        System.out.println(this.computeAnswer());
    }

    private void solve(String instruction){
        String direction = instruction.split(" ")[0];
        int magnitude = Integer.parseInt(instruction.split(" ")[1]);

        handleInstruction(direction, magnitude);
    }

    private int computeAnswer(){
        return distanceForward * depth;
    }
}
