package Day12.Puzzle2;

import Day12.GenericSolution;

import java.util.HashSet;
import java.util.Set;

public class Solution extends GenericSolution {
    @Override
    protected boolean routeIsValid(Route route){

        Set<Cave> seenSmallCaves = new HashSet<>();

        Cave doubleSmallCave = null;
        for(Cave cave : route){
            if(cave.isBig()){
                continue;
            }

            if(seenSmallCaves.contains(cave)){
                if(doubleSmallCave == null && !cave.getName().equals("start") && !cave.getName().equals("end")){
                    doubleSmallCave = cave;
                    continue;
                }
                return false;
            } else {
                seenSmallCaves.add(cave);
            }
        }

        return true;
    }
}
