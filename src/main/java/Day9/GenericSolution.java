package Day9;

import Common.Day;

public class GenericSolution extends Day {

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

    protected int[][] parseAsIntMatrix(){
        String[] input = parseAsStringArray();

        int[][] matrix = new int[input.length][];

        for(int i = 0; i < input.length; i++){
            int[] line = new int[input[0].length()];
            for(int j = 0; j < input[0].length(); j++){
                line[j] = Integer.parseInt(String.valueOf(input[i].toCharArray()[j]));
            }
            matrix[i] = line;
        }

        return matrix;
    }

    @Override
    protected String getInputFile(){
        return "src/main/java/Day9/input.txt";
    }
}
