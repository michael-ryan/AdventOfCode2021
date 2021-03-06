package Day8;

import Common.Day;

public abstract class GenericSolution extends Day {
    protected int answer = 0;

    public Number run(){
        parseAsStringStream().forEach(line -> {
            String[] splitLine = line.split(" \\| ");
            String[] keys = splitLine[0].split(" ");
            String[] digits = splitLine[1].split(" ");
            this.handleLine(keys, digits);
        });

        return this.answer;
    }

    protected abstract void handleLine(String[] keys, String[] digits);

    @Override
    protected String getInputFile(){
        return "src/main/java/Day8/input.txt";
    }
}
