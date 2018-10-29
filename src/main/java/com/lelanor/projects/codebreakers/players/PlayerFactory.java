package com.lelanor.projects.codebreakers.players;

import com.lelanor.projects.codebreakers.behaviors.BehaviorFactory;
import com.lelanor.projects.codebreakers.datatypes.GameMode;
import com.lelanor.projects.codebreakers.datatypes.GameType;
import com.lelanor.projects.codebreakers.datatypes.PlayerType;
import com.lelanor.projects.codebreakers.evaluators.EvaluatorFactory;
import org.apache.log4j.Logger;

/**
 * This is a simple factory pattern class to instantiate the players
 *
 * @author Enrico Lo Faro
 * @version 1.0
 */

public class PlayerFactory {
    /**
     * This object is the logger of the application
     */
    private final static Logger logger = Logger.getLogger(PlayerFactory.class);

    /**
     * @param playerType tells the method which type of player is wanted
     * @param gameType   tells the method the type of the game (MasterMind or Codex)
     * @param gameMode   tells the method the behavior needed
     * @return a player congruent to the parameters
     */
    public Player getPlayer(PlayerType playerType, GameType gameType, GameMode gameMode) {

        EvaluatorFactory evaluatorFactory = new EvaluatorFactory();
        BehaviorFactory behaviorFactory = new BehaviorFactory();

        if (playerType == PlayerType.CODEMAKER) {
            return new Player(behaviorFactory.getBehavior(PlayerType.CODEMAKER, gameMode), evaluatorFactory.getEvaluator(gameType));
        } else if (playerType == PlayerType.CODEBREAKER) {
            return new Player(behaviorFactory.getBehavior(PlayerType.CODEBREAKER, gameMode), evaluatorFactory.getEvaluator(gameType));
        } else {
            System.out.println("Error occurred during instantiation of players");
            logger.error("Impossible to instantiate players");
        }
        return null;
    }
}
