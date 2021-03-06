package de.tuberlin.snet.prog2.ue05.sportreporter;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Represents a reporter posting comments of the current match to his live
 * ticker.
 * 
 * @author prog2-team
 *
 */
public class SportReporter implements Runnable {

	String spiel;
	BlockingQueue<String> ereignis;
	Random random = new Random();

	SoccerEvent event;

	/**
	 * Sport reporter used for comment a match.
	 * 
	 * @param match
	 *            name of the match (e.g. names of the playing teams)
	 * @param eventList
	 *            list for posting the comments
	 */
	public SportReporter(String match, BlockingQueue<String> eventList) {
		this.spiel = match;
		this.ereignis = eventList;
	}

	@Override
	public void run() {
		
		SoccerEvent neu;
		
		while (!Thread.currentThread().isInterrupted()) {

			 neu = SoccerEvent.getRandomEvent();

			try {
				Thread.sleep(random.nextInt(6000));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			
			synchronized (ereignis) {
				ereignis.add(spiel +": " + neu.getCatchword() + "\n");
			}
		}
	}

}
