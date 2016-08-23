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

    private final long MIN_LENGTH_OF_FILE = 0;
    private final int MIN_THRESHOLD_VALUE = 1;
    private File inputFile;
    private Map<String, Integer> map;
    private int threshold;


    public FileParser(File file, int threshold) throws FileNotFoundException, IllegalArgumentException {
        map = new HashMap<>();
        setFile(file);
        setThreshold(threshold);
    }

    public void setFile(File file) throws FileNotFoundException, IllegalArgumentException {
        //   assert (file != null) : "File couldn't be set";
        if (isFileObjectIsNull(file)) {
            throw new NullPointerException("file has null ...");
        }

        if (!isFileExisting(file)) {
            throw new FileNotFoundException("Please enter a valid file name....");
        }

        if (isFileADirectory(file)) {
            throw new IllegalArgumentException("File is a directory...");
        }

        if (isFileEmpty(file)) {
            throw new IllegalArgumentException("File is Not valid..");
        }
        this.inputFile = file;
        map = populateMap();

    }

    public void setThreshold(int threshold) throws IllegalArgumentException {
        //  assert (threshold >= MIN_THRESHOLD_VALUE) : "File couldn't be set";
        if (isThresholdLimitValid(threshold)) {
            this.threshold = threshold;
        }else{
            throw new IllegalArgumentException("Threshold must be greater than zero...");
        }
    }

    private boolean isFileObjectIsNull(File file) {
        if (file == null) {
            return true;
        }
        return false;
    }

    private boolean isFileExisting(File file) {
        if (file.exists()) {
            return true;
        }
        return false;
    }

    private boolean isFileADirectory(File file) {
        if (file.isDirectory()) {
            return true;
        }
        return false;
    }

    private boolean isFileEmpty(File file) {
        if (file.length() <= MIN_LENGTH_OF_FILE) {
            return true;
        }
        return false;
    }

    private boolean isThresholdLimitValid(int threshold)  {

        if (threshold < MIN_THRESHOLD_VALUE) {
             return false;
        }
        return true;
    }


    private Map populateMap() throws FileNotFoundException {
        String word = null;
        Map<String, Integer> localMap = new HashMap<>();
        Scanner scanner = new Scanner(inputFile);
        scanner.useDelimiter("\\W|\\s");
        while (scanner.hasNext()) {
            word = scanner.next().trim().toLowerCase();
            if(!word.isEmpty()) {
                if (localMap.containsKey(word)) {
                    localMap.put(word, localMap.get(word) + 1);
                } else {
                    localMap.put(word, 1);
                }
            }
        }

        //    assert (map != null) : "map is null";
        scanner.close();
        return localMap;
    }

    public void printData() {
        Set<Entry<String, Integer>> set = map.entrySet();
        Iterator<Entry<String, Integer>> iterator = set.iterator();
        System.out.println();
        while (iterator.hasNext()) {
            Entry<String, Integer> entry = iterator.next();
            if (entry.getValue() >= threshold) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
        if (set.isEmpty()) {
            throw new NullPointerException("No words to show...");
        }
    }

}
