package io.d8a.conjure;

import java.util.LinkedHashMap;

interface Printer{
    void print(LinkedHashMap<String,Object> event);
}
