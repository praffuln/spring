package com.fissionlabs.dao;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.fissionlabs.model.Employee;

/**
 * @author Prafful
 * 
 * This class will manage employee information, will work as Employee
 * DAO. Using synchronized version of ArrayList.
 * 
 * Not making method synchronized, as this will be access from service
 * layer and service methods are synchronized.
 *
 */
public class EmployeeDataManager {

	// Create a thread safe concurrent counterpart of arrayList.
	private static final List<Employee> EMPLOYEES = new CopyOnWriteArrayList<Employee>();

	public static void addEmployee(Employee e) {
		EMPLOYEES.add(e);
	}

	public static List<Employee> getEmployees() {
		return EMPLOYEES;
	}

	public static List<Employee> removeEmployee(Employee e) {
		 throw new UnsupportedOperationException();
	}
}
