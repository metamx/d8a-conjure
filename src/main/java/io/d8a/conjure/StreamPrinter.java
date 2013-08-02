package io.d8a.conjure;

import java.io.PrintStream;
import java.util.LinkedHashMap;

/**
 * Created: 4/19/13 10:15 AM
 */
public class StreamPrinter implements Printer{
    private PrintStream out;

    public StreamPrinter(PrintStream out){
        this.out = out;
    }

    @Override
    public void print(LinkedHashMap<String,Object> event){
        for (Object value: event.values())
        {
          out.print(value.toString());
        }
        out.print("\n");
        out.checkError();
    }

    public void close(){
        out.flush();
        out.close();
    }

    @Override
    public String toString(){
        return "StreamPrinter";
    }
}
