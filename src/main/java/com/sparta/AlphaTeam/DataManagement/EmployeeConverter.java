package com.sparta.AlphaTeam.DataManagement;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EmployeeConverter {
	private static final Logger LOG = LogManager.getLogger(EmployeeConverter.class);

	public static List<Employee> convertStringsToEmployees(List<String> input) throws ParseException, IndexOutOfBoundsException {
		ArrayList<Employee> employees = new ArrayList<>();
		LOG.info("Beginning to parse CSV file entries.");
		for (String line : input) {
			String[] delimitedResult = line.split(",");
			int id;

			try {
				id = Integer.parseInt(delimitedResult[0]);
			} catch (NumberFormatException e) {
				LOG.warn("Could not parse Integer value where it was expected. Setting it to -1.");
				id = -1;
			}
			String prefix = delimitedResult[1];
			String fName = delimitedResult[2];

			Character mInitial;
			try {
				mInitial = delimitedResult[3].charAt(0);
			} catch (StringIndexOutOfBoundsException e) {
				LOG.warn("Could not parse Character value where it was expected. Setting it to null.");
				mInitial = null;
			}
			String lName = delimitedResult[4];
			Character gender;
			try {
				gender = delimitedResult[5].charAt(0);
			} catch (StringIndexOutOfBoundsException e) {
				LOG.warn("Could not parse Character value where it was expected. Setting it to null.");
				gender = null;
			}
			String email = delimitedResult[6];
			Date dateOfBirth = parseDate(delimitedResult[7]);
			Date joinDate = parseDate(delimitedResult[8]);
			int salary;
			try {
				salary = Integer.parseInt(delimitedResult[9]);
			}catch (IndexOutOfBoundsException | NumberFormatException e) {
				LOG.warn("Could not parse Integer value where it was expected. Setting it to -1.");
				salary = -1;
			}
			employees.add(new Employee(id, prefix, fName, mInitial, lName, gender, email, dateOfBirth, joinDate, salary));
		}
		LOG.info("Done parsing CSV entries.");
		return employees;
	}

	private static Date parseDate(String string) throws ParseException {
		SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
		return parser.parse(string);
	}
}
