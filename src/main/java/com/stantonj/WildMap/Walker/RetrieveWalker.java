package com.stantonj.WildMap.Walker;

import com.stantonj.WildMap.MapNode;

import java.util.Map;

/**
 * Created by Joseph Stanton on 2/22/15.
 */
public class RetrieveWalker<V> implements iWalker<V>{

    @Override
    public V Operate(String key, MapNode<V> node) {
        return node.getValue();
    }

    @Override
    public boolean ContinueDescent(String key, MapNode<V> node) {
        return key.length() != 0;
    }

    @Override
    public boolean isGoal(String remainder, MapNode<V> node) {
        return remainder.length() == 0;
    }
}
