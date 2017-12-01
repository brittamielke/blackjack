package com.lmig.gfc.blackjack.models;

public class Player {

	private double bankTotal;
	private Hand playerHand;

	public Player(double bankTotal) {
		this.bankTotal = bankTotal;
		// playerHand = new Hand();
	}

	public void subtractFromBank(double currentBet) {
		bankTotal = bankTotal - currentBet;
	}

	public void acceptCardFromGame(Card c) {
		playerHand.acceptCardToHand(c);
	}

	public void createNewHand() {
		playerHand = new Hand();
	}

	public void acceptPayoutFromGame(double payout) {
		bankTotal = bankTotal + payout;
	}

	public double getBankTotal() {
		return bankTotal;
	}

	public boolean outOfMoney() {
		return (bankTotal == 0);
	}

	public Hand getPlayerHand() {
		return playerHand;
	}

}
