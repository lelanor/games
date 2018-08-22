package com.lelanor.projects.codebreakers.factories;

import com.lelanor.projects.codebreakers.behaviors.Automatic;
import com.lelanor.projects.codebreakers.behaviors.Behavior;
import com.lelanor.projects.codebreakers.behaviors.Manual;
import com.lelanor.projects.codebreakers.datatypes.GameType;
import com.lelanor.projects.codebreakers.datatypes.PlayerMode;
import com.lelanor.projects.codebreakers.evaluators.Codex;
import com.lelanor.projects.codebreakers.evaluators.Evaluator;
import com.lelanor.projects.codebreakers.evaluators.MasterMind;
import org.apache.log4j.Logger;

public class PlayerCharacteristics {

    Behavior behavior;
    Evaluator evaluator;

    private static final Logger logger = Logger.getLogger(PlayerCharacteristics.class);

    PlayerCharacteristics(Behavior behavior, Evaluator evaluator){
        this.behavior = behavior;
        this.evaluator = evaluator;
    }

    public static PlayerCharacteristics getPlayerCharacteristic(GameType gameType, PlayerMode playerMode){

        if (gameType == GameType.MASTERMIND){
            if (playerMode == PlayerMode.AUTOMATIC){
                return new PlayerCharacteristics(new Automatic(), new MasterMind());
            } else if (playerMode == PlayerMode.MANUAL){
                return new PlayerCharacteristics(new Manual(), new MasterMind());
            } else {
                logger.error("Impossible to define player characteristics - MASTERMIND");
                return null;
            }
        } else if (gameType == GameType.CODEX){
            if (playerMode == PlayerMode.AUTOMATIC){
                return new PlayerCharacteristics(new Automatic(), new Codex());
            } else if (playerMode == PlayerMode.MANUAL){
                return new PlayerCharacteristics(new Manual(), new Codex());
            } else {
                logger.error("Impossible to define player characteristics - CODEX");
                return null;
            }
        } else {
            logger.error("Impossible to define player characteristics - GENERAL");
            return null;
        }
    }
}
