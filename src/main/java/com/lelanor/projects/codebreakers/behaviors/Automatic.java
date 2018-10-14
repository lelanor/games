package com.lelanor.projects.codebreakers.behaviors;

import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.CombinationLists;
import com.lelanor.projects.codebreakers.datatypes.PlayerType;
import com.lelanor.projects.codebreakers.datatypes.Result;
import com.lelanor.projects.codebreakers.evaluators.Evaluator;

public class Automatic implements Behavior {

    private PlayerType playerType;
    private CombinationLists combinationLists;

    public Automatic(PlayerType playerType) {
        setPlayerType(playerType);
        System.out.println("Behavior = AUTOMATIC      PlayerType = " + getPlayerType());
            setCombinationLists(new CombinationLists());
    }

    @Override
    public Combination generateCombination(int combinationSize, int range) {
        return new Combination(combinationSize, range);
    }

    @Override
    public Result analyseCombination(Combination guess, Combination goal, Evaluator evaluator) {
        return evaluator.analyse(guess, goal);
    }

    @Override
    public Result analyseCombination(Result result, Evaluator evaluator, Combination combination) {
        return evaluator.analyse(result, combination, getCombinationLists());
    }


    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public CombinationLists getCombinationLists() {
        return combinationLists;
    }

    public void setCombinationLists(CombinationLists combinationLists) {
        this.combinationLists = combinationLists;
    }
}
