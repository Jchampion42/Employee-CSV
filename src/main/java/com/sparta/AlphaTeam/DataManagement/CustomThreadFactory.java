package com.sparta.AlphaTeam.DataManagement;

import java.util.List;

public class CustomThreadFactory {


	public Thread[] customThreadFactory(int threadCount, List<Employee> employeeList) {
		Thread[] threadArray = new Thread[threadCount];

		int remainder = employeeList.toArray().length % threadCount;
		int innerArraysSize = employeeList.toArray().length / threadCount;

		Employee[][] employeeNestedArray = new Employee[threadCount][innerArraysSize];

//		employeeList.toArray();
		int k = 0;
		for (int i = 0; i < threadCount; i++) {
			for (int j = 0; j < innerArraysSize; j++) {
				employeeNestedArray[i][j] = employeeList.get(k);
				k++;
			}
		}
		Employee[] remainderArray = new Employee[employeeNestedArray[0].length + remainder];
		for (int i = 0; i < employeeNestedArray[0].length; i++) {
			remainderArray[i] = employeeNestedArray[0][i];
		}
		while (remainder > 0) {
			remainderArray[remainderArray.length - 1 - remainder] = employeeList.get(employeeList.size() - remainder);
			remainder--;
		}
		//TODO
		// - Assistance Required
		// - HOW TO ASSIGN THREADS TO EMPLOYEE OBJECTS
		// - IS RUNNABLE r IN METHOD SIGNATURE NEEDED?
		//

		threadArray[0] = new Thread(new AddTask(remainderArray));
		for (int i = 1; i < threadCount; i++) {
			for (int j = 0; j < innerArraysSize; j++ )
				threadArray[i] = new Thread(new AddTask(employeeNestedArray[i]));
		}
		return threadArray;
	}
}

