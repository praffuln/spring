package com.fissionlabs.task;

import java.io.IOException;

import com.fissionlabs.fileoperation.EmployeeFileWriter;
import com.fissionlabs.operation.EmployeeFieldAppender;

/**
 * @author Prafful
 *  
 */
public class EmployeeFieldAppenderTask  implements Runnable {
	
	public String fileName;
	
	// Can take Interface and pass implementation of that interface into
	// constructor. but as per requirement is only one find appender than 
	// I am using class.
	public EmployeeFieldAppender employeeFieldAppender;
	
	public EmployeeFieldAppenderTask(EmployeeFieldAppender employeeFieldAppender, String fileName) {
		this.employeeFieldAppender = employeeFieldAppender;
		this.fileName = fileName;
	}
	
	@Override
	public void run() {
		try {
			EmployeeFileWriter.writeInFile(fileName, employeeFieldAppender.appendFieldsAsToString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
