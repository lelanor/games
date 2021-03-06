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

/**
 * This is the core class of the application.
 *
 * @author Enrico Lo Faro
 * @version 1.0
 */

public class Game {

    /**
     * This object is the logger of the application
     */
    private final static Logger logger = Logger.getLogger(Game.class);
    /**
     * contains the range of the combination (within 0 and ...)
     */
    private static int range;
    /**
     * contains the number of digits of the combination
     */
    private static int combinationSize;
    /**
     * contains the kind of game to play (MasterMind or Codex)
     */
    private static GameType gameType;
    /**
     * The mode the app goes to play (Attack, Defense, Duel, CPUSolo)
     */
    private static GameMode gameMode;
    /**
     * The Keyboard I/O stream
     */
    private Console console = new Console();
    /**
     * The properties set from config.properties file
     */
    private boolean debugSession = false;
    /**
     * The file I/O stream
     */
    private InputStream input = null;
    /**
     * The max numbers of possibles tries to solve the puzzle
     */
    private int numberOfTries;
    /**
     * The CodeMaker analyse result
     */
    private Result result;
    /**
     * this flag indicates if a user config file needs to be loaded or not
     */
    private boolean hasUserConfigFile = false;
    /**
     * user config file path
     */
    private String userConfigFilePath;

    /**
     * Instantiate a Game object in debug or normal way
     *
     * @param isDebug           Flag - true: debug session is activated | false: normal session is activated
     * @param hasUserConfigFile Flag - true: an user configuration file has been passed by the command line
     */
    public Game(boolean isDebug, boolean hasUserConfigFile) {
        setDebugSession(isDebug);
        setHasUserConfigFile(hasUserConfigFile);
        logger.debug("The constructor for Game class is called with isDebug = " + isDebug + " and hasUserConfigFile = " + hasUserConfigFile);
    }

    public Game() {
        super();
    }

    /**
     * getter
     *
     * @return the range of possible values for the combination attribute
     */
    public static int getRange() {
        return range;
    }

    /**
     * setter
     *
     * @param range set the value of range attribute
     */
    public void setRange(int range) {
        this.range = range;
    }

    /**
     * getter
     *
     * @return the value of the CombinationSize attribute
     */
    public static int getCombinationSize() {
        return combinationSize;
    }

    /**
     * setter
     *
     * @param combinationSize set the value of combinationSize attribute
     */
    public void setCombinationSize(int combinationSize) {
        this.combinationSize = combinationSize;
    }

    /**
     * getter
     *
     * @return the value of GameType attribute
     */
    public static GameType getGameType() {
        return gameType;
    }

    /**
     * setter
     *
     * @param type set the value of GameType attribute
     */
    public void setGameType(GameType type) {
        this.gameType = type;
    }

    /**
     * Launch the application and collect the game type and mode info from the user
     */
    void run() {
        logger.debug("Application starts running");
        getProperties();
        logger.debug("default range is " + getRange());
        logger.debug("default combinationSize is " + getCombinationSize());
        logger.debug("default numberOfTries is " + getNumberOfTries());
        if (hasUserConfigFile) {
            logger.debug("An user config file is present");
            logger.debug("the user config file path is " + getUserConfigFilePath());
            getUserProperties(getUserConfigFilePath());
            logger.debug("After user config file loading : ");
            logger.debug("range setting is " + getRange());
            logger.debug("combinationSize setting is " + getCombinationSize());
            logger.debug("numberOfTries setting is " + getNumberOfTries());
        }
        if (isDebugSession()) {
            logger.debug("Debug session initialized");
            System.out.println("Debug session initialized");
        }
        int endChoice;
        do {
            if (this.getGameType() == null) {
                setGameType(console.gameChoice());
            }
            if (this.getGameMode() == null) {
                setGameMode(console.gameModeChoice(isDebugSession()));
            }
            play(getGameMode());
            endChoice = console.endMenu();
            if (endChoice == 2) {
                this.setGameType(null);
                this.setGameMode(null);
            }
        } while (endChoice != 3);

    }

