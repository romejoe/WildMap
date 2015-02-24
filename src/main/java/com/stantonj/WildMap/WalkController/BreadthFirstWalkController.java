package com.stantonj.WildMap.WalkController;

import com.stantonj.WildMap.MapNode;
import com.stantonj.WildMap.Walker.iWalker;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Implements the iWalkController using the breadth first search algorithm.
 * @param <V>
 * @since 0.1
 * @author Joe Stanton
 * @version 0.1
 */
public class BreadthFirstWalkController<V> implements iWalkController<V> {

    private class WalkState{
        String Path;
        String Remainder;
        //List<String> PathParts;
        MapNode<V> node;
    }


    /**
     * Implements the breadth first search algorithm to traverse the map
     * @param initialNode Node to begin traversal at
     * @param Key Key to use as traversal "guide"
     * @param walker Visitor object used to operate on each node
     * @return result of walker's operation on final node
     */
    @Override
    public V WalkMap(final MapNode<V> initialNode, final String Key, iWalker<V> walker) {
        final Queue<WalkState> toCheck = new LinkedBlockingQueue<WalkState>();
        toCheck.add(new WalkState(){{
            Path = "";
            Remainder = Key;
            node = initialNode;
            //PathParts = new LinkedList<String>();
        }});

        while(!toCheck.isEmpty()){
            WalkState CurState = toCheck.remove();
            V result = walker.Operate(
                    CurState.Remainder,
                    CurState.node
            );
            if(walker.isGoal(CurState.Remainder, CurState.node))
                return result;

            if(walker.ContinueDescent(CurState.Remainder, CurState.node)){ // && CurState.Remainder != null
                String Selector;// = CurState.Remainder.substring(0,1);
                String Remainder;// = CurState.Remainder.substring(1);
                if(CurState.Remainder.startsWith("\\*")){
                    Selector = "\\*";
                    Remainder = CurState.Remainder.substring(2);
                }
                else{
                    Selector = CurState.Remainder.substring(0,1);
                    Remainder = CurState.Remainder.substring(1);
                }
                if(CurState.node.getChildren().containsKey(Selector)){
                    WalkState newState = new WalkState();
                    newState.Path = CurState.Path + Selector;
                    newState.Remainder = Remainder;
                    newState.node = CurState.node.getChildren().get(Selector);
                    toCheck.add(newState);
                }
                if(CurState.node.getChildren().containsKey("*")){
                    WalkState newState = new WalkState();
                    newState.Path = CurState.Path + Selector;
                    newState.Remainder = Remainder;
                    newState.node = CurState.node.getChildren().get("*");
                    toCheck.add(newState);
                }
                /*if(CurState.node.isWildcard()){
                    WalkState newState = new WalkState();
                    newState.Path = CurState.Path + Selector;
                    newState.Remainder = Remainder;
                    newState.node = CurState.node;
                    toCheck.add(newState);
                }*/
            }

        }

        return null;
    }

}
