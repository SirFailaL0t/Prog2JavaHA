package de.tuberlin.snet.prog2.ue04.treatment;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 
 * Class that can treat patient threads
 * 
 * @author Jakob Feldmann
 *
 */
public class Behandlungsraum {

	// max number of patients that can be treated simultaniously
	int maxp;

	// semaphore to keep patients at hold
	Semaphore patientz;
	
	/**
	 * Constructor that takes the max number of patients at a time
	 * 
	 * @param anz max number of patients
	 */
	public Behandlungsraum(int anz) {
		maxp = anz;
		patientz = new Semaphore(maxp);
	}

	/**
	 * Treatment for patient threads
	 * 
	 * @param x the patient
	 */
	public void behandlung(Patient x) {
		try {
			patientz.acquire();
			try {
				System.out.println(x + " in Behandlung");
				Patient.sleep(5000);
				System.out.println(x + " gute Besserung!");
				x.setBehandelt(true);

			} finally {
				patientz.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Takes min max and selects a random integer between
	 * 
	 * @param min
	 *            minimal time in ms
	 * @param max
	 *            maximal time
	 * @return returns a random integer
	 */
	public static int randInt(int min, int max) {

		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;

	}

	/**
	 * Determines a treatment time for patients
	 * 
	 * @param anf
	 *            minimal treatment time
	 * @param end
	 *            maximal treatment time
	 * @return returns a treatment time as a integer in ms
	 */
	@SuppressWarnings("unused")
	private int behandlungszeit(int anf, int end) {
		int rng = randInt(anf, end);
		return rng;
	}

}
