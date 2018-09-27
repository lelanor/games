package com.lelanor.projects.codebreakers.players;

import com.lelanor.projects.codebreakers.behaviors.Behavior;
import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.Result;
import com.lelanor.projects.codebreakers.evaluators.Evaluator;

public  class Player {

    private Behavior behavior;
    private Evaluator evaluator;
    private Combination defenseCode;
    private Combination attackCode;
    private Result result;

    public Player(Behavior behavior, Evaluator evaluator) {
        setBehavior(behavior);
        setEvaluator(evaluator);
    }

    public void printDefenseCombination() {
        getDefenseCode().printCombination();
    }

    public void printAttackCombination() {
        getAttackCode().printCombination();
    }

    public void generateAttackCode(int combinationSize, int range) {
        setAttackCode(getBehavior().generateCombination(combinationSize, range));
    }

    public void generateDefenseCode(int combinationSize, int range) {
        setDefenseCode(getBehavior().generateCombination(combinationSize, range));
    }

    public Result analyseCombination(Combination code) {
        return this.getBehavior().analyseCombination(code, this.getDefenseCode(), getEvaluator());
    }

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
