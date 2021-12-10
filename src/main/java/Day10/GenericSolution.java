package Day10;

import Common.Day;

import java.util.*;

public abstract class GenericSolution extends Day {
    protected List<Character> openCharacters;
    protected List<Character> closeCharacters;
    protected Map<Character, BracketType> characterTypes;

    protected enum BracketType {
        NORMAL(3, 1),
        SQUARE(57, 2),
        CURLY(1197, 3),
        ANGLE(25137, 4);

        public final int corruptWeight;
        public final int completeWeight;

        BracketType(int corruptWeight, int completeWeight){
            this.corruptWeight = corruptWeight;
            this.completeWeight = completeWeight;
        }
    }

    @Override
    protected String getInputFile(){
        return "src/main/java/Day10/input.txt";
    }

    protected GenericSolution(){
        this.openCharacters = new ArrayList<>(Arrays.asList('(', '[', '{', '<'));
        this.closeCharacters = new ArrayList<>(Arrays.asList(')', ']', '}', '>'));

        this.characterTypes = new HashMap<>();
        characterTypes.put('(', BracketType.NORMAL);
        characterTypes.put(')', BracketType.NORMAL);
        characterTypes.put('[', BracketType.SQUARE);
        characterTypes.put(']', BracketType.SQUARE);
        characterTypes.put('{', BracketType.CURLY);
        characterTypes.put('}', BracketType.CURLY);
        characterTypes.put('<', BracketType.ANGLE);
        characterTypes.put('>', BracketType.ANGLE);
    }

    /**
     * Returns the offending bracket type if a line is corrupted, else returns null.
     * @param line input line
     * @return see above
     */
    public BracketType isCorrupted(String line){
        Stack<BracketType> openChunks = new Stack<>();

        for(char c : line.toCharArray()){
            BracketType characterType = this.characterTypes.get(c);

            if(this.openCharacters.contains(c)){
                openChunks.push(characterType);
            } else if(this.closeCharacters.contains(c)){
                if(openChunks.peek().equals(characterType)){
                    openChunks.pop();
                } else {
                    return characterType;
                }
            } else {
                throw new RuntimeException("Unknown character '" + c + "' encountered!");
            }
        }

        return null;
    }


}
