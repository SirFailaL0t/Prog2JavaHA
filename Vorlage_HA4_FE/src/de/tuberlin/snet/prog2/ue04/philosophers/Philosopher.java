package de.tuberlin.snet.prog2.ue04.philosophers;

import java.util.Random;

/**
 * This class represents a philosopher. He is doing the following (in this
 * order):
 * <ul>
 * <li>thinking for 0-1 seconds</li>
 * <li>picks up his left chopstick (or waits until it is free)</li>
 * <li>picks up his right chopstick (or waits until it is free)</li>
 * <li>eats for 0-1 seconds</li>
 * <li>puts down his right chopstick</li>
 * <li>puts down his left chopstick</li>
 * </ul>
 * and repeats this.
 * 
 * @author prog2-team
 */
public class Philosopher implements Runnable {

	Chopstick leftChopstick;
	Chopstick rightChopstick;
	String name;
	Random random = new Random();

	public Philosopher(String name, Chopstick leftChopstick, Chopstick rightChopstick) {
		this.name = name;
		this.leftChopstick = leftChopstick;
		this.rightChopstick = rightChopstick;
	}

	private static synchronized void lockChopsticks(Philosopher phil) throws InterruptedException {
		phil.leftChopstick.pickUp();
		System.out.println(phil + " picks up his left chopstick");
		// to provoke a deadlock:
//		Thread.yield();
		phil.rightChopstick.pickUp();
		System.out.println(phil + " picks up his right chopstick");
	}

	@Override
	public void run() {
		try {
			while (true) {
				lockChopsticks(this);
				eat();
				rightChopstick.putDown();
				System.out.println(this + " puts down his right chopstick");
				leftChopstick.putDown();
				System.out.println(this + " puts down his left chopstick");
				think();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void think() throws InterruptedException {
		System.out.println(this + " is thinking");
		Thread.sleep(random.nextInt(10000));
	}

	private void eat() throws InterruptedException {
		System.out.println(this + " is eating");
		Thread.sleep(random.nextInt(10000));
	}

	public String toString() {
		return name;
	}

}
