package Day13.Puzzle1;

import Day13.GenericSolution;

public class Solution extends GenericSolution {
    @Override
    public Number run(){
        this.doInstruction();

        return this.dots.size();
    }
}
