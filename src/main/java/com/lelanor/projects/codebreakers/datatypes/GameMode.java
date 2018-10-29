package com.lelanor.projects.codebreakers.datatypes;

/**
 * This enum lists the possible game modes
 */
public enum GameMode {
    /**
     * mode duel : the human and computer players play turn by turn
     */
    DUEL,
    /**
     * the human player is the codebreaker
     */
    ATTACK,
    /**
     * the human player is the codemaker
     */
    DEFENSE,
    /**
     * [available in debug mode only] the computer plays against itself
     */
    CPU_SOLO
}
