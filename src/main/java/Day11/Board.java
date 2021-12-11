package Day11;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Board {

    private class Octopus {

        private boolean isFlashing = false;
        private int energy;
        private int x;
        private int y;
        public Octopus(int energy, int x, int y){
            this.energy = energy;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString(){
            return "Octopus{" + "isFlashing=" + isFlashing + ", energy=" + energy + ", x=" + x + ", y=" + y + '}';
        }

        public int getX(){
            return this.x;
        }

        public int getY(){
            return this.y;
        }

        public int getEnergy(){
            return this.energy;
        }

        public void incrementEnergy(){
            this.energy++;
            if(this.energy > 9){
                this.isFlashing = true;
            }
        }

        public boolean isFlashing(){
            return this.isFlashing;
        }

        public void reset(){
            this.isFlashing = false;
            this.energy = 0;
        }

    }
    private Octopus[][] octopi;

    private int numberOfFlashes = 0;
    private int stepsSimulated = 0;
    private boolean isSynced = false;

    public Board(int[][] map){
        this.octopi = new Octopus[map.length][];

        for(int y = 0; y < map.length; y++){
            this.octopi[y] = new Octopus[map[y].length];
            for(int x = 0; x < map[y].length; x++){
                this.octopi[y][x] = new Octopus(map[y][x], x, y);
            }
        }
    }

    public int getNumberOfFlashes(){
        return this.numberOfFlashes;
    }

    public int findSyncStep(){
        while(!isSynced){
            simulateStep();
        }

        return this.stepsSimulated;
    }

    public void simulateStep(){
        this.stepsSimulated++;

        // fifo
        Queue<Octopus> flashingOctopi = new ArrayDeque<>();
        Set<Octopus> doneOctopi = new HashSet<>();

        // increment everyone
        for(Octopus[] octopi : this.octopi){
            for(Octopus octopus : octopi){
                octopus.incrementEnergy();
                if(octopus.isFlashing()){
                    flashingOctopi.add(octopus);
                    doneOctopi.add(octopus);
                }
            }
        }

        // resolve chain flashes
        while(flashingOctopi.size() > 0){
            Octopus currentOctopus = flashingOctopi.remove();

            int[] directions = {-1, 0, 1};

            for(int yDirection : directions){
                for(int xDirection : directions){
                    if(yDirection == 0 && xDirection == 0){
                        continue;
                    }
                    Octopus targetOctopus;
                    try {
                        targetOctopus =
                                this.octopi[currentOctopus.getY() + yDirection][currentOctopus.getX() + xDirection];
                    } catch(IndexOutOfBoundsException ignored){
                        continue;
                    }

                    targetOctopus.incrementEnergy();
                    if(targetOctopus.isFlashing() && !doneOctopi.contains(targetOctopus)){
                        doneOctopi.add(targetOctopus);
                        flashingOctopi.add(targetOctopus);
                    }
                }
            }
        }

        // log number of flashes
        this.numberOfFlashes += doneOctopi.size();

        // reset everyone who flashed
        for(Octopus flashedOctopus : doneOctopi){
            flashedOctopus.reset();
        }

        this.isSynced = checkIfSynced(this.octopi);
    }

    private static boolean checkIfSynced(Octopus[][] grid){
        int target = grid[0][0].getEnergy();

        for(int y = 0; y < grid.length; y++){
            for(int x = 0; x < grid[y].length; x++){
                if(target != grid[y][x].getEnergy()){
                    return false;
                }
            }
        }

        return true;
    }

    public void simulateStep(int n){
        for(int i = 0; i < n; i++){
            simulateStep();
        }
    }

}
