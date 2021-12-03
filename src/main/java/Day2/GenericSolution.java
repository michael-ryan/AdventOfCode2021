package Day2;

import Common.Day;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Generic abstract class that implements annoying boilerplate parsing and stuff.
 */
public abstract class GenericSolution extends Day {

    /**
     * Possible keywords in instructions
     */
    protected enum Direction {
        FORWARD,
        UP,
        DOWN
    }

    private static final File f = new File("src/main/java/Day2/input.txt");
    protected int distanceForward;
    protected int depth;

    @Override
    protected Path getInputFile(){
        return FileSystems.getDefault().getPath("src/main/java/Day2/input.txt");
    }

    /**
     * Handles a single instruction. The only business logic function an implementing solution needs to have.
     *
     * @param direction the keyword of the instruction
     * @param magnitude the magnitude of the instruction
     */
    protected abstract void handleInstruction(Direction direction, int magnitude);

    protected void run(){
        parseAsStringStream().forEach(this::solve);

        System.out.println(this.computeAnswer());
    }

    private void solve(String instruction){
        String direction = instruction.split(" ")[0];
        int magnitude = Integer.parseInt(instruction.split(" ")[1]);
        Direction keyword;

        switch(direction){
            case "forward":
                keyword = Direction.FORWARD;
                break;
            case "up":
                keyword = Direction.UP;
                break;
            case "down":
                keyword = Direction.DOWN;
                break;
            default:
                throw new RuntimeException("Unknown direction \"" + direction + "\"");
        }

        handleInstruction(keyword, magnitude);
    }

    private int computeAnswer(){
        return distanceForward * depth;
    }
}
