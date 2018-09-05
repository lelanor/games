package com.lelanor.projects.codebreakers;

import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.GameMode;

public class CodesArrayFactory {

    public Combination[] getArray(GameMode gameMode) {
        if (gameMode == GameMode.DUEL){
            return new Combination[2];
        } else if (gameMode == GameMode.DEFENSE || gameMode == GameMode.ATTACK || gameMode == GameMode.CPUSOLO){
            return new Combination[1];
        } else return new Combination[0];
    }
}
