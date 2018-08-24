package com.lelanor.projects.codebreakers.evaluators;

import com.lelanor.projects.codebreakers.datatypes.GameType;

public class MasterMind implements Evaluator {

    private GameType gameType;

    public MasterMind(GameType gameType) {
        setGameType(gameType);
        System.out.println("Evaluator = "+getGameType());

    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }
}
