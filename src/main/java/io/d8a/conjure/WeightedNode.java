package io.d8a.conjure;

import java.util.LinkedHashMap;

public class WeightedNode implements ConjureTemplateNode {
    private ConjureTemplateNode target;
    private int weight;

    public WeightedNode(ConjureTemplateNode target, int weight) {
        this.target = target;
        this.weight = weight;
    }

    @Override
    public LinkedHashMap<String,Object> generateValue(LinkedHashMap<String,Object> map) {
        return target.generateValue(map);
    }

    public int getWeight() {
        return weight;
    }
}
