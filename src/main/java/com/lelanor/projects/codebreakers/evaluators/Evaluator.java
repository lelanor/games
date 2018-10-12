package com.lelanor.projects.codebreakers.evaluators;

import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.CombinationLists;
import com.lelanor.projects.codebreakers.datatypes.Result;

/**
 * This interface is the contract for the automatic evaluators.
 * It contains two methods for Combination and Result analysis, used respectively in attack or defense modes.
 */
public interface Evaluator {
    /**
     * this method analyse a Combination Guess vs a Combination Goal. It is used by the CodeMaker player
     * @param guess the Combination to analyse
     * @param goal the Combination to compare with
     * @return a Result expressed according to the GameType
     * @see com.lelanor.projects.codebreakers.datatypes.GameType
     */
    Result analyse(Combination guess, Combination goal);

    /**
     * this method analyse a Result comparing it to a Combination Goal and use the CombinationList to find the result within
     * @param result the Result to analyse
     * @param goal the Combination to compare with
     * @param lists the list of all possible Combination according to the range et the combination size
     * @return a Result that is the next code to be tried to guess
     */
    Result analyse(Result result, Combination goal, CombinationLists lists);
}
