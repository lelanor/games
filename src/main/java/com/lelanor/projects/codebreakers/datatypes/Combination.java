package com.lelanor.projects.codebreakers.datatypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Combination {

    private int[] code;

    public Combination(int size, int range) {
        setCode(createCode(size, range));
    }

    public Combination(int[] pCode) {
        setCode(pCode);
    }

    public Combination(String code) {
        int[] intCode = new int[code.length()];
        char[] charCode = code.toCharArray();
        for (int i = 0; i < code.length(); i++) {
            intCode[i] = Character.getNumericValue(charCode[i]);
        }
        setCode(intCode);
    }

    private int[] createCode(int size, int range) {
        Random random = new Random();
        int[] code = new int[size];
        for (int i = 0; i < size; i++) {
            code[i] = random.nextInt(range);
        }
        return code;
    }

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

    @Override
    public String toString() {
        String str = String.valueOf(getCode());
        return str;
    }

    public void printCombination() {
        for (int i = 0; i < getCode().length; i++) {
            System.out.print(getCode()[i] + " ");
        }
        System.out.println();
    }

    public int[] getCode() {
        return code;
    }

    private void setCode(int[] code) {
        this.code = code;
    }

}
