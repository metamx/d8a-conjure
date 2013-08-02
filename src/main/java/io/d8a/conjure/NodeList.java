package io.d8a.conjure;

import java.util.*;

public abstract class NodeList implements ConjureTemplateNode {
    protected List<ConjureTemplateNode> nodes = new ArrayList<ConjureTemplateNode>();
    private boolean allowsGenerateOnEmpty = false;

    public NodeList(){
        this(false);
    }

    public NodeList(List<ConjureTemplateNode> nodes)
    {
      this.nodes=nodes;
    }

    public NodeList(boolean allowsGenerateOnEmpty){
        this.allowsGenerateOnEmpty = allowsGenerateOnEmpty;
    }

    public void add(ConjureTemplateNode...nodes){
        add(Arrays.asList(nodes));
    }

    public void add(Collection<ConjureTemplateNode> nodes){
        this.nodes.addAll(nodes);
    }

    public List<ConjureTemplateNode> getNodes() {
        return Collections.unmodifiableList(nodes);
    }


    @Override
    public LinkedHashMap<String,Object> generateValue(LinkedHashMap<String,Object> map) {
      if(!nodes.isEmpty()){
        generateNonEmpty(map);
        return map;
      }
      if(allowsGenerateOnEmpty){
        generateEmpty(map);
        return map;
      }
      throw new IllegalStateException("Nodes must first be added to "+getClass().getSimpleName()+" before calling generate.");
    }

    protected void generateEmpty(LinkedHashMap<String,Object> map){
    }

    protected abstract void generateNonEmpty(LinkedHashMap<String,Object> map);

    public boolean isEmpty() {
        return nodes == null || nodes.isEmpty();
    }
}
