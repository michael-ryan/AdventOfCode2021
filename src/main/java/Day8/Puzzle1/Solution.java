package Day8.Puzzle1;

import Day8.GenericSolution;

import java.util.ArrayList;
import java.util.List;

public class Solution extends GenericSolution {

    private int uniqueCount;

    private Solution(){
    }

    public static void main(String[] args){
        new Solution().run();
    }

    private void run(){
        this.uniqueCount = 0;

        parseAsStringStream().forEach(this::handleLine);

        System.out.println(this.uniqueCount);
    }

    private void handleLine(String line){
        String[] digits = line.split(" \\| ")[1].split(" ");

        List<Integer> uniqueLengths = new ArrayList<>();
        uniqueLengths.add(2);
        uniqueLengths.add(3);
        uniqueLengths.add(4);
        uniqueLengths.add(7);

        for(String digit : digits){
            if(uniqueLengths.contains(digit.length())){
                this.uniqueCount++;
            }
        }
    }
}
