package de.tuberlin.snet.prog2.ue09.task2and3;

import java.util.stream.Stream;

public class Task2{
	public static void main(String[] args) {

		
		
		Stream.of("d2", "a2", "b1", "b3", "c", "a6")
			.filter(s -> {System.out.println("filter: " + s); return s.startsWith("a");})
			.limit(2)
			.map(s -> {System.out.println("map: " + s);return s.toUpperCase();})
			.forEach(s -> System.out.println("forEach: " + s + "\n"));
		
		
		Stream.of("d2", "a2", "b1", "b3", "c", "a6")
			  .sorted()
			  .filter(s -> {System.out.println("filter: " + s); return s.startsWith("a");})
			  .limit(2)
			  .map(s -> {System.out.println("map: " + s);return s.toUpperCase();})
			  .forEach(s -> System.out.println("forEach: " + s));
	}


}
