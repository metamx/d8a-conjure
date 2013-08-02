package io.d8a.conjure;

import java.util.LinkedHashMap;

public interface ConjureTemplateNode {
  public LinkedHashMap<String,Object> generateValue(LinkedHashMap<String,Object> map);
}
