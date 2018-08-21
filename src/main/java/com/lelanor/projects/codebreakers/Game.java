package com.lelanor.projects.codebreakers;

import com.lelanor.projects.codebreakers.datatypes.GameMode;
import com.lelanor.projects.codebreakers.datatypes.GameType;
import com.lelanor.projects.codebreakers.userinterface.Console;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Game {

    private final static Logger logger = Logger.getLogger(App.class);
    private Console console = new Console();
    private boolean isDebugSession = false;
    private Properties properties = new Properties();
    private InputStream input = null;
    private int range;
    private int combinationSize;
    private int numberOfTries;
    private GameType gameType;
    private GameMode gameMode;



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
        System.out.println("\nI play a "+gameType+" game in a "+gameMode+" way");
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
        return isDebugSession;
    }

    public void setDebugSession(boolean debugSession) {
        isDebugSession = debugSession;
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
