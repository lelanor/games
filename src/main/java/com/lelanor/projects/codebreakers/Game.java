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
     * The Keyboard I/O stream
     */
    private Console console = new Console();
    /**
     * Flag for debug session ON/OFF
     */
    private boolean debugSession = false;
    /**
     * The properties set from config.properties file
     */
    private Properties properties = new Properties();
    /**
     * The file I/O stream
     */
    private InputStream input = null;
    /**
     * The max numbers of possibles tries to solve the puzzle
     */
    private int numberOfTries;
    /**
     * The mode the app goes to play (Attack, Defense, Duel, CPUSolo)
     */
    private static GameMode gameMode;
    /**
     * The CodeMaker analyse result
     */
    private Result result;

    /**
     * Instantiate a Game object in debug or normal way
     *
     * @param isDebug Flag - true: debug session is activated | false: normal session is activated
     */
    public Game(boolean isDebug) {
        setDebugSession(isDebug);
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
        if (isDebugSession()) {
            logger.debug("Debug session initialized");
            System.out.println("Debug session initialized");
        }
        int endChoice;
        do {
            if (this.getGameType()==null) {
                setGameType(console.gameChoice());
            }
            if (this.getGameMode()==null) {
                setGameMode(console.gameModeChoice(isDebugSession()));
            }
            play(getGameMode());
            endChoice = console.endMenu();
            if (endChoice==2){
                this.setGameType(null);
                this.setGameMode(null);
            }
        }while (endChoice!=3);

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

            codeMaker.setName("CODEMAKER");
            codeBreaker.setName("CODEBREAKER");

            codeMaker.generateDefenseCode(getCombinationSize(), getRange());

            if (isDebugSession()) {
                System.out.print("\n[CodeMaker] The combination to guess is : ");
                codeMaker.printDefenseCombination();
            }

            codeBreaker.generateAttackCode(getCombinationSize(), getRange());
            System.out.print("\n[CodeBreaker] My initial guess is : ");
            codeBreaker.printAttackCombination();

            int tries = 0;
            do {
                result = codeMaker.analyseCombination(codeBreaker.getAttackCode());
                if (!result.isWinner(getGameType(), getCombinationSize())) {
                    int[] result = codeBreaker.analyseResult(this.result).getResult();
                    codeBreaker.setAttackCode(new Combination(result));
                }
                tries += 1;
            } while ((!result.isWinner(getGameType(), getCombinationSize())) && (tries < getNumberOfTries() - 1));

            if (tries >= getNumberOfTries() - 1) {
                console.declareVictory(codeMaker.getName());
            } else {
                console.declareVictory(codeBreaker.getName());
            }
        } else if (gameMode == GameMode.DUEL) {
            System.out.println("we are in a DUEL MODE");
            logger.info("Duel mode activated");
            Player playerOne = playerFactory.getPlayer(PlayerType.CODEMAKER, getGameType(), getGameMode());
            Player playerTwo = playerFactory.getPlayer(PlayerType.CODEBREAKER, getGameType(), getGameMode());
            playerOne.setName("COMPUTER");
            playerTwo.setName("YOU");
            System.out.println("The computer is choosing its code to guess.");
            playerOne.generateDefenseCode(getCombinationSize(), getRange());
            System.out.println("Please choose your first attempt combination");
            playerTwo.generateAttackCode(getCombinationSize(), getRange());

            System.out.println("\nPlease choose your code to guess.");
            playerTwo.generateDefenseCode(getCombinationSize(), getRange());
            System.out.println("Computer is choosing its first attempt combination");
            playerOne.generateAttackCode(getCombinationSize(), getRange());

            if (isDebugSession()) {
                System.out.print("\n[player1] Computer combination to guess is : ");
                playerOne.printDefenseCombination();

                System.out.print("\n[player2] Your combination to guess is : ");
                playerTwo.printDefenseCombination();
            }
            do {
                System.out.print("\nProposition: ");
                playerTwo.printAttackCombination();
                playerOne.setResult(playerOne.analyseCombination(playerTwo.getAttackCode()));
                if (!playerOne.getResult().isWinner(getGameType(), getCombinationSize())) {
                    playerTwo.setResult(playerTwo.analyseResult(playerOne.getResult()));
                    playerTwo.setAttackCode(new Combination(playerTwo.getResult().getResult()));
                }
                Player swapper;
                swapper = playerOne;
                playerOne = playerTwo;
                playerTwo = swapper;
            }
            while (!playerTwo.getResult().isWinner(getGameType(), getCombinationSize()));
            System.out.println(playerOne.getName());
            console.declareVictory(playerOne.getName());
        }
    }

    private void getProperties(){
        InputStream input = null;
        try {

            input = App.class.getClassLoader().getResourceAsStream("config.properties");
            if(input==null){
                System.out.println("Sorry, unable to find the default config file");
                return;
            }

            //load a properties file from class path, inside static method
            properties.load(input);

            //get the property value and print it out
            if (!(properties.getProperty("isDebugMode").isEmpty()) && properties.getProperty("isDebugMode").contentEquals("true")) {
                System.out.println("setting IsDebugSession");
                setDebugSession(true);
                System.out.println(isDebugSession());
            }
            if (!(properties.getProperty("range").isEmpty())) {
                System.out.println("setting range");
                int temp = Integer.parseInt(properties.getProperty("range"));
                if (temp<4){
                    setRange(4);
                } else if (temp>10){
                    setRange(10);
                }else {
                    setRange((Integer.parseInt(properties.getProperty("range"))));
                }
                System.out.println(getRange());
            }
            if (!(properties.getProperty("combinationSize").isEmpty())) {
                System.out.println("setting size");
                setCombinationSize((Integer.parseInt(properties.getProperty("combinationSize"))));
                System.out.println(getCombinationSize());
            }
            if (!(properties.getProperty("numberOfTries").isEmpty())) {
                System.out.println("setting possible tries");
                setNumberOfTries((Integer.parseInt(properties.getProperty("numberOfTries"))));
                System.out.println(getNumberOfTries());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex){
            System.out.println("one or more properties are not available");
        } finally{
            if(input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Collect properties from config.properties
     */
    private void getProperties(String userConfigFileName) {
        try {
            input = new FileInputStream(userConfigFileName);
            properties.load(input);
            try {
                if (!(properties.getProperty("isDebugMode").isEmpty()) && properties.getProperty("isDebugMode").contentEquals("true"))
                    setDebugSession(true || isDebugSession());
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
}