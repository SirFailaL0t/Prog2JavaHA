package de.tuberlin.snet.prog2.ue04.treatment;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents a chopstick used by the philosophers. If a philosopher holds the
 * chopstick and another wants to pick it up he has to wait until the chopstick
 * is free.
 * 
 * @author prog2-team
 *
 */
public class Chopstick {
	
	ReentrantLock inuse = new ReentrantLock();

	/**
	 * Waits until the chopstick is free and picks it up.
	 * @throws InterruptedException
	 */
	public void pickUp() throws InterruptedException {
		while (inuse.isLocked()) {
			Thread.sleep(10);
		}
		inuse.lock();
	}

	/**
	 * Puts the chopstick down so it can be used by another philosopher.
	 */
	public void putDown() {
		inuse.unlock();
	}

}


