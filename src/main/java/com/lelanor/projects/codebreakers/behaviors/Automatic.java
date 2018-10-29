package com.lelanor.projects.codebreakers.behaviors;

import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.CombinationLists;
import com.lelanor.projects.codebreakers.datatypes.PlayerType;
import com.lelanor.projects.codebreakers.datatypes.Result;
import com.lelanor.projects.codebreakers.evaluators.Evaluator;

/**
 * This class represents the Computer behavior
 */
public class Automatic implements Behavior {
    /**
     * the type of the player using this behavior
     */
    private PlayerType playerType;
    /**
     * the list of all possible combinations according to size and range
     */
    private CombinationLists combinationLists;

    /**
     * @param playerType the type of the player
     */
    public Automatic(PlayerType playerType) {
        setPlayerType(playerType);
        setCombinationLists(new CombinationLists());
    }

    /**
     * generatze a combination according to demanded size and the range
     *
     * @param combinationSize the size of the combination to create
     * @param range           the range of the combination to create
     * @return a Combination
     */
    @Override
    public Combination generateCombination(int combinationSize, int range) {
        return new Combination(combinationSize, range);
    }

    /**
     * defender way of analysing of a combination
     *
     * @param guess     the combination to analyse
     * @param goal      the combination to compare to
     * @param evaluator the evaluator to use for the analysis
     * @return a Result according to the type of game actually played
     */
    @Override
    public Result analyseCombination(Combination guess, Combination goal, Evaluator evaluator) {
        return evaluator.analyse(guess, goal);
    }

    /**
     * attacker way of analysing a Result
     *
     * @param result      the result to compare to
     * @param evaluator   the evaluator to use for the analysis
     * @param combination the combination to compare to
     * @return a Result according to the game actually played
     */
    @Override
    public Result analyseCombination(Result result, Evaluator evaluator, Combination combination) {
        return evaluator.analyse(result, combination, getCombinationLists());
    }

    /**
     * getter
     *
     * @return the type of the player
     */
    @Override
    public PlayerType getPlayerType() {
        return playerType;
    }

    /**
     * setter
     *
     * @param playerType the type of the player to set
     */
    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    /**
     * getter
     *
     * @return the actual list of all possible combinations
     */
    public CombinationLists getCombinationLists() {
        return combinationLists;
    }

    /**
     * actualises the list of all possible combinations
     *
     * @param combinationLists the actual list of all possible combinations
     */
    public void setCombinationLists(CombinationLists combinationLists) {
        this.combinationLists = combinationLists;
    }
}
