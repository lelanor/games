package com.lelanor.projects.codebreakers.players;

import com.lelanor.projects.codebreakers.behaviors.Behavior;
import com.lelanor.projects.codebreakers.evaluators.Evaluator;

public class CodeMaker extends Player {
    public CodeMaker(Behavior behavior, Evaluator evaluator) {
        super(behavior, evaluator);
        System.out.println("I am a Codemaker, behavior "+getBehavior().toString()+" and evaluator "+getEvaluator().toString());
    }
}
