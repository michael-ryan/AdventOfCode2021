import java.time.Duration;
import java.time.Instant;

public class Main {
    /*
    Increase this int
    Add expected answers
    Uncomment around line 100
     */
    private static final int PUZZLES_DONE = 11;
    private int day = 1;
    private int puzzle = 1;
    private Instant now;

    private void go(){
        Number[][] answers = generateAnswerArray();

        if(answersCorrect(answers)){
            System.out.println("All tests passed!");
            for(int i = 0; i < PUZZLES_DONE; i++){
                printAnswers(i + 1, answers[i][0], answers[i][1]);
            }
        }
    }

    public static void main(String[] args){
        new Main().go();
    }

    private static boolean answersCorrect(Number[][] actual){
        Number[][] expected = new Number[][]{
                {1532, 1571},
                {1804520, 1971095320},
                {2250414, 6085575},
                {12796, 18063},
                {8111, 22088},
                {343441L, 1569108373832L},
                {349769, 99540554},
                {554, 990964},
                {500, 970200},
                {316851, 2182912364L},
                {1601, 368},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
        };

        boolean allCorrect = true;

        for(int day = 0; day < 25; day++){
            for(int puzzle = 0; puzzle < 2; puzzle++){
                Number expectedAnswer = expected[day][puzzle];
                Number actualAnswer = actual[day][puzzle];

                if(expectedAnswer == null){
                    continue;
                }

                if(!expectedAnswer.equals(actualAnswer)){
                    System.out.println("Incorrect answer for day " + (day + 1) + ", puzzle " + (puzzle + 1)
                            + ". Expected '" + expectedAnswer + "', got '" + actualAnswer + "'.");
                    allCorrect = false;
                }
            }
        }

        return allCorrect;
    }

    private static void printAnswers(int day, Number answerOne, Number answerTwo){
        System.out.println("Day " + day + ":");
        System.out.println(" - Puzzle one: " + answerOne);
        System.out.println(" - Puzzle two: " + answerTwo);
    }

    private Number[][] generateAnswerArray(){
        Number[][] answers = new Number[25][];
        for(int i = 0; i < answers.length; i++){
            answers[i] = new Number[2];
        }

        now = Instant.now();
        answers[0][0] = new Day1.Puzzle1.Solution().run(); reportTime();
        answers[0][1] = new Day1.Puzzle2.Solution().run(); reportTime();
        answers[1][0] = new Day2.Puzzle1.Solution().run(); reportTime();
        answers[1][1] = new Day2.Puzzle2.Solution().run(); reportTime();
        answers[2][0] = new Day3.Puzzle1.Solution().run(); reportTime();
        answers[2][1] = new Day3.Puzzle2.Solution().run(); reportTime();
        answers[3][0] = new Day4.Puzzle1.Solution().run(); reportTime();
        answers[3][1] = new Day4.Puzzle2.Solution().run(); reportTime();
        answers[4][0] = new Day5.Puzzle1.Solution().run(); reportTime();
        answers[4][1] = new Day5.Puzzle2.Solution().run(); reportTime();
        answers[5][0] = new Day6.Puzzle1.Solution().run(); reportTime();
        answers[5][1] = new Day6.Puzzle2.Solution().run(); reportTime();
        answers[6][0] = new Day7.Puzzle1.Solution().run(); reportTime();
        answers[6][1] = new Day7.Puzzle2.Solution().run(); reportTime();
        answers[7][0] = new Day8.Puzzle1.Solution().run(); reportTime();
        answers[7][1] = new Day8.Puzzle2.Solution().run(); reportTime();
        answers[8][0] = new Day9.Puzzle1.Solution().run(); reportTime();
        answers[8][1] = new Day9.Puzzle2.Solution().run(); reportTime();
        answers[9][0] = new Day10.Puzzle1.Solution().run(); reportTime();
        answers[9][1] = new Day10.Puzzle2.Solution().run(); reportTime();
        answers[10][0] = new Day11.Puzzle1.Solution().run(); reportTime();
        answers[10][1] = new Day11.Puzzle2.Solution().run(); reportTime();
/*        answers[11][0] = new Day12.Puzzle1.Solution().run(); reportTime();
        answers[11][1] = new Day12.Puzzle2.Solution().run(); reportTime();
        answers[12][0] = new Day13.Puzzle1.Solution().run(); reportTime();
        answers[12][1] = new Day13.Puzzle2.Solution().run(); reportTime();
        answers[13][0] = new Day14.Puzzle1.Solution().run(); reportTime();
        answers[13][1] = new Day14.Puzzle2.Solution().run(); reportTime();
        answers[14][0] = new Day15.Puzzle1.Solution().run(); reportTime();
        answers[14][1] = new Day15.Puzzle2.Solution().run(); reportTime();
        answers[15][0] = new Day16.Puzzle1.Solution().run(); reportTime();
        answers[15][1] = new Day16.Puzzle2.Solution().run(); reportTime();
        answers[16][0] = new Day17.Puzzle1.Solution().run(); reportTime();
        answers[16][1] = new Day17.Puzzle2.Solution().run(); reportTime();
        answers[17][0] = new Day18.Puzzle1.Solution().run(); reportTime();
        answers[17][1] = new Day18.Puzzle2.Solution().run(); reportTime();
        answers[18][0] = new Day19.Puzzle1.Solution().run(); reportTime();
        answers[18][1] = new Day19.Puzzle2.Solution().run(); reportTime();
        answers[19][0] = new Day20.Puzzle1.Solution().run(); reportTime();
        answers[19][1] = new Day20.Puzzle2.Solution().run(); reportTime();
        answers[20][0] = new Day21.Puzzle1.Solution().run(); reportTime();
        answers[20][1] = new Day21.Puzzle2.Solution().run(); reportTime();
        answers[21][0] = new Day22.Puzzle1.Solution().run(); reportTime();
        answers[21][1] = new Day22.Puzzle2.Solution().run(); reportTime();
        answers[22][0] = new Day23.Puzzle1.Solution().run(); reportTime();
        answers[22][1] = new Day23.Puzzle2.Solution().run(); reportTime();
        answers[23][0] = new Day24.Puzzle1.Solution().run(); reportTime();
        answers[23][1] = new Day24.Puzzle2.Solution().run(); reportTime();
        answers[24][0] = new Day25.Puzzle1.Solution().run(); reportTime();
        answers[24][1] = new Day25.Puzzle2.Solution().run(); reportTime();
*/
        return answers;
    }

    private void reportTime(){
        System.out.println("Day " + day + ", puzzle " + puzzle + " took "
                + Duration.between(this.now, Instant.now()).toMillis() + "ms.");

        if(this.puzzle == 2){
            this.day++;
            this.puzzle = 1;
        } else {
            this.puzzle++;
        }

        this.now = Instant.now();
    }
}
