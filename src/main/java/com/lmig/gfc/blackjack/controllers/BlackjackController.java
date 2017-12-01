package com.lmig.gfc.blackjack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.blackjack.models.BlackjackGame;

@Controller
public class BlackjackController {

	private BlackjackGame game;

	public BlackjackController() {
		this.initGame();
	}

	public void initGame() {
		game = new BlackjackGame();
	}

	@GetMapping("/")
	public ModelAndView homePage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("default");
		mv.addObject("TheGame", game);
		return mv;
	}

	@PostMapping("/bet")
	public ModelAndView handleBet(int bet) {
		ModelAndView mv = new ModelAndView();

		game.acceptBet(bet);
		if (game.getDeck().getDeckSize() >= 4) {
			game.deal();
			mv.setViewName("redirect:/play");
		} else {
			mv.setViewName("redirect:/over");
		}
		return mv;
	}

	@GetMapping("/play")
	public ModelAndView gamePlay() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("play");
		mv.addObject("TheGame", game);

		return mv;
	}

	@PostMapping("/hit")
	public ModelAndView hit() {
		ModelAndView mv = new ModelAndView();

		if (game.getDeck().getDeckSize() >= 1) {
			game.hit();
			if (game.isHandOver()) {
				game.determinePayout();
				mv.setViewName("redirect:/endOfHand");
			} else {
				mv.setViewName("redirect:/play");
			}
		} else {
			mv.setViewName("redirect:/over");
		}

		mv.addObject("TheGame", game);

		return mv;
	}

	@GetMapping("/endOfHand")
	public ModelAndView endOfHand() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("endOfHand");
		mv.addObject("TheGame", game);

		return mv;
	}

	@PostMapping("/stand")
	public ModelAndView stand() {
		ModelAndView mv = new ModelAndView();
		if (game.getDeck().getDeckSize() >= 1) {
			game.houseDraw();
			game.determinePayout();
			mv.setViewName("endOfHand");
		} else {
			mv.setViewName("redirect:/over");
		}

		mv.addObject("TheGame", game);
		return mv;
	}

	@GetMapping("/over")
	public ModelAndView gameOver() {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("gameOver");

		mv.addObject("TheGame", game);
		return mv;
	}

	@PostMapping("/reset")
	public ModelAndView reset() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		initGame();
		mv.addObject("TheGame", game);
		return mv;
	}
}
