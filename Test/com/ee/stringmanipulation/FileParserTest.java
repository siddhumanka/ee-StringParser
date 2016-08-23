package com.ee.stringmanipulation;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;


public class FileParserTest {
    public static File file;

    @Test(expected = java.lang.NullPointerException.class)
    public void setFileWithFileAsNull() throws Exception {
        FileParser fileParser = new FileParser(file, 3);

    }

    @Test(expected = java.io.FileNotFoundException.class)
    public void setFileWithInvalidFileName() throws Exception {
        FileParser fileParser = new FileParser(new File(""), 3);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void setFileWithDirectoryName() throws Exception {
        FileParser fileParser = new FileParser(new File("out"), 3);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void setFileWithNoContent() throws Exception {
        FileParser fileParser = new FileParser(new File("rr.txt"), 3);
    }

    @Test
    public void setAValidFile() throws Exception {
        FileParser fileParser = new FileParser(new File("input.txt"), 3);

    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void setThresholdWithInvalidValue() throws Exception {
        FileParser fileParser = new FileParser(new File("input.txt"),-6);
    }

    @Test
    public void setThresholdWithAValidValue() throws  Exception{
        FileParser fileParser = new FileParser(new File("input.txt"),3);
    }

//    @Test
//    public void printReport() throws Exception {
//        FileParser fileParser = new FileParser(new File("input.txt"),3);
//
//    }

    @Before
    public void setUp() throws Exception {
        file = null;
    }

}
