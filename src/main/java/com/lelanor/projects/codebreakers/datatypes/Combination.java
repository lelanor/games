package com.lelanor.projects.codebreakers.datatypes;



import com.lelanor.projects.codebreakers.data.GameData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Combination {

    private int[] code;
    private int size = GameData.getInstance().getCombinationSize();


    public Combination() {
        setCode(createCode());
    }

    public Combination(int[] pCode) {
        setCode(pCode);
    }

    public Combination(String code) {
        int[] intCode = new int[size];
        char[] charCode = code.toCharArray();
        for (int i = 0; i < size; i++) {
            intCode[i] = Character.getNumericValue(charCode[i]);
        }
        setCode(intCode);
    }

    private int[] createCode() {
        Random random = new Random();
        GameData gameData = GameData.getInstance();
        int[] code = new int[gameData.getCombinationSize()];
        for (int i = 0; i < gameData.getCombinationSize(); i++) {
            code[i] = random.nextInt(gameData.getRange());
        }
        return code;
    }

    public Result MasterMindCompareTo(Combination candidate) {
        int black = 0;
        int white = 0;

        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();

        for (int i = 0; i < candidate.size; i++) {
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

    public Result CodexCompareTo(Combination candidate){
        int[] tempResult = new int[candidate.getCode().length];
        for (int i=0; i<candidate.getCode().length; i++){
            if (candidate.getCode()[i] == this.getCode()[i]) {
                tempResult[i] = 0;
            }else if (candidate.getCode()[i] > this.getCode()[i]){
                tempResult[i] = 1;
            }else if (candidate.getCode()[i] < this.getCode()[i]){
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
        for (int i = 0; i < size; i++) {
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
