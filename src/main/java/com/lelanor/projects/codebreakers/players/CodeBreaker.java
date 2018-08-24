package com.lelanor.projects.codebreakers.players;

import com.lelanor.projects.codebreakers.behaviors.Behavior;
import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.Result;
import com.lelanor.projects.codebreakers.evaluators.Evaluator;

public class CodeBreaker extends Player {
    public CodeBreaker(Behavior behavior, Evaluator evaluator) {
        super(behavior, evaluator);
        System.out.println("I am a Codebreaker, behavior "+getBehavior().toString()+" and evaluator "+getEvaluator().toString());
    }

    @Override
    public Result analyseCombination(Combination code) {
        return null;
    }

    @Override
    public Result analyseCombination(Result result) {
        return getBehavior().analyseCombination(result, getEvaluator());
    }
}
