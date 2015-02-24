package com.stantonj.WildMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Nodes that contains the data and the structure of WildMaps
 * @param <V> type of map values
 */
public class MapNode<V> {

    /**
     *  Returns the value of the current node
     * @return Value of the current node
     */
    public V getValue() {
        return value;
    }

    /**
     * Sets the value of the current node
     * @param value new value of the node
     */
    public void setValue(V value) {
        this.value = value;
        this.isSet = true;
    }

    /**
     * Returns the parent of the node
     * @return parent of the node
     */
    public MapNode<V> getParent() {
        return Parent;
    }

    /**
     * Sets the parent of the node
     * @param parent parent of the node
     */
    public void setParent(MapNode<V> parent) {
        Parent = parent;
    }

    /**
     * Returns the children of the node
     * @return children of the node
     */
    public Map<String, MapNode<V>> getChildren() {
        return Children;
    }

    /**
     * Replaces all of the children of the node
     * @param children new children of the node
     */
    public void setChildren(Map<String, MapNode<V>> children) {
        Children = children;
    }

    /**
     * Determines if the current node is a wildcard
     * @return wildcard status
     */
    public boolean isWildcard() {
        return isWildcard;
    }

    /**
     * Sets the wildcard status of the node
     * @param isWildcard new wildcard status
     */
    public void setWildcard(boolean isWildcard) {
        this.isWildcard = isWildcard;
    }

    /**
     * Returns the selector for the node
     * @return selector
     */
    public String getSelector() {
        return selector;
    }

    /**
     * Sets the selector for the node
     * @param selector
     */
    public void setSelector(String selector) {
        this.selector = selector;
    }

    /**
     * Determines if the value of the node has been set
     * @return true if the value has been set, false otherwise
     */
    public boolean isSet() {
        return isSet;
    }

    /**
     * Selector for the node
     * @type String
      */
    private String selector;

    /**
     * Value of this node
     * @type V
     */
    private V value;

    /**
     * Determine if the value has been set
     */
    private boolean isSet;

    /**
     * Parent of this node
     * @type MapNode<V>
     */
    private MapNode<V> Parent;

    /**
     * Children of this node
     * @type Map<String, MapNode<V>>
     */
    private Map<String, MapNode<V>> Children;

    /**
     * Determines whether this node is a wildcard node
     */
    private boolean isWildcard;

    /**
     * Constructor just initializes children and wildcard
     */
    public MapNode() {
        Children = new HashMap<String, MapNode<V>>();
        isWildcard = false;
    }
}
