package Day13.Puzzle2;

import Day13.GenericSolution;

public class Solution extends GenericSolution {
    @Override
    public Number run(){
        while(this.hasInstructionsLeft()){
            this.doInstruction();
        }

        this.visualiseGrid();

        return null;
    }
}
