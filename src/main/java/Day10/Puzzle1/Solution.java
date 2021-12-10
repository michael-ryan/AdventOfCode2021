package Day10.Puzzle1;

import Day10.GenericSolution;

import java.util.*;

public class Solution extends GenericSolution {
    public Number run(){
        return parseAsStringStream()
                .map(this::isCorrupted)
                .filter(Objects::nonNull)
                .mapToInt(type -> type.corruptWeight)
                .sum();
    }
}
