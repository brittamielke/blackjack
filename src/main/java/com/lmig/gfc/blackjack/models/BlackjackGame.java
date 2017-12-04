package com.lmig.gfc.blackjack.models;

public class BlackjackGame {

	private Player player;
	private Deck deck;
	private House house;
	private double currentBet;

	public BlackjackGame() {
		player = new Player(100);
		deck = new Deck();
		house = new House();
	}

	public void acceptBet(double bet) {
		currentBet = bet;
		player.subtractFromBank(currentBet);
	}

	public void acceptDoubleDownBet() {
		player.subtractFromBank(currentBet);
		currentBet = currentBet * 2;
	}

	public void deal() {
		player.createNewHand();
		house.createNewHand();
		Card pFirstCard = (deck.drawCard());
		player.acceptCardFromGame(pFirstCard);
		Card hFirstCard = (deck.drawCard());
		house.acceptCardFromGame(hFirstCard);

		Card pSecondCard = (deck.drawCard());
		player.acceptCardFromGame(pSecondCard);
		Card hSecondCard = (deck.drawCard());
		house.acceptCardFromGame(hSecondCard);
	}

	public void hit() {
		Card newCard = (deck.drawCard());
		player.acceptCardFromGame(newCard);
	}

	public void houseDraw() {
		while (house.getHouseHand().getTotal() <= 16 && deck.getDeckSize() >= 1) {
			Card newCard = (deck.drawCard());
			house.acceptCardFromGame(newCard);
		}
	}

	public void determinePayout() {
		if (outcomeIsPlayerWinnerByBlackjack()) {
			player.acceptPayoutFromGame(currentBet * 1.5);
		}
		if (outcomeIsTie()) {
			player.acceptPayoutFromGame(currentBet);
		}
		if (outcomeIsPlayerWinnerNotByBlackjack()) {
			player.acceptPayoutFromGame(currentBet * 2);
		}
	}

	public double getCurrentBet() {
		return currentBet;
	}

	public boolean outcomeIsPlayerWinnerByBlackjack() {
		// Player = blackjack AND dealer != blackjack
		return (player.getPlayerHand().isBlackjack() && !house.getHouseHand().isBlackjack());
	}

	public boolean outcomeIsTie() {
		// Player = blackjack AND dealer = blackjack
		// OR player total == dealer total
		return (player.getPlayerHand().isBlackjack() && house.getHouseHand().isBlackjack())
				|| (player.getPlayerHand().getTotal() == house.getHouseHand().getTotal());
	}

	public boolean outcomeIsPlayerWinnerNotByBlackjack() {

		// returns true if:
		// player is NOT blackjack and Player total is <=21 and house is busted
		// player is NOT blackjack and player total <=21 and (player total > house
		// total)

		if ((!player.getPlayerHand().isBlackjack())
				&& (player.getPlayerHand().getTotal() <= 21 && house.getHouseHand().isBusted())) {
			return true;
		}
		if ((!player.getPlayerHand().isBlackjack()) && player.getPlayerHand().getTotal() <= 21
				&& (player.getPlayerHand().getTotal() > house.getHouseHand().getTotal())) {
			return true;
		} else {
			return false;
		}

	}

	public boolean outcomeIsPlayerLost() {
		return ((player.getPlayerHand().isBusted())
				|| ((player.getPlayerHand().getTotal() < house.getHouseHand().getTotal())
						&& house.getHouseHand().getTotal() <= 21));
	}

	public boolean isHandOver() {
		return (player.getPlayerHand().isBusted());
	}

	public boolean isGameOver() {
		return (player.getBankTotal() == 0 || deck.isDeckEmpty());
	}

	public Deck getDeck() {
		return deck;
	}

	// used to display the double down button
	// if the player has only been dealt 2 cards and those 2 are not blackjack.
	// also, the player must have the money in the bank to cover the double bet
	public boolean doubleDownAvailable() {
		return (player.getPlayerHand().getCards().size() == 2 && !player.getPlayerHand().isBlackjack()
				&& currentBet <= player.getBankTotal());
	}

}
