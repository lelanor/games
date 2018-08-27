package com.lelanor.projects.codebreakers.behaviors;

import com.lelanor.projects.codebreakers.Game;
import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.PlayerType;
import com.lelanor.projects.codebreakers.datatypes.Result;
import com.lelanor.projects.codebreakers.evaluators.Evaluator;
import com.lelanor.projects.codebreakers.userinterface.Console;

import java.util.Scanner;

public class Manual implements Behavior {

    private PlayerType playerType;

    public Manual(PlayerType playerType) {
        setPlayerType(playerType);
        System.out.println("Behavior = MANUAL      PlayerType = " + getPlayerType());
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    @Override
    public Combination generateCombination(int combinationSize, int range) {
        /*int[] code = new int[combinationSize];
        String userEntry;
        Scanner keyboard = new Scanner(System.in);

        boolean error;
        do {
            System.out.println("Please type in a valid combination (" + combinationSize + " chiffres, within 0 and " + (range - 1) + ")");
            userEntry = keyboard.nextLine();
            char[] charEntry = userEntry.toCharArray();
            error = false;
            for (int i = 0; i < charEntry.length; i++) {
                if ((Character.getNumericValue(charEntry[i]) >= range) || (Character.getNumericValue(charEntry[i]) < 0)) {
                    error = true;
                } else {
                    code[i] = Character.getNumericValue(charEntry[i]);
                }
            }
        } while (error);*/

        Console console = new Console();
        return new Combination(console.askUserCombination());
    }

    @Override
    public Result analyseCombination(Combination guess, Combination goal, Evaluator evaluator) {
        Result result;
        Console console = new Console();
        result = new Result(console.makerAnalyse(Game.getGameType()));
        return result;
    }

    @Override
    public Result analyseCombination(Result pResult, Evaluator evaluator, Combination goal) {
        Console console = new Console();
        Result result = new Result(console.askUserCombination());
        return result;
    }
}