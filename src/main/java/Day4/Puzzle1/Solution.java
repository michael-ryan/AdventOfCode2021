package Day4.Puzzle1;

import Day4.Board;
import Day4.GenericSolution;

public class Solution extends GenericSolution {

    private Solution(){
    }

    public static void main(String[] args){
        new Solution().run();
    }

    private void run(){
        this.parseInput();

        for(int calledNumber : calledNumbers){
            for(Board board : boards){
                board.markNumber(calledNumber);
                if(board.isWinning()){
                    System.out.println(board.computeScore());
                    return;
                }
            }
        }
    }
}
