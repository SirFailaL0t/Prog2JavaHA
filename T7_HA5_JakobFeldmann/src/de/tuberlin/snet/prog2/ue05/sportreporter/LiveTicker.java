package de.tuberlin.snet.prog2.ue05.sportreporter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Represents a server consuming comments of sport reporters, updating the
 * output.
 * 
 * @author prog2-team
 *
 */
public class LiveTicker implements Runnable {
	
	//List of events that are going to be printed
	public LinkedBlockingQueue<String> eventList = new LinkedBlockingQueue<>();
	
	/**
	 * prints out the news that were send in by the reporters
	 */
	@Override
	public void run() {

		while (!Thread.currentThread().isInterrupted()) {

			try {
				Thread.sleep(1000);
				if (!eventList.isEmpty()) {
					System.out.println(eventList.poll());
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

	}
	
	/**
	 * @return returns the list containing the comments
	 */
	public BlockingQueue<String> getEventList() {
		return eventList;
	}
	
	/**
	 * Creates reporter teams, starts and suspends this
	 * @param args
	 */
	public static void main(String[] args) {

		LiveTicker liveTicker = new LiveTicker();
		SportReporter reporter1 = new SportReporter("M�nchen vs. Dortmund", liveTicker.getEventList());
		SportReporter reporter2 = new SportReporter("Berlin vs. Hamburg", liveTicker.getEventList());
		SportReporter reporter3 = new SportReporter("Stuttgart vs. K�ln", liveTicker.getEventList());

		Thread live = new Thread(liveTicker);

		Thread rep1 = new Thread(reporter1);
		Thread rep2 = new Thread(reporter2);
		Thread rep3 = new Thread(reporter3);

		live.start();
		rep1.start();
		rep2.start();
		rep3.start();

		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		live.interrupt();
		rep1.interrupt();
		rep2.interrupt();
		rep3.interrupt();

	}

}
