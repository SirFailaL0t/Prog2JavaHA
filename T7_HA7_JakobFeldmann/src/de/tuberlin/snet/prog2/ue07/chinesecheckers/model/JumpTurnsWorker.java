package de.tuberlin.snet.prog2.ue07.chinesecheckers.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

import de.tuberlin.snet.prog2.ue07.chinesecheckers.controller.Main;

public class JumpTurnsWorker extends Thread {

	private LinkedBlockingQueue<Turn> incompleteJumps;
	private LinkedBlockingQueue<Turn> jumpTurns;

	public JumpTurnsWorker(LinkedBlockingQueue<Turn> incompleteJumps, LinkedBlockingQueue<Turn> jumpTurns) {
		this.incompleteJumps = incompleteJumps;
		this.jumpTurns = jumpTurns;
	}

	@Override
	public void run() {
		try {
			Turn currentTurn = null;
			while (!isInterrupted()) {
				currentTurn = incompleteJumps.take();
				// finish, if there are no incomplete jumps
				if (currentTurn == null) {
					this.wait(400);
					if (currentTurn == null) {
						return;
					}
				}
				System.out.println(currentTurn.getScore());
				Collection<Turn> foundJumpTurns = searchForJumpTurns(currentTurn);
				jumpTurns.addAll(foundJumpTurns);
				incompleteJumps.addAll(foundJumpTurns);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Collection<Turn> searchForJumpTurns(Turn currentTurn) throws InterruptedException {
		Collection<Turn> foundJumpTurns = new LinkedList<Turn>();
		Position initialJumpPosition;
		// check, if this is the first jump of this turn
		if (currentTurn.getJumplist().isEmpty())
			initialJumpPosition = new Position(currentTurn.getPiece());
		else
			initialJumpPosition = GameState.getLastPosition(currentTurn.getPiece(), currentTurn.getJumplist());

		for (Piece obstacle : Board.getAllPossibleJumpTurns(Main.state, initialJumpPosition)) {
			// only add new obstacles, to avoid forward and backward
			// jumps (although it can be jumped over in another
			// direction)
			if (currentTurn.getJumplist().contains(obstacle))
				continue;
			Turn foundJumpTurn = currentTurn.clone();
			foundJumpTurn.addJumpPiece(obstacle);
			foundJumpTurns.add(foundJumpTurn);
		}
		return foundJumpTurns;
	}
}
