package de.tuberlin.snet.prog2.ue04.lock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The NewsTeam is the abstract representation of one group at the news agency.
 *
 */
public abstract class NewsTeam extends Thread {

	private NewsTicker ticker;
	
	//locker to keep news from interfering
	static ReentrantLock locker = new ReentrantLock(true);

	/**
	 * constructs a new NewsTeam and set the ticker attribute
	 * 
	 * @param t
	 *            is a reference to the NewsTicker object this team will post to
	 */
	public NewsTeam(NewsTicker t) {
		this.ticker = t;
	}

	public void run() {
		
		while (true) {

			try {
				locker.lock();
				long amount = (long) (new Random().nextDouble() * 2000);
				Thread.sleep(amount);
				String message = getLatestNews();
				ticker.displayMessage(message);
				locker.unlock();
			}

			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * The getLatestNews method has to be implemented by every NewTeam
	 * 
	 * @return a String containing the news message
	 */
	protected abstract String getLatestNews();
}
