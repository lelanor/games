package com.lelanor.projects.codebreakers.behaviors;

import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.PlayerType;

public class Automatic implements Behavior {

    private PlayerType playerType;

    public Automatic(PlayerType playerType) {
        setPlayerType(playerType);
        System.out.println("Behavior = AUTOMATIC      PlayerType = "+getPlayerType());
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    @Override
    public Combination generateCombination(int combinationSize, int range) {
        return new Combination(combinationSize, range);
    }
}
