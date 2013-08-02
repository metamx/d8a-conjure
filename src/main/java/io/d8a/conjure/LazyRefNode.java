package io.d8a.conjure;

import java.util.LinkedHashMap;

public class LazyRefNode implements ConjureTemplateNode {
    private String ref;
    private ConjureTemplateNode refNode;
    private ConjureTemplate template;

    public LazyRefNode(String ref, ConjureTemplate template) {
        this.ref = ref;
        this.template = template;
    }

    @Override
    public LinkedHashMap<String,Object> generateValue(LinkedHashMap<String,Object> map) {
        if(refNode == null){
            refNode = template.getNode(ref);
            if(refNode == null){
                throw new IllegalArgumentException("Referenced node '"+ref+"' not found.");
            }
        }
        return refNode.generateValue(map);
    }
}
