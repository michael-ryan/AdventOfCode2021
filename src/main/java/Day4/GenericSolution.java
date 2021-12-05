package Day4;

import Common.Day;

import java.util.Arrays;

public abstract class GenericSolution extends Day {

    protected int[] calledNumbers;
    protected Board[] boards;

    @Override
    protected String getInputFile(){
        return "src/main/java/Day4/input.txt";
    }

    protected void parseInput(){
        String[] input = parseAsStringArray();

        this.calledNumbers = Arrays.stream(input[0].split(",")).mapToInt(Integer::parseInt).toArray();

        int boardSize = (int) Arrays.stream(input[2].split(" ")).filter(s -> s.length() > 0).count();

        int numberOfBoards = (input.length - 1) / (boardSize + 1);
        this.boards = new Board[numberOfBoards];

        for(int boardNumber = 0; boardNumber < numberOfBoards; boardNumber++){
            int firstLineOfBoard = 2 + (boardNumber * (boardSize + 1));
            this.boards[boardNumber] =
                    new Board(Arrays.copyOfRange(input, firstLineOfBoard, firstLineOfBoard + boardSize));
        }
    }
}
