package com.lelanor.projects.codebreakers.players;

import com.lelanor.projects.codebreakers.behaviors.Behavior;
import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.Result;
import com.lelanor.projects.codebreakers.evaluators.Evaluator;

public  class Player {

    private Behavior behavior;
    private Evaluator evaluator;
    private Combination code;

    public Player(Behavior behavior, Evaluator evaluator) {
        setBehavior(behavior);
        setEvaluator(evaluator);
    }

    public void printCombination() {
        getCode().printCombination();
    }

    public void generateCode(int combinationSize, int range) {
        setCode(getBehavior().generateCombination(combinationSize, range));
    }

    public Result analyseCombination(Combination code) {
        return this.getBehavior().analyseCombination(code, this.getCode(), getEvaluator());
    }

    public Result analyseResult(Result result) {
        return getBehavior().analyseCombination(result, getEvaluator(), getCode());
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

    public Combination getCode() {
        return code;
    }

    public void setCode(Combination code) {
        this.code = code;
    }
}
