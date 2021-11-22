package com.mancala.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String homePage()
	{
		return "homePage";
	}
	
	@RequestMapping("/quitGame")
	public String quitPage()
	{
		return "quitGame";
	}
	
	@RequestMapping("/gameRule")
	public String gameRule()
	{
		return "gameRule";
	}
}
