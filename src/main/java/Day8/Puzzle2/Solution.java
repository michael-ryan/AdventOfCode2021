package Day8.Puzzle2;

import Day8.GenericSolution;

public class Solution extends GenericSolution {
    protected void handleLine(String[] keys, String[] digits){
        WireSolver solver = new WireSolver(keys);

        StringBuilder number = new StringBuilder();

        for(String digit : digits){
            number.append(solver.decodeNumber(digit));
        }

        this.answer += Integer.parseInt(number.toString());
    }
}
