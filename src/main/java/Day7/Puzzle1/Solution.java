package Day7.Puzzle1;

import Day7.GenericSolution;

public class Solution extends GenericSolution {
    @Override
    protected int computeFuelUseForCrab(int start, int end){
        return Math.abs(start - end);
    }
}
