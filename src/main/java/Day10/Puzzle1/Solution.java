package Day10.Puzzle1;

import Day10.GenericSolution;

import java.util.*;

public class Solution extends GenericSolution {

    private Solution(){}

    private void run(){
        int points = parseAsStringStream()
                .map(this::isCorrupted)
                .filter(Objects::nonNull)
                .mapToInt(type -> type.corruptWeight)
                .sum();

        System.out.println(points);
    }

    public static void main(String[] args){
        new Solution().run();
    }
}
