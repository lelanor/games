package com.lelanor.projects.codebreakers.evaluators;

import com.lelanor.projects.codebreakers.Game;
import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.CombinationLists;
import com.lelanor.projects.codebreakers.datatypes.GameType;
import com.lelanor.projects.codebreakers.datatypes.Result;

import java.util.ArrayList;
import java.util.List;

public class MasterMind implements Evaluator {

    private GameType gameType;

    public MasterMind(GameType gameType) {
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
        result = goal.MasterMindCompareTo(guess);
        result.printResult(Game.getGameType(), Game.getCombinationSize());
        return result;
    }

    @Override
    public Result analyse(Result result, Combination goal, CombinationLists lists) {
        List<Combination> newCombinationList = new ArrayList<>();
        Result partialResult;

        for (int i = 0; i < lists.getCombinationList().size(); i++) {
            partialResult = goal.MasterMindCompareTo(lists.getCombinationList().get(i));
            if (result.compareWith(partialResult)) {
                newCombinationList.add(lists.getCombinationList().get(i));
            }
        }
        lists.setCombinationList(newCombinationList);
        System.out.print("\n[CodeBreaker] My new guess is = ");
        lists.getCombinationList().get(0).printCombination();
        return new Result(lists.getCombinationList().get(0).getCode());
    }
}
