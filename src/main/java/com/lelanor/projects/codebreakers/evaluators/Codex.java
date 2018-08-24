package com.lelanor.projects.codebreakers.evaluators;

import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.GameType;
import com.lelanor.projects.codebreakers.datatypes.Result;

public class Codex implements Evaluator{

    private GameType gameType;

    public Codex(GameType gameType) {
        setGameType(gameType);
        System.out.println("Evaluator = "+getGameType());

    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    @Override
    public Result analyse(Combination guess) {
        return null;
    }

    @Override
    public Result analyse(Result result) {
        return null;
    }
}
