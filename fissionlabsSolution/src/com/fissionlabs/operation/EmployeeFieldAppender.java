package com.fissionlabs.operation;

import java.util.stream.Stream;

import com.fissionlabs.dao.EmployeeDataManager;
import com.fissionlabs.model.Employee;

/**
 * This class is to append employees based on it's field. Order of employee is
 * same as insertion order. Using toString() implementation of Employee class to
 * store values in file.
 *
 */
public class EmployeeFieldAppender {

	/**
	 * Make method thread safe using synchronized keyword. As this will call
	 * from thread.
	 * 
	 * @return
	 */
	public synchronized Stream<String> appendFieldsAsToString() {
		Stream<Employee> employees = EmployeeDataManager.getEmployees().stream();
		return employees.map(emp -> emp.toString());
	}

}
