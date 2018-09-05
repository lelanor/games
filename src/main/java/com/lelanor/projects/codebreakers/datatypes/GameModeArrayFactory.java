package com.lelanor.projects.codebreakers.datatypes;

public class GameModeArrayFactory {


    public GameMode[] getArray(GameMode gameMode) {
        if (gameMode == GameMode.DUEL) {
            return new GameMode[]{GameMode.ATTACK, GameMode.DEFENSE};
        } else if (gameMode == GameMode.ATTACK) {
            return new GameMode[]{GameMode.ATTACK};
        } else if (gameMode == GameMode.DEFENSE) {
            return new GameMode[]{GameMode.DEFENSE};
        } else if (gameMode == GameMode.CPUSOLO) {
            return new GameMode[]{GameMode.CPUSOLO};
        }
        return new GameMode[0];
    }
}
