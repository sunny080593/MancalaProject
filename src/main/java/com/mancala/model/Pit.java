package com.mancala.model;


public class Pit {

	protected String pitOwner; // Owner of the pit
	protected int pitNumber; // pit index no
	protected int stones; // number of stones in the pit
	protected Pit nextPit;// next pit index

	public Pit(String owner, int pitNumber) {
		this(owner, pitNumber, 0, null);
	}

	public Pit(String owner, int pitNumber, int d, Pit n) {
		this.stones = d;
		this.nextPit = n;
		this.pitOwner = owner;
		this.pitNumber = pitNumber;
	}

	public void setNextPit(Pit n) {
		nextPit = n;
	}

	public void setStones(int d) {
		stones = d;
	}

	public Pit getNextPit() {
		return nextPit;
	}

	public int getStones() {
		return stones;
	}

	public void setPitOwner(String pitOwner) {
		this.pitOwner = pitOwner;
	}

	public String getpitOwner() {
		return this.pitOwner;
	}

	public void setPitNumber(int pitNumber) {
		this.pitNumber = pitNumber;
	}

	public int getPitNumber() {
		return this.pitNumber;
	}

	@Override
	public String toString() {
		return "Pit [owner=" + pitOwner + ", pitNumber=" + pitNumber + ", stones=" + stones + ", nextPit=["
				+ nextPit.pitNumber + " of " + nextPit.pitOwner + "]]";
	}

	public String getPitDetails() {
		if (this.stones < 10) {
			return +this.stones + "";
		}
		return +this.stones + "";
	}

}
