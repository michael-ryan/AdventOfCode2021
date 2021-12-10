package Day9.Puzzle1;

import Day9.GenericSolution;

public class Solution extends GenericSolution {
    public Number run(){
        int[][] matrix = parseAsIntMatrix();

        int width = matrix[0].length;
        int height = matrix.length;

        int risk = 0;
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                if(isLowPoint(matrix, x, y)){
                    risk += 1 + matrix[y][x];
                }
            }
        }

        return risk;
    }
}
