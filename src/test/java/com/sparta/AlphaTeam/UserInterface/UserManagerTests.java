package com.sparta.AlphaTeam.UserInterface;
import com.sparta.AlphaTeam.DataManagement.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UserManagerTests {

    public UserManagerTests() throws ParseException {
    }

    private static Date parseDate(String string) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        return parser.parse(string);
    }
    UserManager userManager;
    @BeforeEach
    void setUp(){userManager = new UserManager();}
    Employee temp = new Employee(290333, "Miss", "Kira", 'G', "Coke", 'F', "kcoke@spartaglobal.com"
            ,parseDate("19991103"), parseDate("20220620"), 21000);

    @Test
    public void displayTest(){

    }


}


