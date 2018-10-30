package com.lelanor.projects.codebreakers;

import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * App is the main class of Games application
 * it checks if a command line parameter is available and set isDebug attribute if applicable
 *
 * @author Enrico Lo Faro
 * @version 1.0
 */
public class App {
    /**
     * This method launch the App and check for the parameters in the command line in order to activate the debug mode and
     * to change the configuration by a properties file
     *
     * @param args the list of parameters in the command line
     */
    public static void main(String[] args) {

        Pattern pattern;
        Matcher matcher;
        boolean isDebug = false;
        boolean hasUserConfigFile = false;
        String userConfigFilePath = "";
        final org.apache.log4j.Logger logger = Logger.getLogger(App.class);

        if (args.length > 0) {
            logger.info(args.length+" parameters has been found on the command line");
            for (int i = 0; i < args.length; i++) {
                pattern = Pattern.compile("debug");
                matcher = pattern.matcher(args[i]);
                if (matcher.find()) {
                    isDebug = true;
                    logger.info("the 'debug' parameter has been found on the command line");
                }
                pattern = Pattern.compile(".properties");
                matcher = pattern.matcher(args[i]);
                if (matcher.find()) {
                    hasUserConfigFile = true;
                    userConfigFilePath = args[i];
                    logger.info("A user config file path parameter has been found on the command line");

                }
            }
        }
        Game game = new Game(isDebug, hasUserConfigFile);
        game.setUserConfigFilePath(userConfigFilePath);
        game.run();
        logger.debug("***************************************************");
    }
}