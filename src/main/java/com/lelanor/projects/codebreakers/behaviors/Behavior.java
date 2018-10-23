package com.lelanor.projects.codebreakers.behaviors;

import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.PlayerType;
import com.lelanor.projects.codebreakers.datatypes.Result;
import com.lelanor.projects.codebreakers.evaluators.Evaluator;

/**
 * the interface needed to separate players from behaviors
 */
public interface Behavior {
    /**
     * generate a combination according to demanded size and range
     *
     * @param combinationSize the size of the combination to create
     * @param range           the range of the combination to create
     * @return a Combination
     */
    Combination generateCombination(int combinationSize, int range);

    /**
     * defensive analysis of a Combination
     *
     * @param guess     the combination to analyse
     * @param goal      the combination to compare to
     * @param evaluator the evaluator to use for the analysis
     * @return a Result according to the game played
     */
    Result analyseCombination(Combination guess, Combination goal, Evaluator evaluator);

    /**
     * offensive analysis of a Result
     *
     * @param result    the result to compare to
     * @param evaluator the evaluator to use for the analysis
     * @param goal      the combination to compare to
     * @return a Combination according to the game played
     */
    Result analyseCombination(Result result, Evaluator evaluator, Combination goal);

    /**
     * getter
     *
     * @return the type of the player
     */
    PlayerType getPlayerType();
}
