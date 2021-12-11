package Day11.Puzzle2;

import Day11.Board;
import Day11.GenericSolution;

public class Solution extends GenericSolution {
    @Override
    public Number run(){
        Board board = new Board(this.parseAsIntMatrix());
        return board.findSyncStep();
    }
}
