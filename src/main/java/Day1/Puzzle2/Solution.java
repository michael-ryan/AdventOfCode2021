package Day1.Puzzle2;

import Day1.GenericSolution;

public class Solution extends GenericSolution {
    public Number run(){
        int[] input = parseAsIntArray();

        int[] summedThrees = new int[input.length - 2];

        for(int i = 2; i < input.length; i++){
            summedThrees[i - 2] += input[i];
            summedThrees[i - 2] += input[i - 1];
            summedThrees[i - 2] += input[i - 2];
        }

        return solve(summedThrees);
    }
}
