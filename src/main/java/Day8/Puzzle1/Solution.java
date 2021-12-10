package Day8.Puzzle1;

import Day8.GenericSolution;

import java.util.ArrayList;
import java.util.List;

public class Solution extends GenericSolution {
    protected void handleLine(String[] keys, String[] digits){
        List<Integer> uniqueLengths = new ArrayList<>();
        uniqueLengths.add(2);
        uniqueLengths.add(3);
        uniqueLengths.add(4);
        uniqueLengths.add(7);

        for(String digit : digits){
            if(uniqueLengths.contains(digit.length())){
                this.answer++;
            }
        }
    }
}
