package Day7.Puzzle2;

import Day7.GenericSolution;

public class Solution extends GenericSolution {
    @Override
    protected int computeFuelUseForCrab(int start, int end){
        int distance = Math.abs(start - end);
        return (distance * (distance + 1)) / 2;
    }
}
