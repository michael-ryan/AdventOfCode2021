package Day2.Puzzle2;

import Day2.GenericSolution;

public class Solution extends GenericSolution {

    private int aim;

    private Solution(){
    }

    public static void main(String[] args){
        new Solution().run();
    }

    protected void handleInstruction(String direction, int magnitude){
        switch(direction){
            case "forward":
                distanceForward += magnitude;
                depth += aim * magnitude;
                break;
            case "up":
                aim -= magnitude;
                break;
            case "down":
                aim += magnitude;
                break;
            default:
                throw new RuntimeException("Unknown direction \"" + direction + "\"");
        }
    }
}