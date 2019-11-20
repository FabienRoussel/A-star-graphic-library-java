package Controleur;

import Controleur.Heuristics.Heuristic;
import Modele.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import static Vue.Menu.displayLabByAI;

/**
 * Created by Fabien on 28/04/2017
 *  @author Fabien
 *  classe principale de l'a*
 */
public class Search {
    private String levelName;

    //Structures containing useful data
    private Labyrinthe myMaze;

    //Search tools
    private ArrayList<Direction> directionsList;
    private boolean isSearching;

    /**
     *
     * @param levelName
     * nom du niveau
     */
    public Search(String levelName) {
        isSearching = false;
        this.levelName = levelName;
        directionsList = new ArrayList<>();
        //Read the level file and store it into the dedicated structures
        //loadDataFromLevel();
    }

    /**
     *
     * @param lab
     * A instance of labyrinthe
     */
    public Search(Labyrinthe lab) {
        isSearching = false;
        directionsList = new ArrayList<>();
        myMaze =lab;
        //Read the level file and store it into the dedicated structures
        //loadDataFromLevel();
    }

    /**
     *
     * @param heuristic
     * need the heuristic to perfome the astar
     * @return
     * a stack of node as the solution
     */
    public Stack<CaseImplementee> Astar(Heuristic heuristic) {
        System.out.println("\n" + heuristic.type() + "\n");
        clearSearch();
        //We build the directions list with "true" parameter when we want the NONE direction to be in the list, false otherwise
        buildDirectionsList(false);
        //Counters of nodes
        long nodesAddedToPriorityQueue = 0;
        long nodesDequeued = 0;
        //Tools: PlayerPriorityQueue (OpenList), Hashmap (ClosedList)
        PlayerPriorityQueue playerPriorityQueue = new PlayerPriorityQueue();
        HashMap<Integer, Node> closedList = new HashMap<>();
        //Creation of the initial node
        short posInitX= myMaze.getPosX();
        short posInitY= myMaze.getPosY();
        CaseTrou startPos = new CaseTrou(posInitX,posInitY);
        Node node = new Node(startPos);
        //Storing initial node
        playerPriorityQueue.add(node);
        //Creating chronometer
        long startTime = System.currentTimeMillis();
        //The algorithm keeps running while there is something in the priority queue
        while (!playerPriorityQueue.isEmpty()) {
            //Retrieving the most optimistic node we found yet
            node = playerPriorityQueue.poll();
            nodesDequeued++;
            //Mark this node to avoid revisiting it
            closedList.putIfAbsent(node.hashCode(), node);
            //Test if this node is solution
            if (isPlayerOnGoal(node)) break;
            //Search for neighbour states
            for (Direction direction : directionsList) {
                //If the specified robot can move in the specified direction
                if (myMaze.canMoveObjectToCase(node, direction)) {
                    //Calculate its future location
                    short newPos = getNewPosWithDir(node, direction);
                    if(newPos>-1){
                        //System.out.println(node +"\n\n");
                        //Create of the child node with the parent child model
                        CaseTrou myCase= new CaseTrou(getRowFromPos(newPos), getColumnFromPos(newPos));
                        Node nodeChild = new Node(node.getCost() + getBlockCost(newPos), myCase, node.hashCode(), heuristic.getHeuristicAtPos(getRowFromPos(newPos), getColumnFromPos(newPos)));
                        //Update the robots list with the move of the robot
                        //          nodeChild.moveRobot(robotIndex, direction);
                        //          nodeChild.setHeuristic(heuristic.getTotalHeuristic(nodeChild.getListOfRobotsLocations()));
                        //If already in closedList but the new node is more optimistic, we just replace it
                        if (closedList.containsKey(nodeChild.hashCode())) {
                            if (closedList.get(nodeChild.hashCode()).getCost() > nodeChild.getCost()) {
                                closedList.remove(nodeChild.hashCode());
                                closedList.put(nodeChild.hashCode(), nodeChild);
                            }
                        } else {
                            //If relevant, add the new node to the priority queue (tests are made internally)
                            if (playerPriorityQueue.add(nodeChild)) nodesAddedToPriorityQueue++;
                        }
                    }

                }
            }
        }
        //Display the diagnosis of the search onto the console
        if (!playerPriorityQueue.isEmpty())
            System.out.println("Solution found in " + (float) ((System.currentTimeMillis() - startTime) / 1000) + "s!\nNodes dequeued: " + nodesDequeued + "\nNodes added to priority queue: " + nodesAddedToPriorityQueue);
        else
            System.out.println("Solution not found after " + (float) ((System.currentTimeMillis() - startTime) / 1000) + "s:\nNodes dequeued: " + nodesDequeued + "\nNodes added to priority queue: " + nodesAddedToPriorityQueue);
        System.out.println("Total cost of solution: " + node.getCost());
        //Decode the solution found to build the robots path (their stack of directions)
        return constructPaths(node, closedList);
    }

