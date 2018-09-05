package com.lelanor.projects.codebreakers.evaluators;

import com.lelanor.projects.codebreakers.Game;
import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.CombinationLists;
import com.lelanor.projects.codebreakers.datatypes.GameType;
import com.lelanor.projects.codebreakers.datatypes.Result;

public class Codex implements Evaluator {

    private GameType gameType;

    public Codex(GameType gameType) {
        setGameType(gameType);
        System.out.println("Evaluator = " + getGameType());

    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    @Override
    public Result analyse(Combination guess, Combination goal) {
        Result result;
        System.out.println("[CodeMaker] I am analysing your guess...");
        result = goal.CodexCompareTo(guess);
        result.printResult(Game.getGameType(), Game.getCombinationSize());
        return result;
    }

    @Override
    public Result analyse(Result result, Combination goal, CombinationLists lists) {

        int[] tempResult = new int[Game.getCombinationSize()];
        int range = Game.getRange() - 1;
        for (int i = 0; i < tempResult.length; i++) {
            if (result.getResult()[i] == 0) {
                tempResult[i] = goal.getCode()[i];
            } else if (result.getResult()[i] == 1) {
                tempResult[i] = goal.getCode()[i] / 2;
            } else if (result.getResult()[i] == -1) {
                if (range - goal.getCode()[i]==1){
                    tempResult[i] = goal.getCode()[i]+1;
                } else {
                    tempResult[i] = (((range - goal.getCode()[i]) / 2) + goal.getCode()[i]);
                }
            }
        }
        System.out.print("\n[CodeBreaker] My new guess is = ");
        for (int i = 0; i < tempResult.length; i++) {
            System.out.print(tempResult[i]);
        }
        System.out.println();
        return new Result(tempResult);
    }
}
