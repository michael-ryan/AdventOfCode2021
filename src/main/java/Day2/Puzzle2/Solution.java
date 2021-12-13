package Day2.Puzzle2;

import Day2.GenericSolution;

public class Solution extends GenericSolution {
    private int aim;

    @Override
    protected void handleInstruction(Direction direction, int magnitude){
        switch(direction){
            case FORWARD:
                distanceForward += magnitude;
                depth += aim * magnitude;
                break;
            case UP:
                aim -= magnitude;
                break;
            case DOWN:
                aim += magnitude;
                break;
        }
    }
}