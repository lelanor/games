package com.lelanor.projects.codebreakers.factories;

import com.lelanor.projects.codebreakers.datatypes.GameMode;
import com.lelanor.projects.codebreakers.datatypes.GameType;
import com.lelanor.projects.codebreakers.datatypes.PlayerType;
import com.lelanor.projects.codebreakers.players.CodeBreaker;
import com.lelanor.projects.codebreakers.players.CodeMaker;
import com.lelanor.projects.codebreakers.players.Player;
import org.apache.log4j.Logger;

public class PlayerFactory {

    private final static Logger logger = Logger.getLogger(PlayerFactory.class);

    public Player getPlayer(PlayerType playerType, GameType gameType, GameMode gameMode) {

        EvaluatorFactory evaluatorFactory = new EvaluatorFactory();
        BehaviorFactory behaviorFactory = new BehaviorFactory();

        if (playerType == PlayerType.CODEMAKER) {
            return new CodeMaker(behaviorFactory.getBehavior(PlayerType.CODEMAKER, gameMode), evaluatorFactory.getEvaluator(gameType));
        } else if (playerType == PlayerType.CODEBREAKER) {
            return new CodeBreaker(behaviorFactory.getBehavior(PlayerType.CODEBREAKER, gameMode), evaluatorFactory.getEvaluator(gameType));
        } else {
            System.out.println("Un error occurred during instantiation of players");
            logger.error("Impossible to instantiate players");
        }
        return null;
    }
}
