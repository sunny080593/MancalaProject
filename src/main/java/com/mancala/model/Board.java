package com.mancala.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

	
	String player1Name;
	String player2Name;
	private boolean isPlayer1Turn;//Checking the turn of player
	boolean isGameOver;
	public MancalaPit Player1Mancala;//Mancala pit of player1
	public MancalaPit Player2Mancala;//Mancala pit of player2
	String message; // display message on baord

	public Pit[] Player1Pits;
	public Pit[] Player2Pits;

	public Board(String player1Name, String player2Name) {
		// initialize the names
		this.player1Name = player1Name;
		this.player2Name = player2Name;	
		this.setPlayer1Turn(true);// give player 1 as first chance
		this.isGameOver = false;

		//define 2 array for the  two different player
		Player1Pits = new Pit[6]; 
		Player2Pits = new Pit[6];
		this.initialiseBoard();
	}

	public void initialiseBoard() {
		// Define Player1 Pits in descending order 
		Player1Mancala = new MancalaPit(this.player1Name, 0, null);
		Pit player1_Pit6 = new Pit(this.player1Name, 6, 1, Player1Mancala);
		Pit player1_Pit5 = new Pit(this.player1Name, 5, 1, player1_Pit6);
		Pit player1_Pit4 = new Pit(this.player1Name, 4, 1, player1_Pit5);
		Pit player1_Pit3 = new Pit(this.player1Name, 3, 1, player1_Pit4);
		Pit player1_Pit2 = new Pit(this.player1Name, 2, 1, player1_Pit3);
		Pit player1_Pit1 = new Pit(this.player1Name, 1, 1, player1_Pit2);

		// Define Player2 Pits in descending order 
		Player2Mancala = new MancalaPit(this.player2Name, 0, null);
		Pit player2_Pit6 = new Pit(this.player2Name, 6, 1, Player2Mancala);
		Pit player2_Pit5 = new Pit(this.player2Name, 5, 1, player2_Pit6);
		Pit player2_Pit4 = new Pit(this.player2Name, 4, 1, player2_Pit5);
		Pit player2_Pit3 = new Pit(this.player2Name, 3, 1, player2_Pit4);
		Pit player2_Pit2 = new Pit(this.player2Name, 2, 1, player2_Pit3);
		Pit player2_Pit1 = new Pit(this.player2Name, 1, 1, player2_Pit2);

		// Complete the  board by connecting mancala with player pits
		Player1Mancala.setNextPit(player2_Pit1);
		Player2Mancala.setNextPit(player1_Pit1);

		//Initialize the Player1 board 
		this.Player1Pits[0] = player1_Pit1;
		this.Player1Pits[1] = player1_Pit2;
		this.Player1Pits[2] = player1_Pit3;
		this.Player1Pits[3] = player1_Pit4;
		this.Player1Pits[4] = player1_Pit5;
		this.Player1Pits[5] = player1_Pit6;

		// Initialize the Player2 board 
		this.Player2Pits[0] = player2_Pit1;
		this.Player2Pits[1] = player2_Pit2;
		this.Player2Pits[2] = player2_Pit3;
		this.Player2Pits[3] = player2_Pit4;
		this.Player2Pits[4] = player2_Pit5;
		this.Player2Pits[5] = player2_Pit6;
	}

	private Pit getPlayer1Pit(int pitNumber) {
		return this.Player1Pits[pitNumber - 1];
	}

	
	private Pit getPlayer2Pit(int pitNumber) {
		return this.Player2Pits[pitNumber - 1];
	}

	// This method provide the movement of stones return message to board
	public boolean makeMove(int pitNumber) {
		Pit aPit = null;
		String currentPlayer;

				
		if (this.isPlayer1Turn()) {

			aPit = this.getPlayer1Pit(pitNumber);

		} else {

			aPit = this.getPlayer2Pit(pitNumber);

		}
		currentPlayer = aPit.getpitOwner();
		// check for pit capacity is empty or not
		if (aPit.getStones() == 0) {

			this.message = "House is empty. Move again.";
			return false;
		}
		int stonesOnHand = aPit.getStones();
		aPit.setStones(0);
		while (true) {
			// get the nextPit
			aPit = aPit.getNextPit();
			// System.out.println("6----------");

			// if the next pit is a Mancala pit of the other user.
			if (isMancalaPit(aPit) && !currentPlayer.equalsIgnoreCase(aPit.getpitOwner())) {
				continue;
			}
			aPit.setStones(aPit.getStones() + 1); // increment number of Stones
													// of the

			// if the number of stones on hand is z then break
			if (--stonesOnHand == 0) { //if last stone drop is the player's mancala Pit, then the player gets another chance.
				
			//	this.message = this.isPlayer1Turn() + "player will get anothe chance.";

				if (!isMancalaPit(aPit)) {//if last stone drop is not  player's mancala Pit then he will get opposite side stone to his mancala pit.
					System.out.println("8----------");

					if (aPit.getpitOwner().equals(currentPlayer) && aPit.getStones() == 1) {
						Pit oppositePitStone = getOppositePitStone(aPit.getpitOwner(), aPit.getPitNumber());
						if (oppositePitStone.getStones() != 0) {// move all the stones present in opposite side  pit to owner mancalaPit.
							
							Pit mancalaPit = getMancala(currentPlayer);
							mancalaPit.setStones(oppositePitStone.getStones() + mancalaPit.getStones() + aPit.getStones());
							oppositePitStone.setStones(0); // reset opposite pit's stone count as 0.
							aPit.setStones(0);
						}
					}
					this.setPlayer1Turn(!this.isPlayer1Turn());// now next player turn
				}

				/* Determine if Game is over. */
				if (0 == getStonesInPits(this.getNextPlayer())) {
					if (this.player1Name.equalsIgnoreCase(this.getWinner())) {
						this.message = "Player 1 wins!";
					} else if ("Tie".equalsIgnoreCase(this.getWinner())) {
						this.message = "A tie!";
					} else {
						this.message = "Player 2 wins!";
					}
					this.isGameOver = true;
				}

				break;
			}

		}

		return true;
	}

	/** to get the pit that is opposite to players pit. */
	private Pit getOppositePitStone(String Owner, int pitNumber) throws NullPointerException {
		try {
			// if the owner of pitNumber is Player 1
			if (this.player1Name.equals(Owner)) {
				// return player 2's pit that is opposite to player 1's pitNumber
				return Player2Pits[6 - pitNumber];
			}
			// else,... return player 1's pit that is opposite to player 2's pitNumber
			return Player1Pits[6 - pitNumber];
		} catch (IndexOutOfBoundsException ie) {
			return null;
		}
	}

	/** to determine if the current pit is a mancalaPit */
	private boolean isMancalaPit(Pit aPit) {
		if (aPit.getPitNumber() == 7) {
			return true;
		}
		return false;
	}

	/** returns Mancala Pit of the Owner */
	public Pit getMancala(String owner) {
		// If the owner is
		if (this.player1Name.equalsIgnoreCase(owner)) {
			return this.Player1Mancala;
		}
		return this.Player2Mancala;
	}

	

	private int getStonesInPits(String playerName) {
		int pitStoneCount = 0;
		Pit[] playerpits;
		if (this.player1Name.equalsIgnoreCase(playerName)) {
			playerpits = this.Player1Pits;
		} else {
			playerpits = this.Player2Pits;
		}

		for (Pit aPit : playerpits) {
			pitStoneCount += aPit.getStones();
		}
		return pitStoneCount;
	}

	/** returns the stones collected by the Player */
	public int getScore(String playerName) {
		return this.getMancala(playerName).getStones() + this.getStonesInPits(playerName);
	}

	

	/** returns */
	public boolean isGameOver() {
		return isGameOver;
	}

	/** */
	public String getNextPlayer() {
		if (this.isPlayer1Turn()) {
			return this.player1Name;
		}
		return this.player2Name;
	}

	/** */
	public String getMessage() {
		return this.message;
	}

	public boolean isPlayer1Turn() {
		return isPlayer1Turn;
	}

	public void setPlayer1Turn(boolean isPlayer1Turn) {
		this.isPlayer1Turn = isPlayer1Turn;
	}

	public List<String> getBoardState() {
		
		List<String> board = new ArrayList<String>();
		for (int i = 5; i >= 0; --i) {
			board.add(this.Player2Pits[i].getPitDetails());
		}
		board.add(this.Player1Mancala.getPitDetails());
		board.add(this.Player2Mancala.getPitDetails());

		for (int i = 0; i <= 5; ++i) {
			board.add(this.Player1Pits[i].getPitDetails());
		}
		return board;
	}
	
	/** returns the name of the winner */
	public String getWinner() {
		if (this.getScore(this.player1Name) > this.getScore(this.player2Name)) {
			return this.player1Name;
		} else if (this.getScore(this.player1Name) == this.getScore(this.player2Name)) {
			return "Tie";
		}
		return this.player2Name;
	}
}
