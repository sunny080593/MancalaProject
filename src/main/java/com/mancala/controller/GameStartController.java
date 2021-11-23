package com.mancala.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.mancala.model.Board;

@Controller
public class GameStartController {
public enum PageReference {
    BOARD("mancalaBoard"), RULE("gameRule"), HOME("homePage");

    private final String reference;

    PageReference(String reference) {
        this.reference = reference;
    }

    public String getReference() {
        return reference;
    }

}
	Board board = null;
	private static final String playerOneName = "Rahul";
	private static final String playerTwoName = "Tina";
	private Map<String, Object> boardState = null;

	@GetMapping("/start-board")
	public String startPage(Model model) {
		board = new Board(playerOneName, playerTwoName);

		boardState = new HashMap<>();

		boardState.put("board", board.getBoardState());
		boardState.put("disablep2", board.isPlayer1Turn());

		populateAttributes(boardState, model);

		return PageReference.BOARD.getReference();
	}

	@PostMapping("/move")
	public String move(@RequestParam(value = "pit") String id, Model model) {

		board.makeMove(Integer.parseInt(id));

		boardState.put("disablep1", board.isPlayer1Turn() ? false : true);
		boardState.put("disablep2", board.isPlayer1Turn() ? true : false);

		boardState.put("board", board.getBoardState());
		boardState.put("message", board.getMessage());

		if (isGameEnded()) {
			boardState.put("score1", "Player 1 Score is  :" + board.getScore(playerOneName));
			boardState.put("score2", "Player 2 Score is :" + board.getScore(playerTwoName));
		}
		populateAttributes(boardState, model);

		return PageReference.BOARD.getReference();
	}

	private void populateAttributes(Map<String, ?> gameInfo, Model model) {
		gameInfo.entrySet().stream().forEach(entrySet -> model.addAttribute(entrySet.getKey(), entrySet.getValue()));
	}

	private boolean isGameEnded() {
		if (board.getMessage() == "Player 1 wins!" || board.getMessage() == "Player 2 wins!"
				|| board.getMessage() == "A tie!") {
			return true;
		}
		return false;
	}		
}
