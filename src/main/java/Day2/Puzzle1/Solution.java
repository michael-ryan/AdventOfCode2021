package Day2.Puzzle1;

import Day2.GenericSolution;

public class Solution extends GenericSolution {

    public Solution(){
    }

    public static void main(String[] args){
        new Solution().run();
    }

    @Override
    protected void handleInstruction(Direction direction, int magnitude){
        switch(direction){
            case FORWARD:
                distanceForward += magnitude;
                break;
            case UP:
                depth -= magnitude;
                break;
            case DOWN:
                depth += magnitude;
                break;
        }
    }
}