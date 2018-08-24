package com.lelanor.projects.codebreakers.players;

import com.lelanor.projects.codebreakers.behaviors.Behavior;
import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.Result;
import com.lelanor.projects.codebreakers.evaluators.Evaluator;

public class CodeMaker extends Player {

    public CodeMaker(Behavior behavior, Evaluator evaluator) {
        super(behavior, evaluator);
        System.out.println("I am a Codemaker, behavior " + getBehavior().toString() + " and evaluator " + getEvaluator().toString());
    }

    public Result analyseCombination(Combination guess) {
        return this.getBehavior().analyseCombination(guess, this.getCode(), getEvaluator());
    }
}
