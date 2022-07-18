package com.sparta.AlphaTeam.UserInterface.Reader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {
    private static final Logger LOG = LogManager.getLogger(Reader.class);
    public static List<String> readFile(String filePath) {

        LOG.info("Opening " + filePath + " to scan.");
        try (Scanner scan = new Scanner(new FileReader(filePath)).useDelimiter("\r\n")) {
            List<String> employeeStringList = new ArrayList<>();
            String dataString;
            scan.nextLine();
            while (scan.hasNext()) {
                dataString = scan.next();
                employeeStringList.add(dataString);
            }
            LOG.info("Done reading file.");
            return employeeStringList;
        } catch (FileNotFoundException e) {
            LOG.error("Could not load file. Ensure file is in src/main/resources and is correctly named.");
            throw new RuntimeException(e);
        }
    }
    public static List<String> streamReadFile (String fileName){
        LOG.info("Opening " + fileName + " to scan.");
        try {
            List<String> employeeList = new ArrayList<>();
            List<String[]> employeeStringListArray = Files.lines(Path.of(fileName)).skip(1).map(s -> s.split(",")).toList();
            for(String[] e: employeeStringListArray){
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i<e.length; i++){
                    sb.append(e[i]);
                    sb.append(",");
                }
                employeeList.add(sb.toString());
            }
            LOG.info("Done reading file.");
            return employeeList;
        } catch (IOException e) {
            LOG.error("Could not load file. Ensure file is in src/main/resources and is correctly named.");
            throw new RuntimeException(e);
        }
    }
}
