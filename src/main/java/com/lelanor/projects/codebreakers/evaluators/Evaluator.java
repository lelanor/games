package com.lelanor.projects.codebreakers.evaluators;

import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.CombinationLists;
import com.lelanor.projects.codebreakers.datatypes.Result;

public interface Evaluator {
    Result analyse(Combination guess, Combination goal);

    Result analyse(Result result, Combination goal, CombinationLists lists);
}
