package com.lelanor.projects.codebreakers.evaluators;

import com.lelanor.projects.codebreakers.Game;
import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.CombinationLists;
import com.lelanor.projects.codebreakers.datatypes.Result;

import java.util.ArrayList;
import java.util.List;
/**
 * This class manage the automatic evaluation of the MasterMind game according to the contract established with the Evaluator interface
 * @see com.lelanor.projects.codebreakers.evaluators.Evaluator
 */
public class MasterMind implements Evaluator {
    /**
     * this method analyse a Combination Guess vs a Combination Goal. It is used by the CodeMaker player
     *
     * @param guess the Combination to analyse
     * @param goal  the Combination to compare with
     * @return a Result expressed according to the GameType
     * @see com.lelanor.projects.codebreakers.datatypes.GameType
     */
    @Override
    public Result analyse(Combination guess, Combination goal) {
        Result result;
        System.out.println("[CodeMaker] I am analysing your guess...");
        result = goal.MasterMindCompareTo(guess);
        result.printResult(Game.getGameType(), Game.getCombinationSize());
        return result;
    }

    /**
     * this method analyse a Result comparing it to a Combination Goal and use the CombinationList to find the result within
     *
     * @param result the Result to analyse
     * @param goal   the Combination to compare with
     * @param lists  the list of all possible Combination according to the range et the combination size
     * @return a Result that is the next code to be tried to guess
     */
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
        return new Result(lists.getCombinationList().get(0).getCode());
    }
}
