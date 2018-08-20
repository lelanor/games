package com.lelanor.projects.codebreakers;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Game {

    public final static Logger logger = Logger.getLogger(App.class);

    private boolean isDebugSession = false;
    private Properties properties = new Properties();
    private InputStream input = null;
    private int range;
    private int combinationSize;
    private int numberOfTries;

    public Game(boolean isDebug) {
        setDebugSession(isDebug);
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


    public void run(){
        logger.debug("Application starts running");
        getProperties();
        if (isDebugSession()){
            logger.debug("Debug session initialized");
        }
    }


}
