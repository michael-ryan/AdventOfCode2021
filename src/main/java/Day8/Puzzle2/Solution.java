package Day8.Puzzle2;

import Day8.GenericSolution;

public class Solution extends GenericSolution {

    private Solution(){
    }

    public static void main(String[] args){
        new Solution().run();
    }

    private void run(){
        String[] input = this.parseAsStringArray();

        int total = 0;

        for(String line : input){
            total += sumLine(line);
        }

        System.out.println(total);
    }

    private int sumLine(String line){
        String[] splitLine = line.split(" \\| ");

        WireSolver solver = new WireSolver(splitLine[0].split(" "));

        String[] values = splitLine[1].split(" ");

        StringBuilder digits = new StringBuilder();

        for(String value : values){
            digits.append(solver.decodeNumber(value));
        }

        return Integer.parseInt(digits.toString());
    }
}
