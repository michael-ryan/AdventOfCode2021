package Day9.Puzzle2;

import Day9.GenericSolution;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class Solution extends GenericSolution {

    private Solution(){
    }

    private static int popBiggestBasin(Map<Pair<Integer, Integer>, Integer> basinSizes){
        int biggestSize = 0;
        Pair<Integer, Integer> biggestLowPoint = null;

        boolean started = false;

        for(Map.Entry<Pair<Integer, Integer>, Integer> basinSize : basinSizes.entrySet()){
            if(!started){
                biggestLowPoint = basinSize.getKey();
                biggestSize = basinSize.getValue();
                started = true;
            } else if(biggestSize < basinSize.getValue()){
                biggestLowPoint = basinSize.getKey();
                biggestSize = basinSize.getValue();
            }
        }

        basinSizes.remove(biggestLowPoint);
        return biggestSize;
    }

    public static void main(String[] args){
        new Solution().run();
    }

    private void run(){
        int[][] matrix = parseAsIntMatrix();

        Map<Pair<Integer, Integer>, Integer> basinSizes = new HashMap<>();

        int width = matrix[0].length;
        int height = matrix.length;

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                if(matrix[y][x] == 9){
                    continue;
                }

                Pair<Integer, Integer> lowPoint = findLowestPoint(matrix, x, y);
                if(basinSizes.containsKey(lowPoint)){
                    basinSizes.put(lowPoint, basinSizes.get(lowPoint) + 1);
                } else {
                    basinSizes.put(lowPoint, 1);
                }
            }
        }

        System.out.println(popBiggestBasin(basinSizes) * popBiggestBasin(basinSizes) * popBiggestBasin(basinSizes));
    }

    /*
    Don't give me nines
     */
    private Pair<Integer, Integer> findLowestPoint(int[][] matrix, int x, int y){
        if(isLowPoint(matrix, x, y)){
            return new Pair<>(x, y);
        }

        Direction bestDirection = null;
        int lowestAltitude = 10;

        // left
        if(x != 0){
            if(matrix[y][x - 1] < lowestAltitude){
                bestDirection = Direction.LEFT;
                lowestAltitude = matrix[y][x - 1];
            }
        }

        // right
        if(x != matrix[0].length - 1){
            if(matrix[y][x + 1] < lowestAltitude){
                bestDirection = Direction.RIGHT;
                lowestAltitude = matrix[y][x + 1];
            }
        }

        // up
        if(y != 0){
            if(matrix[y - 1][x] < lowestAltitude){
                bestDirection = Direction.UP;
                lowestAltitude = matrix[y - 1][x];
            }
        }

        // down
        if(y != matrix.length - 1){
            if(matrix[y + 1][x] < lowestAltitude){
                bestDirection = Direction.DOWN;
            }
        }

        int nextX;
        int nextY;

        assert bestDirection != null;
        switch(bestDirection){
            case DOWN:
                nextX = x;
                nextY = y + 1;
                break;
            case UP:
                nextX = x;
                nextY = y - 1;
                break;
            case LEFT:
                nextX = x - 1;
                nextY = y;
                break;
            case RIGHT:
                nextX = x + 1;
                nextY = y;
                break;
            default:
                throw new RuntimeException("WHAT");
        }

        return findLowestPoint(matrix, nextX, nextY);
    }

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
