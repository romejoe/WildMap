package com.stantonj.WildMap.Walker;

import com.stantonj.WildMap.MapNode;

import java.util.Map;

/**
 * Created by Joseph Stanton on 2/22/15.
 */
public class InsertWalker<V> implements iWalker<V>{

    private V newValue;

    public InsertWalker(V value){
        newValue = value;
    }

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
            String selector = key.substring(0,1);
            if(!Children.containsKey(selector)){
                MapNode<V> newNode = new MapNode<V>();
                newNode.setSelector(selector);
                if(selector.equals("*")){
                    newNode.setWildcard(true);
                    newNode.getChildren().put("*", newNode);
                }
                else{
                    newNode.setWildcard(false);
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

    @Override
    public boolean ContinueDescent(String key, MapNode<V> node) {
        return key.length() != 0;
    }

    @Override
    public boolean isGoal(String remainder, MapNode<V> node) {
        return remainder.length() == 0;
    }
}
