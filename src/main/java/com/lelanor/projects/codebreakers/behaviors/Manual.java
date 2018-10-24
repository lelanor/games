package com.lelanor.projects.codebreakers.behaviors;

import com.lelanor.projects.codebreakers.Game;
import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.PlayerType;
import com.lelanor.projects.codebreakers.datatypes.Result;
import com.lelanor.projects.codebreakers.evaluators.Evaluator;
import com.lelanor.projects.codebreakers.userinterface.Console;

/**
 * This class represents the manual(human) behavior
 */
public class Manual implements Behavior {

    /**
     * the rôle of the player
     */
    private PlayerType playerType;

    /**
     * construct a Behavior according to the type of the player
     * @param playerType the type of the player
     */
    public Manual(PlayerType playerType) {
        setPlayerType(playerType);
    }

    /**
     * getter
     * @return the type of the player connected to this behavior
     */
    public PlayerType getPlayerType() {
        return playerType;
    }

    /**
     * setter
     * @param playerType set the type of player that is connected to the behavior
     */
    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    /**
     * generate a combination of a determined range and size
     * @param combinationSize the size of the combination
     * @param range the range of the combination
     * @return a Combination
     */
    @Override
    public Combination generateCombination(int combinationSize, int range) {
        Console console = new Console();
        return new Combination(console.askUserCombination());
    }

    /**
     * Ask the user to analyse a combination in relation with a guess
     * @param guess the combination to analyse
     * @param goal the related combination
     * @param evaluator the evaluator to use for the analyse
     * @return a result according to the game played
     */
    @Override
    public Result analyseCombination(Combination guess, Combination goal, Evaluator evaluator) {
        Result result;
        Console console = new Console();
        result = new Result(console.makerAnalyse(Game.getGameType()));
        return result;
    }

    /**
     * Ask the user to analyse a result in relation with the previous attempt, in order to decide the next one
     * @param pResult the reference result
     * @param evaluator the evaluator to use to compare
     * @param goal the combination to compare with
     * @return the new combination to play as a Result
     */
    @Override
    public Result analyseCombination(Result pResult, Evaluator evaluator, Combination goal) {
        Console console = new Console();
        Result result = new Result(console.askUserCombination());
        return result;
    }
}