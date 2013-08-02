package io.d8a.conjure;

import java.util.LinkedHashMap;
import java.util.Map;

class TestNode implements ConjureTemplateNode {
    private Object text;

    TestNode(Object text) {
        this.text = text;
    }

    @Override
    public LinkedHashMap<String,Object> generateValue(LinkedHashMap<String,Object> map) {
        map.put("test",text);
        return map;
    }

    public static ConjureTemplateNode createNode(final Map config, final ConjureTemplate generator){
        if(config.containsKey("value")){
            return new TestNode(config.get("value"));
        }
        return new ConjureTemplateNode(){
            @Override
            public LinkedHashMap<String,Object> generateValue(LinkedHashMap<String,Object> map) {
                generator.getNode((String)config.get("valueRef")).generateValue(map);
                return map;
            }
        };
    }

}
