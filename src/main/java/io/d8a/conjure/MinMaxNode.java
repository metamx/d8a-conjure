package io.d8a.conjure;

import java.util.LinkedHashMap;
import java.util.Map;

public class MinMaxNode implements ConjureTemplateNode {
    private MinMax minmax;

    public MinMaxNode(long min, long max) {
        minmax = new MinMax(min, max);
    }

    @Override
    public LinkedHashMap<String,Object> generateValue(LinkedHashMap<String,Object> map) {
        map.put("minmax",minmax.nextValue());
        return map;
    }

    public MinMax getMinmax() {
        return minmax;
    }

    public static MinMaxNode createNode(Map config) {
        Number min = (Number)config.get("min");
        Number max = (Number)config.get("max");
        if(min == null || max == null){
            throw new IllegalArgumentException("Both min and max must be specified.");
        }
        return new MinMaxNode(min.longValue(), max.longValue());
    }
}
