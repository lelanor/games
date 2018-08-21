package com.lelanor.projects.codebreakers.userinterface;

import com.lelanor.projects.codebreakers.datatypes.GameMode;
import com.lelanor.projects.codebreakers.datatypes.GameType;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class Console {

    Scanner scanner = new Scanner(System.in);

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
            if (debugSession){
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
}
