package Controleur;

import Modele.CaseImplementee;
import Modele.CaseTrou;

/**
 * Classe noeud qui contient les information joueur a un certain moment
 * Created by Fabien on 29/04/2017
 *  @author Fabien
 */

public class Node {
    //variables
    private int cost;
    private float heuristic;
    private CaseImplementee posPlayer;
    private final Integer predecessorHashCode;

    /**
     *
     * @param casetrou
     *      represent the position
     */
    public Node(CaseTrou casetrou) {this(0, casetrou);}

    /**
     *
     * @param cost
     *      cost of the node
     * @param casetrou
     *      represent the position
     */
    public Node(int cost, CaseTrou casetrou) {
        this(cost, casetrou, null);
    }

    /**
     *
     * @param cost
     *      cost of the node
     * @param casetrou
     *      represent the position
     * @param predecessorHashCode
     *      in order to have the path to success
     *
     */
    public Node(int cost, CaseTrou casetrou, Integer predecessorHashCode) {
        this(cost, casetrou, predecessorHashCode, 0);
    }

    /**
     *
     * @param cost
     *      cost of the node
     * @param casetrou
     *      represent the position
     * @param predHashCode
     *      in order to have the path to success
     * @param newPosHeuristic
     *      the heuristic of the new node
     */
    public Node(int cost, CaseTrou casetrou, Integer predHashCode, float newPosHeuristic) {
        this.posPlayer = new CaseTrou();
        this.posPlayer = casetrou;
        this.cost = cost;
        this.predecessorHashCode = predHashCode;
        this.heuristic = newPosHeuristic;
    }

    /**
     *
     * @param o
     *      an object
     * @return
     *      if they are equal
     */
    @Override
    public boolean equals(Object o) {
        //If both objects are the same instance, they are equal.
        if (this == o) return true;
        //If both objects classes are different, both objects are different.
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return (node.getPosPlayerX() == this.getPosPlayerX() && node.getPosPlayerY() == this.getPosPlayerY());
    }

    /**
     *
     * @return
     *      return the hashcode of the node
     */
    @Override
    public int hashCode() {
        String hash = "";
        hash += posPlayer.getPosX()+","+posPlayer.getPosY();
        return hash.hashCode();
    }

    /**
     *
     * @return
     * the cost og the node
     */
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setPosPlayerX(short lig){ this.posPlayer.setPosX(lig);}

    public void setPosPlayerY(short col){ this.posPlayer.setPosY(col);}

    /**
     *
     * @return
     * the pos of the player in row
     */
    public short getPosPlayerX(){ return this.posPlayer.getPosX();}

    /**
     *
     * @return
     * return the pos of the player in column
     */
    public short getPosPlayerY(){ return this.posPlayer.getPosY();}

    public CaseImplementee getCasePos(){ return this.posPlayer;}

    public void setHeuristic(float heuristic) {
        this.heuristic = heuristic;
    }

    /**
     *
     * @return
     * total evaluation
     */
    public float getTotalEvaluation() {
        return heuristic + cost;
    }

    /**
     *
     * @return
     * predecessor hach code
     */
    public Integer getPredecessorHashCode() {
        return predecessorHashCode;
    }

    /**
     *
     * @return
     * string of the node with all the information
     */
    @Override
    public String toString() {
        return "Node{" +
                "cost=" + cost +
                ", \ntotal cost=" + getTotalEvaluation() +
                ", \nPosPlayer=" + posPlayer.getPosX() +" "+posPlayer.getPosY()+
                '}';
    }/**/
}
