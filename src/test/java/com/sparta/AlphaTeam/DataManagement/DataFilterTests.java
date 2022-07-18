package com.sparta.AlphaTeam.DataManagement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
public class DataFilterTests {
    SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
    DataFilter dataFilter;

    public DataFilterTests() throws ParseException {
    }

    @BeforeEach
    void setUp(){dataFilter = new DataFilter();}
    Employee missing = new Employee(-1,  "Miss", "Kira", 'G', "Coke", 'F', "kcoke@spartaglobal.com"
            ,(parser.parse("11/03/1999")), (parser.parse("06/20/2022")), 21000);
    Employee invalid1 = new Employee(123127,  "Miss", "Kira", 'G', "Coke", 'F', "kira.coke@spartaglobal.com"
            ,(parser.parse("11/03/1901")), (parser.parse("06/20/2022")), 21000);
    Employee invalid2 = new Employee(123918,  "Miss", "Kira", 'G', "Coke", 'F', "kira.coke@spartaglobal.com"
            ,(parser.parse("11/03/2023")), (parser.parse("06/20/2022")), 21000);
    Employee invalid3 = new Employee(213917,  "Miss", "Kira", 'G', "Coke", 'F', "kira.cokre@spartaglobal.com"
            ,(parser.parse("11/03/1999")), (parser.parse("06/20/2022")), 21000);
    Employee clean = new Employee(290333, "Miss", "Kira", 'G', "Coke", 'F', "kira.coke@spartaglobal.com"
            ,(parser.parse("11/03/1999")), (parser.parse("06/20/2022")), 21000);
    Employee clean2 = new Employee(79820, "Miss", "Lira", 'G', "Coke", 'F', "kira.coke@spartaglobal.com"
            ,(parser.parse("11/03/1999")), (parser.parse("06/20/2022")), 21000);
    Employee clean3 =  new Employee(902321, "Mr", "Kira", 'G', "Coke", 'F', "kira.coke@spartaglobal.com"
            ,(parser.parse("11/03/1999")), (parser.parse("06/20/2022")), 21000);
    Employee clean4 =  new Employee(78291, "Miss", "Suzy", 'G', "Coke", 'F', "sira.coke@spartaglobal.com"
            ,(parser.parse("11/03/1999")), (parser.parse("06/20/2022")), 22000);
    Employee clean5 =  new Employee(87782, "Miss", "Bira", 'G', "Coke", 'F', "bira.coke@spartaglobal.com"
            ,(parser.parse("11/03/1999")), (parser.parse("06/20/2022")), 21000);

    Employee duplicate1= new Employee(290333, "Miss", "Kira", 'G', "Coke", 'F', "kira.coke@spartaglobal.com"
            ,(parser.parse("11/03/1999")), (parser.parse("06/20/2022")), 21000);
    Employee duplicate2= new Employee(7982, "Miss", "Lira", 'G', "Coke", 'F', "kira.coke@spartaglobal.com"
            ,(parser.parse("11/03/1999")), (parser.parse("06/20/2022")), 21000);

    @Test
    public void missingTest(){
        Assertions.assertTrue(dataFilter.filterMissing(missing));
        missing.setEmail(null);
        Assertions.assertTrue(dataFilter.filterMissing(missing));
    }
    @Test
    public void invalidTest() throws ParseException {
        Assertions.assertTrue(dataFilter.filterInvalidData(invalid1));
        Assertions.assertTrue(dataFilter.filterInvalidData(invalid2));
        Assertions.assertTrue(dataFilter.filterInvalidData(invalid3));
    }
    @Test
    public void duplicatesTest(){
        List<Employee> list = new ArrayList<>();
        list.add(clean);
        list.add(clean2);
        System.out.println(list.size());
        Assertions.assertTrue(dataFilter.filterDuplictes(duplicate1, list));
        Assertions.assertTrue(dataFilter.filterDuplictes(duplicate2,list));

    }
    @Test
    public void cleanTests() throws ParseException {
        List<Employee> list2 = new ArrayList<>();
        list2.add(clean);
        list2.add(clean2);
        list2.add(clean3);
        Assertions.assertFalse(dataFilter.filterMissing(clean));
        Assertions.assertFalse(dataFilter.filterInvalidData(clean));
        Assertions.assertFalse(dataFilter.filterDuplictes(clean4, list2));
        Assertions.assertFalse(dataFilter.filterDuplictes(clean5, list2));
    }
}
