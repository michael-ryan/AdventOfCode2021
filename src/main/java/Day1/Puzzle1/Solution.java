package Day1.Puzzle1;

import Day1.GenericSolution;

public class Solution extends GenericSolution {
    public static void main(String[] args){
        int[] input = parseProblem();

        int prev = input[0];
        int count = 0;

        for(int current : input){
            if(current > prev){
                count++;
            }
            prev = current;
        }

        System.out.println(count);
    }
}
