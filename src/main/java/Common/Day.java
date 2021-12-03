package Common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class Day {
    protected abstract Path getInputFile();

    protected Stream<String> parseAsStringStream(){
        Path inputFilePath = getInputFile();
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
}
