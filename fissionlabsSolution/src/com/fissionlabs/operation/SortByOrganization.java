package com.fissionlabs.operation;

import static com.fissionlabs.model.Constants.COMMA;

import java.util.Comparator;
import java.util.stream.Stream;

import com.fissionlabs.dao.EmployeeDataManager;
import com.fissionlabs.model.Employee;

/**
 * @author Prafful
 * 
 *  Make this class also a task along with sort operation.
 *
 */
public class SortByOrganization implements SortOperator {

	/*
	 * This method can also be synchronized, but as per requirement this is
	 * calling from synchronized so not making it synchronized.
	 * 
	 * First It will sort by organization, then by Experience then by first name
	 * and then by last name.
	 */
	@Override
	public synchronized Stream<String> sort() {
		Stream<Employee> employees = EmployeeDataManager.getEmployees().stream();
		Comparator<Employee> byOraganization = Comparator.comparing(Employee::getOrganization)
				.thenComparing(Employee::getExperienceInMonth).thenComparing(Employee::getFirstName)
				.thenComparing(Employee::getLastName);
		Stream<Employee> empoyeeStream = employees.sorted(byOraganization);
		return empoyeeStream.map(e -> e.getOrganization() + COMMA + e.getExperienceInMonth() 
												+ COMMA + e.getFirstName()
												+ COMMA + e.getLastName());
	}

}
