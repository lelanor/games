package com.lelanor.projects.codebreakers.userinterface;

import com.lelanor.projects.codebreakers.Game;
import com.lelanor.projects.codebreakers.datatypes.GameMode;
import com.lelanor.projects.codebreakers.datatypes.GameType;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class Console {

    private Scanner scanner = new Scanner(System.in);

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
                return GameMode.CPUSOLO;
            } else {
                Logger logger = Logger.getLogger(Console.class);
                logger.error("[USER ERROR] Game mode is not in the possible range");
                System.out.println("\nGame mode is not in the possible range\n");
            }
        } while ((choice != 1) && (choice != 2) && (choice != 3) && !(debugSession && (choice == 4)));
        return null;
    }

    public int[] askUserCombination() {
        int[] code = new int[Game.getCombinationSize()];
        String userEntry;
        Scanner keyboard = new Scanner(System.in);
        boolean error;
        do {
            System.out.println("Please type in a valid combination (" + Game.getCombinationSize() + " chiffres, within 0 and " + (Game.getRange() - 1) + ")");
            userEntry = keyboard.nextLine();
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

    public void declareVictory() {
    }
}
