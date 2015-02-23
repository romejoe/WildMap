package com.stantonj.WildMap.Walker;

import com.stantonj.WildMap.MapNode;

/**
 * Created by Joseph Stanton on 2/22/15.
 */
public interface iWalker<V> {
    public V Operate(String key, MapNode<V> node);
    public boolean ContinueDescent(String key, MapNode<V> node);

    boolean isGoal(String remainder, MapNode<V> node);
}
