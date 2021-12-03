package Day3;

import Common.Day;

public class GenericSolution extends Day {

    protected static int binaryStringToInt(String s){
        int factor = 1;
        int total = 0;

        for(int i = s.length() - 1; i >= 0; i--){
            if(s.charAt(i) == '1'){
                total += factor;
            }
            factor *= 2;
        }

        return total;
    }

    @Override
    protected String getInputFile(){
        return "src/main/java/Day3/input.txt";
    }
}
