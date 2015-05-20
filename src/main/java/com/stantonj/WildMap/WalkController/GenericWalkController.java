package com.stantonj.WildMap.WalkController;

import com.stantonj.WildMap.MapNode;
import com.stantonj.WildMap.Walker.iWalker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Joey on 2/24/15.
 */
public abstract class GenericWalkController<V> implements iWalkController<V> {

    public V WalkMap(final MapNode<V> initialNode, final String Key, iWalker<V> walker){
        init(createWalkState(
                ""
                , Key
                , initialNode
        ));

        while(this.HasWalkState()){
            WalkState<V> curState = this.getNextWalkState();
            V result = walker.Operate(curState.Remainder, curState.node);

            if(walker.isGoal(curState.Remainder, curState.node))
                return result;
            if(walker.ContinueDescent(curState.Remainder, curState.node)){
                WalkState wildCardState = null;
                Collection<WalkState> subNodes = new ArrayList<WalkState>();
                if(curState.node.getChildren().containsKey("*")){
                    wildCardState = createWalkState(
                            curState.Path + curState.Remainder.charAt(0)
                            , curState.Remainder.substring(1)
                            , curState.node.getChildren().get("*")
                    );
                }
                //add all children that match the key
                for(String k:curState.node.getChildren().keySet()){
                    if(curState.Remainder.startsWith(k)){
                        subNodes.add(createWalkState(
                                curState.Path + curState.Remainder.substring(k.length())
                                , curState.Remainder.substring(k.length())
                                , curState.node.getChildren().get(k)
                        ));
                    }
                }
                addDescendantNodes(subNodes, wildCardState);
            }

        }

        return null;
    }
    public WalkState<V> createWalkState(String Path, String Remainder, MapNode<V> node){
        WalkState<V> state = new WalkState<V>();
        state.node = node;
        state.Path = Path;
        state.Remainder = Remainder;
        return state;
    }

    protected abstract void init(WalkState initialState);
    public abstract WalkState<V> getNextWalkState();
    public abstract void addDescendantNodes(Collection<WalkState> nodes, WalkState WildcardNode);
    public abstract boolean HasWalkState();
}
