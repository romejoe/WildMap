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
 * This map implementation supports wildcard as keys to the map.
 * <p>
 * For example:<br/>
 * put("/test/1",1) <br/>
 * put("/test/*",-1) <br/>
 * <br/>
 * get("/test/55") == -1 <br/>
 * get("/test/1") == 1 <br/>
 * get("/t/") == null <br/>
 * @param <V> Type of value
 */
public class WildMap<V> implements Map<String, V> {
    /**
     * Base node of the map
     * @type MapNode<V>
     */
    private MapNode<V> rootNode;
    /**
     * iWalker used to walk the MapNodes
     * @type iWalkController<V>
     */
    private iWalkController<V> walkController;
    /**
     * Map values
     * @type Map<String, MapNode<V>>
     */
    private Map<String, MapNode<V>> ValueMaps;

    /**
     * Initializes the map using a BreadthFirstWalkController<V>
     */
    public WildMap(){
        this(new BreadthFirstWalkController<V>());
    }

    /**
     * Initializing the WildMap with the given iWalkController<V>
     * @param newWalkController
     */
    public WildMap(iWalkController<V> newWalkController){
        walkController = newWalkController;
        rootNode = new MapNode<V>();
        ValueMaps = new HashMap<String, MapNode<V>>();
    }

    /**
     * Changes the iWalkController being used by the map
     * @param newWalkController new iWalkController
     */
    public void setWalkController(iWalkController<V> newWalkController){
        walkController = newWalkController;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int size() {
        return ValueMaps.size();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isEmpty() {
        return ValueMaps.isEmpty();
    }

    /**
     * Not Implemented
     */
    @Override
    public boolean containsKey(Object key) {
        throw new NotImplementedException();
    }

    /**
     * Not Implemented
     */
    @Override
    public boolean containsValue(Object value) {
        throw new NotImplementedException();
    }

    /**
     * @inheritDoc
     */
    @Override
    public V get(Object key) {
        iWalker<V> walky = new RetrieveWalker<V>();
        return walkController.WalkMap(rootNode, (String) key, walky);
    }

    /**
     * @inheritDoc
     */
    @Override
    public V put(String key, V value) {
        iWalker walky = new InsertWalker<V>(value);
        return walkController.WalkMap(rootNode, key, walky);
    }

    /**
     * Not Implemented
     */
    @Override
    public V remove(Object key) {
        return null;
    }

    /**
     * Not Implemented
     */
    @Override
    public void clear() {
        throw new NotImplementedException();
    }

    /**
     * Not Implemented
     */
    @Override
    public Set<String> keySet() {
        throw new NotImplementedException();
    }

    /**
     * Not Implemented
     */
    @Override
    public Collection<V> values() {
        throw new NotImplementedException();
    }

    /**
     * Not Implemented
     */
    @Override
    public Set<Entry<String, V>> entrySet() {
        throw new NotImplementedException();
    }

    /**
     * Not Implemented
     */
    @Override
    public void putAll(Map m) {
        throw new NotImplementedException();
    }
}
