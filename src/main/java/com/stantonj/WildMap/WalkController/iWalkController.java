package com.stantonj.WildMap.WalkController;

import com.stantonj.WildMap.MapNode;
import com.stantonj.WildMap.Walker.iWalker;

/**
 * Interface used to walk the internal graph of the WildMap.
 * @param <V> type of walker and map
 * @since 0.1
 * @author Joe Stanton
 * @version 0.1
 */
public interface iWalkController<V> {
    /**
     * Walks the map using the Visitor pattern and starting at the initial node.
     * @param initialNode Node to begin traversal at
     * @param Key Key to use as traversal "guide"
     * @param walker Visitor object used to operate on each node
     * @return result of walker's operation on final node
     */
    public V WalkMap(MapNode<V> initialNode, String Key, iWalker<V> walker);
}
