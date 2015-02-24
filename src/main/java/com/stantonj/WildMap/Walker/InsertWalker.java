package com.stantonj.WildMap.Walker;

import com.stantonj.WildMap.MapNode;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles the insertion of values into the WildMap
 * <p>
 * Creates new MapNodes under the current node if they do not already exist and inserts newValue into the final node
 * @param <V> type of map values
 */
public class InsertWalker<V> implements iWalker<V>{

    /**
     * Value to be inserted
     */
    private V newValue;

    /**
     * @param value Value to be inserted
     */
    public InsertWalker(V value){
        newValue = value;
    }

    /**
     * Either inserts a new node under the current one or inserts new value into the node
     * @param key Key used to determine how to operate on node
     * @param node Node to operate on
     * @return newValue if value is inserted, else null
     */
    @Override
    public V Operate(String key, MapNode node) {
        Map<String, MapNode<V>> Children = node.getChildren();
        if(key.startsWith("\\*") && !Children.containsKey("*")){
            MapNode<V> newNode = new MapNode<V>();
            newNode.setSelector("\\*");
            newNode.setWildcard(false);
            newNode.setParent(node);
            Children.put("\\*", newNode);
        }
        else if(key.length() > 0){
            Pattern pat = Pattern.compile("((?<=[^\\\\])\\*)|(^\\*)");
            Matcher mat = pat.matcher(key);
            int offset;
            if(mat.find()) {
                offset = mat.start();
            }
            else{
                offset = key.length();
            }
            if(offset == 0){
                offset = 1;
            }
            String selector = key.substring(0,offset);

            if(!Children.containsKey(selector)){
                MapNode<V> newNode = new MapNode<V>();
                newNode.setSelector(selector);
                if(selector.equals("*")){
                    newNode.setWildcard(true);
                    newNode.getChildren().put("*", newNode);
                }
                newNode.setParent(node);
                //newNode.setParent(node);
                Children.put(selector, newNode);
            }
        }
        else{
            node.setValue(newValue);
            return newValue;
        }

        return null;
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
