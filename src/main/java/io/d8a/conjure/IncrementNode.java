package io.d8a.conjure;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A simple node that generates an ever increasing value,
 * starts with value and changes by step (defaults to 1).
 */
public class IncrementNode implements ConjureTemplateNode {
  private final long rollAfterSteps;
  private long initialValue;
  private long value;
  private long step;
  private long stepsSinceRoll;

  public IncrementNode(long value, long step) {
    this(value, step, Long.MAX_VALUE);
  }

  public IncrementNode(long value, long step, long rollAfterSteps) {
    this.initialValue = value;
    this.value = initialValue;
    this.step = step;
    this.rollAfterSteps = rollAfterSteps;
  }

  @Override
  public LinkedHashMap<String,Object> generateValue(LinkedHashMap<String,Object> map) {
    if(++stepsSinceRoll > rollAfterSteps){
      value = initialValue;
      stepsSinceRoll = 0;
    }
    long currentValue = value;
    value += step;
    map.put("increment node",currentValue);
    return map;
  }

  public static IncrementNode createNode(Map config)
  {
    Number startValue = (Number)config.get("value");
    Number step = (Number)config.get("step");
    Number rollAfterSteps = (Number)config.get("rollAfterSteps");

    if(startValue == null){
      throw new IllegalArgumentException("value must be specified.");
    }
    if(step == null){
      step = 1;
    }
    if(rollAfterSteps == null){
      rollAfterSteps = Long.MAX_VALUE;
    }
    return new IncrementNode(startValue.longValue(), step.longValue(), rollAfterSteps.longValue());
  }
}