package com.lelanor.projects.codebreakers.players;

import com.lelanor.projects.codebreakers.behaviors.Behavior;
import com.lelanor.projects.codebreakers.evaluators.Evaluator;

public class CodeBreaker extends Player {
    public CodeBreaker(Behavior behavior, Evaluator evaluator) {
        super(behavior, evaluator);
        System.out.println("I am a Codebreaker, behavior "+getBehavior().toString()+" and evaluator "+getEvaluator().toString());
    }
}
