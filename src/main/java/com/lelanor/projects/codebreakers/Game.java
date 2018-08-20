package com.lelanor.projects.codebreakers;

import org.apache.log4j.Logger;

public class Game {

    public final static Logger logger = Logger.getLogger(App.class);
    private boolean isDebugSession = false;

    public Game(boolean isDebug) {
        setDebugSession(isDebug);
    }

    public void run(){

        logger.debug("Application starts running");
        if (isDebugSession()){
            logger.debug("Debug session initialized");
        }

    }

    public boolean isDebugSession() {
        return isDebugSession;
    }

    public void setDebugSession(boolean debugSession) {
        isDebugSession = debugSession;
    }
}
