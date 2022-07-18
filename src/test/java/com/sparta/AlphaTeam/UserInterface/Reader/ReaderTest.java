package com.sparta.AlphaTeam.UserInterface.Reader;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import org.junit.jupiter.api.Test;

class ReaderTest {

    @Test
    void test() throws IOException {

        File file = new File("test.txt");

        FileWriter writer = new FileWriter(file);
        writer.append("abcd\n");
        writer.append("abcd\n");
        writer.append("abcd\n");
        writer.append("abcd\n");
        writer.close();

        List<String> employeeStringList = new ArrayList<>();

        employeeStringList = Reader.readFile(file.getAbsolutePath());

        System.out.println(employeeStringList.size() + ": size");

        assertTrue(employeeStringList.size() != 0);
        file.delete();

    }

}
