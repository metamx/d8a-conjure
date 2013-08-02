package io.d8a.conjure;

import kafka.javaapi.producer.Producer;
import kafka.javaapi.producer.ProducerData;
import kafka.producer.ProducerConfig;

import java.util.LinkedHashMap;
import java.util.Properties;

class KafkaPrinter implements Printer{
    private final Producer<String, String> producer;
    private final String topic;

    public KafkaPrinter(String zkString, String topic){
        this.topic = topic;
        Properties props = new Properties();
        props.put("zk.connect", zkString);
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        ProducerConfig config = new ProducerConfig(props);
        producer = new Producer<String, String>(config);
    }

    @Override
    public void print(LinkedHashMap<String,Object> event){
      StringBuilder builder = new StringBuilder();
      for (Object value: event.values())
      {
         builder.append(value.toString());
      }
        ProducerData<String, String> data = new ProducerData<String, String>(topic, builder.toString());
        producer.send(data);
    }

    @Override
    public String toString(){
        return "Kafka topic '"+topic+"'";
    }
}
