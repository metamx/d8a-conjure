package io.d8a.conjure;

import com.google.common.base.Throwables;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

//Queue printer is used to print event objects from conjure directly to a queue. Prevents need for serializing and deserealizing.
public class QueuePrinter implements Printer{
    private final BlockingQueue<Map<String,Object>> queue;
    private final long waitTime;
    private final TimeUnit unit;

    public QueuePrinter(BlockingQueue queue, long waitTime, TimeUnit unit){
        this.waitTime = waitTime;
        this.unit = unit;
        this.queue = queue;
    }

    @Override
    public void print(LinkedHashMap<String,Object> event){
        try{
            queue.offer(event, waitTime, unit);
        } catch(InterruptedException e){
            throw Throwables.propagate(e);
        }
    }

}
