package com.viva.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * <p>
 * Project Name : Stream API Demo
 * </p>
 * <p>
 * File Name : StreamCreation.java
 * </p>
 * Description:
 * <p>
 * This utility class demonstrates various ways to create and use Java 8+ Stream
 * APIs. Streams represent sequences of elements supporting sequential and
 * parallel operations. Unlike collections, streams do not store data; they
 * operate on the source data such as arrays, collections, or I/O channels. The
 * class highlights several methods of stream creation including:
 * <ul>
 * <li>From values</li>
 * <li>From collections and arrays</li>
 * <li>Using builder, iterator, generator</li>
 * <li>From empty or regex-based filtering</li>
 * <li>From Iterator and Iterable interfaces</li>
 * </ul>
 * It also showcases intermediate and terminal operations like `forEach()`,
 * `filter()`, `limit()`, etc.
 * </p>
 * 
 * @author : Vijai Srirangan
 */
public class StreamCreation {

	/**
	 * Creates a Stream from individual String values using {@code Stream.of(...)}.
	 * Demonstrates a basic stream pipeline with a terminal operation
	 * {@code forEach()}.
	 */
	public static void createStreamFromValues() {
		Stream<String> strStream = Stream.of("Dv", "Vn", "Vj");
		strStream.forEach(System.out::println);
	}

	/**
	 * Creates a Stream from a {@code List} using the {@code stream()} method.
	 * Elements are printed using {@code forEach()} terminal operation.
	 */
	public static void createStreamFromCollection() {
		List<String> list = new ArrayList<String>();
		list.add("Dv");
		list.add("Vn");
		list.add("Vj");

		Stream<String> stream = list.stream();
		stream.forEach(System.out::println);
	}

	/**
	 * Creates a Stream from an array using {@code Arrays.stream(...)}. The array
	 * elements are printed to the console.
	 */
	public static void createStreamFromArray() {
		String[] strArray = new String[] { "Dv", "Vj", "vn" };
		Stream<String> strStream = Arrays.stream(strArray);
		strStream.forEach(System.out::println);
	}

	/**
	 * Creates a Stream from an array using {@code Stream.of(...)} method.
	 * Demonstrates an alternative to {@code Arrays.stream()}.
	 */
	public static void createStreamUsingStreamOf() {
		String[] strArray = new String[] { "Dv", "Vj", "vn" };
		Stream<String> strStream = Stream.of(strArray);
		strStream.forEach(System.out::println);
	}

	/**
	 * Demonstrates creation of an empty stream using {@code Stream.empty()}. Prints
	 * the count of elements, which should be zero.
	 */
	public static void createStreamUsingStreamEmpty() {
		Stream<String> streamOfArray = Stream.empty();
		System.out.println("Stream created using empty() method. (Size will be 0): Size :" + streamOfArray.count());
	}

	/**
	 * Demonstrates use of {@code Stream.builder()} to construct a stream. Allows
	 * dynamic construction of a stream before it is consumed.
	 */
	public static void createStreamUsingStreamBuilder() {
		Stream.Builder<String> builder = Stream.builder();
		Stream<String> strStream = builder.add("Dv").add("Vn").add("Vj").build();
		strStream.forEach(System.out::println);
	}

	/**
	 * Creates a stream using {@code Stream.iterate()} to generate an infinite
	 * sequence. The stream is limited by the {@code limitValue} parameter.
	 *
	 * @param initValue  the initial value to start the iteration
	 * @param limitValue the maximum number of elements to generate
	 */
	public static void createStreamUsingStreamIterate(int initValue, int limitValue) {
		Stream.iterate(initValue, (Integer n) -> n * n).limit(limitValue).forEach(System.out::println);
	}

	/**
	 * Creates a stream using {@code Stream.generate()} with {@code Math.random()}.
	 * The stream is limited to the specified number of random values.
	 *
	 * @param limitValue number of random values to generate
	 */
	public static void createStreamUsingStreamGenerate(int limitValue) {
		Stream.generate(Math::random).limit(limitValue).forEach(System.out::println);
	}

	/**
	 * Filters a list of strings using a regular expression pattern (like starts
	 * with 'P'). Demonstrates use of {@code Pattern.asPredicate()} for stream
	 * filtering.
	 *
	 * @param input list of strings to filter and print
	 */
	public static void createStreamUsingPattern(List<String> input) {
		input.stream().filter(Pattern.compile("^P").asPredicate()).forEach(System.out::println);
	}

	/**
	 * Converts an {@code Iterator} into a stream using
	 * {@code StreamSupport.stream()}. Demonstrates interoperability between legacy
	 * iterators and modern stream API.
	 *
	 * @param input list of strings to iterate and stream
	 */
	public static void createStreamUsingIterator(List<String> input) {
		Iterator<String> it = input.iterator();
		Spliterator<String> spi = Spliterators.spliteratorUnknownSize(it, Spliterator.NONNULL);
		StreamSupport.stream(spi, false).forEach(System.out::println);
	}

	/**
	 * Converts an {@code Iterable} into a stream using
	 * {@code StreamSupport.stream()}. Suitable for data structures that implement
	 * Iterable but not Collection.
	 *
	 * @param input iterable source to convert and print
	 */
	public static void createStreamUsingIterable(List<String> input) {
		Iterable<String> it = input;
		StreamSupport.stream(it.spliterator(), false).forEach(System.out::println);
	}

	/**
	 * Entry point of the program. Invokes all static stream creation methods with
	 * sample data and prints output to the console.
	 *
	 * @param args command-line arguments (unused)
	 */
	public static void main(String[] args) {
		List<String> fruits = Arrays.asList("Apple", "Banana", "Orange", "Mango", "Pear", "Pomegranate", "Grape",
				"Guava", "Pineapple", "Strawberry", "Watermelon", "Avocado", "Kiwi", "Melons", "Papaya");

		System.out.println("---- Creating Stream from specified Values ----");
		createStreamFromValues();

		System.out.println("---- Creating Stream from Collection ----");
		createStreamFromCollection();

		System.out.println("---- Creating Stream from Array ----");
		createStreamFromArray();

		System.out.println("---- Creating Stream Using Stream.of() Method ----");
		createStreamUsingStreamOf();

		System.out.println("---- Creating Stream Using Stream.empty() Method ----");
		createStreamUsingStreamEmpty();

		System.out.println("---- Creating Stream Using Stream.builder() Method ----");
		createStreamUsingStreamBuilder();

		System.out.println("---- Creating infinite Stream Using Stream.iterate() ----");
		createStreamUsingStreamIterate(4, 5);

		System.out.println("---- Creating Stream Using Stream.generate() ----");
		createStreamUsingStreamGenerate(5);

		System.out.println("---- Creating Stream from a Pattern using Predicate ----");
		createStreamUsingPattern(fruits);

		System.out.println("---- Creating Stream Using Iterator ----");
		createStreamUsingIterator(fruits);

		System.out.println("---- Creating Stream Using Iterable ----");
		createStreamUsingIterable(fruits);
	}
}
