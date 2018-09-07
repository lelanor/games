package com.lelanor.projects.codebreakers;

import com.lelanor.projects.codebreakers.datatypes.*;
import com.lelanor.projects.codebreakers.players.Player;
import com.lelanor.projects.codebreakers.players.PlayerFactory;
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

        PlayerFactory playerFactory = new PlayerFactory();

        if (gameMode != GameMode.DUEL) {
            Player codeMaker = playerFactory.getPlayer(PlayerType.CODEMAKER, getGameType(), getGameMode());
            Player codeBreaker = playerFactory.getPlayer(PlayerType.CODEBREAKER, getGameType(), getGameMode());

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
                    int[] result = codeBreaker.analyseResult(this.result).getResult();
                    codeBreaker.setCode(new Combination(result));
                }
            } while (!result.isWinner(getGameType(), getCombinationSize()));

        } else if (gameMode == GameMode.DUEL) {
            System.out.println("we are in a DUEL MODE, not yet implemented");
            logger.info("Duel mode activated");

            Player player1 = playerFactory.getPlayer(PlayerType.CODEMAKER, getGameType(), getGameMode());
            Player player2 = playerFactory.getPlayer(PlayerType.CODEBREAKER, getGameType(), getGameMode());

            System.out.println("The computer is choosing its code.");
            player1.generateCode(getCombinationSize(), getRange());

            System.out.println("Please choose your code.");
            player2.generateCode(getCombinationSize(), getRange());

            if (isDebugSession()) {
                System.out.print("\n[player1] The combination to guess is : ");
                player1.printCombination();

                System.out.print("\n[player2] The combination to guess is : ");
                player2.printCombination();
            }


            player1.generateCode(getCombinationSize(), getRange());
            System.out.print("\n[player1] My initial guess is : ");
            player1.printCombination();

            System.out.println("Please enter your first proposition.");
            player2.generateCode(getCombinationSize(), getRange());
            System.out.print("\n[player2] My initial guess is : ");
            player2.printCombination();

            do {
                Player t = player1;
                player1 = player2;
                player2 = t;

                System.out.print("Proposition: ");
                player2.printCombination();

                result = player1.analyseCombination(player2.getCode());
                if (!result.isWinner(getGameType(), getCombinationSize())) {
                    int[] result = player2.analyseResult(this.result).getResult();
                    player2.setCode(new Combination(result));
                }
            } while (!result.isWinner(getGameType(), getCombinationSize()));

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
