package Day2.Puzzle1;

import Day2.GenericSolution;

public class Solution extends GenericSolution {
    private static int distanceForward;
    private static int depth;

    public static void main(String[] args){
        parseProblem().forEach(Solution::handleLine);

        System.out.println("Distance forward: " + distanceForward);
        System.out.println("Depth: " + depth);
        System.out.println("depth * distance = " + (distanceForward * depth));
    }

    private static void handleLine(String s){
        String direction = s.split(" ")[0];
        int magnitude = Integer.parseInt(s.split(" ")[1]);

        switch(direction){
            case "forward" :
                distanceForward += magnitude;
                break;
            case "up" :
                depth -= magnitude;
                break;
            case "down" :
                depth += magnitude;
                break;
            default:
                throw new RuntimeException("Unknown direction \"" + direction + "\"");
        }
    }
}