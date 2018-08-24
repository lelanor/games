package com.lelanor.projects.codebreakers.behaviors;

import com.lelanor.projects.codebreakers.datatypes.Combination;

public interface Behavior {
    Combination generateCombination(int combinationSize, int range);
}
