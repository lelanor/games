package com.lelanor.projects.codebreakers.datatypes;

import org.apache.log4j.Logger;

public class Result {

    Logger logger = Logger.getLogger(Result.class);
    private int[] result;

    public Result(int[] result){
        setResult(result);
    }

    public Result(String stringResult){
        int[] result = new int[stringResult.length()];
        char[] charTemp = stringResult.toCharArray();
        for (int i=0; i<charTemp.length; i++){
            result[i] = Character.getNumericValue(charTemp[i]);
        }
        setResult(result);
    }

    public void printResult(GameType gameType, int combinationSize){
        if (gameType == GameType.MASTERMIND) {
            System.out.println("    You have " + result[0] + " black tokens (good guess in right place)");
            System.out.println("    You have " + result[1] + " white tokens (good guess in wrong place)");
        } else if (gameType == GameType.CODEX){
            System.out.print("  your score is : ");
            for (int i=0; i<combinationSize;i++) {
                if(this.getResult()[i]==0){
                    System.out.print(" * exact *");
                } else if (this.getResult()[i]==-1){
                    System.out.print(" * too big * ");
                } else if (this.getResult()[i]==1){
                    System.out.print(" * too low * ");
                }else {
                    logger.error("Impossible to print the result");
                    System.out.println("An error has occurred");
                }
            }
            System.out.println();
        }
    }


    public boolean compareWith(Result testResult) {
        return (this.getResult()[0] == testResult.getResult()[0]) && (this.getResult()[1] == testResult.getResult()[1]);
    }

    public boolean isWinner(GameType gameType, int combinationSize) {
        if (gameType == GameType.MASTERMIND) {
            return this.getResult()[0] == combinationSize;

        } else if (gameType == GameType.CODEX){
            int check = 0;
            for (int i=0; i<result.length; i++){
                if (result[i]==0){
                    check +=1;
                }
            }
            return (check == this.result.length);
        } else {
            logger.error("Impossible to define the winner");
            System.out.println("an error has occurred");
            return false;
        }
    }

    public int[] getResult() {
        return result;
    }

    public void setResult(int[] result) {
        this.result = result;
    }
}
