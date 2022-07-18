package com.sparta.AlphaTeam.DataManagement;

import com.sparta.AlphaTeam.DataManagement.Database.DAO;
import com.sparta.AlphaTeam.DataManagement.Database.DatabaseInit;
import com.sparta.AlphaTeam.DataManagement.Database.EmployeeDAO;
import com.sparta.AlphaTeam.core.Timer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class DataManager {
	private Logger LOG = LogManager.getLogger(DataManager.class);
	private File chosenFile;
	private int threadCount;
	private List<String> dataString = new ArrayList<>();
	private List<Employee> unsortedRecords = new ArrayList<>();
	private List<Employee> cleanRecords = new ArrayList<>();
	private List<Employee> allDirtyRecords = new ArrayList<>();
	private List<Employee> missingValueRecords = new ArrayList<>();
	private List<Employee> invalidDateRecords = new ArrayList<>();
	private List<Employee> duplicatedRecords = new ArrayList<>();
	private List<Employee> fetchedRecords = new ArrayList<>();
	private Thread[] threads;
	private double timeTaken;


	public DataManager() {
		DatabaseInit.init();
	}


	public void createThreads() {
		CustomThreadFactory customThreadFactory = new CustomThreadFactory();
		threads = customThreadFactory.customThreadFactory(threadCount, cleanRecords);
	}

	public void getEmployeeFromDatabase() {
		DAO dataAccess = new EmployeeDAO();
		fetchedRecords = dataAccess.getAll();
	}

	// --------------------- testing adding to database
	public void addAllToDatabase() {
		if (threadCount <= 0) {
			threadCount = 1;
		}
		System.out.println("Using " + threadCount + " threads to push to database");
		Timer timer = new Timer();
		Timer timer1 = new Timer();
		timer.start();

		createThreads();
		timer1.start();
		for (Thread t : threads) {
			t.start();
		}
		boolean aliveCheck = true;
		while (aliveCheck) {
			aliveCheck = false;
			for (Thread t : threads) {
				if (t.isAlive()) {
					aliveCheck = true;
				}
			}
		}
		timeTaken = timer.stop() / 1E9;
		timeTaken = Math.floor(timeTaken * 1000) / 1000;
		System.out.println(" Time taken to create and push using " + threadCount + " threads: " + timeTaken + "Seconds");
		timeTaken = timer1.stop() / 1E9;
		timeTaken = Math.floor(timeTaken * 1000) / 1000;
		System.out.println("\n Time taken to execute push using " + threadCount + " threads: " + timeTaken + "Seconds");
	}

      /*  }
        DAO dataAccess = new EmployeeDAO();
        DatabaseInit.init();
        for (Employee e : cleanRecords){
            dataAccess.add(e);
        }*/

	public void sortUnsortedRecords() {
		DataFilter dataFilter = new DataFilter();
		boolean isDirty = false;
		LOG.info("Filtering Employee entries.");
		for (Employee e : unsortedRecords) {
			isDirty = false;

			try {
				if (dataFilter.filterInvalidData(e)) {
					invalidDateRecords.add(e);
					isDirty = true;
				}
			} catch (ParseException ex) {
				LOG.error("Encountered error while parsing an invalid record.");
			}
			if (dataFilter.filterMissing(e)) {
				missingValueRecords.add(e);
				isDirty = true;
			}

			if (dataFilter.filterDuplictes(e, cleanRecords)) {
				duplicatedRecords.add(e);
				isDirty = true;
			}

			if (isDirty) {
				allDirtyRecords.add(e);
			} else {
				cleanRecords.add(e);
			}
		}
        LOG.info("Done filtering.");
}

	public void convertStringListToEmployee(List<String> inputList) {
		try {
			unsortedRecords = EmployeeConverter.convertStringsToEmployees(inputList);
		} catch (ParseException e) {
			LOG.error("Error while parsing entries from CSV file.");
		}
	}

	//---------------------GETTERS AND SETTERS------------------
	public List<Employee> getFetchedRecords() {
		return fetchedRecords;
	}

	public void setFetchedRecords(List<Employee> fetchedRecords) {
		this.fetchedRecords = fetchedRecords;
	}

	public int getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	public File getChosenFile() {
		return chosenFile;
	}

	public void setChosenFile(String filePath) {
		this.chosenFile = new File(filePath);
	}

	public List<String> getDataString() {
		return dataString;
	}

	public void setDataString(List<String> dataString) {
		this.dataString = dataString;
	}

	public List<Employee> getAllDirtyRecords() {
		return allDirtyRecords;
	}

	public List<Employee> getCleanRecords() {
		return cleanRecords;
	}

	public List<Employee> getMissingValueRecords() {
		return missingValueRecords;
	}

	public List<Employee> getDuplicatedRecords() {
		return duplicatedRecords;
	}

	public List<Employee> getInvalidDateRecords() {
		return invalidDateRecords;
	}

	public List<Employee> getUnsortedRecords() {
		return unsortedRecords;
	}

	public void setUnsortedRecords(List<Employee> unsortedRecords) {
		this.unsortedRecords = unsortedRecords;
	}

	public void setAllDirtyRecords(List<Employee> allDirtyRecords) {
		this.allDirtyRecords = allDirtyRecords;
	}

	public void setCleanRecords(List<Employee> cleanRecords) {
		this.cleanRecords = cleanRecords;
	}

	public void setDuplicatedRecords(List<Employee> duplicatedRecords) {
		this.duplicatedRecords = duplicatedRecords;
	}

	public void setInvalidDateRecords(List<Employee> invalidDateRecords) {
		this.invalidDateRecords = invalidDateRecords;
	}

	public void setMissingValueRecords(List<Employee> missingValueRecords) {
		this.missingValueRecords = missingValueRecords;
	}
}
