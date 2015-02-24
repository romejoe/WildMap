package com.stantonj.WildMap.Walker;

import com.stantonj.WildMap.MapNode;

import java.util.Map;

/**
 * Handles the retrieval of values fo wild maps
 * @param <V> type of map values
 */
public class RetrieveWalker<V> implements iWalker<V>{

    /**
     * Retrieves the value of the current node
     * @param key Key used to determine how to operate on node
     * @param node Node to operate on
     * @return value of the current node
     */
    @Override
    public V Operate(String key, MapNode<V> node) {
        return node.getValue();
    }

    /**
     * Determines whether an iWalkController should descend to children nodes
     * @param key Key used to determine how to proceed
     * @param node Node to operate on
     * @return true if there is still part of a key remaining, false otherwise
     */
    @Override
    public boolean ContinueDescent(String key, MapNode<V> node) {
        return key.length() != 0;
    }

    /**
     * Determines whether node is the goal
     * @param remainder Key used to determine how to proceed
     * @param node Node to operate on
     * @return true if there is no part of the key remaining, false otherwise
     */
    @Override
    public boolean isGoal(String remainder, MapNode<V> node) {
        return remainder.length() == 0;
    }
}
