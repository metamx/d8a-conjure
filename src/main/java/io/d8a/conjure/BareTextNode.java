package io.d8a.conjure;

import java.util.LinkedHashMap;

public class BareTextNode implements ConjureTemplateNode {
    private String text;

    public BareTextNode(String text) {
        this.text = text;
    }

  @Override
  public LinkedHashMap<String, Object> generateValue(LinkedHashMap<String, Object> map)
  {
    map.put("bareTextNode",text);
    return map;
  }

  public String getText(){
        return text;
    }
}
