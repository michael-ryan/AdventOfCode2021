package Day14;

import Common.Day;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ConstantConditions")
public abstract class GenericSolution extends Day {

    private final Map<String, String> rules = new HashMap<>();
    private Map<String, Long> pairCounts = new HashMap<>();
    private final char first;
    private final char last;

    public GenericSolution(){
        String[] lines = this.parseAsStringArray();

        // parse polymer
        String polymer = lines[0];
        this.first = polymer.charAt(0);
        this.last = polymer.charAt(polymer.length() - 1);

        for(int i = 0; i < polymer.length() - 1; i++){
            String pair = polymer.substring(i, i + 2);
            if(pairCounts.containsKey(pair)){
                pairCounts.compute(pair, (s, l) -> l + 1);
            } else {
                pairCounts.put(pair, 1L);
            }
        }

        // parse rules
        for(int i = 2; i < lines.length; i++){
            String[] splitLine = lines[i].split(" -> ");

            this.rules.put(splitLine[0], splitLine[1]);
        }
    }

    private static void addPair(String pair, Map<String, Long> counts, long times){
        if(counts.containsKey(pair)){
            counts.compute(pair, (s, l) -> l + times);
        } else {
            counts.put(pair, times);
        }
    }

    private static Map<Character, Long> countCharacters(Map<String, Long> pairCounts, char first, char last){
        Map<Character, Long> characterCounts = new HashMap<>();

        // count up all the characters. since pairs overlap, characters are double-counted
        for(Map.Entry<String, Long> pairCount : pairCounts.entrySet()){
            char[] pair = pairCount.getKey().toCharArray();
            long count = pairCount.getValue();

            for(char character : pair){
                if(characterCounts.containsKey(character)){
                    characterCounts.compute(character, (c, l) -> l + count);
                } else {
                    characterCounts.put(character, count);
                }
            }
        }

        // correct double counted
        Map<Character, Long> correctedCharacterCounts = new HashMap<>();

        for(Map.Entry<Character, Long> characterCount : characterCounts.entrySet()){
            char character = characterCount.getKey();
            long count = characterCount.getValue();

            correctedCharacterCounts.put(character, count / 2);
        }

        // add start and end (they wouldn't have been double counted, since they don't overlap)
        correctedCharacterCounts.compute(first, (c, l) -> l + 1);
        correctedCharacterCounts.compute(last, (c, l) -> l + 1);

        return correctedCharacterCounts;
    }

    protected long getAnswerAfterNExpansions(int expansions){
        this.applyExpansion(expansions);

        return this.countMostCommonCharacter() - this.countLeastCommonCharacter();
    }

    @Override
    protected String getInputFile(){
        return "src/main/java/Day14/input.txt";
    }

    private void applyExpansion(int times){
        for(int i = 0; i < times; i++){
            this.applyExpansion();
        }
    }

    private void applyExpansion(){
        Map<String, Long> newCounts = new HashMap<>();

        for(Map.Entry<String, Long> pairCount : this.pairCounts.entrySet()){
            String middleChar = this.rules.get(pairCount.getKey());

            String firstPair = pairCount.getKey().charAt(0) + middleChar;
            String secondPair = middleChar + pairCount.getKey().charAt(1);

            addPair(firstPair, newCounts, pairCount.getValue());
            addPair(secondPair, newCounts, pairCount.getValue());
        }

        this.pairCounts = newCounts;
    }

    private long countLeastCommonCharacter(){
        Map<Character, Long> counts = countCharacters(pairCounts, first, last);

        long count = counts.get(first);

        for(Map.Entry<Character, Long> frequency : counts.entrySet()){
            if(frequency.getValue() < count){
                count = frequency.getValue();
            }
        }

        return count;
    }

    private long countMostCommonCharacter(){
        Map<Character, Long> counts = countCharacters(pairCounts, first, last);

        long count = counts.get(first);

        for(Map.Entry<Character, Long> frequency : counts.entrySet()){
            if(frequency.getValue() > count){
                count = frequency.getValue();
            }
        }

        return count;
    }
}
