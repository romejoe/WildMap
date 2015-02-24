package com.stantonj.WildMap.WalkController;

import com.stantonj.WildMap.MapNode;
import com.stantonj.WildMap.Walker.iWalker;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Created by Joey on 2/24/15.
 */
public class DepthFirstWalkController<V> implements iWalkController<V> {

    private class WalkState{
        String Path;
        String Remainder;
        //List<String> PathParts;
        MapNode<V> node;
    }
/*
    private String[] keySplitter(String key){
        return key.split("((?<=[^\\\\])\\*)|(^\\*)");
    }
*/
    @Override
    public V WalkMap(final MapNode<V> initialNode, final String Key, iWalker<V> walker) {
        final Deque<WalkState> toCheck = new ArrayDeque<WalkState>();
        toCheck.addFirst(new WalkState(){{
            Path = "";
            Remainder = Key;
            node = initialNode;
        }});

        while(!toCheck.isEmpty()){
            WalkState curState = toCheck.removeFirst();
            V result = walker.Operate(
                    curState.Remainder,
                    curState.node
            );
            if(walker.isGoal(curState.Remainder, curState.node))
                return result;

            if(walker.ContinueDescent(curState.Remainder, curState.node)) {
                if(curState.node.getChildren().containsKey("*")){
                    WalkState wildNode = new WalkState();
                    wildNode.node = curState.node.getChildren().get("*");
                    wildNode.Path = curState.Path + curState.Remainder.charAt(0);
                    wildNode.Remainder = curState.Remainder.substring(1);
                    toCheck.addFirst(wildNode);
                }
                //add all children that match the key
                for(String k:curState.node.getChildren().keySet()){
                    if(curState.Remainder.startsWith(k)){
                        WalkState newNode = new WalkState();
                        newNode.node = curState.node.getChildren().get(k);
                        newNode.Path = curState.Path + curState.Remainder.substring(k.length());
                        newNode.Remainder = curState.Remainder.substring(k.length());
                        toCheck.addFirst(newNode);
                    }
                }
            }
        }
        return null;
    }
}
