package Day10.Puzzle2;

import Day10.GenericSolution;

import java.util.*;

public class Solution extends GenericSolution {

    private Solution(){}

    private void run(){
        long[] scores = parseAsStringStream()
                .filter(line -> this.isCorrupted(line) == null)
                .mapToLong(this::completeLine)
                .sorted()
                .toArray();

        System.out.println(scores[(scores.length - 1) / 2]);
    }

    /*
    returns the score for that line
     */
    private long completeLine(String line){
        Stack<BracketType> openChunks = new Stack<>();

        for(char c : line.toCharArray()){
            BracketType characterType = characterTypes.get(c);

            if(openCharacters.contains(c)){
                openChunks.push(characterType);
            } else {
                openChunks.pop();
            }
        }

        long score = 0L;

        while(openChunks.size() != 0){
            score *= 5;
            score += openChunks.pop().completeWeight;
        }

        return score;
    }

    public static void main(String[] args){
        new Solution().run();
    }
}
