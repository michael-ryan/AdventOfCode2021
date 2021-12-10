package Day6;

import Common.Day;

import java.util.Arrays;

public abstract class GenericSolution extends Day {
    protected int daysToRun;

    @Override
    protected String getInputFile(){
        return "src/main/java/Day6/input.txt";
    }

    public Number run(){
        int[] input = Arrays.stream(parseAsStringArray()[0].split(",")).mapToInt(Integer::parseInt).toArray();

        long[] initialState = new long[9];

        for(int value : input){
            initialState[value] += 1L;
        }

        Population population = new Population(initialState);

        population.simulate(daysToRun);

        return population.getNumberOfFish();
    }
}
