package de.tuberlin.snet.prog2.ue02.scheduling;

/**
 * Use this class to test your FIFOScheduler-class. Created by prog2-team and
 * you!
 */
public class SchedulingMain {

	public static void main(String[] args) {

		Prog2Thread prog2 = new Prog2Thread('o');
		PiThread pi = new PiThread("Pi", 5);
		PiThread pi2 = new PiThread("Pi", 500);
		PiThread pi3 = new PiThread("Pi", 50000);
		PiThread pi4 = new PiThread("Pi", 500000);

		FIFOScheduler schedule = new FIFOScheduler();

		schedule.addThread(prog2);
		schedule.addThread(pi);
		schedule.addThread(pi2);
		schedule.addThread(pi3);
		schedule.addThread(pi4);

		schedule.start();
	}

}