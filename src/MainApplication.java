import com.ee.stringmanipulation.StringParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by user-2 on 22/8/16.
 */
public class MainApplication {
    public static void main(String [] argv) throws FileNotFoundException {
        StringParser stringParser = new StringParser(new File("input.txt"));
        Map map = stringParser.readDataFromFile();
        stringParser. printData(stringParser.getWordsGreaterThanThreshold(map, 7));
    }
}
