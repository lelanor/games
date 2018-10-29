package com.lelanor.projects.codebreakers.datatypes;

import org.apache.log4j.Logger;

/**
 * This class model the result of the analysis of à combination
 */
public class Result {
    /**
     * the logger of the class
     */
    Logger logger = Logger.getLogger(Result.class);
    /**
     * the value of the Result
     */
    private int[] result;

    /**
     * Constructs a Result from an int[]
     *
     * @param result the result to incapsulate
     */
    public Result(int[] result) {
        setResult(result);
    }

    /**
     * Construct a Result from a String
     *
     * @param stringResult the String to incapsulate
     */
    public Result(String stringResult) {
        int[] result = new int[stringResult.length()];
        char[] charTemp = stringResult.toCharArray();
        for (int i = 0; i < charTemp.length; i++) {
            result[i] = Character.getNumericValue(charTemp[i]);
        }
        setResult(result);
    }

    /**
     * Prints the result according to GameType
     *
     * @param gameType        the game currently played
     * @param combinationSize the size of the combination to print
     */
    public void printResult(GameType gameType, int combinationSize) {
        if (gameType == GameType.MASTERMIND) {
            System.out.println("    You have " + result[0] + " black tokens (good guess in right place)");
            System.out.println("    You have " + result[1] + " white tokens (good guess in wrong place)");
        } else if (gameType == GameType.CODEX) {
            System.out.print("  your guess is : ");
            for (int i = 0; i < combinationSize; i++) {
                if (this.getResult()[i] == 0) {
                    System.out.print(" * exact *");
                } else if (this.getResult()[i] == -1) {
                    System.out.print(" * too low * ");
                } else if (this.getResult()[i] == 1) {
                    System.out.print(" * too big * ");
                } else {
                    logger.error("Impossible to print the result");
                    System.out.println("An error has occurred");
                }
            }
            System.out.println();
        }
    }

    /**
     * Compare two results
     *
     * @param testResult the result to compare with
     * @return true if the results are equals
     */
    public boolean compareWith(Result testResult) {
        return (this.getResult()[0] == testResult.getResult()[0]) && (this.getResult()[1] == testResult.getResult()[1]);
    }

    /**
     * Checks if the result is a winning result
     *
     * @param gameType        the game currently played
     * @param combinationSize the size of the combination
     * @return true if is a winning result
     */
    public boolean isWinner(GameType gameType, int combinationSize) {
        if (gameType == GameType.MASTERMIND) {
            return this.getResult()[0] == combinationSize;

        } else if (gameType == GameType.CODEX) {
            int check = 0;
            for (int i = 0; i < result.length; i++) {
                if (result[i] == 0) {
                    check += 1;
                }
            }
            return (check == this.result.length);
        } else {
            logger.error("Impossible to define the winner");
            System.out.println("an error has occurred");
            return false;
        }
    }

    /**
     * getter
     *
     * @return the value of result
     */
    public int[] getResult() {
        return result;
    }

    /**
     * setter
     *
     * @param result set the result attribute
     */
    public void setResult(int[] result) {
        this.result = result;
    }
}
