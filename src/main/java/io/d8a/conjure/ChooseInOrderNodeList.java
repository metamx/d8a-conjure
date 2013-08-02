package io.d8a.conjure;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChooseInOrderNodeList extends NodeList {
    private int next = 0;

    @Override
    protected void generateNonEmpty(LinkedHashMap<String,Object> map){
        if(next >= nodes.size()){
            next = 0;
        }
        nodes.get(next++).generateValue(map);
    }

    public static ChooseInOrderNodeList createNode(Map config, ConjureTemplate template) {
        ChooseInOrderNodeList nodes = new ChooseInOrderNodeList();
        List list = (List)config.get("list");
        if(list != null){
            for(Object obj : list){
                nodes.add(template.parseNodes(String.valueOf(obj)));
            }
        }
        return nodes;
    }
}
