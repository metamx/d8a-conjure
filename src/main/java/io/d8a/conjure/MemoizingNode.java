package io.d8a.conjure;

import java.util.LinkedHashMap;
import java.util.Map;

public class MemoizingNode implements ConjureTemplateNode {
    private ConjureTemplateNode targetNode;
    private String name;
    private Map<String,LinkedHashMap<String,Object>> cache;

    public MemoizingNode(ConjureTemplateNode targetNode, String name, Map cache) {
        this.targetNode = targetNode;
        this.name = name;
        this.cache = cache;
    }

    @Override
    public LinkedHashMap<String,Object> generateValue(LinkedHashMap<String,Object> map) {
        if(!cache.containsKey(name)){
            cache.put(name, targetNode.generateValue(new LinkedHashMap<String, Object>()));
        }
        map.putAll(cache.get(name));
        return map;
    }

    public ConjureTemplateNode getTargetNode() {
        return targetNode;
    }
}
