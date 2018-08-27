package com.lelanor.projects.codebreakers.behaviors;

import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.CombinationLists;
import com.lelanor.projects.codebreakers.datatypes.PlayerType;
import com.lelanor.projects.codebreakers.datatypes.Result;
import com.lelanor.projects.codebreakers.evaluators.Evaluator;

public class Automatic implements Behavior {

    private PlayerType playerType;
    private CombinationLists pippoCombinationLists;

    public Automatic(PlayerType playerType) {
        setPlayerType(playerType);
        System.out.println("Behavior = AUTOMATIC      PlayerType = " + getPlayerType());
        if (getPlayerType() == PlayerType.CODEBREAKER) {
            setPippoCombinationLists(new CombinationLists());
        }
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
    public Result analyseCombination(Result result, Evaluator evaluator, Combination goal) {
        return evaluator.analyse(result, goal, getPippoCombinationLists());
    }


    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public CombinationLists getPippoCombinationLists() {
        return pippoCombinationLists;
    }

    public void setPippoCombinationLists(CombinationLists pippoCombinationLists) {
        this.pippoCombinationLists = pippoCombinationLists;
    }
}
