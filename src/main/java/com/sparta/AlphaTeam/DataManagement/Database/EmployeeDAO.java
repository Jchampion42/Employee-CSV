package com.sparta.AlphaTeam.DataManagement.Database;

import com.sparta.AlphaTeam.DataManagement.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*"CREATE TABLE `employees`" +
        " (`id` int NOT NULL," +
        "`prefix` varchar(10) DEFAULT NULL," +
        "`first_name` varchar(45) DEFAULT NULL," +
        "`middle_initial` char(1) DEFAULT NULL," +
        "`last_name` varchar(45) DEFAULT NULL," +
        "`gender` char(1) DEFAULT NULL," +
        "`email` varchar(45) DEFAULT NULL," +
        "`date_of_birth` date DEFAULT NULL," +
        "`date_of_joining` date DEFAULT NULL," +
        "`salary` int DEFAULT NULL," +
        " PRIMARY KEY (`id`)," +
        " UNIQUE KEY `email_UNIQUE` (`email`))" +
        " ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 " +
        "COLLATE=utf8mb4_0900_ai_ci";*/
public class EmployeeDAO implements DAO<Employee> {
	private static final Logger LOG = LogManager.getLogger(EmployeeDAO.class);
	List<Employee> employeeList = new ArrayList<>();

	public List<Employee> getAll() {
		Connection connection = ConnectionFactory.getConnection();
		LOG.info("Getting all employee records from database.");
		try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM employees ")) {


			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				employeeList.add(createEmployeeFromDatabase(rs));
			}

		} catch (SQLException e) {
			LOG.error("Error while executing SQL query to get all Employees from database.");
			throw new RuntimeException(e);
		}
		LOG.info("Returning Employees queried from database.");
		return employeeList;
	}

	@Override
	public void add(Employee employee) {
		Connection connection = ConnectionFactory.getConnection();
		LOG.info("Adding employee record to database.");
		try (PreparedStatement statement = connection.prepareStatement("INSERT INTO employees VALUES (?,?,?,?,?,?,?,?,?,?)")) {
			statement.setInt(1, employee.getId());
			statement.setString(2, employee.getPrefix());
			statement.setString(3, employee.getfName());
			statement.setString(4, employee.getmName().toString());
			statement.setString(5, employee.getlName());
			statement.setString(6, employee.getGender().toString());
			statement.setString(7, employee.getEmail());
			statement.setDate(8, new Date(employee.getDateOfBirth().getTime()));
			statement.setDate(9, new Date(employee.getJoinDate().getTime()));
			statement.setInt(10, employee.getSalary());
			statement.executeUpdate();
			LOG.info("Employee record added.");
		} catch (SQLIntegrityConstraintViolationException e) {
			LOG.warn("Database hit a constraint violation. Employee id or email was not unique to an existing entry.");
		} catch (SQLException e) {
			LOG.error("Error while executing SQL query to add Employee to Database.");
			throw new RuntimeException(e);
		}

	}

	@Override
	public Optional<Employee> get(int id) {
		Connection connection = ConnectionFactory.getConnection();
		LOG.info("Getting single Employee entry from database.");
		try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees where id == (?)")) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.getResultSet();
			LOG.error("Retrieved single Employee entry.");
			return Optional.of(createEmployeeFromDatabase(resultSet));
		} catch (SQLException e) {
			LOG.error("Error while executing SQL query to get Employee from Database.");
			throw new RuntimeException(e);
		}
	}

	private Employee createEmployeeFromDatabase(ResultSet resultSet) throws SQLException {
		return new Employee(
				resultSet.getInt(1),
				resultSet.getString(2),
				resultSet.getString(3),
				resultSet.getString(4).charAt(0),
				resultSet.getString(5),
				resultSet.getString(6).charAt(0),
				resultSet.getString(7),
				new java.util.Date(resultSet.getDate(8).getTime()),
				new java.util.Date(resultSet.getDate(9).getTime()),
				resultSet.getInt(10));

	}
}
