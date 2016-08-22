package com.ee.stringmanipulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;


public class StringParser {

    public void printData(Map map) {
        Set<Entry<String,Integer>> set=map.entrySet();
        Iterator<Entry<String,Integer>> iterator=set.iterator();
        while(iterator.hasNext()){
            Entry<String, Integer> entry = iterator.next();
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

    }

    private Map findKeysGreaterThanThreshold(Map<String, Integer> map, int threshold){
        Map<String,Integer> map2 = new HashMap<>();
        Set<Entry<String,Integer>> set=map.entrySet();
        Iterator<Entry<String,Integer>> iterator=set.iterator();
        while(iterator.hasNext()){
            Entry<String, Integer> entry = iterator.next();
            if(entry.getValue()>=threshold){
                String keyString = entry.getKey();
                map2.put(keyString, map.get(keyString));
            }
        }
        return map2;

    }

    private Map readAndStoreData(Scanner scanner){
        Map<String,Integer> map = new HashMap<>();
        String str = null;
        while(scanner.hasNext()){
            str = scanner.next().replace('.', '\u0000').replace(',', '\u0000').replace('"', '\u0000').trim().toLowerCase();
            if(map.containsKey(str))
                map.put(str, map.get(str)+1);
            else
                map.put(str, 1);
        }
        return map;
    }


    public Map getData(Scanner scanner){
        return readAndStoreData(scanner);
    }

    public Map getWordsGreaterThanThreshold(Map map, int threshold){
        return findKeysGreaterThanThreshold(map,threshold);
    }
}