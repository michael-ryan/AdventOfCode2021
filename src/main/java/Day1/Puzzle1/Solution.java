package Day1.Puzzle1;

import Day1.GenericSolution;

public class Solution extends GenericSolution {

    private Solution(){
    }

    public static void main(String[] args){
        new Solution().run();
    }

    private void run(){
        System.out.println(solve(parseAsIntArray()));
    }
}