    /**
     * This method applies the GameMode parameter and chooses the way the game will run
     *
     * @param gameMode tells the method what type of game mode apply
     */
    private void play(GameMode gameMode) {
        logger.info("Playing a " + getGameType() + " game in a " + gameMode + " way");

        PlayerFactory playerFactory = new PlayerFactory();

        if (gameMode != GameMode.DUEL) {
            Player codeMaker = playerFactory.getPlayer(PlayerType.CODEMAKER, getGameType(), getGameMode());
            Player codeBreaker = playerFactory.getPlayer(PlayerType.CODEBREAKER, getGameType(), getGameMode());
            logger.debug("Players created");
            codeMaker.setName("CODEMAKER");
            codeBreaker.setName("CODEBREAKER");

            codeMaker.generateDefenseCode(getCombinationSize(), getRange());

            logger.debug("defense code generated");
            if (isDebugSession()) {
                System.out.print("\n[CodeMaker] The combination to guess is : ");
                codeMaker.printDefenseCombination();
                logger.debug("defense code displayed");
            }

            codeBreaker.generateAttackCode(getCombinationSize(), getRange());
            System.out.print("\n[CodeBreaker] My initial guess is : ");
            codeBreaker.printAttackCombination();

            int tries = 0;
            do {
                logger.debug("codeBreaker analyse, " + (tries + 1) + " try");
                result = codeMaker.analyseCombination(codeBreaker.getAttackCode());
                if (!result.isWinner(getGameType(), getCombinationSize())) {
                    int[] result = codeBreaker.analyseResult(this.result).getResult();
                    codeBreaker.setAttackCode(new Combination(result));
                }
                tries += 1;
            } while ((!result.isWinner(getGameType(), getCombinationSize())) && (tries < getNumberOfTries() - 1));
            logger.debug("codeBreaker analyse terminated");
            if (tries >= getNumberOfTries() - 1) {
                console.declareVictory(codeMaker.getName());
                logger.debug("codeBreaker has lost");
            } else {
                console.declareVictory(codeBreaker.getName());
                logger.debug("codeBreaker has won");
            }
        } else if (gameMode == GameMode.DUEL) {
            System.out.println("we are in a DUEL MODE");
            logger.info("Duel mode activated");
            Player playerOne = playerFactory.getPlayer(PlayerType.CODEMAKER, getGameType(), getGameMode());
            Player playerTwo = playerFactory.getPlayer(PlayerType.CODEBREAKER, getGameType(), getGameMode());
            playerOne.setName("COMPUTER");
            playerTwo.setName("YOU");
            logger.debug("Players created");
            System.out.println("The computer is choosing its code to guess.");
            playerOne.generateDefenseCode(getCombinationSize(), getRange());
            logger.debug("computer player code is created");
            System.out.println("Please choose your first attempt combination");
            playerTwo.generateAttackCode(getCombinationSize(), getRange());
            logger.debug("human player first attempt typed-in");
            System.out.println("\nPlease choose your code to guess.");
            playerTwo.generateDefenseCode(getCombinationSize(), getRange());
            logger.debug("human player code is created");
            System.out.println("Computer is choosing its first attempt combination");
            playerOne.generateAttackCode(getCombinationSize(), getRange());
            logger.debug("computer player first attempt typed-in");
            if (isDebugSession()) {
                System.out.print("\n[player1] Computer combination to guess is : ");
                playerOne.printDefenseCombination();

                System.out.print("\n[player2] Your combination to guess is : ");
                playerTwo.printDefenseCombination();
                logger.debug("codes displayed");
            }
            do {
                System.out.print("\nProposition: ");
                playerTwo.printAttackCombination();
                logger.debug("codeBreaker analyse");
                playerOne.setResult(playerOne.analyseCombination(playerTwo.getAttackCode()));
                if (!playerOne.getResult().isWinner(getGameType(), getCombinationSize())) {
                    playerTwo.setResult(playerTwo.analyseResult(playerOne.getResult()));
                    playerTwo.setAttackCode(new Combination(playerTwo.getResult().getResult()));
                }
                logger.debug("swapping players");
                Player swapper;
                swapper = playerOne;
                playerOne = playerTwo;
                playerTwo = swapper;
            }
            while (!playerTwo.getResult().isWinner(getGameType(), getCombinationSize()));
            System.out.println(playerOne.getName());
            console.declareVictory(playerOne.getName());
            logger.debug("victory of "+playerOne.getName());
        }
    }

