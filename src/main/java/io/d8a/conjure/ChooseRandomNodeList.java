package io.d8a.conjure;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ChooseRandomNodeList extends NodeList {
    private static final Random RAND = new Random();

    protected void generateNonEmpty(LinkedHashMap<String,Object> map){
        nodes.get(RAND.nextInt(nodes.size())).generateValue(map);
    }

    public static ChooseRandomNodeList createNode(Map config, ConjureTemplate template) {
        ChooseRandomNodeList nodes = new ChooseRandomNodeList();
        List list = (List)config.get("list");
        if(list != null){
            for(Object obj : list){
                nodes.add(template.parseNodes(String.valueOf(obj)));
            }
        }
        return nodes;
    }
}
