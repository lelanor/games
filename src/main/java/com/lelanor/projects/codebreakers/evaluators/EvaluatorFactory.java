package com.lelanor.projects.codebreakers.evaluators;

import com.lelanor.projects.codebreakers.datatypes.GameType;
import org.apache.log4j.Logger;

public class EvaluatorFactory {

    private final static Logger logger = Logger.getLogger(EvaluatorFactory.class);

    public Evaluator getEvaluator(GameType gameType) {
        if (gameType == GameType.CODEX) {
            return new Codex(gameType);
        } else if (gameType == GameType.MASTERMIND) {
            return new MasterMind(gameType);
        } else {
            System.out.println("an error has occurred during Evaluator instantiation");
            logger.error("Error during evaluator instantiation ");
            return null;
        }
    }
}