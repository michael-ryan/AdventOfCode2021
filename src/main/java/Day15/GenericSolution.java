package Day15;

import Common.Day;

import java.awt.*;
import java.util.*;

public abstract class GenericSolution extends Day {
    @Override
    protected String getInputFile(){
        return "src/main/java/Day15/input.txt";
    }

    @Override
    public Number run(){
        Tile[][] board = this.parseInputGrid();

        return dijsktra(board);
    }

    protected abstract Tile[][] parseInputGrid();

    protected static class Tile implements Comparable<Tile>{
        private final Point position;
        private final Integer risk;
        private Integer minCost = Integer.MAX_VALUE;

        public Tile(Point position, int risk){
            this.position = position;
            this.risk = risk;
        }

        public Point getPosition(){
            return position;
        }

        public int getRisk(){
            return risk;
        }

        public Integer getMinCost(){
            return minCost;
        }

        public void improveMinCost(int minCost){
            if(minCost < this.minCost){
                this.minCost = minCost;
            }
        }

        @Override
        public int compareTo(Tile o){
            return this.minCost.compareTo(o.minCost);
        }
    }

    protected static int dijsktra(Tile[][] graph){
        Queue<Tile> tiles = new PriorityQueue<>();
        Map<Tile, Tile> previousTile = new HashMap<>();

        for(Tile[] row : graph){
            for(Tile tile : row){
                previousTile.put(tile, null);
                tiles.add(tile);
            }
        }

        improveCost(tiles, graph[0][0], 0);

        while(tiles.size() > 0){
            Tile tile = tiles.remove();
            for(Tile neighbour : findNeighbours(tile, graph)){
                int candidateDistance = tile.getMinCost() + neighbour.getRisk();
                if(candidateDistance < neighbour.getMinCost()){
                    improveCost(tiles, neighbour, candidateDistance);
                    previousTile.put(neighbour, tile);
                }
            }
        }

        // return cost of bottom-right tile
        return graph[graph.length - 1][graph[graph.length - 1].length - 1].getMinCost();
    }

    private static Set<Tile> findNeighbours(Tile tile, Tile[][] graph){
        int baseX = tile.getPosition().x;
        int baseY = tile.getPosition().y;

        Set<Tile> neighbours = new HashSet<>();

        // up
        try {
            neighbours.add(graph[baseY - 1][baseX]);
        } catch(IndexOutOfBoundsException ignored){}

        // down
        try {
            neighbours.add(graph[baseY + 1][baseX]);
        } catch(IndexOutOfBoundsException ignored){}

        // left
        try {
            neighbours.add(graph[baseY][baseX - 1]);
        } catch(IndexOutOfBoundsException ignored){}

        // right
        try {
            neighbours.add(graph[baseY][baseX + 1]);
        } catch(IndexOutOfBoundsException ignored){}

        return neighbours;
    }

    private static void improveCost(Queue<Tile> tiles, Tile tile, int newCost){
        tiles.remove(tile);
        tile.improveMinCost(newCost);
        tiles.add(tile);
    }
}
