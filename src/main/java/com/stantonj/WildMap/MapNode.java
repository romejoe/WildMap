package com.stantonj.WildMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joseph Stanton on 2/22/15.
 */
public class MapNode<V> {

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public MapNode<V> getParent() {
        return Parent;
    }

    public void setParent(MapNode<V> parent) {
        Parent = parent;
    }

    public Map<String, MapNode<V>> getChildren() {
        return Children;
    }

    public void setChildren(Map<String, MapNode<V>> children) {
        Children = children;
    }

    public boolean isWildcard() {
        return isWildcard;
    }

    public void setWildcard(boolean isWildcard) {
        this.isWildcard = isWildcard;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    private String selector;
    private V value;
    private MapNode<V> Parent;
    private Map<String, MapNode<V>> Children;
    private boolean isWildcard;


    public MapNode() {
        Children = new HashMap<String, MapNode<V>>();
        isWildcard = false;
    }
}
