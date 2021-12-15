package Day15.Puzzle2;

import Day15.GenericSolution;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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

        int width = board[0].length;
        int height = board.length;

        Tile[][] bigBoard = new Tile[height * 5][];
        for(int row = 0; row < bigBoard.length; row++){
            bigBoard[row] = new Tile[width * 5];
        }

        Map<Point, Integer> translationWeights = new HashMap<>();

        int h = 0;
        for(int y = 0; y < height * 5; y += height){
            int w = 0;
            for(int x = 0; x < width * 5; x += width){
                translationWeights.put(new Point(x, y), h + w);
                w += 1;
            }
            h += 1;
        }

        for(int y = 0; y < board.length; y++){
            for(int x = 0; x < board[y].length; x++){
                Tile currentTile = board[y][x];
                for(Map.Entry<Point, Integer> translationWeight : translationWeights.entrySet()){
                    Point translation = translationWeight.getKey();
                    int weight = translationWeight.getValue();
                    int newRisk = currentTile.getRisk() + weight;
                    if(newRisk > 9){
                        newRisk -= 9;
                    }
                    bigBoard[y + translation.y][x + translation.x] = new Tile(
                            new Point(x + translation.x, y + translation.y), newRisk
                    );
                }
            }
        }

        return bigBoard;
    }
}
