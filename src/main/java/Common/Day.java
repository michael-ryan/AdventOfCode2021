package Common;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class Day {

    protected abstract String getInputFile();

    protected Stream<String> parseAsStringStream(){
        Path inputFilePath = FileSystems.getDefault().getPath(getInputFile());
        try {
            return Files.lines(inputFilePath);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected String[] parseAsStringArray(){
        return parseAsStringStream().toArray(String[]::new);
    }

    protected int[] parseAsIntArray(){
        return parseAsIntStream().toArray();
    }

    protected IntStream parseAsIntStream(){
        return parseAsStringStream().mapToInt(Integer::parseInt);
    }

    protected int[][] parseAsIntMatrix(){
        String[] input = parseAsStringArray();

        int[][] matrix = new int[input.length][];

        for(int i = 0; i < input.length; i++){
            int[] line = new int[input[0].length()];
            for(int j = 0; j < input[0].length(); j++){
                line[j] = Integer.parseInt(String.valueOf(input[i].toCharArray()[j]));
            }
            matrix[i] = line;
        }

        return matrix;
    }

    public abstract Number run();
}
