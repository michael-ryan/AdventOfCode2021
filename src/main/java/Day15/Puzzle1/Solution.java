package Day15.Puzzle1;

import Day15.GenericSolution;

import java.awt.*;

public class Solution extends GenericSolution {
    @Override
    protected Tile[][] parseInputGrid(){
        int[][] intBoard = this.parseAsIntMatrix();
        Tile[][] board = new Tile[intBoard.length][];

        for(int y = 0; y < intBoard.length; y++){
            board[y] = new Tile[intBoard[y].length];
            for(int x = 0; x < board[y].length; x++){
                board[y][x] = new Tile(new Point(x, y), intBoard[y][x]);
            }
        }

        return board;
    }
}
