package Day6;

import Common.Day;

import java.util.Arrays;

public abstract class GenericSolution extends Day {

    @Override
    protected String getInputFile(){
        return "src/main/java/Day6/input.txt";
    }

    protected void run(int daysToSimulate){
        int[] input = Arrays.stream(parseAsStringArray()[0].split(",")).mapToInt(Integer::parseInt).toArray();

        long[] initialState = new long[9];

        for(int value : input){
            initialState[value] += 1L;
        }

        Population population = new Population(initialState);

        population.simulate(daysToSimulate);

        System.out.println(population.getNumberOfFish());
    }
}
