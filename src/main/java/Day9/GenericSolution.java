package Day9;

import Common.Day;

public abstract class GenericSolution extends Day {

    protected static boolean isLowPoint(int[][] matrix, int x, int y){
        boolean candidate = true;
        int point = matrix[y][x];

        // left
        if(x != 0){
            candidate = point < matrix[y][x - 1];
        }

        // right
        if(x != matrix[0].length - 1){
            candidate = candidate && (point < matrix[y][x + 1]);
        }

        // up
        if(y != 0){
            candidate = candidate && (point < matrix[y - 1][x]);
        }

        // down
        if(y != matrix.length - 1){
            candidate = candidate && (point < matrix[y + 1][x]);
        }

        return candidate;
    }

    @Override
    protected String getInputFile(){
        return "src/main/java/Day9/input.txt";
    }
}
