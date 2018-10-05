package com.lelanor.projects.codebreakers.players;

import com.lelanor.projects.codebreakers.behaviors.Behavior;
import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.Result;
import com.lelanor.projects.codebreakers.evaluators.Evaluator;

/** The Player class describe players and manage their actions
 *
 * @author Enrico Lo Faro
 * @version 1.0
 */

public  class Player {
    /**
     * contains the player behavior
     */
    private Behavior behavior;
    /**
     * contains the evaluator according to the game type
     */
    private Evaluator evaluator;
    /**
     * contains the player code to guess
     */
    private Combination defenseCode;
    /**
     * contains the player actual try
     */
    private Combination attackCode;
    /**
     * contains the result of the analyse when in defensive phase
     */
    private Result result;

    /**
     * instantiate the player and set the behavior and evaluator as needed
     * @param behavior contains the behavior according to the PlayerType
     * @param evaluator contains the evaluator according to the GameType
     * @see com.lelanor.projects.codebreakers.datatypes.PlayerType
     * @see com.lelanor.projects.codebreakers.datatypes.GameType
     */
    public Player(Behavior behavior, Evaluator evaluator) {
        setBehavior(behavior);
        setEvaluator(evaluator);
    }

    /**
     * prints the code to guess
     */
    public void printDefenseCombination() {
        getDefenseCode().printCombination();
    }

    /**
     * prints the attempting combination
     */
    public void printAttackCombination() {
        getAttackCode().printCombination();
    }

    /**
     * generate an attack combination according to the range, the size needed and the behavior
     * @param combinationSize the number of digits allowed for the combination
     * @param range the range within the code must be
     * @see Behavior
     */
    public void generateAttackCode(int combinationSize, int range) {
        setAttackCode(getBehavior().generateCombination(combinationSize, range));
    }
    /**
     * generate a defense combination according to the range, the size needed and the behavior
     * @param combinationSize the number of digits allowed for the combination
     * @param range the range within the code must be
     * @see Behavior
     */
    public void generateDefenseCode(int combinationSize, int range) {
        setDefenseCode(getBehavior().generateCombination(combinationSize, range));
    }

    /**
     * analyse the combination according to the behavior of the player
     * @param code the combination to analyse
     * @return the result according to the GameType
     * @see Behavior
     * @see com.lelanor.projects.codebreakers.datatypes.GameType
     */
    public Result analyseCombination(Combination code) {
        return this.getBehavior().analyseCombination(code, this.getDefenseCode(), getEvaluator());
    }
    /**
     * analyse the result according to the behavior of the player in order to create a new attempt
     * @param result the result to analyse
     * @return the result according to the GameType
     * @see Behavior
     * @see com.lelanor.projects.codebreakers.datatypes.GameType
     */
    public Result analyseResult(Result result) {
        return getBehavior().analyseCombination(result, getEvaluator(), getAttackCode());
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    public Evaluator getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    public Combination getDefenseCode() {
        return defenseCode;
    }

    public void setDefenseCode(Combination defenseCode) {
        this.defenseCode = defenseCode;
    }

    public Combination getAttackCode() {
        return attackCode;
    }

    public void setAttackCode(Combination attackCode) {
        this.attackCode = attackCode;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }
}
