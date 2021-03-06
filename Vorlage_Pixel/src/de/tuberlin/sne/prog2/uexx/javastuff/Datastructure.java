package de.tuberlin.sne.prog2.uexx.javastuff;

import java.io.File;
import java.util.concurrent.LinkedBlockingQueue;


@SuppressWarnings("unused")
public class Datastructure {
	
	public static void main(String[] args) {
		
		LinkedBlockingQueue<File> fl = new LinkedBlockingQueue<File>();
		
		LinkedBlockingQueue<File> folders = foldering(new File("C:\\Users\\jakob\\Documents\\My Games"), fl);
	
		for(File filo : folders){
			
			System.out.println(filo.toString());
			
		}
		
	}
	
	public static LinkedBlockingQueue<File> foldering(File x,LinkedBlockingQueue<File> fl){
		
		for(File fi : x.listFiles()){
			
			if(fi.isDirectory()){
				
					foldering(fi,fl);
				
					
			}
			else{
				fl.add(fi);
			}
				
		}
			
		return fl;
		
	}

}
