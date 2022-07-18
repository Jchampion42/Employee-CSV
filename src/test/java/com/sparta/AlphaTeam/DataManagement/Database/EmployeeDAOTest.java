package com.sparta.AlphaTeam.DataManagement.Database;

import com.sparta.AlphaTeam.DataManagement.Employee;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDAOTest {

	@Test
	void addTest(){
		Date dob = new Date();
		Date doj = new Date();
		Employee employee = new Employee(28372,"Mrs","Sarah",'G',"Bowlens",'F',"sarah.bowlens@gmail.com",dob,doj,36287);
		EmployeeDAO employeeDAO = new EmployeeDAO();
		employeeDAO.add(employee);

	}
	@Test
	void getTest(){
		EmployeeDAO employeeDAO = new EmployeeDAO();
		employeeDAO.get(28372);

	}
	@Test
	void getAllTest(){
		EmployeeDAO employeeDAO = new EmployeeDAO();
		employeeDAO.getAll();

	}

}