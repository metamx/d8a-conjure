package io.d8a.conjure;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
/**
 *  CardinalityNodeList is analogous to NodeList, except it only holds CardinalityNodes.
 */
public class CardinalityNodeList implements ConjureTemplateNode{
    protected List<CardinalityNode> cardinalityNodes = Lists.newArrayList();
    private Map<String, Object> event = Maps.newHashMap();
    private final Clock clock;

    public CardinalityNodeList(Clock clock){
        this.clock = clock;
    }

    public CardinalityNodeList(List<CardinalityNode> nodes, Clock clock){
        cardinalityNodes.addAll(nodes);
        this.clock = clock;
    }


    public void add(Collection<CardinalityNode> nodes){
        cardinalityNodes.addAll(nodes);
    }

    public void add(CardinalityNode...nodes){
        Collections.addAll(cardinalityNodes, nodes);
    }

    public List<CardinalityNode> getNodes(){
        return Collections.unmodifiableList(cardinalityNodes);
    }

    @Override
    public StringBuilder generate(StringBuilder buff){
        return buff.append(generateEvent().toString());
    }

    /**
     * calls getValue() on each node in cardinalityNodes to generate an event.
     * @return - a new event composed of the values of each of the nodes in cardinalityNodes
     */
    public Map<String, Object> generateEvent(){
        for(CardinalityNode variable : cardinalityNodes){
            event.put(variable.getName(), variable.getValue());
        }
        event.put("timestamp", clock.currentTimeMillis());
        return event;
    }

    public int size(){
        return cardinalityNodes.size();
    }

    @Override
    public int hashCode(){
        int result = cardinalityNodes.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object list){
        return getNodes().equals(((CardinalityNodeList)list).getNodes());
    }
}
