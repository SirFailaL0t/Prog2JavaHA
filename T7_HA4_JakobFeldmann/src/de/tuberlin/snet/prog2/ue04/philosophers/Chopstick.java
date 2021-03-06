package de.tuberlin.snet.prog2.ue04.philosophers;

import java.util.concurrent.Semaphore;

/**
 * Represents a chopstick used by the philosophers. If a philosopher holds the
 * chopstick and another wants to pick it up he has to wait until the chopstick
 * is free.
 * 
 * @author prog2-team
 *
 */
public class Chopstick {
	
	//determines if stick is in use
	Semaphore inuse = new Semaphore(1);

	/**
	 * Waits until the chopstick is free and picks it up.
	 * @throws InterruptedException
	 */
	public void pickUp() throws InterruptedException {
		while (inuse.tryAcquire() == false) {
			Thread.sleep(100);
		}
	}

	/**
	 * Puts the chopstick down so it can be used by another philosopher.
	 */
	public void putDown() {
		inuse.release();
	}

}


