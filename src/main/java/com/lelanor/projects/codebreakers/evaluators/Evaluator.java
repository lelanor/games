package com.lelanor.projects.codebreakers.evaluators;

import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.Result;

public interface Evaluator {
    Result analyse(Combination guess);

    Result analyse(Result result);
}
