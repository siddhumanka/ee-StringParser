package com.ee.stringmanipulation;

import com.ee.stringmanipulation.FileParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Created by user-2 on 22/8/16.
 */
public class MainApplication {
    private final static Scanner scanner = new Scanner(System.in);
    private final static int MIN_THRESHOLD_VALUE = 1;

    public static void main(String[] argv) {
        try {
            FileParser fileParser2 = new FileParser(getFile(), getThresholdValue());
            fileParser2.printReport();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public static File getFile() {
//        System.out.print("Enter the file name : ");
//        File file = new File(scanner.next());
//        while (!file.exists()) {
//            System.out.print("\nfile does not exist , please enter a valid name: ");
//            file = new File(scanner.next());
//        }
        File file;
        do {
            System.out.print("Enter the file name : ");
            file = new File(scanner.next());
            if (file.exists()) {
                if (file.length() <= 0) {
                    System.out.println("File is empty please provide a file with some data..");
                    continue;
                }
                if(file.isDirectory()){
                    System.out.println("Please enter a valid file name not a directory");
                    continue;
                }
                break;
            } else {
                System.out.print("\nfile does not exist , please enter a valid name: ");
            }
        } while (true);
        assert (file != null) : "file is null";
        return file;
    }

    public static int getThresholdValue() {
        int threshold;
        System.out.print("Enter the threshold value  : ");
        while (true) {
            try {
                threshold = Integer.parseInt(scanner.next());
                if (threshold >= MIN_THRESHOLD_VALUE) {
                    break;
                } else System.out.print("Threshold must be greater than 0 ...please enter a valid threshold value");
            } catch (NumberFormatException e) {
                System.out.print("enter the valid number ....");
            }
        }
        assert (threshold >= MIN_THRESHOLD_VALUE) : "Incorrect Threshold value";
        return threshold;
    }

    public void finalize() {
        scanner.close();
    }
}