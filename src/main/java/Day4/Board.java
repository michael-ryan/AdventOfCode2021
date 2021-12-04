package Day4;

import java.util.Arrays;

public class Board {

    // [y][x] = row, column. 0,0 is top left
    private final Cell[][] numbers;
    private int lastCalledNumber;

    public Board(String[] board){
        this.numbers = new Cell[board.length][];

        for(int row = 0; row < board.length; row++){
            String[] textRow = Arrays.stream(board[row].split(" ")).filter(s -> s.length() > 0).toArray(String[]::new);
            this.numbers[row] = new Cell[board.length];
            for(int column = 0; column < board.length; column++){
                this.numbers[row][column] = new Cell(Integer.parseInt(textRow[column]));
            }
        }
    }

    public int computeScore(){
        if(!this.isWinning()){
            throw new RuntimeException("Board isn't in winning state, makes no sense to compute score");
        }

        int score = 0;

        for(Cell[] row : numbers){
            for(Cell cell : row){
                if(cell.isNotMarked()){
                    score += cell.getValue();
                }
            }
        }

        return score * this.lastCalledNumber;
    }

    public void markNumber(int n){
        if(this.isWinning()){
            return;
        }

        for(Cell[] row : numbers){
            for(Cell cell : row){
                if(cell.getValue() == n){
                    cell.setMarked(true);
                    lastCalledNumber = n;
                }
            }
        }
    }

    public boolean isWinning(){
        // check columns
        for(int column = 0; column < numbers.length; column++){
            boolean candidate = true;
            for(Cell[] row : numbers){
                if(row[column].isNotMarked()){
                    candidate = false;
                    break;
                }
            }
            if(candidate){
                return true;
            }
        }

        // check rows
        for(Cell[] row : numbers){
            boolean candidate = true;
            for(Cell cell : row){
                if(cell.isNotMarked()){
                    candidate = false;
                    break;
                }
            }
            if(candidate){
                return true;
            }
        }

        return false;
    }

    private static class Cell {

        private final int value;
        private boolean isMarked = false;

        public Cell(int value){
            this.value = value;
        }

        public boolean isNotMarked(){
            return !isMarked;
        }

        public void setMarked(boolean marked){
            isMarked = marked;
        }

        public int getValue(){
            return value;
        }
    }
}
