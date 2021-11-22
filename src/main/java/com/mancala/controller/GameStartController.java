package com.mancala.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.mancala.model.Board;

@Controller
public class GameStartController {

	Board aMancalaBoard = new Board("rahul", "tina");

	@GetMapping("/start-board")
	public String startPage(Model m) {

		m.addAttribute("disablep2", true);

		m.addAttribute("board", aMancalaBoard.getBoardState());
		return "mancalaBoard";
	}

	
	@PostMapping("/move")
	public String move(@RequestParam(value = "pit") String id, Model m) {

		aMancalaBoard.makeMove(Integer.parseInt(id));

		m.addAttribute("disablep1", aMancalaBoard.isPlayer1Turn() ? false : true);
		m.addAttribute("disablep2", aMancalaBoard.isPlayer1Turn() ? true : false);

		m.addAttribute("board", aMancalaBoard.getBoardState());
		m.addAttribute("message", aMancalaBoard.getMessage());

		if (aMancalaBoard.getMessage() == "Player 1 wins!" || aMancalaBoard.getMessage() == "Player 2 wins!"
				|| aMancalaBoard.getMessage() == "A tie!") {
			m.addAttribute("score1","Player 1 Score is  :" + aMancalaBoard.getScore("rahul"));
			m.addAttribute("score2", "Player 2 Score is :"+ aMancalaBoard.getScore("tina"));

		}

		return "mancalaBoard";
	}}
