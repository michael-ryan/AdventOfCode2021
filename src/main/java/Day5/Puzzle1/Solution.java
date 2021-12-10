package Day5.Puzzle1;

import Day5.GenericSolution;

public class Solution extends GenericSolution {

    public Solution(){
    }

    public static void main(String[] args){
        new Solution().run();
    }

    @Override
    protected void parseCoordinates(int fromX, int fromY, int toX, int toY){
        if(fromX != toX && fromY != toY){
            return;
        }

        map.addVent(fromX, fromY, toX, toY);
    }
}
