package com.fissionlabs.main;

import static com.fissionlabs.model.Constants.COMMA;
import static com.fissionlabs.model.Constants.EXIT;
import static com.fissionlabs.model.Constants.SORT;
import static com.fissionlabs.model.Constants.TXT_APPEND_ALL_FIELDS_EACH_RECORD;
import static com.fissionlabs.model.Constants.TXT_SORT_BY_EXPERIENCE_AGE_RATIO;
import static com.fissionlabs.model.Constants.TXT_SORT_BY_ORGANIZATION;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.fissionlabs.operation.EmployeeCollector;
import com.fissionlabs.operation.EmployeeFieldAppender;
import com.fissionlabs.operation.SortByOrganization;
import com.fissionlabs.operation.SortedByExperienceAgeRatio;
import com.fissionlabs.task.EmployeeFieldAppenderTask;
import com.fissionlabs.task.SortingTask;

/**
 * @author Prafful
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
		// create a scanner so we can read the command-line input
		Scanner scanner = new Scanner(System.in);
		// can create also cachedThreadPool, but we need three threads so
		// created fixed thread pool.
		ExecutorService executor = Executors.newFixedThreadPool(3);
		while (true) {
			// prompt for the user's name
			System.out.print("Enter your input: ");
			// get employee input as a String
			String input = scanner.nextLine();
			if (EXIT.equalsIgnoreCase(input)) {
				break;
			} else if (SORT.equalsIgnoreCase(input)) {
				//separate thread from main thread for file operations.
				sortOperations(executor);
				simulateFiveSecondWaitInMainThread();
			} else if (input != null && input.contains(COMMA)) {
				EmployeeCollector.collectEmployee(input);
			} else {
				System.out.println("Input is not correct. Please enter right Input");
			}
		}
		// close scanner and shutdown executor
		scanner.close();
		shutDownExecutor(executor);
	}

	/**
	 * @param executor
	 */
	private static void sortOperations(ExecutorService executor) {
		executor.submit(new SortingTask(new SortByOrganization(), TXT_SORT_BY_ORGANIZATION));
		executor.submit(new SortingTask(new SortedByExperienceAgeRatio(), TXT_SORT_BY_EXPERIENCE_AGE_RATIO));
		executor.submit(new EmployeeFieldAppenderTask(new EmployeeFieldAppender(), TXT_APPEND_ALL_FIELDS_EACH_RECORD));
	}

	/**
	 * @param executor
	 */
	private static void shutDownExecutor(ExecutorService executor) {
		executor.shutdown();
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * simulate a five second wait in main thread, while writing in files.
	 */
	private static void simulateFiveSecondWaitInMainThread() {
		try {
			System.out.println("*******wait writting in file**************");
			Thread.sleep(5000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
