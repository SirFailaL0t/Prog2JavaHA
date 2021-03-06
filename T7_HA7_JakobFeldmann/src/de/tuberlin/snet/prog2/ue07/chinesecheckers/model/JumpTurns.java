package de.tuberlin.snet.prog2.ue07.chinesecheckers.model;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class JumpTurns {

	ArtificialPlayer player;

	public JumpTurns(ArtificialPlayer player) {
		this.player = player;
	}

	/**
	 * Returns the best move turn of all possible move turns.
	 * 
	 * @return move turn with the best progress
	 */
	Turn getBestJumpTurn() {
		Collection<Turn> allJumpTurns = getAllPossibleJumpTurns();

		Turn bestJump = allJumpTurns.stream()
				.sorted((t2, t1) -> Float.compare(player.getTurnBenefit(new Position(t1.getPiece()), t1.getGoal()),
						player.getTurnBenefit(new Position(t2.getPiece()), t2.getGoal())))
				.findFirst().get();

		return bestJump;
	}

	/**
	 * Searches and returns all possible jump turns of this player.
	 * 
	 * @return collection of jump consisting of positions
	 */
	Collection<Turn> getAllPossibleJumpTurns() {
		Collection<Piece> jumpables = player.getPieces().stream()
				.filter(e -> (Board.getAllPossibleJumpTurns(player.state, new Position(e)).size() > 0))
				.collect(Collectors.toList());
		
		//LinkedBlockingQueue
		LinkedBlockingQueue<Turn> incompleteJumps = new LinkedBlockingQueue<>();
		LinkedBlockingQueue<Turn> jumpTurns = new LinkedBlockingQueue<>();

		for (Piece jumpable : jumpables) {
			incompleteJumps.add(new Turn(player, jumpable));
		}
		
		ExecutorService execution = Executors.newFixedThreadPool(4);
		
		for (int i = 0; i < 4; i++) {
			execution.submit(new JumpTurnsWorker(incompleteJumps, jumpTurns));
		}
		
		System.out.println(jumpTurns);
		return jumpTurns;
	}
}
