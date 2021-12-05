package Day5;

import Common.Day;

public abstract class GenericSolution extends Day {

    protected VentMap map;

    @Override
    protected String getInputFile(){
        return "src/main/java/Day5/input.txt";
    }

    protected void run(){
        this.parseInput();

        System.out.println(countOverlaps());
    }

    protected void parseInput(){
        this.map = new VentMap();

        parseAsStringStream().forEach(this::parseLine);
    }

    protected int countOverlaps(){
        int twoOrMoreCount = 0;
        for(int y = 0; y < map.getHeight(); y++){
            for(int x = 0; x < map.getWidth(); x++){
                if(map.queryCoordinate(x, y) >= 2){
                    twoOrMoreCount++;
                }
            }
        }
        return twoOrMoreCount;
    }

    protected void parseLine(String line){
        String[] splitLine = line.split(" ");
        String[] from = splitLine[0].split(",");
        String[] to = splitLine[2].split(",");

        int fromX = Integer.parseInt(from[0]);
        int fromY = Integer.parseInt(from[1]);
        int toX = Integer.parseInt(to[0]);
        int toY = Integer.parseInt(to[1]);

        parseCoordinates(fromX, fromY, toX, toY);
    }

    protected abstract void parseCoordinates(int fromX, int fromY, int toX, int toY);
}
