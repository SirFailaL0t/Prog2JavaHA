package de.tuberlin.snet.prog2.ue04.treatment;

public class Patient extends Thread {
	
	int patientNr;
	Behandlungsraum raum;

	Patient(int nr, Behandlungsraum raum) {
		this.patientNr = nr;
		this.raum = raum;
	}

	public void run() {
		raum.behandeln(this);
	}
}
