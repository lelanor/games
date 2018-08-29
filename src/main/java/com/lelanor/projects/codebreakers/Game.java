package com.lelanor.projects.codebreakers;

import com.lelanor.projects.codebreakers.datatypes.GameMode;
import com.lelanor.projects.codebreakers.datatypes.GameType;
import com.lelanor.projects.codebreakers.datatypes.PlayerType;
import com.lelanor.projects.codebreakers.datatypes.Result;
import com.lelanor.projects.codebreakers.players.PlayerFactory;
import com.lelanor.projects.codebreakers.players.Player;
import com.lelanor.projects.codebreakers.userinterface.Console;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Game {

    private final static Logger logger = Logger.getLogger(Game.class);

    private static int range;
    private static int combinationSize;
    private static GameType gameType;

    private Console console = new Console();
    private boolean debugSession = false;
    private Properties properties = new Properties();
    private InputStream input = null;
    private int numberOfTries;
    private GameMode gameMode;
    private Result result;


    public Game(boolean isDebug) {
        setDebugSession(isDebug);
    }


    void run() {
        logger.debug("Application starts running");
        getProperties();
        if (isDebugSession()) {
            logger.debug("Debug session initialized");
            System.out.println("Debug session initialized");
        }
        setGameType(console.gameChoice());
        setGameMode(console.gameModeChoice(isDebugSession()));
        play(getGameMode());
    }

    private void play(GameMode gameMode) {
        logger.info("Playing a " + getGameType() + " game in a " + gameMode + " way");
        System.out.println("\nI play a " + getGameType() + " game in a " + gameMode + " way");
        if (gameMode != GameMode.DUEL) {

            PlayerFactory playerFactory = new PlayerFactory();
            Player codeMaker = playerFactory.getPlayer(PlayerType.CODEMAKER, getGameType(), gameMode);
            Player codeBreaker = playerFactory.getPlayer(PlayerType.CODEBREAKER, getGameType(), gameMode);

            codeMaker.generateCode(getCombinationSize(), getRange());
            if (isDebugSession()) {
                System.out.print("\n[CodeMaker] The combination to guess is : ");
                codeMaker.printCombination();
            }
            codeBreaker.generateCode(getCombinationSize(), getRange());
            System.out.print("\n[CodeBreaker] My initial guess is : ");
            codeBreaker.printCombination();
            do {
                result = codeMaker.analyseCombination(codeBreaker.getCode());
                if (!result.isWinner(getGameType(), getCombinationSize())) {
                    codeBreaker.analyseCombination(result);
                }
            } while (!result.isWinner(getGameType(), getCombinationSize()));
        } else if (gameMode == GameMode.DUEL) {
            //TODO: Enrico -- implement DUEL MODE
            System.out.println("we are in a DUEL MODE, not yet implemented");
            logger.info("Duel mode activated");
        }
        console.declareVictory();
    }


    private void getProperties() {
        try {
            input = new FileInputStream("src/main/resources/config.properties");
            properties.load(input);
            try {
                if (!(properties.getProperty("isDebugMode").isEmpty()) && properties.getProperty("isDebugMode").contentEquals("true"))
                    setDebugSession(true);
                else
                    setDebugSession(false);
                setRange(Integer.parseInt(properties.getProperty("range")));
                setCombinationSize(Integer.parseInt(properties.getProperty("combinationSize")));
                setNumberOfTries(Integer.parseInt(properties.getProperty("numberOfTries")));
            } catch (NullPointerException e) {
                e.printStackTrace();
                logger.error("Error occurred reading configuration parameters, one or more parameters could be absents");
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Error occurred accessing properties file");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("Error occurred closing properties file");
                }
            }
        }
    }


    public boolean isDebugSession() {
        return debugSession;
    }

    public void setDebugSession(boolean debugSession) {
        this.debugSession = debugSession;
    }

    public static int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getNumberOfTries() {
        return numberOfTries;
    }

    public void setNumberOfTries(int numberOfTries) {
        this.numberOfTries = numberOfTries;
    }

    public static int getCombinationSize() {
        return combinationSize;
    }

    public void setCombinationSize(int combinationSize) {
        this.combinationSize = combinationSize;
    }

    public static GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType type) {
        this.gameType = type;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }
}
