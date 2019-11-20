/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *Inreface de case
 * @author Léo
 */
public interface Case {
    
    //Methods

    /**
     *
     * @return
     * posX
     */
    short getPosX();
    /**
     *
     * @return
     * posY
     */
    short getPosY();
    /**
     *
     * @return
     * true if we can move to case
     */
    boolean canMoveToCase();

    /**
     *
     * @param x
     * set X
     */
    void setPosX(short x);
    /**
     *
     * @param y
     * set y
     */
    void setPosY(short y);

    /**
     *
     * @param ability
     * set ability
     */
    void setAbilityToMove(boolean ability);

    /**
     *
     * @return cost
     */
    byte getCost();
}
