package com.stantonj.WildMap.Walker;

import com.stantonj.WildMap.MapNode;

/**
 * Interface to operate on nodes and determine walking movements
 * @param <V> type of map values
 */
public interface iWalker<V> {
    /**
     * Operation to perform on a node
     * @param key Key used to determine how to operate on node
     * @param node Node to operate on
     * @return depends on implementation
     */
    public V Operate(String key, MapNode<V> node);

    /**
     * Determines whether an iWalkController should descend to children nodes
     * @param key Key used to determine how to proceed
     * @param node Node to operate on
     * @return true if an iWalkController should descend to children nodes
     */
    public boolean ContinueDescent(String key, MapNode<V> node);

    /**
     * Determines whether node is the goal
     * @param remainder Key used to determine how to proceed
     * @param node Node to operate on
     * @return true if the node is a goal node
     */
    boolean isGoal(String remainder, MapNode<V> node);
}
