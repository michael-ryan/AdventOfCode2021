package Day2.Puzzle1;

import Day2.GenericSolution;

public class Solution extends GenericSolution {

    public static void main(String[] args){
        parseProblem().forEach(s -> Solution.solve(s, Solution::handleInstruction));

        System.out.println("Distance forward: " + distanceForward);
        System.out.println("Depth: " + depth);
        System.out.println("depth * distance = " + (distanceForward * depth));
    }

    private static void handleInstruction(String direction, int magnitude){
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