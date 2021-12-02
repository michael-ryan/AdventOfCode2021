package Day2.Puzzle1;

import Day2.GenericSolution;

public class Solution extends GenericSolution {

    private Solution(){
    }

    public static void main(String[] args){
        new Solution().run();
    }

    protected void handleInstruction(String direction, int magnitude){
        switch(direction){
            case "forward":
                distanceForward += magnitude;
                break;
            case "up":
                depth -= magnitude;
                break;
            case "down":
                depth += magnitude;
                break;
            default:
                throw new RuntimeException("Unknown direction \"" + direction + "\"");
        }
    }
}