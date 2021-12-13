package Day8.Puzzle2;

import java.util.*;
import java.util.stream.Collectors;

class WireSolver {

    private final Map<Set<Character>, Integer> numbers;

    /**
     * Takes the numbers before the pipe and constructs an internal mapping from letters to digits
     *
     * @param input the numbers before the pipe
     */
    @SuppressWarnings("ConstantConditions")
    public WireSolver(String[] input){
        ArrayList<Set<Character>> unknownNumbers = new ArrayList<>();

        for(String number : input){
            Set<Character> numberRepresentation = new HashSet<>();
            for(char character : number.toCharArray()){
                numberRepresentation.add(character);
            }
            unknownNumbers.add(numberRepresentation);
        }

        Set<Character> one = null;
        Set<Character> four = null;
        Set<Character> seven = null;
        Set<Character> eight = null;

        for(Set<Character> representation : unknownNumbers){
            switch(representation.size()){
                case 2:
                    one = representation;
                    break;
                case 3:
                    seven = representation;
                    break;
                case 4:
                    four = representation;
                    break;
                case 7:
                    eight = representation;
                    break;
                default:
                    break;
            }
        }

        unknownNumbers.remove(one);
        unknownNumbers.remove(four);
        unknownNumbers.remove(seven);
        unknownNumbers.remove(eight);

        char top = findTop(one, seven);
        char[] middleAndTopLeft = findMiddleAndTopLeft(one, four);
        char middle = findMiddle(unknownNumbers, middleAndTopLeft);
        char topLeft = middleAndTopLeft[0] == middle ? middleAndTopLeft[1] : middleAndTopLeft[0];
        char bottomLeft = findBottomLeft(unknownNumbers, one, topLeft, top, middle);
        char topRight = findTopRight(unknownNumbers, top, topLeft, middle, bottomLeft);
        char bottomRight = findBottomRight(one, topRight);
        char bottom = findBottom(top, topLeft, topRight, middle, bottomLeft, bottomRight);

        this.numbers = new HashMap<>();
        numbers.put(new HashSet<>(Arrays.asList(topRight, bottomRight, bottom, bottomLeft, topLeft, top)), 0);
        numbers.put(new HashSet<>(Arrays.asList(topRight, bottomRight)), 1);
        numbers.put(new HashSet<>(Arrays.asList(top, topRight, middle, bottomLeft, bottom)), 2);
        numbers.put(new HashSet<>(Arrays.asList(top, topRight, middle, bottomRight, bottom)), 3);
        numbers.put(new HashSet<>(Arrays.asList(topLeft, middle, topRight, bottomRight)), 4);
        numbers.put(new HashSet<>(Arrays.asList(top, topLeft, middle, bottomRight, bottom)), 5);
        numbers.put(new HashSet<>(Arrays.asList(top, topLeft, middle, bottomLeft, bottomRight, bottom)), 6);
        numbers.put(new HashSet<>(Arrays.asList(top, topRight, bottomRight)), 7);
        numbers.put(new HashSet<>(Arrays.asList(top, topLeft, topRight, middle, bottomLeft, bottomRight, bottom)), 8);
        numbers.put(new HashSet<>(Arrays.asList(top, topLeft, topRight, middle, bottomRight, bottom)), 9);
    }

    private static char findBottom(char top, char topLeft, char topRight, char middle, char bottomLeft,
                                   char bottomRight){
        List<Character> candidates = new ArrayList<>();

        for(char c : new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'}){
            candidates.add(c);
        }

        candidates.remove(new Character(top));
        candidates.remove(new Character(topLeft));
        candidates.remove(new Character(topRight));
        candidates.remove(new Character(middle));
        candidates.remove(new Character(bottomLeft));
        candidates.remove(new Character(bottomRight));

        assert candidates.size() == 1;

        for(char c : candidates){
            return c;
        }

        throw new RuntimeException("Panic");
    }

    private static char findBottomRight(Set<Character> one, char topRight){
        for(char c : one){
            if(c != topRight){
                return c;
            }
        }

        throw new RuntimeException("Panic");
    }

    private static char findTopRight(ArrayList<Set<Character>> unknowns, char top, char topLeft, char middle,
                                     char bottomLeft){
        for(Set<Character> unknown : unknowns){
            if(unknown.size() == 6){
                if(unknown.contains(top) && unknown.contains(topLeft) && unknown.contains(middle) &&
                        unknown.contains(bottomLeft)){
                    for(char c : new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'}){
                        if(!unknown.contains(c)){
                            return c;
                        }
                    }
                }
            }
        }

        throw new RuntimeException("Panic");
    }

    private static char findTop(Set<Character> one, Set<Character> seven){
        for(char c : seven){
            if(!one.contains(c)){
                return c;
            }
        }

        throw new RuntimeException("Impossible layout!");
    }

    private static char[] findMiddleAndTopLeft(Set<Character> one, Set<Character> four){
        char[] middleAndTopLeft = new char[2];

        int i = 0;
        for(char c : four){
            if(!one.contains(c)){
                middleAndTopLeft[i] = c;
                i++;
            }
        }

        return middleAndTopLeft;
    }

    private static char findMiddle(ArrayList<Set<Character>> unknowns, char[] middleAndTopLeft){
        char candidateA = middleAndTopLeft[0];
        char candidateB = middleAndTopLeft[1];

        for(Set<Character> unknown : unknowns){
            if(unknown.size() == 5){
                if(!unknown.contains(candidateA)){
                    return candidateB;
                } else if(!unknown.contains(candidateB)){
                    return candidateA;
                }
            }
        }

        throw new RuntimeException("Panic");
    }

    private static char findBottomLeft(ArrayList<Set<Character>> unknowns, Set<Character> one, char topLeft, char top,
                                       char middle){
        char[] oneCharacters = new char[2];

        int i = 0;
        for(char c : one){
            oneCharacters[i] = c;
            i++;
        }

        for(Set<Character> unknown : unknowns){
            if(unknown.size() == 6){
                if(unknown.contains(topLeft) && unknown.contains(top) && unknown.contains(middle) &&
                        unknown.contains(oneCharacters[0]) && unknown.contains(oneCharacters[1])){
                    for(char c : new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'}){
                        if(!unknown.contains(c)){
                            return c;
                        }
                    }
                }
            }
        }

        throw new RuntimeException("Panic");
    }

    public int decodeNumber(String number){
        Set<Character> numberAsSet = number.chars().mapToObj(i -> (char) i).collect(Collectors.toSet());

        return this.numbers.get(numberAsSet);
    }
}
