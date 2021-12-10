package Day4.Puzzle1;

import Day4.Board;
import Day4.GenericSolution;

public class Solution extends GenericSolution {

    public Solution(){
    }

    public static void main(String[] args){
        new Solution().run();
    }

    public Number run(){
        this.parseInput();

        for(int calledNumber : calledNumbers){
            for(Board board : boards){
                board.markNumber(calledNumber);
                if(board.isWinning()){
                    return board.computeScore();
                }
            }
        }

        throw new RuntimeException("Panic!");
    }
}
