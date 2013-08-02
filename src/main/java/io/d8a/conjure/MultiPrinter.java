package io.d8a.conjure;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;

public class MultiPrinter implements Printer{
    private Collection<Printer> printers;

    public MultiPrinter(Collection<Printer> printers){
        this.printers = printers;
    }

    public MultiPrinter(Printer... printers){
        this(Arrays.asList(printers));
    }

    @Override
    public void print(LinkedHashMap<String,Object> event){
        for(Printer printer : printers){
            printer.print(event);
        }
    }

    @Override
    public String toString(){
        return "MultiPrinter{"+
                "printers="+printers+
                '}';
    }
}
