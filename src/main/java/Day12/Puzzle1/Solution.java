package Day12.Puzzle1;

import Day12.GenericSolution;

import java.util.HashSet;
import java.util.Set;

public class Solution extends GenericSolution {
    @Override
    protected boolean routeIsValid(Route route){
        Set<Cave> seenSmallCaves = new HashSet<>();

        for(Cave cave : route){
            if(cave.isBig()){
                continue;
            }

            if(seenSmallCaves.contains(cave)){
                return false;
            } else {
                seenSmallCaves.add(cave);
            }
        }

        return true;
    }
}
