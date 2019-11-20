package Controleur;

import java.util.HashMap;

/**
 * Created by Fabien on 29/04/2017
 *  @author Fabien
 *
 * une priority queue super pratique pour l'a*
 */
public class PlayerPriorityQueue extends java.util.PriorityQueue<Node> {

    private HashMap<Integer, Node> hashMap;

    public PlayerPriorityQueue() {
        //Declaration of the comparator
        super((Node node1, Node node2) -> (int) (node1.getTotalEvaluation() - node2.getTotalEvaluation()));
        hashMap = new HashMap<>();
    }

    @Override
    public boolean add(Node node) {
        //If the node is not already contained: add it
        if (!hashMap.containsKey(node.hashCode())) {
            hashMap.put(node.hashCode(), node);
            return super.add(node);
        }
        /*Node presentNode = hashMap.get(node.hashCode());
        //If the node is already contained, but more optimistic: delete the old one from the HashMap, and new request to add the node
        if (presentNode.getPredecessorHashCode() != null && node.getCost() < presentNode.getCost()) {
            hashMap.remove(presentNode.hashCode());
            this.add(node);
        }
        //If the node is already contained and less optimistic: do nothing*/
        return false;
    }

    @Override
    public Node poll() {
        hashMap.remove(super.peek().hashCode());
        return super.poll();
    }

    @Override
    public void clear() {
        super.clear();
        hashMap.clear();
    }

    @Override
    public boolean isEmpty() {
        if (super.isEmpty() != hashMap.isEmpty()) {
            System.out.println("Error in PlayerPriorityQueue.isEmpty(): HashMap and PlayerPriorityQueue are not synchronous.");
        }
        return super.isEmpty();
    }

    @Override
    public int size() {
        if (hashMap.size() != super.size())
            System.out.println("Error in PlayerPriorityQueue.size(): sizes of HashMap and PlayerPriorityQueue are not synchronous");
        return hashMap.size();
    }
    /**/
}
