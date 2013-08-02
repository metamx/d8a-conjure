package io.d8a.conjure;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CombineNodeList extends NodeList {
    private static final String DEFAULT_SEPARATOR = "";
    private String separator;

    public CombineNodeList(){
        this(DEFAULT_SEPARATOR);
    }

    public CombineNodeList(List<ConjureTemplateNode> nodes) {
        this();
        add(nodes);
    }

    public CombineNodeList(String separator) {
        super(true);
        this.separator = separator;
        if(this.separator == null){
            this.separator = DEFAULT_SEPARATOR;
        }
    }

    @Override
    protected void generateNonEmpty(LinkedHashMap<String,Object> map){
        boolean first = true;
        for(ConjureTemplateNode node : nodes){
            if(first){
                first = false;
            }else{
                map.put("seperator",separator);
            }
            node.generateValue(map);
        }
    }

    public static CombineNodeList createNode(Map config, ConjureTemplate template) {
        String separator = (String)config.get("separator");
        CombineNodeList nodes = new CombineNodeList(separator);
        List list = (List)config.get("list");
        if(list != null){
            for(Object obj : list){
                nodes.add(template.parseNodes(String.valueOf(obj)));
            }
        }
        return nodes;
    }
}
