package com.lelanor.projects.codebreakers.datatypes;

import com.lelanor.projects.codebreakers.Game;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

/**
 * This class manage the list of all possible combinations for the Knuth's algorithm
 */
public class CombinationLists {
    /**
     * stocks the list of the actualised possible combinations
     */
    private List<Combination> combinationList = new ArrayList<>();

    /**
     * generic constructor
     */
    public CombinationLists() {
        combinationListGenerator();
    }

    /**
     * create the list of all possible combinations of a determined size and range
     */
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

    /**
     * getter
     *
     * @return the list of combinations
     */
    public List<Combination> getCombinationList() {
        return this.combinationList;
    }

    /**
     * setter
     *
     * @param pCombinationList actualise the list
     */
    public void setCombinationList(List<Combination> pCombinationList) {
        this.combinationList = pCombinationList;
    }
}
