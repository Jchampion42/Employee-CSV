package com.sparta.AlphaTeam.DataManagement;

import com.sparta.AlphaTeam.DataManagement.Database.DAO;
import com.sparta.AlphaTeam.DataManagement.Database.EmployeeDAO;

public class AddTask implements Runnable{
    Employee[] employeeArray;

    public AddTask(Employee[] employeeArray) {

        this.employeeArray = employeeArray;
    }

    @Override
    public void run() {
        DAO dataAccess = new EmployeeDAO();
        for (Employee e : employeeArray){
            if (e!=null) {
                dataAccess.add(e);
            }
        }
    }
}
