package com.lelanor.projects.codebreakers.behaviors;

import com.lelanor.projects.codebreakers.datatypes.GameMode;
import com.lelanor.projects.codebreakers.datatypes.PlayerType;
import org.apache.log4j.Logger;

/**
 * A simple factory to create the right behavior
 */
public class BehaviorFactory {
    /**
     * the class logger
     */
    private final static Logger logger = Logger.getLogger(BehaviorFactory.class);

    /**
     * returns a behavior according to the game mode and the type of the player
     *
     * @param playerType the type of the player
     * @param gameMode   the type of the game
     * @return a behavior
     */
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
        } else if (gameMode == GameMode.DUEL) {
            if (playerType == PlayerType.CODEBREAKER) {
                return new Manual(playerType);
            } else if (playerType == PlayerType.CODEMAKER) {
                return new Automatic(playerType);
            }
        } else if (gameMode == GameMode.CPU_SOLO) {
            if (playerType == PlayerType.CODEBREAKER) {
                return new Automatic(playerType);
            } else if (playerType == PlayerType.CODEMAKER) {
                return new Automatic(playerType);
            }
        }
        declareError();
        return null;
    }

    /**
     * declares and logs an error during instantiation
     */
    private void declareError() {
        System.out.println("Error occurred during Behavior instantiation");
        logger.error("Error during behavior instantiation");
    }

}
