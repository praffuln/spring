package com.fissionlabs.task;

import java.io.IOException;
import java.util.stream.Stream;

import com.fissionlabs.fileoperation.EmployeeFileWriter;
import com.fissionlabs.operation.SortOperator;

/**
 * @author Prafful
 *
 */
public class SortingTask implements Runnable {
	
	private SortOperator operator;
	private String fileName;
	
	public SortingTask(SortOperator operator, String fileName) {
		this.operator = operator;
		this.fileName = fileName;
	}
	
	@Override
	public void run() {
		try {
			Stream<String> employeeAsStringStream = operator.sort();
			EmployeeFileWriter.writeInFile( fileName, employeeAsStringStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
