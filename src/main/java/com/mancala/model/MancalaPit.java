package com.mancala.model;


public class MancalaPit extends Pit {

	//  Mancala  index is Always has pitNumber =7
	public MancalaPit(String owner) {
		super(owner, 7);
	}

//  Mancala  index is Always has pitNumber =7
	public MancalaPit(String owner, int d, Pit n) {
		super(owner, 7, d, n);
	}

	@Override
	public String getPitDetails() {
		if (this.stones < 10) {
			return this.stones + ""; 
		}
		return this.stones + ""; 
		}
}