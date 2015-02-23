package com.stantonj.WildMap.WalkController;

import com.stantonj.WildMap.Walker.iWalker;
import com.stantonj.WildMap.WildMap;

import java.util.List;

/**
 * Created by Joseph Stanton on 2/22/15.
 */
public interface iWalkController<V> {
    public V WalkMap(WildMap<V> map, String Key, iWalker<V> walker);
}
