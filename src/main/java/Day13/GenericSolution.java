package Day13;

import Common.Day;

import java.awt.*;
import java.util.List;
import java.util.*;

public abstract class GenericSolution extends Day {
    protected final Set<Point> dots = new HashSet<>();
    private final List<Instruction> instructions = new ArrayList<>();

    private enum FoldAxis {
        X,
        Y
    }

    private static class Instruction {
        private final int coordinate;
        private final FoldAxis foldAxis;

        public Instruction(int coordinate, FoldAxis foldAxis){
            this.coordinate = coordinate;
            this.foldAxis = foldAxis;
        }

        public int getCoordinate(){
            return coordinate;
        }

        public FoldAxis getFoldAxis(){
            return foldAxis;
        }
    }

    public GenericSolution(){
        this.parseInput();
    }

    private void parseInput(){
        boolean isInstruction = false;

        for(String line : parseAsStringArray()){
            if(line.isEmpty()){
                isInstruction = true;
                continue;
            }

            if(isInstruction){
                this.parseInstruction(line);
            } else {
                this.parseDot(line);
            }
        }
    }

    private void parseInstruction(String line){
        String[] instruction = line.split(" ")[2].split("=");
        FoldAxis foldAxis;

        if(instruction[0].equals("y")){
            foldAxis = FoldAxis.Y;
        } else if(instruction[0].equals("x")){
            foldAxis = FoldAxis.X;
        } else {
            throw new RuntimeException("Panic on instruction: " + instruction[0] + "=" + instruction[1]);
        }

        int coordinate = Integer.parseInt(instruction[1]);

        this.instructions.add(new Instruction(coordinate, foldAxis));
    }

    private void parseDot(String line){
        String[] splitLine = line.split(",");
        int x = Integer.parseInt(splitLine[0]);
        int y = Integer.parseInt(splitLine[1]);

        this.dots.add(new Point(x, y));
    }

    @Override
    protected String getInputFile(){
        return "src/main/java/Day13/input.txt";
    }

    protected boolean hasInstructionsLeft(){
        return this.instructions.size() > 0;
    }

    protected void doInstruction(){
        Instruction instruction = this.instructions.remove(0);

        Set<Point> deletedDots = new HashSet<>();
        Set<Point> newDots = new HashSet<>();

        for(Point point : this.dots){
            if(instruction.getFoldAxis().equals(FoldAxis.X) && point.x < instruction.getCoordinate()){
                continue;
            } else if(instruction.getFoldAxis().equals(FoldAxis.Y) && point.y < instruction.getCoordinate()){
                continue;
            }

            deletedDots.add(point);
            newDots.add(reflectPoint(instruction, point));
        }

        this.dots.removeAll(deletedDots);
        this.dots.addAll(newDots);
    }

    protected void visualiseGrid(){
        List<Integer> xs = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();

        this.dots.forEach(point -> {
            xs.add(point.x);
            ys.add(point.y);
        });

        int width = 1 + xs.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow(RuntimeException::new);

        int height = 1 + ys.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow(RuntimeException::new);


        char[][] grid = new char[height][];
        for(int i = 0; i < grid.length; i++){
            grid[i] = new char[width];
            Arrays.fill(grid[i], ' ');
        }

        for(Point point : this.dots){
            grid[point.y][point.x] = '#';
        }

        for(char[] chars : grid){
            for(char aChar : chars){
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    private static Point reflectPoint(Instruction instruction, Point point){
        Point newPoint;

        if(instruction.getFoldAxis().equals(FoldAxis.Y)){
            newPoint = new Point(point.x, (2 * instruction.getCoordinate()) - point.y);
        } else {
            newPoint = new Point((2 * instruction.getCoordinate()) - point.x, point.y);
        }

        return newPoint;
    }
}