    /**
     *
     * @param node
     * a Node
     * @return
     * true if player is on goal else false
     */
    public boolean isPlayerOnGoal(Node node) {
        return node.getPosPlayerX() == myMaze.getArriveeX() && node.getPosPlayerY() == myMaze.getArriveeY();

    }

    /**
     *
     * @param winNode
     * the last node or winN Node
     * @param closedList
     * the hashmap
     * @return
     * a stack of node as the solution
     *
     */
    private Stack<CaseImplementee> constructPaths(Node winNode, HashMap<Integer, Node> closedList) {
        //We start from the goal node and backtrack until the start node.
        //Between every parent and child node we retrieve the index of the robot which has to move, and the direction it has to take
        Stack<CaseImplementee> solution = new Stack<>();
        while (winNode.getPredecessorHashCode() != null) {
            if (!closedList.containsKey(winNode.getPredecessorHashCode()))
                System.out.println("Error in SearchField.constructPaths(): Could not find predecessor in hashMap.");
            solution.push(winNode.getCasePos());
            winNode = closedList.get(winNode.getPredecessorHashCode());
        }
        isSearching = false;
        return solution;

    }

    /**
     *
     * @param solution
     * the stack of solution node
     */
    public void displaySol(Stack<CaseImplementee> solution){
        while (!solution.isEmpty())
            displayLabByAI(myMaze, solution.pop());
    }
    /**
     * create the distance
     */
    private void buildDirectionsList(boolean addNone) {
        directionsList.add(Direction.LEFT);
        directionsList.add(Direction.UP);
        directionsList.add(Direction.RIGHT);
        directionsList.add(Direction.DOWN);
        if (addNone) directionsList.add(Direction.NONE);
    }

    /**
     * clear search if something wrong happened
     */
    private void clearSearch() {
        isSearching = true;
        directionsList.clear();
    }

    /**
     *
     * @param node
     * the Node
     * @param direction
     * a direction
     * @return
     * the nex pos
     */
    public short getNewPosWithDir(Node node, Direction direction) {
        short newPos = (short) (node.getPosPlayerX()*getColumnNumber()+node.getPosPlayerY() + direction.getDirectionValueOneD(getColumnNumber()));
        if (newPos < getSize() && newPos >= 0 && !((direction==Direction.RIGHT && getColumnFromPos(newPos)==0) ||
                                    (direction==Direction.LEFT && getColumnFromPos(newPos)==getColumnNumber()-1))) {
            return newPos;
        }
        //System.out.println("Error in SearchField.getNewPosWithDir(): Index out of range -> pos=" + newPos);
        return -1;
    }

    /**
     *
     * @param position
     * th postion
     * @return
     * the cost of the block
     */
    public byte getBlockCost(short position) {return myMaze.getValueFromLigColMatrice(getRowFromPos(position),
            getColumnFromPos(position)).getCost();}

    public short getPosFromCoords(short row, short column) {
        return (short) (row * getColumnNumber() + column);
    }

    public short getColumnFromPos(short position) {
        return (short) (position % getColumnNumber());
    }

    public short getRowFromPos(short position) {
        return (short) (position / getColumnNumber());
    }

    public Case getCasetypeFromPos(short lig, short col) {
        return myMaze.getValueFromLigColMatrice(lig, col);
    }

    public short getRowNumber() { return myMaze.getTailleX();  }

    public short getColumnNumber() {  return myMaze.getTailleY();}

    public int getSize() {  return myMaze.getTailleY()*myMaze.getTailleY();}

    public String getLevelName() {
        return levelName;
    }

    public Labyrinthe getLabyrinthe(){ return myMaze;}

}
