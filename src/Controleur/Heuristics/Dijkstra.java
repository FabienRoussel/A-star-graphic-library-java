package Controleur.Heuristics;

import Controleur.Search;
import Modele.Direction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by Fabien on 29/04/2017
 * Heritates from heuristics
 * @author Fabien
 * @version 2.2
 *
 * We call it dijkstra but it doesn't seem to exist on the internet
 * It is inspired by the algorithm of Dijkstra seen with our dear friend Jean Pierre Segado
 */
public class Dijkstra extends Heuristic{

    //we prepare a list of direction
    private ArrayList<Direction> directionsList;
    //overload contructor

    /**
     *
     * @param searchField
     *      need to be connected with the SearchFiedl for the A*
     */
    public Dijkstra(Search searchField) {
        super(searchField);
        directionsList = new ArrayList<>();
        buildDirectionsList();
        //Heuristic values are calculated as soon as the object is declared.
        calculateValues();
    }

    /**
     * only call distance Dijkstra
     *
     * @version 0.9
     *      will be updated soon with new heuristics
     */
    private void calculateValues() {
        distanceDijkstra();
    }

    /**
     * create the distance
     */
    private void buildDirectionsList() {
        directionsList.add(Direction.UP);
        directionsList.add(Direction.DOWN);
        directionsList.add(Direction.LEFT);
        directionsList.add(Direction.RIGHT);
    }

    /**
     * Base of the heuristic
     *
     */
    private void distanceDijkstra() {
        //ici on va devoir travailler en 1D pour faciliter la priority queue et la hashMap.
        short goalLocationX = theSearchField.getLabyrinthe().getArriveeX();
        short goalLocationY = theSearchField.getLabyrinthe().getArriveeY();
        short goalLocation = toOneD(goalLocationX, goalLocationY);
        short actualPos, nextPos;
        float addition;
        short tailleX = theSearchField.getRowNumber();
        short tailleY = theSearchField.getColumnNumber();
        int sizeOfLab = tailleX * tailleY;

        //Create a priority queue
        PriorityQueue<Short> myPriority = new PriorityQueue<>(
                (nb1, nb2) -> (int) (heuristicField.get(toTwoDLig(nb1)).get(toTwoDCol(nb1)) -
                        heuristicField.get(toTwoDLig(nb2)).get(toTwoDCol(nb2))));

        //Define a marking table
        Set<Short> isMarked = new HashSet<>();
        //Initialise every location heuristic at a maximal value
        for (short i = 0; i < tailleX; i++) {
            for (short j = 0; j < tailleY; j++){
                heuristicField.get(i).add(Float.MAX_VALUE);
            }
        }

        //The goal location heuristic is set to 0
        heuristicField.get(goalLocationX).set(goalLocationY, (float) 0);

        //Insert first location (goal) in the priority queue
        myPriority.add(goalLocation);

        //While the priority queue is not empty
        while (!myPriority.isEmpty()) {
            //Dequeue the top location
            actualPos = myPriority.poll();

            //Mark the location dequeued
            isMarked.add(actualPos);

            //For every adjacent location that has not been marked yet
            for (Direction dir : directionsList) {
                //Calculate the value of the neighbour location
                nextPos = (short) (actualPos + dir.getDirectionValueOneD(tailleY));
                if (nextPos < sizeOfLab && nextPos >= 0 && !((dir==Direction.RIGHT && toTwoDCol(nextPos)==0) ||
                                                            (dir==Direction.LEFT && toTwoDCol(nextPos)==tailleY-1))) {
                    //If the neighbour location has not already been marked
                    if (!isMarked.contains(nextPos) && (theSearchField.getCasetypeFromPos(toTwoDLig(nextPos), toTwoDCol(nextPos)).canMoveToCase())) {
                        //The heuristic of the neighbour location is equal to its parent location + the cost of the block at this neighbour location
                        addition = (heuristicField.get(toTwoDLig(actualPos)).get(toTwoDCol(actualPos)) +
                                theSearchField.getBlockCost(nextPos));

                        //If the calculated cost for the neighbour location is inferior to any cost calculated before for this location
                        if (addition < heuristicField.get(toTwoDLig(nextPos)).get(toTwoDCol(nextPos)) && addition > -1) {
                            //Enqueue this neighbour location with its cost updated
                            heuristicField.get(toTwoDLig(nextPos)).set(toTwoDCol(nextPos), addition);
                            myPriority.add(nextPos);
                        }
                    }
                }
            }
        }
    }

    /**
     *
     * @return the labyrinthe in string (useful with print)
     */
    @Override
    public String toString() {
        String output = "";
        for (short i = 0; i < theSearchField.getRowNumber(); i++) {
            for (short j = 0; j < theSearchField.getColumnNumber(); j++) {
                output += " " + Math.round(heuristicField.get(i).get(j));
            }
            output += "\n";
        }
        return output;
    }

    /**
     *
     * @param lig
     *      parameter row
     * @param col
     *      parameter column
     * @return short position
     *      absolute position in the matrix
     */
    public short toOneD(short lig, short col) {
        return (short) (lig*theSearchField.getColumnNumber() + col);
    }

    /**
     *
     * @param pos
     *      ABbsolute pos
     * @return
     *      row with the absolute pos
     */
    public short toTwoDLig(short pos) {
        return (short) (pos / theSearchField.getColumnNumber());
    }
    /**
     *
     * @param pos
     *      ABbsolute pos
     * @return
     *      column with the absolute pos
     */
    public short toTwoDCol(short pos) {
        return (short) (pos % theSearchField.getColumnNumber());
    }

    /**
     * @return
     *      the map of the heuristic in println()
     */
    @Override
    public String type() {
        return "Dijkstra Heuristic";
    }
    /**/
}

