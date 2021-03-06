package de.tuberlin.snet.prog2.ue03.watchtxt;

import java.io.File;
import java.io.IOException;

/**
 * 
 * Program execution instructions for task 3.2
 * 
 * @author Jakob Feldmann
 *
 */
public class ReadTxt {
	public static void main(String[] args) {
		
		File read = new File("Read.txt");
		try {
			read.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		System.out.println(read.getAbsolutePath());
		
		///*
		ReadTextRunnable rtr = new ReadTextRunnable(read);
		Thread rtrt = new Thread(rtr);
		rtrt.start();
		//*/
		
		/*
		ReadTextThread rtt = new ReadTextThread(read);
		rtt.start();
		*/
		
		
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		rtrt.interrupt();
		//rtt.interrupt();
		
	}
}
