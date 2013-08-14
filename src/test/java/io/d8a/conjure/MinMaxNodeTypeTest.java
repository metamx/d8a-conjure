package io.d8a.conjure;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test
public class MinMaxNodeTypeTest {
    private ConjureTemplate samples;

    @BeforeMethod
    public void setUp() throws Exception {
        samples = new ConjureTemplate();
        samples.addNodeType("minmax", MinMaxNode.class);
    }

    public void generatesNumberBetweenMinMax(){
        samples.addFragment("sample", "The current temp is [${type:\"minmax\",min:10, max:20}].");

        long value = (Long)samples.conjure().get("minmax");
        assertInRange(value, 10, 100);
    }

    public void generatesAllValuesInRangeInclusive(){
        int min = -50;
        int max = 50;
        int count = (max - min)+1;

        samples.addFragment("sample", "The current temp is [${type:\"minmax\",min:" + min + ", max:" + max + "}].");

        HashSet<Long> values = new HashSet<Long>(count);
        int attempts = 0;
        while(values.size() < count && attempts < count * 1000){
          long value = (Long)samples.conjure().get("minmax");
            values.add(value);
            ++attempts;
        }
        assertEquals(values.size(), count);

        for(int i=min; i<=max; i++){
            assertTrue(values.contains(new Long(i)));
        }
    }

    public void allowsNegativeForMin(){
        samples.addFragment("sample", "The current temp is [${type:\"minmax\",min:-110, max:100}].");
      long value = (Long)samples.conjure().get("minmax");
        assertInRange(value, -110, 100);
    }

    public void allowsNegativeForMax(){
        samples.addFragment("sample", "The current temp is [${type:\"minmax\",min:-110, max:-10}].");
      long value = (Long)samples.conjure().get("minmax");
        assertInRange(value, -110, -10);
    }

    public void fixesTransposedMinMax(){
        samples.addFragment("sample", "The current temp is [${type:\"minmax\",min:100, max:10}].");
      long value = (Long)samples.conjure().get("minmax");
        assertInRange(value, 10, 100);
    }

    public void canHaveSameMinAndMax(){
        samples.addFragment("sample", "The current temp is [${type:\"minmax\",min:100, max:100}].");
      long value = (Long)samples.conjure().get("minmax");
        assertEquals(value, 100);
    }

    public void canBeCreatedFromFactoryMethod(){
        Map config = new HashMap();
        config.put("min", 1);
        config.put("max", 100);
        MinMaxNode minmax = MinMaxNode.createNode(config);
        int value = new Integer((int)minmax.getMinmax().nextValue());
        assertTrue(value >= 1 && value <= 100);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void templatesFailWhenAddedWithoutMin(){
        samples.addFragment("sample", "The current temp is [${type:\"minmax\",max:100}].");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createFailsMinNotSpecified(){
        Map config = new HashMap();
        config.put("max", 1);
        MinMaxNode.createNode(config);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void templatesFailWhenAddedWithoutMax(){
        samples.addFragment("sample", "The current temp is [${type:\"minmax\",min:100}].");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createFailsMaxNotSpecified(){
        Map config = new HashMap();
        config.put("min", 1);
        MinMaxNode.createNode(config);
    }

    private long parseNumber(String text) {
        return Long.valueOf(text.substring(text.indexOf('[')+1, text.indexOf(']')));
    }

    private void assertInRange(long value, long start, long stop) {
        assertTrue(value >= start && value <= stop, "Value '" + value + "' not contained in the range [" + start + "," + stop + "]");
    }


}
