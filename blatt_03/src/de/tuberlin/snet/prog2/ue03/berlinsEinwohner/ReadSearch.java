package de.tuberlin.snet.prog2.ue03.berlinsEinwohner;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import jdk.nashorn.internal.runtime.regexp.joni.SearchAlgorithm;

public class ReadSearch {
	
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
	
	private static void addBerliner(String[] line){
		/*
		for(String b : line){
			System.out.println(b);
		}
		*/
		Berliner neuerB = new Berliner(line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7]);
		
	}
	
	public static String search(ArrayList<Berliner> berliner){
		
		int i = 0;
		
		for(Berliner b : berliner){
			
			i++;
			
			//System.out.println(b.getOrtsname() + " " + b.getGeschl() + " " + b.getStaatsangeh() + " " +  b.getAltersgr());
			
			if (b.ortsname.equals("Kaulsdorf") && b.geschl.equals("2") && b.staatsange.equals("D") && b.altersgr.equals("20_25")){
				
				return b.getHäufigkeit();
				
			}
			
		}
		
		System.out.println(i);
		
		return null;
		
		
		
	}
	
	public static void main(String[] args) {
		
		reader();
		System.out.println(search(Berliner.einwohner));
		
	}
	
}