package com.lelanor.projects.codebreakers.behaviors;

import com.lelanor.projects.codebreakers.datatypes.GameMode;
import com.lelanor.projects.codebreakers.datatypes.PlayerType;
import org.apache.log4j.Logger;

public class BehaviorFactory {

    private final static Logger logger = Logger.getLogger(BehaviorFactory.class);

    public Behavior getBehavior(PlayerType playerType, GameMode gameMode) {

        if (gameMode == GameMode.DEFENSE) {
            if (playerType == PlayerType.CODEMAKER) {
                return new Manual(playerType);
            } else if (playerType == PlayerType.CODEBREAKER) {
                return new Automatic(playerType);
            }
        } else if (gameMode == GameMode.ATTACK) {
            if (playerType == PlayerType.CODEBREAKER) {
                return new Manual(playerType);
            } else if (playerType == PlayerType.CODEMAKER) {
                return new Automatic(playerType);
            }
        }
        declareError();
        return null;
    }

    private void declareError() {
        System.out.println("un error is occurred during Behavior instantiation");
        logger.error("Error during behavior instantiation");
    }

}
