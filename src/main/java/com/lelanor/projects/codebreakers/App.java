package com.lelanor.projects.codebreakers;

/** App is the main class of Games application
 * it checks if a command line parameter is available and set isDebug attribute if applicable
 *
 * @author Enrico Lo Faro
 * @version 1.0
 */
public class App {

    public static void main(String[] args) {
        boolean isDebug = false;
        if ((args.length>0) && (args[0].equals("debug"))) {
            System.out.println("args n'est pas vide");
            isDebug = true;
        }
        Game game = new Game(isDebug);
            game.run();
    }
}
