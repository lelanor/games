package com.lelanor.projects.codebreakers.userinterface;

import com.lelanor.projects.codebreakers.Game;
import com.lelanor.projects.codebreakers.datatypes.GameMode;
import com.lelanor.projects.codebreakers.datatypes.GameType;
import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * This class manage the exchanges with the human user
 *
 * @author Enrico Lo Faro
 * @version 1.0
 */

public class Console {
    /**
     * This is the keyboard stream
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Reads the user choice for the type of game to play (MasterMind or Codex)
     *
     * @return the type of the game to play
     */
    public GameType gameChoice() {
        int choice;
        do {
            System.out.println("\n-- Choose a game: --");
            System.out.println("1. Codex");
            System.out.println("2. Mastermind");
            System.out.print("-> ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                return GameType.CODEX;
            } else if (choice == 2) {
                return GameType.MASTERMIND;
            } else {
                Logger logger = Logger.getLogger(Console.class);
                logger.error("[USER ERROR] Game choice is not in the possible range");
                System.out.println("\nGame choice is not in the possible range\n");
            }
        } while ((choice != 1) && (choice != 2));
        return null;
    }

    /**
     * Reads the user choice for the mode to play (Attack, Defense, Duel, CPUSolo)
     *
     * @param debugSession if the flag is on allows the choice of CPUSolo mode
     * @return the mode to play
     */
    public GameMode gameModeChoice(boolean debugSession) {
        int choice;
        do {
            System.out.println("\n-- Choose a mode: --");
            System.out.println("1. Attack");
            System.out.println("2. Defense");
            System.out.println("3. Duel");
            if (debugSession) {
                System.out.println("4. CPU vs CPU");
            }
            System.out.print("-> ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                return GameMode.ATTACK;
            } else if (choice == 2) {
                return GameMode.DEFENSE;
            } else if (choice == 3) {
                return GameMode.DUEL;
            } else if (debugSession && (choice == 4)) {
                return GameMode.CPU_SOLO;
            } else {
                Logger logger = Logger.getLogger(Console.class);
                logger.error("[USER ERROR] Game mode is not in the possible range");
                System.out.println("\nGame mode is not in the possible range\n");
            }
        } while ((choice != 1) && (choice != 2) && (choice != 3) && !(debugSession && (choice == 4)));
        return null;
    }

    /**
     * Reads the user combination both in attack and defense phase,
     *
     * @return combination typed in by the user
     */
    public int[] askUserCombination() {
        int[] code = new int[Game.getCombinationSize()];
        String userEntry;
        Scanner keyboard = new Scanner(System.in);
        boolean error;
        do {
            do {
                System.out.println("Please type in a valid combination (" + Game.getCombinationSize() + " chiffres, within 0 and " + (Game.getRange() - 1) + ")");
                userEntry = keyboard.nextLine();
            } while (userEntry.length() != Game.getCombinationSize());
            char[] charEntry = userEntry.toCharArray();
            error = false;
            for (int i = 0; i < charEntry.length; i++) {
                if ((Character.getNumericValue(charEntry[i]) >= Game.getRange()) || (Character.getNumericValue(charEntry[i]) < 0)) {
                    error = true;
                } else {
                    code[i] = Character.getNumericValue(charEntry[i]);
                }
            }
        } while (error);
        return code;
    }

    /**
     * Reads the user analyse of the opponent try both for MasterMind and Codex game
     *
     * @param gameType allows to choice the kind of analyse
     * @return the type of the game to play
     */
    public int[] makerAnalyse(GameType gameType) {
        if (gameType == GameType.MASTERMIND) {
            int[] result = new int[2];
            do {
                System.out.print("CodeMaker, please, type-in how many BLACK tokens you find : ");
                result[0] = scanner.nextInt();
            } while ((result[0] > Game.getCombinationSize()) || (result[0] < 0));
            System.out.println();
            do {
                System.out.print("CodeMaker, please, type-in how many WHITE tokens you find : ");
                result[1] = scanner.nextInt();
            } while (result[1] > (Game.getCombinationSize() - result[0]));// || ((result[1]-result[0])<0));
            System.out.println();
            return result;
        } else if (gameType == GameType.CODEX) {
            int[] result = new int[Game.getCombinationSize()];
            System.out.println("to check the guess, please type-in, for every digit of the combination, 0 if equal, 1 if greater and -1 if lesser");
            System.out.println();
            for (int i = 0; i < result.length; i++) {
                do {
                    System.out.print("CodeMaker, please, check the " + (i + 1) + " digit of the CodeBreaker guess : ");
                    result[i] = scanner.nextInt();
                } while (!((result[i] == 0) || (result[i] == 1) || (result[i] == -1)));
            }
            return result;
        } else {
            System.out.println("un error has occurred");
            return null;
        }
    }

    /**
     * declare the victory of one player
     *
     * @param name the winning player
     */
    public void declareVictory(String name) {
        System.out.println("\n\n\n**************************************************");
        System.out.println("               " + name.toUpperCase() + " HAS WON!!!!");
        System.out.println("**************************************************\n\n\n");
    }

    /**
     * declare the victory
     */
    public void declareVictory() {
        System.out.println("\n\n\n**************************************************");
        System.out.println("                  VICTORY!!!!");
        System.out.println("**************************************************\n\n\n");
    }

    public int endMenu() {
        int choice;
        do {
            System.out.println("\n-- What would you like to do now? ");
            System.out.println("1. Play again");
            System.out.println("2. Change the game");
            System.out.println("3. Exit the App");
            System.out.print("-> ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if ((choice == 1) || (choice == 2) || (choice == 3)){
                return choice;
            } else {
                Logger logger = Logger.getLogger(Console.class);
                logger.error("[USER ERROR] User choice is not in the possible range");
                System.out.println("\nYour choice is not in the possible range\n");
            }
        } while ((choice != 1) && (choice != 2) && (choice != 3));
        return 0;
    }
}

