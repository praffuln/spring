package com.fissionlabs.operation;

import static com.fissionlabs.model.Constants.COMMA;

import java.util.Comparator;
import java.util.stream.Stream;

import com.fissionlabs.dao.EmployeeDataManager;
import com.fissionlabs.model.Employee;

/**
 * @author Prafful
 *
 */
public class SortedByExperienceAgeRatio implements SortOperator {

	/*
	 * Experience/age ratio, Organization Experience is in months and age is in
	 * year. We can convert age into month and then get the ratio of Experience
	 * and age.
	 * 
	 * In this solution, Experience/age ratio is calculating directly, then
	 * comparing between organizations. (Experience in month and age in year)
	 * 
	 * @see com.fissionlabs.operation.SortOperator#sort()
	 */
	@Override
	public synchronized Stream<String> sort() {
		Stream<Employee> employees = EmployeeDataManager.getEmployees().stream();
		Comparator<Employee> experienceAgeRatioComparator = (Employee emp1, Employee emp2) -> {
			if(emp1.getExperienceInMonth() != null && emp1.getAgeInYear() != null &&
					emp2.getExperienceInMonth() != null && emp2.getAgeInYear() != null) {
			return Double.compare((emp1.getExperienceInMonth() / emp1.getAgeInYear()),
					(emp2.getExperienceInMonth() / emp2.getAgeInYear()));
			}
			return 0;
		};
		// after ratio comparing with organization
		experienceAgeRatioComparator.thenComparing(Employee::getOrganization);
		Stream<Employee> empoyeeStream = employees.sorted(experienceAgeRatioComparator);
		return empoyeeStream.map(e -> (e.getExperienceInMonth() / e.getAgeInYear()) + COMMA 
															+ e.getOrganization());
	}
}
