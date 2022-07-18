package com.sparta.AlphaTeam.DataManagement;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class DataFilter {
    private static Date parseDate(String string) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        return parser.parse(string);
    }

    public boolean filterMissing(Employee employee){

        int id = employee.getId();
        int salary = employee.getSalary();
        if ((id == -1) || (employee.getPrefix() == null) || (employee.getfName() == null) || (employee.getmName() == null) ||
                (employee.getlName() == null) || (employee.getGender() == null) || (employee.getEmail() == null) || (employee.getDateOfBirth() == null) ||
                (employee.getJoinDate() == null) || (salary == -1)) {
            return true;
        }
        return false;
    }

    public boolean filterInvalidData(Employee employee) throws ParseException {
        Date temp = parseDate("12/31/1903");
        int fName = employee.getfName().length();
        int lName = employee.getlName().length();
        String firstN = employee.getEmail().substring(0, fName);
        String lastN = employee.getEmail().substring(fName+1, (fName+lName+1));
        if(employee.getJoinDate().before(employee.getDateOfBirth())){
            return true;
        }else{
            if(employee.getDateOfBirth().before(temp)){
                return true;
            }else{
                if((!employee.getfName().toLowerCase().equals(firstN)) || (!employee.getlName().toLowerCase().equals(lastN))){ //email doesnt match name
                    return true;
                }else {
                    return false; //valid dates
                }
            }
        }
    }

    public boolean filterDuplictes(Employee employee,List<Employee> list){
        for(Employee e: list){
            if(employee.getfName().equals(e.getfName()) && employee.getlName().equals(e.getlName())){
                return true; //exists
            }else if(employee.getId() == e.getId()){
                return true;
            }else if(employee.getEmail().equals(e.getEmail())){
                return true;
            }
        }
        return false;
    }

}
