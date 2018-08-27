package com.lelanor.projects.codebreakers.datatypes;

import com.lelanor.projects.codebreakers.Game;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

public class CombinationLists {

    private List<Combination> combinationList = new ArrayList<>();

    public CombinationLists() {
        combinationListGenerator();
        System.out.println("liste créé");
    }

    public void combinationListGenerator() {
        int max = 1;
        for (int i = 0; i < Game.getCombinationSize(); i++) {
            max *= Game.getRange();
        }
        List<Combination> combinationList = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            String string = String.format("%0" + Game.getCombinationSize() + "d", parseLong(Integer.toString(i, Game.getRange())));
            combinationList.add(new Combination(string));
        }
        setCombinationList(combinationList);
    }

    public List<Combination> getCombinationList() {
        return combinationList;
    }

    public void setCombinationList(List<Combination> pCombinationList) {
        this.combinationList = combinationList;
    }
}
