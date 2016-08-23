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
    private final long MIN_LENGTH_OF_FILE = 0 ;
    private final int MIN_THRESHOLD_VALUE = 1;
    private File inputFile;
    private Map<String, Integer> map;
    private int threshold;

    public FileParser(){}
   /* public FileParser(File file) throws FileNotFoundException,IllegalArgumentException {
        map = new HashMap<>();
        setFile(file);
    }*/

    public FileParser(File file, int threshold) throws FileNotFoundException,IllegalArgumentException {
        map = new HashMap<>();
        setFile(file);
        setThreshold(threshold);
    }

    public void setFile(File file) throws FileNotFoundException,IllegalArgumentException {
     //   assert (file != null) : "File couldn't be set";
        if(isFileObjectIsNull(file)){
            throw new NullPointerException("file has null ...");
        }
        if (checkFileExistance(file)) {
            if (checkIfDirectory(file)) {
                throw new IllegalArgumentException("File is a directory...");
            }
            if (!checkForFileContent(file)) {
                throw new IllegalArgumentException("File is Not valid..");
            }
            this.inputFile = file;
            readAndStoreData(this.inputFile);
        }
        else
            throw new FileNotFoundException("Please enter a valid file name....");
    }

    public void setThreshold(int threshold) throws  IllegalArgumentException{
      //  assert (threshold >= MIN_THRESHOLD_VALUE) : "File couldn't be set";
        if(validateThreshold(threshold)) {
            this.threshold = threshold;
        }
    }

    private boolean isFileObjectIsNull(File file){
        if (file == null) {
            return true;
        }
        return false;
    }

    private  boolean checkFileExistance(File file){
        if (file.exists()) {
            return  true;
        }
        return  false;
    }

    private boolean checkIfDirectory(File file){
        if(file.isDirectory()){
            return true;
        }
        return false;
    }

    private boolean checkForFileContent(File file) {
        if(file.length()<=MIN_LENGTH_OF_FILE){
            return false;
        }
        return true;
    }

    private boolean validateThreshold(int threshold) throws IllegalArgumentException{
        if (threshold < MIN_THRESHOLD_VALUE) {
            throw new IllegalArgumentException("Threshold must be greater than zero...");
        }
        return true;
    }

    private Map readAndStoreData(File file) throws FileNotFoundException {
        String word = null;
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("\\s+|\\.\\s|\\,\\s+|\\;\\s+|\\\\s'+|\\/\\s|\\.|\\,|\\:|\\;+");
        while (scanner.hasNext()) {
            word = scanner.next().replace('.', '\u0000').replace(',', '\u0000').replace('"', '\u0000').trim().toLowerCase();
            if (map.containsKey(word))
                map.put(word, map.get(word) + 1);
            else
                map.put(word, 1);
        }

    //    assert (map != null) : "map is null";
        scanner.close();
        return map;
    }

    private Map findKeysGreaterThanThreshold() {
    //    assert (threshold >= MIN_THRESHOLD_VALUE) : "Threshold is less than zero";
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
   //     assert (map2 != null) : "Map2 is Empty";
        return map2;
    }

    private void printData(Map map) {
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

    public void printReport() throws FileNotFoundException {
        printData(findKeysGreaterThanThreshold());
    }
}
