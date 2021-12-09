package Day7.Puzzle1;

import Day7.GenericSolution;

public class Solution extends GenericSolution {

    public static void main(String[] args){
        new Solution().run();
    }

    @Override
    protected int computeFuelUseForCrab(int start, int end){
        return Math.abs(start - end);
    }
}
