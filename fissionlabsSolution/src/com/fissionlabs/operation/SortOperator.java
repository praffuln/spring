package com.fissionlabs.operation;

import java.util.stream.Stream;

/**
 * @author Prafful
 *
 * Implementation of this interface will produce different ways of
 * sorting for employees data.
 *
 */
public interface SortOperator {
	public Stream<String> sort();
}