    /**
     * Collect properties from config.properties. the file is inside the jar
     */
    private void getProperties() {
        Properties properties = new Properties();
        InputStream input = null;
        try {

            input = App.class.getClassLoader().getResourceAsStream("config.properties");
            if (input == null) {
                System.out.println("Sorry, unable to find the default config file");
                logger.error("default config file not present");
                return;
            }
            properties.load(input);
            if (!(properties.getProperty("isDebugMode").isEmpty()) && properties.getProperty("isDebugMode").contentEquals("true")) {
                setDebugSession(true);
            }
            if (!(properties.getProperty("range").isEmpty())) {
                int temp = Integer.parseInt(properties.getProperty("range"));
                if (temp < 4) {
                    setRange(4);
                    logger.error("minimal range not respected, range setted to 4");
                } else if (temp > 10) {
                    setRange(10);
                    logger.error("maximal range not respected, range setted to 10");
                } else {
                    setRange((Integer.parseInt(properties.getProperty("range"))));
                }
            }
            if (!(properties.getProperty("combinationSize").isEmpty())) {
                setCombinationSize((Integer.parseInt(properties.getProperty("combinationSize"))));
            }
            if (!(properties.getProperty("numberOfTries").isEmpty())) {
                setNumberOfTries((Integer.parseInt(properties.getProperty("numberOfTries"))));
            }
        } catch (IOException ex) {
            logger.error("The default configuration file is unreachable");
        } catch (NullPointerException ex) {
            logger.error("one or more default properties are not available");
            System.out.println("one or more default properties are not available");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error("Impossible to close the default properties file");
                    System.out.println("Impossible to close the default properties file");
                }
            }
        }
    }

    /**
     * Collect properties from user config file
     *
     * @param userConfigFileName the path to the user config file
     */
    private void getUserProperties(String userConfigFileName) {
        Properties userProperties = new Properties();
        try {
            input = new FileInputStream(userConfigFileName);
            userProperties.load(input);
            try {
                if (!(userProperties.isEmpty())) {
                    if (userProperties.keySet().contains("range")) {
                        int temp = Integer.parseInt(userProperties.getProperty("range"));
                        if (temp < 4) {
                            setRange(4);
                            logger.error("minimal range in user configuration file is not respected, range setted to 4");
                        } else if (temp > 10) {
                            setRange(10);
                            logger.error("maximal range in user configuration file is not respected, range setted to 10");
                        } else {
                            setRange((Integer.parseInt(userProperties.getProperty("range"))));
                        }
                    }
                    if (userProperties.keySet().contains("numberOfTries")) {
                        setNumberOfTries(Integer.parseInt(userProperties.getProperty("numberOfTries")));
                    }
                    if (userProperties.keySet().contains("combinationSize")) {
                        setCombinationSize(Integer.parseInt(userProperties.getProperty("combinationSize")));
                    }
                }
            } catch (NullPointerException e) {
                logger.error("Error occurred reading user configuration parameters, one or more parameters could be absents");
                System.out.println("Error occurred reading user configuration parameters, one or more parameters could be absents");
            }
        } catch (IOException e) {
            logger.error("Error occurred accessing user properties file");
            System.out.println("Impossible to reach the user configuration file, program continues with default configuration");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error("Error occurred closing user properties file");
                }
            }
        }
    }

    /**
     * getter
     *
     * @return the value of isDebugSession attribute
     */
    public boolean isDebugSession() {
        return debugSession;
    }

    /**
     * setter
     *
     * @param debugSession set the value of debugSession attribute
     */
    public void setDebugSession(boolean debugSession) {
        this.debugSession = debugSession;
    }

    /**
     * getter
     *
     * @return the max number of tries attribute
     */
    public int getNumberOfTries() {
        return numberOfTries;
    }

    /**
     * setter
     *
     * @param numberOfTries set the value of numberOfTries attribute
     */
    public void setNumberOfTries(int numberOfTries) {
        this.numberOfTries = numberOfTries;
    }

    /**
     * getter
     *
     * @return the value of GameMode attribute
     */
    public GameMode getGameMode() {
        return gameMode;
    }

    /**
     * setter
     *
     * @param gameMode set the value of GameMode attribute
     */
    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    /**
     * setter
     *
     * @param hasUserConfigFile true if the user has passed a configuration file by the command line
     */
    public void setHasUserConfigFile(boolean hasUserConfigFile) {
        this.hasUserConfigFile = hasUserConfigFile;
    }

    /**
     * getter
     *
     * @return the path to the user configuration file
     */
    public String getUserConfigFilePath() {
        return userConfigFilePath;
    }

    /**
     * setter
     *
     * @param userConfigFilePath the path to user configuration file
     */
    public void setUserConfigFilePath(String userConfigFilePath) {
        this.userConfigFilePath = userConfigFilePath;
    }

}