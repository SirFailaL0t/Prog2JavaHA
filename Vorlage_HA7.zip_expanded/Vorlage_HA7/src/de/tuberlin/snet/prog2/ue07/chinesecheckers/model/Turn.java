package de.tuberlin.snet.prog2.ue07.chinesecheckers.model;

import java.util.List;

public class Turn {

	ArtificialPlayer pone;
	Piece figur;
	Position goal;
	List<Piece> jumplist;
	float score;

	public Position getGoal() {
		if (!this.jumplist.isEmpty()) {
			return GameState.getLastPosition(getPiece(), getJumplist());
		}
		if (!this.jumplist.isEmpty() && goal == null) {
			return figur.position;
		} else
			return goal;
	}

	public void setGoal(Position goal) {
		this.goal = goal;
	}

	public ArtificialPlayer getPone() {
		return pone;
	}

	public Piece getPiece() {
		return figur;
	}

	public List<Piece> getJumplist() {
		return jumplist;
	}

	public float getScore() {
		return pone.getTurnBenefit(figur.position, getGoal());
	}

	Turn(ArtificialPlayer player, Piece moving) {
		this.pone = player;
		this.figur = moving;

	}

	Turn(ArtificialPlayer player, Piece moving, Position desti) {
		this.pone = player;
		this.figur = moving;
		this.goal = desti;
	}

	@Override
	public Turn clone() {
		Turn flatcopy = new Turn(this.pone, this.figur, this.goal);
		for (Piece a : this.jumplist) {
			flatcopy.jumplist.add(a);
		}
		return flatcopy;
	}

	public void addJumpPiece(Piece jumpable) {
		jumplist.add(jumpable);
	}

}
