package com.lelanor.projects.codebreakers.evaluators;

import com.lelanor.projects.codebreakers.Game;
import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.CombinationLists;
import com.lelanor.projects.codebreakers.datatypes.Result;

/**
 * This class manage the automatic evaluation of the Codex game according to the contract established with the Evaluator interface
 *
 * @see com.lelanor.projects.codebreakers.evaluators.Evaluator
 */
public class Codex implements Evaluator {
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
        result = goal.CodexCompareTo(guess);
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

        int[] tempResult = new int[Game.getCombinationSize()];
        int range = Game.getRange() - 1;
        for (int i = 0; i < tempResult.length; i++) {
            if (result.getResult()[i] == 0) {
                tempResult[i] = goal.getCode()[i];
            } else if (result.getResult()[i] == 1) {
                tempResult[i] = goal.getCode()[i] / 2;
            } else if (result.getResult()[i] == -1) {
                if (range - goal.getCode()[i] == 1) {
                    tempResult[i] = goal.getCode()[i] + 1;
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
