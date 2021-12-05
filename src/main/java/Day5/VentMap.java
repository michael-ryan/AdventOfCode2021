package Day5;

import java.util.Arrays;

public class VentMap {

    // [y][x] -> (x, y) -> top left is 0, 0
    private int[][] ventCounts;
    private int width = 1;
    private int height = 1;

    public VentMap(){
        this.ventCounts = new int[1][];
        this.ventCounts[0] = new int[1];
    }

    public int queryCoordinate(int x, int y){
        return this.ventCounts[y][x];
    }

    public void addVent(int fromX, int fromY, int toX, int toY){
        int widthNeeded = Math.max(fromX, toX) + 1;
        int heightNeeded = Math.max(fromY, toY) + 1;

        increaseSizeIfNeeded(widthNeeded, heightNeeded);

        if(fromX == toX){
            // y is variable
            for(int y = Math.min(fromY, toY); y < heightNeeded; y++){
                ventCounts[y][fromX] += 1;
            }
        } else {
            // x is variable
            for(int x = Math.min(fromX, toX); x < widthNeeded; x++){
                ventCounts[fromY][x] += 1;
            }
        }
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    private void increaseSizeIfNeeded(int widthNeeded, int heightNeeded){
        if(widthNeeded > this.width){
            widenTo(widthNeeded);
        }

        if(heightNeeded > this.height){
            heightenTo(heightNeeded);
        }
    }

    private void widenTo(int newWidth){
        for(int y = 0; y < height; y++){
            this.ventCounts[y] = Arrays.copyOfRange(ventCounts[y], 0, newWidth);
        }

        this.width = newWidth;
    }

    private void heightenTo(int newHeight){
        this.ventCounts = Arrays.copyOfRange(ventCounts, 0, newHeight);
        for(int y = this.height; y < newHeight; y++){
            this.ventCounts[y] = new int[this.width];
        }

        this.height = newHeight;
    }
}
