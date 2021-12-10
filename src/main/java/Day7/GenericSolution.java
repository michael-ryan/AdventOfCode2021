package Day7;

import Common.Day;

import java.util.Arrays;

public abstract class GenericSolution extends Day {

    public Number run(){
        int[] crabStartingPositions =
                Arrays.stream(this.parseAsStringArray()[0].split(",")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(crabStartingPositions);

        int firstPosition = crabStartingPositions[0];
        int lastPosition = crabStartingPositions[crabStartingPositions.length - 1];

        int minCost = computeFuelUseForAllCrabs(crabStartingPositions, firstPosition);

        for(int i = 1; i <= lastPosition - firstPosition; i++){
            int fuelUsed = computeFuelUseForAllCrabs(crabStartingPositions, i + firstPosition);
            if(fuelUsed < minCost){
                minCost = fuelUsed;
            }
        }

        return minCost;
    }

    protected abstract int computeFuelUseForCrab(int start, int end);

    @Override
    protected String getInputFile(){
        return "src/main/java/Day7/input.txt";
    }

    private int computeFuelUseForAllCrabs(int[] crabs, int end){
        int fuelUsed = 0;

        for(int crab : crabs){
            fuelUsed += computeFuelUseForCrab(crab, end);
        }

        return fuelUsed;
    }
}
