package Controleur.Heuristics;

import Controleur.Search;

import java.util.ArrayList;

/**
 * C'est la classe mère heuristique
 * parfaite si on veut implementer de nouvelles heuristiques a partir de cette classe
 * Created by Fabien on 29/04/2017
 *  @author Fabien
 */
public class Heuristic {

    //Variables
    protected Search theSearchField;
    protected ArrayList<ArrayList<Float>> heuristicField;

    /**
     *
     * @param searchField
     *      searchfield for the a*
     */
    public Heuristic(Search searchField) {
        //Initialise every list and get the searchField data so it can be read while calculating heuristic
        theSearchField = searchField;
        heuristicField = new ArrayList<>();
        for (int i = 0; i < searchField.getRowNumber(); i++) {
            heuristicField.add(new ArrayList<>());
        }
    }
    /**
     * @param lig
     * la ligne
     * @param col
     * la colonne
     * @return
     *      the heuristic at a pos
     */
    public float getHeuristicAtPos(short lig, short col) {
        return heuristicField.get(lig).get(col);
    }

    @Override
    public String toString() {
        String output = "";
        for (short i = 0; i < theSearchField.getRowNumber(); i++) {
            for (short j = 0; j < theSearchField.getColumnNumber(); j++) {
                output += " " + heuristicField.get(i).get(j);
            }
            output += "\n";
        }
        return output;
    }

    public String type() {
        return "Heuristic";
    }
    /**/
}