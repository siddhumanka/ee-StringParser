package com.ee.stringmanipulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;


public class FileParser {
    private final static int MIN_THRESHOLD_VALUE = 1;
    private File file;
    private Map<String, Integer> map;
    private int threshold;

    public FileParser(File file) throws FileNotFoundException {
        //  assert (file!=null):"File couldn't be set";

        if (file == null) {
            throw new NullPointerException();
        }
        setFile(file);
    }

    public FileParser(File file, int threshold) throws FileNotFoundException {
        //  assert (file!=null):"File couldn't be set";
        if (file == null) {
            throw new IllegalArgumentException("File is null");
        }
        if (threshold < MIN_THRESHOLD_VALUE) {
            throw new IllegalArgumentException("Threshold must be greater than zero...");
        }
        setFile(file);
        setThreshold(threshold);
    }

    public void setFile(File file) throws FileNotFoundException {
        assert (file != null) : "File couldn't be set";
        if (file.exists()) {
            if (file.length() <= 0) {
                throw new IllegalArgumentException("File is empty please provide a file with some data..");
            }
            if(file.isDirectory()){
                throw new FileNotFoundException("Please enter a valid file name not a directory");
            }
            this.file = file;
            readAndStoreData(new Scanner(file));
        }
        else
            throw new FileNotFoundException("Please enter a valid file name....");

    }


    public void setThreshold(int threshold) {
        assert (threshold >= MIN_THRESHOLD_VALUE) : "File couldn't be set";
        this.threshold = threshold;

    }

    public void printData(Map map) {
        Set<Entry<String, Integer>> set = map.entrySet();
        Iterator<Entry<String, Integer>> iterator = set.iterator();
        System.out.println();
        while (iterator.hasNext()) {
            Entry<String, Integer> entry = iterator.next();
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        if (set.isEmpty()) {
            throw new NullPointerException("No words to show...");
        }

    }

    private Map findKeysGreaterThanThreshold() {
        assert (threshold >= MIN_THRESHOLD_VALUE) : "Threshold is less than zero";

        Map<String, Integer> map2 = new HashMap<>();
        Set<Entry<String, Integer>> set = map.entrySet();
        Iterator<Entry<String, Integer>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Entry<String, Integer> entry = iterator.next();
            if (entry.getValue() >= threshold) {
                String keyString = entry.getKey();
                map2.put(keyString, map.get(keyString));
            }
        }

        assert (map2 != null) : "Map2 is Empty";
        return map2;

    }

    private Map readAndStoreData(Scanner scanner) {
        map = new HashMap<>();
        String word = null;
        scanner.useDelimiter("\\s+|\\.\\s|\\,\\s+|\\;\\s+|\\\\s'+|\\/\\s|\\.|\\,|\\:|\\;+");
        while (scanner.hasNext()) {
            word = scanner.next().replace('.', '\u0000').replace(',', '\u0000').replace('"', '\u0000').trim().toLowerCase();
            if (map.containsKey(word))
                map.put(word, map.get(word) + 1);
            else
                map.put(word, 1);
        }

        assert (map != null) : "map is null";
        scanner.close();
        return map;
    }

    public void printReport() throws FileNotFoundException {
        printData(findKeysGreaterThanThreshold());
    }
}
