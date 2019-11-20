/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 * * classe fille de la classe case implémenté
 * @author Fabien
 */
public class CaseTrou extends CaseImplementee{

    public CaseTrou(){
        setAbilityToMove(true);
    }
    public CaseTrou(short lig, short col){
        setAbilityToMove(true);
        setPosX(lig);
        setPosY(col);
    }

    /**
     *
     * @return
     * return String
     */
    @Override
    public String toString(){
        return "_";
    }


    /**
     *
     * @return
     * return the cost of the case
     */
    @Override
    public byte getCost(){return (byte)1;}

}
