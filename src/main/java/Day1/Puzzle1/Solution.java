package Day1.Puzzle1;

import Day1.GenericSolution;

public class Solution extends GenericSolution {

    public Solution(){
    }

    public static void main(String[] args){
        new Solution().run();
    }

    public Number run(){
        return solve(parseAsIntArray());
    }
}
