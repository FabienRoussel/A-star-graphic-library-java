/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 * classe fille de la classe case implémenté
 * @author Fabien
 */
public class CaseMur extends CaseImplementee{

    public CaseMur(){
        setAbilityToMove(false);
    }

    public CaseMur(short lig, short col){
        super(lig,col);
        setAbilityToMove(false);
    }

    /**
     *
     * @return
     * string
     */
    @Override
    public String toString(){
        return "X";
    }

    /**
     *
     * @return
     * return the cost of the case
     */
    @Override
    public byte getCost(){return Byte.MAX_VALUE;}
}
