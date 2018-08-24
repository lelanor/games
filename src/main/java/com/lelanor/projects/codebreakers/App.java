package com.lelanor.projects.codebreakers;

public class App {

    public static void main(String[] args) {
        boolean isDebug = false;

        if ((args == null) && (args[0] == "debug")) {
            System.out.println("args n'est pas vide");
            isDebug = true;
        }
        Game game = new Game(isDebug);
        game.run();
    }
}
