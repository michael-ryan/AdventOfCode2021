package Day4.Puzzle2;

import Day4.Board;
import Day4.GenericSolution;

import java.util.ArrayList;
import java.util.List;

public class Solution extends GenericSolution {

    private final List<Board> winningBoards;

    private Solution(){
        this.winningBoards = new ArrayList<>();
    }

    public static void main(String[] args){
        new Solution().run();
    }

    private void run(){
        this.parseInput();

        for(int calledNumber : calledNumbers){
            for(Board board : boards){
                if(winningBoards.contains(board)){
                    continue;
                }
                board.markNumber(calledNumber);
                if(board.isWinning()){
                    winningBoards.add(board);
                }
            }
        }

        System.out.println(winningBoards.get(winningBoards.size() - 1).computeScore());
    }
}
