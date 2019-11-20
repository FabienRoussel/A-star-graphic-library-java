/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 * classe mère des cases différents type de case
 * @author Léo
 */
public class CaseImplementee implements Case {
    
    private short posX;
    private short posY;
    private boolean free;

    public CaseImplementee(short lig, short col){
        setPosX(lig);
        setPosY(col);
    }

    public CaseImplementee(){
    }
    /**
     *
     * @return
     * posX
     */
    @Override
    public short getPosX() {
        return posX;
    }
    /**
     *
     * @return
     * posY
     */
    @Override
    public short getPosY() { return posY; }

    /**
     *
     * @return
     * true if we can move to case
     */
    @Override
    public boolean canMoveToCase() {
        return free;
    }


    /**
     *
     * @param x
     * set X
     */
    @Override
    public void setPosX(short x){ posX = x;}


    /**
     *
     * @param y
     * set Y
     */
    @Override
    public void setPosY(short y) {posY = y;}
    /**
     *
     * @param ability
     * set ability
     */
    @Override
    public void setAbilityToMove(boolean ability){free = ability;}
    /**
     *
     * @return cost
     */
    @Override
    public byte getCost(){return (byte) 1;}

    @Override
    public String toString(){
        if(free) return "_";
        else return "X";
    }
    
}
