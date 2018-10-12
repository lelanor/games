package com.lelanor.projects.codebreakers.evaluators;

import com.lelanor.projects.codebreakers.datatypes.GameType;
import org.apache.log4j.Logger;

/**
 * The factory used to instantiate the correct evaluator. It allows the dependency inversion
 */
public class EvaluatorFactory {
    /**
     * the logger of the class
     */
    private final static Logger logger = Logger.getLogger(EvaluatorFactory.class);

    /**
     * this method returns the evaluator according to the GameType parameter
     *
     * @param gameType the game actually played
     * @return the evaluator according to the parameter
     */
    public Evaluator getEvaluator(GameType gameType) {
        if (gameType == GameType.CODEX) {
            return new Codex();
        } else if (gameType == GameType.MASTERMIND) {
            return new MasterMind();
        } else {
            System.out.println("an error has occurred during Evaluator instantiation");
            logger.error("Error during evaluator instantiation ");
            return null;
        }
    }
}
