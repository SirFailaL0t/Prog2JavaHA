package de.tuberlin.snet.prog2.ue03.berlinsEinwohner;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for reading and searching a csv file
 * 
 * @author Jakob Feldmann
 *
 */
public class ReadSearch {
	
	static ArrayList<Berliner> einwohner = new ArrayList<Berliner>();
	
	/**
	 * reads data out of a csv file
	 */
	private static void reader(){
		Path csv = Paths.get("EWR_Ortsteile_Berlin_2015.csv");
		Scanner reads;
		
		try {
			
			reads = new Scanner(csv);
			while(reads.hasNextLine()){
				
				final String line = reads.nextLine();
				//System.out.println(line);
				String[] lines = line.split(";");
				addBerliner(lines);
				
			}
			reads.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	/**
	 * Adds new Berliner objects to the static ArrayList of Berliners in the Berliner class
	 * @param line of csv document that is read
	 */
	private static void addBerliner(String[] line){
		/*
		for(String b : line){
			System.out.println(b);
		}
		*/
		Berliner neuerB = new Berliner(line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7]);	
		
		einwohner.add(neuerB);
	}
	
	
	/**
	 * Searches through an list of Berliner objects to find specific data
	 * @param berliner input list of citizens
	 * @return returns data that was searched for as a string
	 */
	public static String search(ArrayList<Berliner> berliner){
		
		int i = 0;
		
		for(Berliner b : berliner){
			i++;
			
			if (b.ortsname.equals("Kaulsdorf") && b.geschl.equals("2") && b.staatsange.equals("D") && b.altersgr.equals("20_25")){
				return "Anzahl der Frauen zwischen 20 und 25 in Kaulsdorf: " +b.getHäufigkeit();
				
			}		
		}
		
		System.out.println(i);
		return null;		
	}
	
	
	public static void main(String[] args) {
		reader();
		System.out.println(search(ReadSearch.einwohner));
	}
	
}
