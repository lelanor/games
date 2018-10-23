package com.lelanor.projects.codebreakers.datatypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * this class models a combination according to the game currently played
 */
public class Combination {

    /**
     * the value of the combination
     */
    private int[] code;

    /**
     * construct a Combination according to size and range
     *
     * @param size  the size of the combination
     * @param range the allowed range
     */
    public Combination(int size, int range) {
        setCode(createCode(size, range));
    }

    /**
     * construct a Combination from an int array
     *
     * @param pCode the source array
     */
    public Combination(int[] pCode) {
        setCode(pCode);
    }

    /**
     * construct a Combination from a String
     *
     * @param code the source String
     */
    public Combination(String code) {
        int[] intCode = new int[code.length()];
        char[] charCode = code.toCharArray();
        for (int i = 0; i < code.length(); i++) {
            intCode[i] = Character.getNumericValue(charCode[i]);
        }
        setCode(intCode);
    }

    /**
     * create a random code
     *
     * @param size  the needed size
     * @param range th needed range
     * @return an array of int who represent the code
     */
    private int[] createCode(int size, int range) {
        Random random = new Random();
        int[] code = new int[size];
        for (int i = 0; i < size; i++) {
            code[i] = random.nextInt(range);
        }
        return code;
    }

    /**
     * Compare two Combination with MasterMind rules
     *
     * @param candidate the Combination to compare with
     * @return a Result variable, MasterMind version
     */
    public Result MasterMindCompareTo(Combination candidate) {
        int black = 0;
        int white = 0;

        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();

        for (int i = 0; i < candidate.getCode().length; i++) {
            if (candidate.getCode()[i] == this.getCode()[i]) {
                black += 1;
            } else {
                a.add(candidate.getCode()[i]);
                b.add(this.getCode()[i]);
            }
        }

        for (Integer i : a) {
            if (b.contains(i)) {
                white++;
                b.remove(i);
            }
        }
        return new Result(new int[]{black, white});
    }

    /**
     * Compare two Combination with Codex rules
     *
     * @param candidate the Combination to compare with
     * @return a Result variable, MasterMind version
     */
    public Result CodexCompareTo(Combination candidate) {
        int[] tempResult = new int[candidate.getCode().length];
        for (int i = 0; i < candidate.getCode().length; i++) {
            if (candidate.getCode()[i] == this.getCode()[i]) {
                tempResult[i] = 0;
            } else if (candidate.getCode()[i] > this.getCode()[i]) {
                tempResult[i] = 1;
            } else if (candidate.getCode()[i] < this.getCode()[i]) {
                tempResult[i] = -1;
            }
        }
        Result result = new Result(tempResult);
        return result;
    }

    /**
     * transform an array into a String
     *
     * @return a printable String
     */
    @Override
    public String toString() {
        String str = String.valueOf(getCode());
        return str;
    }

    /**
     * print the value of this combination
     */
    public void printCombination() {
        for (int i = 0; i < getCode().length; i++) {
            System.out.print(getCode()[i] + " ");
        }
        System.out.println();
    }

    /**
     * getter
     *
     * @return the value of this combination
     */
    public int[] getCode() {
        return code;
    }

    /**
     * setter
     *
     * @param code the value to be set
     */
    private void setCode(int[] code) {
        this.code = code;
    }

}
