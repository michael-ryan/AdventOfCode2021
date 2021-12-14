package Day14.Puzzle2;

import Day14.GenericSolution;

public class Solution extends GenericSolution {

    @Override
    public Number run(){
        return this.getAnswerAfterNExpansions(40);
    }
}
