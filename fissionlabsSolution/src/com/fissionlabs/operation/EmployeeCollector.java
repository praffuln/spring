package com.fissionlabs.operation;

import static com.fissionlabs.model.Constants.COMMA;
import static com.fissionlabs.model.Constants.EMPLOYEE_FIELD_NUMBER;

import com.fissionlabs.dao.EmployeeDataManager;
import com.fissionlabs.model.Employee;

/**
 * @author Prafful
 * 
 * This class is used to store employee getting from the console input. Employee
 * is in String format, extract employee from the employee and save them into
 * employee Object.
 * 
 * No method is synchronized, it is single threaded operation.
 * 
 */
public class EmployeeCollector {

	/**
	 * @param employeeStr
	 */
	public static void collectEmployee(String employeeStr) {
		// do nothing is employeeStr is not valid
		if (employeeStr == null || employeeStr.isEmpty())
			return;

		String[] employeeFields = employeeStr.split(COMMA);

		// data has no. of commas equals to EMPLOYEE_FIELD_NUMBER.
		// check if there is valid employee parameter numbers
		if (employeeFields.length == EMPLOYEE_FIELD_NUMBER) {
			String firstName = employeeFields[0] != null ? employeeFields[0].trim() : employeeFields[0];
			String lastName = employeeFields[1] != null ? employeeFields[1].trim() : employeeFields[1];
			String experienceInMonths = employeeFields[2] != null ? employeeFields[2].trim() : employeeFields[2];
			Integer experienceInMonthsIntegerValue = null;
			try {
				experienceInMonthsIntegerValue = Integer.valueOf(experienceInMonths);
			} catch (NumberFormatException nfe) {
				nfe.printStackTrace();
				return;
			}
			String ageInYears = employeeFields[3] != null ? employeeFields[3].trim() : employeeFields[3];
			Integer ageInYearsIntegerValue = null;
			try {
				ageInYearsIntegerValue = Integer.valueOf(ageInYears);
			} catch (NumberFormatException nfe) {
				nfe.printStackTrace();
				return;
			}
			String organization = employeeFields[4] != null ? employeeFields[4].trim() : employeeFields[4];
			EmployeeDataManager.addEmployee(new Employee(firstName, lastName, experienceInMonthsIntegerValue,
					ageInYearsIntegerValue, organization));
		}

	}

}
