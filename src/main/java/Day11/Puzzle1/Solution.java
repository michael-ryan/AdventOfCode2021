package Day11.Puzzle1;

import Day11.Board;
import Day11.GenericSolution;

public class Solution extends GenericSolution {
    @Override
    public Number run(){
        Board board = new Board(this.parseAsIntMatrix());
        board.simulateStep(100);
        return board.getNumberOfFlashes();
    }
}
