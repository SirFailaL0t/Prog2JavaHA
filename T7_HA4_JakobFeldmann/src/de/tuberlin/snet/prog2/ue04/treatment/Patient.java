package de.tuberlin.snet.prog2.ue04.treatment;

/**
 * 
 * Patient class that wants to be treated in a treatment room
 * 
 * @author Jakob Feldmann
 *
 */
public class Patient extends Thread {

	static int id = 0;
	int nxtid = ++id;
	// patient id
	int patientnr = nxtid;
	// patient treatment status
	boolean behandelt = false;
	// the room the patient wants to be treated in
	Behandlungsraum raum;

	public void setBehandelt(boolean behandelt) {
		this.behandelt = behandelt;
	}

	/**
	 * Constructor for Patient
	 * 
	 * @param r Behandlungsraum where the patient is to be treated in   
	 */
	public Patient(Behandlungsraum r) {

		raum = r;

	}

	/**
	 * Method that tries to invoke the behandlung method of the Behandlungsraunm
	 */
	@Override
	public void run() {

		while (this.behandelt == false) {

			raum.behandlung(this);

		}
	}

	@Override
	public String toString() {

		return "Patient Nummer: " + patientnr;

	}

}
