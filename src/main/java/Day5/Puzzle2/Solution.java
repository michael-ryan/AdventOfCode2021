package Day5.Puzzle2;

import Day5.GenericSolution;

public class Solution extends GenericSolution {

    public Solution(){
    }

    public static void main(String[] args){
        new Solution().run();
    }

    @Override
    protected void parseCoordinates(int fromX, int fromY, int toX, int toY){
        if(fromX != toX && fromY != toY){ // diagonal
            if(fromY > toY){
                int tmp = fromY;
                fromY = toY;
                toY = tmp;

                tmp = fromX;
                fromX = toX;
                toX = tmp;
            }

            int k;
            if(fromX > toX){
                k = fromY + fromX;

                for(int y = fromY; y <= toY; y++){
                    map.addVent(k - y, y, k - y, y);
                }
            } else {
                k = fromY - fromX;

                for(int y = fromY; y <= toY; y++){
                    map.addVent(y - k, y, y - k, y);
                }
            }
        } else {
            map.addVent(fromX, fromY, toX, toY);
        }
    }
}
