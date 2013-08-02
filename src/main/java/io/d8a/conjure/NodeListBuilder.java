package io.d8a.conjure;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class NodeListBuilder
{
    private final List<Spec> specList;
    private Clock clock = Clock.SYSTEM_CLOCK;

    @JsonCreator
    public NodeListBuilder(
        @JsonProperty("specs") List<Spec> specList
    ){
        this.specList = specList;
    }

    public Map<String, ConjureTemplateNode> build() throws IllegalArgumentException{
      int i=0;
      Map<String,ConjureTemplateNode> nodesToAdd = Maps.newLinkedHashMap();
        for(Spec spec : specList){
            for (ConjureTemplateNode node: spec.getNodesToAdd())
            {
              nodesToAdd.put("node"+i,node);
              i++;
            }
        }
        return nodesToAdd;
    }

    public NodeListBuilder withClock(Clock clock){
        this.clock = clock;
        return this;
    }
}