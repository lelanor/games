package com.lelanor.projects.codebreakers.players;

import com.lelanor.projects.codebreakers.behaviors.Behavior;
import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.evaluators.Evaluator;

public class Player {

    private Behavior behavior;
    private Evaluator evaluator;
    private Combination code;

    public Player(Behavior behavior, Evaluator evaluator){
        setBehavior(behavior);
        setEvaluator(evaluator);
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

    private Combination getCode() {
        return code;
    }

    public void setCode(Combination code) {
        this.code = code;
    }

    public void printCombination() {
        getCode().printCombination();
    }

    public void generateCode(int combinationSize, int range) {
        setCode(getBehavior().generateCombination(combinationSize, range));
    }
}
