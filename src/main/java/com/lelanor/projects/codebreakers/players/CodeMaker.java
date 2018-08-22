package com.lelanor.projects.codebreakers.players;

import com.lelanor.projects.codebreakers.datatypes.Combination;
import com.lelanor.projects.codebreakers.datatypes.GameMode;
import com.lelanor.projects.codebreakers.datatypes.GameType;
import com.lelanor.projects.codebreakers.datatypes.Result;
import com.lelanor.projects.codebreakers.factories.PlayerCharacteristics;

public class CodeMaker extends Player{

    private PlayerCharacteristics makerCharacteristics;
    private Combination combination;
    private int size;
    private int range;

    /*public CodeMaker(int size, int range){
        setSize(size);
        setRange(range);
    }*/

    public CodeMaker(GameType gameType, GameMode gameMode){

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
}
