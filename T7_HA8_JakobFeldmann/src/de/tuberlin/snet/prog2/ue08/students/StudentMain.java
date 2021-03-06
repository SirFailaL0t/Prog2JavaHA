package de.tuberlin.snet.prog2.ue08.students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentMain {


	public static void main(String[] args) {
		List<Student> students = generateStudents();
		
		
		
		List<Student> goodStudents = students.stream()
											 .filter(d -> d.studymajor == "Wi-Inf")
											 .filter(d -> d.grade<4.0)
											 .collect(Collectors.toList());
		
		
		
		goodStudents.stream().forEach(System.out::println);
		
		
		
		
		
		Optional<Student> bestStudent = students.stream()
									  			.reduce(Student::compareTo);
		
		
		
		
		System.out.println(bestStudent);
		
		
	}

public static List<Student> generateStudents() {
		List<Student> students = new ArrayList<Student>();
		Student student = new Student("John", "Doe","Wi-Inf",1.0);
		Student student1 = new Student("Jack", "Doe", "Wi-Inf", 5.0);
		Student student2 = new Student("Chuck", "Norris", "Everything", 0.9);
		Student student3 = new Student("John", "Hancock", "DrugPrevention", 1.3);
		Student student4 = new Student("Peter", "Pan", "PiracyStudies", 3.7);
		Student student5 = new Student("Tony", "Stark", "ElectricalEngineering", 1.7);
		Student student6 = new Student("Peter", "Parker", "Biology", 5.0);
		Student student7 = new Student("Bruce", "Wayne", "BusinessAdministration", 4.0);
		Student student8 = new Student("Clark", "Kent", "Journalism", 1.0);
		Student student9 = new Student("Logan", "Howlett", "IndustrialDesign", 2.7);
		Student student10 = new Student("Robert Bruce", "Banner", "Physics", 5.0);
		Student student11 = new Student("Arthur", "Dent", "Ultimate Question of Life, the Universe, and Everything",42.0);
		Student student12 = new Student("Alice", "Wonderland", "Wi-Inf", 2.0);
		Student student13 = new Student("Bob", "Wonderland", "Wi-Inf", 3.0);
		
		students.add(student);
		students.add(student1);
		students.add(student2);
		students.add(student3);
		students.add(student4);
		students.add(student5);
		students.add(student6);
		students.add(student7);
		students.add(student8);
		students.add(student9);
		students.add(student10);
		students.add(student11);
		students.add(student12);
		students.add(student13);
		return students;
	}
	
}
