package com.stantonj.WildMap;

import com.stantonj.WildMap.WalkController.BreadthFirstWalkController;
import com.stantonj.WildMap.WalkController.iWalkController;
import com.stantonj.WildMap.Walker.InsertWalker;
import com.stantonj.WildMap.Walker.RetrieveWalker;
import com.stantonj.WildMap.Walker.iWalker;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Joseph Stanton on 2/22/15.
 */
public class WildMap<V> implements Map<String, V> {
    private MapNode<V> rootNode;
    private iWalkController<V> walkController;
    private Map<String, MapNode<V>> ValueMaps;

    public WildMap(){
        this(new BreadthFirstWalkController<V>());
    }

    public WildMap(iWalkController<V> newWalkController){
        walkController = newWalkController;
        rootNode = new MapNode<V>();
        ValueMaps = new HashMap<String, MapNode<V>>();
    }

    public void setWalkController(iWalkController<V> newWalkController){
        walkController = newWalkController;
    }

    @Override
    public int size() {
        return ValueMaps.size();
    }

    @Override
    public boolean isEmpty() {
        return ValueMaps.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        throw new NotImplementedException();
    }

    @Override
    public boolean containsValue(Object value) {
        throw new NotImplementedException();
    }

    @Override
    public V get(Object key) {
        iWalker<V> walky = new RetrieveWalker<V>();
        return walkController.WalkMap(this, (String) key, walky);
    }

    @Override
    public V put(String key, V value) {
        iWalker walky = new InsertWalker<V>(value);
        return walkController.WalkMap(this, key, walky);
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void clear() {
        throw new NotImplementedException();
    }

    @Override
    public Set<String> keySet() {
        throw new NotImplementedException();
    }

    @Override
    public Collection<V> values() {
        throw new NotImplementedException();
    }

    @Override
    public Set<Entry<String, V>> entrySet() {
        throw new NotImplementedException();
    }

    @Override
    public void putAll(Map m) {

    }


    public MapNode<V> getRootNode() {
        return rootNode;
    }
}
