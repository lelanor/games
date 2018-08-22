package com.lelanor.projects.codebreakers.players;

import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.Result;

public class CodeBreaker extends Player{

    private Combination combination;
    private Result result;
    private int size;
    private int range;

    public  CodeBreaker(int size, int range){
        setSize(size);
        setRange(range);
    }

    public void createCombination() {
        setCombination(new Combination(getSize(), getRange()));
    }

    public void printCombination() {
        getCombination().printCombination();
    }

    public Result analyseCombination(Result result) {
        return null;
    }

    // GETTERS AND SETTERS

    public Combination getCombination() {
        return combination;
    }

    private void setCombination(Combination combination) {
        this.combination = combination;
    }

    private int getSize() {
        return size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    private int getRange() {
        return range;
    }

    private void setRange(int range) {
        this.range = range;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
