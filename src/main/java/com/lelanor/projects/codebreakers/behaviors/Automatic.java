package com.lelanor.projects.codebreakers.behaviors;

import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.PlayerType;
import com.lelanor.projects.codebreakers.datatypes.Result;
import com.lelanor.projects.codebreakers.evaluators.Evaluator;

public class Automatic implements Behavior {

    private PlayerType playerType;

    public Automatic(PlayerType playerType) {
        setPlayerType(playerType);
        System.out.println("Behavior = AUTOMATIC      PlayerType = "+getPlayerType());
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    @Override
    public Combination generateCombination(int combinationSize, int range) {
        return new Combination(combinationSize, range);
    }

    @Override
    public Result analyseCombination(Combination guess, Evaluator evaluator) {
        return evaluator.analyse(guess);
    }

    @Override
    public Result analyseCombination(Result result, Evaluator evaluator) {
        return evaluator.analyse(result);
    }
}
