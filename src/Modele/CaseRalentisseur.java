package Modele;

/**
 * * classe fille de la classe case implémenté
 * Created by Fabien on 30/04/2017
 */
public class CaseRalentisseur extends CaseImplementee{

    public CaseRalentisseur(){
        setAbilityToMove(true);
    }

    public CaseRalentisseur(short lig, short col){
        super(lig,col);
        setAbilityToMove(true);
    }

    /**
     *
     * @return
     * a string
     */
    @Override
    public String toString(){
        return ".";
    }

    /**
     *
     * @return
     *  the cost
     */
    @Override
    public byte getCost(){return (byte) 3;}
}
