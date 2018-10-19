package com.lelanor.projects.codebreakers.behaviors;

import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.PlayerType;
import com.lelanor.projects.codebreakers.datatypes.Result;
import com.lelanor.projects.codebreakers.evaluators.Evaluator;

public interface Behavior {
    Combination generateCombination(int combinationSize, int range);

    Result analyseCombination(Combination guess, Combination goal, Evaluator evaluator);

    Result analyseCombination(Result result, Evaluator evaluator, Combination goal);

    PlayerType getPlayerType();
}
