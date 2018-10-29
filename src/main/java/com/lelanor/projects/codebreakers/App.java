package com.lelanor.projects.codebreakers;

/**
 * App is the main class of Games application
 * it checks if a command line parameter is available and set isDebug attribute if applicable
 *
 * @author Enrico Lo Faro
 * @version 1.0
 */
public class App {

    public static void main(String[] args) {
        boolean isDebug = false;
        boolean hasUserConfigFile = false;
        String userConfigFilePath = "";

        if (args.length > 0) {

            //todo: gerer les args : si no debug et oui file?
            if (args[0].equals("debug")) {
                isDebug = true;
            }
            if (!args[1].isEmpty()) {
                userConfigFilePath = args[1];
                hasUserConfigFile = true;
            }
        }
        Game game = new Game(isDebug,hasUserConfigFile);
        game.setUserConfigFilePath(userConfigFilePath);
        game.run();
    }
}
