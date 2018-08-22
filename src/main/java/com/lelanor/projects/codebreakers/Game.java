package com.lelanor.projects.codebreakers;

import com.lelanor.projects.codebreakers.datatypes.GameMode;
import com.lelanor.projects.codebreakers.datatypes.GameType;
import com.lelanor.projects.codebreakers.datatypes.PlayerType;
import com.lelanor.projects.codebreakers.datatypes.Result;
import com.lelanor.projects.codebreakers.factories.PlayerFactory;
import com.lelanor.projects.codebreakers.players.Player;
import com.lelanor.projects.codebreakers.userinterface.Console;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Game {

    private final static Logger logger = Logger.getLogger(App.class);
    private Console console = new Console();
    private boolean debugSession = false;
    private Properties properties = new Properties();
    private InputStream input = null;
    private int range;
    private int combinationSize;
    private int numberOfTries;
    private GameType gameType;
    private GameMode gameMode;
    private Result result;



    public Game(boolean isDebug) {
        setDebugSession(isDebug);
    }


    void run(){
        logger.debug("Application starts running");
        getProperties();
        if (isDebugSession()){
            logger.debug("Debug session initialized");
            System.out.println("Debug session initialized");
        }
        setGameType(console.gameChoice());
        setGameMode(console.gameModeChoice(isDebugSession()));
        play(getGameType(), getGameMode());
    }

    private void play(GameType gameType, GameMode gameMode) {
        logger.info("Playing a "+gameType+" game in a "+gameMode+" way");
        System.out.println("\nI play a "+gameType+" game in a "+gameMode+" way");
        if (gameMode != GameMode.DUEL){

            PlayerFactory playerFactory = new PlayerFactory();
            Player humanPlayer = playerFactory.getPlayer(PlayerType.HUMAN, getGameMode());
            Player CPUPlayer = playerFactory.getPlayer(PlayerType.CPU, getGameMode());




           /* CodeMaker codeMaker = new CodeMaker(getCombinationSize(), getRange());
            CodeBreaker codeBreaker = new CodeBreaker(getCombinationSize(), getRange());

            codeMaker.createCombination();
            if (isDebugSession()){
                System.out.print("\n[CodeMaker] The combination to guess is : ");
                codeMaker.printCombination();
            }
            codeBreaker.createCombination();
            System.out.print("[CodeBreaker] My initial guess is : ");
            codeBreaker.printCombination();
            do {
                result = codeMaker.analyseCombination(codeBreaker.getResult());
                if (!result.isWinner(getGameType(), getCombinationSize())) {
                    codeBreaker.analyseCombination(result);
                }
            } while (!result.isWinner(getGameType(), getCombinationSize()));
            console.declareVictory();*/
        }
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
            }catch (NullPointerException e){
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

    public int getRange() {
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

    public int getCombinationSize() {
        return combinationSize;
    }

    public void setCombinationSize(int combinationSize) {
        this.combinationSize = combinationSize;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }
}
